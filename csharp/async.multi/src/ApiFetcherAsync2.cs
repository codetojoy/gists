using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Threading;

using Newtonsoft.Json;

namespace async.multi
{
    public class ApiFetcherAsync2
    {
        private readonly HttpClient client = new HttpClient();

        // calls WarO_Strategy_API_Java with 4-second delay param
        public async Task<int> Fetch()
        {
            int prizeCard = 10;
            var hand = new List<int>();
            hand.Add(12);
            hand.Add(3);
            hand.Add(7);
            int maxCard = 12;
            return await FetchSelect(prizeCard, hand, maxCard);
        }

        public async Task<int> FetchSelect(int prizeCard, List<int> hand, int maxCard)
        {
            var result = -5150;
            var uri = new Utils().BuildURI(prizeCard, hand, maxCard);

            var response = await client.GetAsync(uri);

            if (response.IsSuccessStatusCode)
            {
                var responseContent = response.Content;
                var responseString = responseContent.ReadAsStringAsync().Result;
                var textReader = new StringReader(responseString);
                var serializer = new JsonSerializer();
                var apiResult = (ApiResult) serializer.Deserialize(textReader, typeof(ApiResult));
                result = apiResult.Card;
                Logger.Log($"ApiStrategy msg: {apiResult.Message}");
            }
            else
            {
                Console.Error.WriteLine("TRACER ApiStrategy failed!");
            }

            return result;
        }
    }
}
