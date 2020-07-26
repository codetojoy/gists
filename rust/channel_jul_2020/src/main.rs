#![feature(mpsc_select)]

use std::cell::Cell;
use std::collections::{HashMap, VecDeque};
use std::sync::mpsc::{Select, Sender, Receiver, channel};
use std::thread;


type WorkflowId = usize;
type NumberOfSteps = usize;
type StepIndex = usize;

enum ExecutorMsg {
    Execute(Workflow),
    Quit
}

enum ConsumerMsg {
    Done(WorkflowId),
    StepExecuted(WorkflowId, StepIndex),
    ExpectWorkflow(WorkflowId),
}

#[derive(Debug, PartialEq)]
enum ConsumerControlMsg {
    AllWorkflowDone
}

enum ProducerMsg {
    Incoming(Workflow),
}

enum ProducerControlMsg {
    Quit
}

enum MainMsg {
    FromProducer(ProducerMsg),
    FromConsumer(ConsumerControlMsg)
}

struct Workflow {
    pub id: WorkflowId,
    pub number_of_steps: NumberOfSteps,
}

impl Workflow {
    fn new(id: WorkflowId, steps: NumberOfSteps) -> Workflow {
        Workflow {
            id: id,
            number_of_steps: steps
        }
    }
}

struct WorkflowExecution {
    pub current_step: Cell<StepIndex>,
}

struct WorkflowExecutor {
    executions: VecDeque<(Workflow, WorkflowExecution)>,
    port: Receiver<ExecutorMsg>,
    chan: Sender<ConsumerMsg>
}

impl WorkflowExecutor {
    fn handle_a_msg(&mut self) -> bool {
        match self.port.try_recv() {
            Ok(ExecutorMsg::Execute(workflow)) => {
                let execution = WorkflowExecution {
                    current_step: Cell::new(0),
                };
                self.executions.push_back((workflow, execution));
            },
            Ok(ExecutorMsg::Quit) => {
                return false
            },
            Err(_) => {
                // Empty mailbox.
            }
        }
        true
    }

    fn execute_a_step(&mut self) {
        if let Some((workflow, execution)) = self.executions.pop_front() {
            if execution.current_step.get() < workflow.number_of_steps {
                execution.current_step.set(execution.current_step.get() + 1);
                let _ = self.chan.send(ConsumerMsg::StepExecuted(workflow.id, execution.current_step.get()));
            }
            if execution.current_step.get() < workflow.number_of_steps {
                self.executions.push_back((workflow, execution));
            } else {
                let _ = self.chan.send(ConsumerMsg::Done(workflow.id));
            }
        }
    }

    fn run(&mut self) -> bool {
        if !self.handle_a_msg() {
            return false
        }
        self.execute_a_step();
        true
    }
}

struct WorkflowProducer {
    port: Receiver<ProducerControlMsg>,
    chan: Sender<ProducerMsg>,
    number_of_workflows: usize,
    produced_workflows: Cell<usize>,
    number_of_steps: NumberOfSteps,
}

impl WorkflowProducer {
    fn receive_a_message(&self) -> bool {
        match self.port.try_recv() {
            Ok(ProducerControlMsg::Quit) => {
                my_log("Producer quitting");
                return false
            },
            Err(_) => {
                // Empty mailbox.
            }
        }
        true
    }

    fn produce_a_workflow(&self) {
        if self.produced_workflows.get() < self.number_of_workflows {
            let workflow_id = self.produced_workflows.get().clone();
            my_log(&format!("Producer sending new workflow, id: {}", workflow_id));
            let workflow = Workflow::new(self.produced_workflows.get(), self.number_of_steps);
            let _ = self.chan.send(ProducerMsg::Incoming(workflow));
            self.produced_workflows.set(self.produced_workflows.get() + 1);
        }
    }

    fn run(&mut self) -> bool {
        if !self.receive_a_message() {
            return false
        }
        self.produce_a_workflow();
        true
    }
}


fn start_executor(chan: Sender<ConsumerMsg>) -> Sender<ExecutorMsg> {
    let (executor_chan, executor_port) = channel();
    let _ = thread::Builder::new().spawn(move || {
        let mut executor = WorkflowExecutor {
            executions: Default::default(),
            port: executor_port,
            chan: chan,
        };
        my_log("begin executor loop");
        while executor.run() {
            // Running...
        }
    });
    executor_chan
}

fn start_consumer(chan: Sender<ConsumerControlMsg>,
                  number_of_workflows: usize,
                  number_of_steps: usize)
                  -> Sender<ConsumerMsg> {
    let (consumer_chan, consumer_port) = channel();
    let _ = thread::Builder::new().spawn(move || {
        let mut track_steps = HashMap::new();
        let mut done = 0;
        my_log("begin consumer loop");
        for msg in consumer_port.iter() {
            match msg {
                ConsumerMsg::StepExecuted(workflow_id, index) => {
                    let last_step = *track_steps.get(&workflow_id).unwrap();
                    // Check the order of the steps for a workflow.
                    assert_eq!(last_step, index - 1);
                    let _ = track_steps.insert(workflow_id, index);
                },
                ConsumerMsg::Done(workflow_id) => {
                    let last_step = *track_steps.get(&workflow_id).unwrap();
                    // Check all steps were done.
                    assert_eq!(last_step, number_of_steps);
                    done = done + 1;
                    if done == number_of_workflows {
                        let _ = chan.send(ConsumerControlMsg::AllWorkflowDone);
                    }
                }
                ConsumerMsg::ExpectWorkflow(workflow_id) => {
                    let _ = track_steps.insert(workflow_id, 0);
                }
            }
        }
    });
    consumer_chan
}

fn start_producer(chan: Sender<ProducerMsg>,
                  number_of_workflows: usize,
                  number_of_steps: usize)
                  -> Sender<ProducerControlMsg> {
    let (producer_chan, producer_port) = channel();
    let _ = thread::Builder::new().spawn(move || {
        let mut producer = WorkflowProducer {
            port: producer_port,
            chan: chan,
            number_of_workflows: number_of_workflows,
            number_of_steps: number_of_steps,
            produced_workflows: Default::default()
        };
        my_log("begin producer loop");
        while producer.run() {
            // Running...
        }
    });
    producer_chan
}

fn my_log(s: &str) {
    println!("TRACER {:?} {}", thread::current().id(), s);
}

fn main() {
    my_log("MAIN begin");
    // ouput is generally:
    //      - MAIN
    //      - Producer
    //      - Consumer
    //      - Executor x 2
    //      - MAIN
    // sender == writer, receiver == reader
    // Producer -> main -> executors -> Consumer -> main
    // generally, start_foo(x) => b
    //      - `x` : channel that foo will write to when "done", call this "announce"
    //      - `a` : internal channel end, that it listens to for input
    //      - `b` : sender for the (a,b) channel
    // specifically:
    //      - Consumer: input from consumer_sender, announces to results_sender
    //      - Producer: input from producer_chan, announces to work_sender
    //      - Executor: input from executor_queue, announces to consumer_sender
    //      - main: input from work_receiver (from Producer) and results_receiver (from Consumer)
    let (results_sender, results_receiver) = channel();
    let (work_sender, work_receiver) = channel();
    let number_of_workflows = 5;
    let number_of_steps = 4;
    let consumer_sender = start_consumer(results_sender, number_of_workflows, number_of_steps);
    let producer_chan = start_producer(work_sender, number_of_workflows, number_of_steps);
    let executors = vec![start_executor(consumer_sender.clone()),
                         start_executor(consumer_sender.clone())];
    let mut executor_queue: VecDeque<Sender<ExecutorMsg>> = executors.into_iter().collect();
    loop {
        let msg = {
            let sel = Select::new();
            let mut work_port = sel.handle(&work_receiver);
            let mut results_port = sel.handle(&results_receiver);
            unsafe {
                work_port.add();
                results_port.add();
            }
            let ready = sel.wait();
            if ready == work_port.id() {
                let payload = work_port.recv().unwrap();
                {
                    let ProducerMsg::Incoming(workflow) = &payload;
                    my_log(&format!("MAIN received message ({:?}) from Producer", workflow.id));
                }
                MainMsg::FromProducer(payload)
            } else if ready == results_port.id() {
                my_log("MAIN received message done message from Consumer");
                let payload = results_port.recv().unwrap();
                MainMsg::FromConsumer(payload)
            } else {
                panic!("unexpected select result")
            }
        };
        let result = match msg {
             MainMsg::FromProducer(ProducerMsg::Incoming(workflow)) => {
                let _ = consumer_sender.send(ConsumerMsg::ExpectWorkflow(workflow.id));
                if let Some(executor) = executor_queue.pop_front() {
                    let _ = executor.send(ExecutorMsg::Execute(workflow));
                    executor_queue.push_back(executor);
                }
                continue;
            },
            MainMsg::FromConsumer(msg) => msg
        };
        // The only message the consumer will send, is that all is done.
        assert_eq!(result, ConsumerControlMsg::AllWorkflowDone);
        for executor in executor_queue {
            let _ = executor.send(ExecutorMsg::Quit);
        }
        let _ = producer_chan.send(ProducerControlMsg::Quit);
        break;
    }

    my_log("Ready.");
}

#[test]
fn test_run_workflows() {
    let (results_sender, results_receiver) = channel();
    let (work_sender, work_receiver) = channel();
    let number_of_workflows = 5;
    let number_of_steps = 4;
    let consumer_sender = start_consumer(results_sender, number_of_workflows, number_of_steps);
    let producer_chan = start_producer(work_sender, number_of_workflows, number_of_steps);
    let executors = vec![start_executor(consumer_sender.clone()),
                         start_executor(consumer_sender.clone())];
    let mut executor_queue: VecDeque<Sender<ExecutorMsg>> = executors.into_iter().collect();
    loop {
        let msg = {
            let sel = Select::new();
            let mut work_port = sel.handle(&work_receiver);
            let mut results_port = sel.handle(&results_receiver);
            unsafe {
                work_port.add();
                results_port.add();
            }
            let ready = sel.wait();
            if ready == work_port.id() {
                MainMsg::FromProducer(work_port.recv().unwrap())
            } else if ready == results_port.id() {
                MainMsg::FromConsumer(results_port.recv().unwrap())
            } else {
                panic!("unexpected select result")
            }
        };
        let result = match msg {
             MainMsg::FromProducer(ProducerMsg::Incoming(workflow)) => {
                let _ = consumer_sender.send(ConsumerMsg::ExpectWorkflow(workflow.id));
                if let Some(executor) = executor_queue.pop_front() {
                    let _ = executor.send(ExecutorMsg::Execute(workflow));
                    executor_queue.push_back(executor);
                }
                continue;
            },
            MainMsg::FromConsumer(msg) => msg
        };
        // The only message the consumer will send, is that all is done.
        assert_eq!(result, ConsumerControlMsg::AllWorkflowDone);
        for executor in executor_queue {
            let _ = executor.send(ExecutorMsg::Quit);
        }
        let _ = producer_chan.send(ProducerControlMsg::Quit);
        break;
    }
}
