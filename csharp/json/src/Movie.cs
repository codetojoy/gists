using System;

namespace json
{
    public class Movie
    {
        public string Name { get; set; }
        public int Year { get; set; }

        public override string ToString() {
            return $"name: {Name} year: {Year}";
        }
    }
}
