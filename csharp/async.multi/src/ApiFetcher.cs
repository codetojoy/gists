using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;

using Newtonsoft.Json;

namespace async.multi
{
   class ApiResult
    {
        [JsonProperty("message")]
        public string Message { get; set; }
        [JsonProperty("card")]
        public int Card { get; set; }
    }
    public class ApiFetcher
    {
        private readonly HttpClient client = new HttpClient();
        private readonly string SCHEME = "http";
        private readonly string HOST = "localhost";
        private readonly string PATH = "waro/strategy";
        private readonly int PORT = 8080;
        private readonly string MODE = "max";

        private readonly string CARDS_PARAM = "cards";
        private string MODE_PARAM = "mode";
        private string PRIZE_CARD_PARAM = "prize_card";
        private string MAX_CARD_PARAM = "max_card";

        public int Fetch()
        {
            int prizeCard = 10;
            var hand = new List<int>();
            hand.Add(12);
            hand.Add(3);
            hand.Add(7);
            int maxCard = 12;
            var tmpCard = FetchSelect(prizeCard, hand, maxCard);
            return tmpCard;
        }

        public int FetchSelect(int prizeCard, List<int> hand, int maxCard)
        {
            var result = -5150;

            UriBuilder uriBuilder = new UriBuilder();
            uriBuilder.Scheme = SCHEME;
            uriBuilder.Host = HOST;
            uriBuilder.Port = PORT;
            uriBuilder.Path = PATH;

            var queryString = System.Web.HttpUtility.ParseQueryString(string.Empty);

            queryString.Add(MODE_PARAM, MODE);
            queryString.Add(PRIZE_CARD_PARAM, prizeCard.ToString());
            queryString.Add(MAX_CARD_PARAM, maxCard.ToString());
            foreach (int card in hand)
            {
                queryString.Add(CARDS_PARAM, card.ToString());
            }

            uriBuilder.Query = queryString.ToString();
            Uri uri = uriBuilder.Uri;

            var response = client.GetAsync(uri.ToString()).Result;

            if (response.IsSuccessStatusCode)
            {
                var responseContent = response.Content;
                var responseString = responseContent.ReadAsStringAsync().Result;
                var textReader = new StringReader(responseString);
                var serializer = new JsonSerializer();
                var apiResult = (ApiResult) serializer.Deserialize(textReader, typeof(ApiResult));
                result = apiResult.Card;
                Console.WriteLine($"TRACER ApiStrategy msg: {apiResult.Message}");
            }
            else
            {
                Console.Error.WriteLine("TRACER ApiStrategy failed!");
            }

            return result;
        }
    }
}
