using System;

namespace code.coverage
{
    class Program
    {
        static void Main(string[] args)
        {
            var foo = new Foo();
            Console.WriteLine("TRACER: created foo");
            Console.WriteLine("Ready.");
        }
    }
}
