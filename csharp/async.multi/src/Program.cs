using System;

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
                runner.GoAsync();
                Logger.Log("Ready.");
            }
            catch (Exception ex)
            {
                Console.Error.WriteLine($"caught exception: {ex.Message}");
            }
        }
    }
}
