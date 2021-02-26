using System;
using System.Threading.Tasks;

namespace async.multi
{
    class Program
    {
        static async Task<int> Main(string[] args)
        {
                Logger.Log("Main: start");
                var runner = new Runner();

                // calls WarO_Strategy_API_Java with 4-second delay param
                Logger.Log("Main: before async call");
                var card = await runner.SimpleGoAsync();
                Logger.Log($"Main: card is {card}");
                Logger.Log("Main: Ready.");
                return card;
/*
                // this is the version if Main returns `void`

                // calls WarO_Strategy_API_Java with 4-second delay param
                var task = runner.SimpleGoAsync();

                Logger.Log("Main: about to wait for task");
                task.Wait();
                int card = task.Result;
                Logger.Log($"Main: card is {card}");
*/
        }
    }
}
