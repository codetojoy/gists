using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;

using Newtonsoft.Json;

namespace async.multi2
{
    public class Utils
    {
        public string BuildURI(int prizeCard, List<int> hand, int maxCard)
        {
            UriBuilder uriBuilder = new UriBuilder();
            uriBuilder.Scheme = Constants.SCHEME;
            uriBuilder.Host = Constants.HOST;
            uriBuilder.Port = Constants.PORT;
            uriBuilder.Path = Constants.PATH;

            var queryString = System.Web.HttpUtility.ParseQueryString(string.Empty);

            queryString.Add(Constants.MODE_PARAM, Constants.MODE);
            queryString.Add(Constants.PRIZE_CARD_PARAM, prizeCard.ToString());
            queryString.Add(Constants.MAX_CARD_PARAM, maxCard.ToString());
            queryString.Add(Constants.DELAY_IN_SECONDS_PARAM, Constants.DELAY_IN_SECONDS);

            foreach (int card in hand)
            {
                queryString.Add(Constants.CARDS_PARAM, card.ToString());
            }

            uriBuilder.Query = queryString.ToString();
            Uri uri = uriBuilder.Uri;

            return uri.ToString();
        }
    }
}
