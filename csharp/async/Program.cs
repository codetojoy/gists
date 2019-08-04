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
            Task<int> lengthTask = GetPageLengthAsync("http://peidevs.github.io");    
            Console.WriteLine(lengthTask.Result);
        }

        static void Main(string[] args)
        {
            var program = new Program();
            program.Go(); 
        }
    }
}
