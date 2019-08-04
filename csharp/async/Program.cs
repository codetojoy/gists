using System;
using System.Net.Http;
using System.Threading.Tasks;

namespace async
{
    class Program
    {
        readonly HttpClient client = new HttpClient(); 

        async Task<int> GetPageLengthAsync(string url)
        {    
            Task<string> fetchTask = client.GetStringAsync(url);    
            int length = (await fetchTask).Length;    
            return length;
        }
        void Go()
        {    
            string url = "https://peidevs.github.io";
            Task<int> lengthTask = GetPageLengthAsync(url);
            Console.WriteLine($"page length for {url} is {lengthTask.Result}");
        }

        static void Main(string[] args)
        {
            var program = new Program();
            program.Go(); 
        }
    }
}
