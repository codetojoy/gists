using System;
using System.IO;

using Newtonsoft.Json;
                    
namespace json
{
    class Program
    {
        void go()
        {
            using (StreamReader file = File.OpenText("movie.json"))
            {
                var serializer = new JsonSerializer();
                var movie = (Movie) serializer.Deserialize(file, typeof(Movie));
                Console.WriteLine($"TRACER movie: {movie}");
            }
            Console.WriteLine("TRACER go => OK");
        }

        static void Main(string[] args)
        {
            try 
            {
                var prog = new Program();
                prog.go();
                Console.WriteLine("Ready.");
            }
            catch (Exception e) 
            {
                Console.WriteLine($"caught exception: {e.Message}");
            }
        }
    }
}
