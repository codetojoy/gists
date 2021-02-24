using System;

namespace async.multi
{
    class Runner
    {
        public void Go()
        {
            var apiFetcher = new ApiFetcher();
            var card = apiFetcher.Fetch();
            Logger.Log($"result: {card}");
        }

        public void GoAsync()
        {
            var apiFetcherAsync = new ApiFetcherAsync();
            var card = apiFetcherAsync.Fetch().Result;
            Logger.Log($"result: {card}");
        }
    }
}
