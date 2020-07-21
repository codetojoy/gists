
// #[derive(Copy, Clone)]
struct Employee {
    id: u32,
    dept_id: u32,
}

impl Copy for Employee { }

impl Clone for Employee {
    fn clone(&self) -> Employee {
        println!("TRACER hello from clone");
        *self
    }
}

// this is a silly example
fn display_employee(emp: Employee) {
    println!("TRACER id: {}", emp.id);
    println!("TRACER dept_id: {}", emp.dept_id);
}

fn go() {
    let emp1 = Employee{id: 11, dept_id: 12};
    let emp2 = emp1.clone();
    display_employee(emp1);
    let emp3 = emp1;
    display_employee(emp3);
}

fn main() {
    go();
    println!("Ready.");
}
