using System;

namespace async.multi
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                var runner = new Runner();
                runner.go();
                Console.WriteLine("Ready.");
            }
            catch (Exception ex)
            {
                Console.Error.WriteLine($"caught exception: {ex.Message}");
            }
        }
    }
}
