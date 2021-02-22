using System;
using System.Collections.Generic;

using Newtonsoft.Json;

namespace json
{
    public class Player
    {
        [JsonProperty("name")]
        public string Name { get; set; }
        [JsonProperty("strategy_name")]
        public string StrategyName { get; set; }

        public override string ToString() {
            return $"name: {Name} strategy: {StrategyName}";
        }
    }
    public class Config
    {
        [JsonProperty("num_cards")]
        public int NumCards { get; set; }
        [JsonProperty("num_games")]
        public int NumGames { get; set; }
        [JsonProperty("is_verbose")]
        public bool IsVerbose { get; set; }

        [JsonProperty("players")]
        public List<Player> players { get; set; }

        public override string ToString() {
            var s = $"# games: {NumGames} # cards: {NumCards} verbose? {IsVerbose}\n";
            foreach (Player p in players)
            {
                s += $"{p}\n";
            }
            return s;
        }
    }
}
