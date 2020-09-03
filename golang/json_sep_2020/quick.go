
package main

import (
    "encoding/json"
    "fmt"
    "io/ioutil"
    "log"
    "os"
)

type Player struct {
    Name string `json:name`
    Strategy string `json:strategy`
}

type Config struct {
    NumCards int `json:numCards`
    NumGames int `json:numGames`
    /*
    IsVerbose bool `json:isVerbose`
    players []Player `json:players`
*/
}

/*
{
"num_cards": 20,
"num_games": 1,
"is_verbose": true,
"players": [
    {"name": "you", "strategy": "console"},
    {"name": "bach", "strategy": "next_card"},
    {"name": "shostakovich", "strategy": "next_card"}
]
}
*/

func main() {
    // from https://tutorialedge.net/golang/parsing-json-with-golang/
    jsonFile, err := os.Open("config.json")
    defer jsonFile.Close()

    if err != nil {
        log.Fatal(err)
    }

    fmt.Println("TRACER json OK")

    data, _ := ioutil.ReadAll(jsonFile)

    var config Config

    json.Unmarshal(data, &config)

    fmt.Printf("numCards: %d\n", config.NumCards)
    fmt.Printf("numGames: %d\n", config.NumGames)
    /*
    fmt.Printf("isVerbose: %v\n", config.IsVerbose)
    */

    fmt.Println("Ready.")
}
