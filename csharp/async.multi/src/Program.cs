using System;
using System.Threading.Tasks;

namespace async.multi
{
    class Program
    {
        static void Main(string[] args)
        {
                Logger.Log("Main: start");
                var runner = new Runner();
                runner.MultiGoAsync();
                Logger.Log("Main: sleeping ...");
                new Sleeper(5150, 20 * 1000).Go();
                Logger.Log("Main: Ready.");
        }
    }
}
