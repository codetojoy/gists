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
    public class ApiResult
    {
        [JsonProperty("message")]
        public string Message { get; set; }
        [JsonProperty("card")]
        public int Card { get; set; }
    }
}
