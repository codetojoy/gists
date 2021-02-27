using System;
using System.Threading.Tasks;

namespace async.multi2
{
    class Program
    {
        static async Task Main(string[] args)
        {
                Logger.Log("Main: start");
                var runner = new Runner();
                await runner.MultiGoAsync();
                Logger.Log("Main: Ready.");
        }
    }
}
