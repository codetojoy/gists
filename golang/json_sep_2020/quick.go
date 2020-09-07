
package main

import (
    "encoding/json"
    "fmt"
    "io/ioutil"
    "log"
    "os"
)

type Player struct {
    Name string `json:"name"`
    Strategy string `json:"strategy"`
}

type Config struct {
    NumCards int `json:"num_cards"`
    NumGames int `json:"num_games"`
    IsVerbose bool `json:"is_verbose"`
    Players []Player `json:"players"`
}

func encode() {
    p1 := Player{Name: "mozart", Strategy: "max"}
    p2 := Player{Name: "chopin", Strategy: "min"}
    players := []Player{p1, p2}

    config := Config{NumCards: 20, NumGames: 1, IsVerbose: true, Players: players}

    var jsonData []byte
    jsonData, err := json.Marshal(config)
    if err != nil {
        log.Println(err)
    }
    fmt.Println(string(jsonData))    
}

func decode() {
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
    fmt.Printf("isVerbose: %v\n", config.IsVerbose)

    for i, _ := range config.Players {
        player := &config.Players[i]
        fmt.Printf("player name: %v strategy: %v\n", player.Name, player.Strategy)
    }

    fmt.Println("Ready.")
}

func main() {
    const which = 2

    if which == 1 {
        decode() 
    } else if which == 2 {
        encode()
    }
}
