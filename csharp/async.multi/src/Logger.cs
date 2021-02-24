using System;

namespace async.multi
{
    public class Logger
    {
        public static void Log(string msg)
        {
            var threadId = System.Threading.Thread.CurrentThread.ManagedThreadId;
            var now = DateTime.Now.ToString("h:mm:ss tt");
            Console.WriteLine($"TRACER ({threadId}) {now} {msg}");
        }
    }
}
