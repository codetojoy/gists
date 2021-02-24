using System;

namespace async.multi
{
    class Runner
    {
        public void go()
        {
            var apiFetcher = new ApiFetcher();
            var card = apiFetcher.Fetch();
            Console.WriteLine($"result: {card}");
        }
    }
}
