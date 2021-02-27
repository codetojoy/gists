using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Threading;

using Newtonsoft.Json;

namespace async.multi2
{
    public class Constants
    {
        public const string DELAY_IN_SECONDS = "4";
        public const string SCHEME = "http";
        public const string HOST = "localhost";
        public const string PATH = "waro/strategy";
        public const int PORT = 8080;
        public const string MODE = "max";

        public const string CARDS_PARAM = "cards";
        public const string DELAY_IN_SECONDS_PARAM = "delay_in_seconds";
        public const string MODE_PARAM = "mode";
        public const string PRIZE_CARD_PARAM = "prize_card";
        public const string MAX_CARD_PARAM = "max_card";
    }
}
