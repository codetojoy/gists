using System;

using Newtonsoft.Json;

namespace json
{
    public class Config
    {
        [JsonProperty("num_cards")]
        public int NumCards { get; set; }
        [JsonProperty("num_games")]
        public int NumGames { get; set; }
        [JsonProperty("is_verbose")]
        public bool IsVerbose { get; set; }

        public override string ToString() {
            return $"# games: {NumGames} # cards: {NumCards} verbose? {IsVerbose}";
        }
    }
}
