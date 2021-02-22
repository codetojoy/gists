using System;
using System.IO;

using Newtonsoft.Json;

namespace json
{
    class Program
    {
        void parseMovie()
        {
            using (StreamReader file = File.OpenText("movie.json"))
            {
                var serializer = new JsonSerializer();
                var movie = (Movie) serializer.Deserialize(file, typeof(Movie));
                Console.WriteLine($"TRACER movie: {movie}");
            }
            Console.WriteLine("TRACER parseMovie => OK");
        }

        void parseConfig()
        {
            using (StreamReader file = File.OpenText("config.console.json"))
            {
                var serializer = new JsonSerializer();
                var config = (Config) serializer.Deserialize(file, typeof(Config));
                Console.WriteLine($"TRACER {config}");
            }
            Console.WriteLine("TRACER parseConfig => OK");
        }

        static void Main(string[] args)
        {
            try
            {
                var prog = new Program();
                var which = 2;

                if (which == 1)
                {
                    prog.parseMovie();

                }
                else if (which == 2)
                {
                    prog.parseConfig();
                }
                Console.WriteLine("Ready.");
            }
            catch (Exception e)
            {
                Console.WriteLine($"caught exception: {e.Message}");
            }
        }
    }
}
