using System;
using System.Threading.Tasks;

namespace async.multi
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                Logger.Log("main start");
                var runner = new Runner();

                // calls WarO_Strategy_API_Java with 4-second delay param
                var task = runner.SimpleGoAsync();

                Logger.Log("Main: about to wait for task");
                task.Wait();
                int card = task.Result;
                Logger.Log($"Main: card is {card}");

                Logger.Log("Ready.");
            }
            catch (Exception ex)
            {
                Console.Error.WriteLine($"caught exception: {ex.Message}");
            }
        }
    }
}
