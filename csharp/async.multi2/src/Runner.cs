using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Threading;

namespace async.multi2
{
    class Runner
    {
        public void SimpleGoSync()
        {
            var apiFetcher = new ApiFetcher();
            var card = apiFetcher.Fetch();
            Logger.Log($"result: {card}");
        }

        public async Task MultiGoAsync()
        {
            const string whoAmI = "Runner.MGA ";
            var numTasks = 5;
            var tasks = new List<Task<int>>();
            for (int i = 1; i <= numTasks; i++)
            {
                var apiFetcherAsync = new ApiFetcherAsync();
                var task = apiFetcherAsync.Fetch();
                tasks.Add(task);
            }
            await Task.WhenAll(tasks);

            for (int i = 0; i < numTasks; i++)
            {
                var card = tasks[i].Result;
                Logger.Log($"{whoAmI} card[{i}] result: {card}");
            }

            /*
            foreach (Task<int> task in tasks)
            {
                Logger.Log($"card result: {task}");
            }
            */
            Logger.Log($"{whoAmI} all tasks done");
        }

        public void MultiGoSync()
        {
            var numTasks = 10;
            var delayInMillis = 3*1000;
            var tasks = new List<Task>();
            for (int i = 1; i < numTasks; i++)
            {
                var sleeper = new Sleeper(i, delayInMillis);
                var task = Task.Run(() => sleeper.Go());
                tasks.Add(task);
            }
            Task.WhenAll(tasks);
            Logger.Log("all tasks done!");
            int overallDelayInMillis = 5*1000;
            Thread.Sleep(overallDelayInMillis);
        }
    }
}
