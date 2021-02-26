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

        public async Task<int> SimpleGoAsync()
        {
            var apiFetcherAsync = new ApiFetcherAsync();
            return await apiFetcherAsync.Fetch();
            /*
            var card = apiFetcherAsync.Fetch().Result;
            Logger.Log($"SimpleGoAsync result: {card}");
            */
        }

        public void MultiGoAsync()
        {
            var numTasks = 10;
            var tasks = new List<Task<int>>();
            for (int i = 1; i < numTasks; i++)
            {
                var apiFetcherAsync = new ApiFetcherAsync();
                var task = apiFetcherAsync.Fetch();
                tasks.Add(task);
            }
            Task.WhenAll(tasks);
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
