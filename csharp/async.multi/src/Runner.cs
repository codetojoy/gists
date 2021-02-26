using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Threading;

namespace async.multi
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
            var numTasks = 5;
            var tasks = new List<Task<int>>();
            for (int i = 1; i < numTasks; i++)
            {
                var apiFetcherAsync = new ApiFetcherAsync();
                var task = apiFetcherAsync.Fetch();
                tasks.Add(task);
            }
            await Task.WhenAll(tasks);

            var card1 = tasks[0].Result;
            Logger.Log($"card result: {card1}");

            /*
            foreach (Task<int> task in tasks)
            {
                Logger.Log($"card result: {task}");
            }
            */
            Logger.Log("all tasks done");
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
