
package main 

import (
    "fmt"
)

type Player struct {
    id int
    value1 int
    value2 int
}

func (p Player) String() string {
    return fmt.Sprintf("id: %d value1: %d value2: %d", p.id, p.value1, p.value2)
}

type metric func(*Player) int

func findHighest(players []Player, f metric) *Player {
    result := &players[0]
    bestTotal := f(result)

    for index := range players {
        player := &players[index]
        thisTotal := f(player)
        // fmt.Printf("TRACER thisTotal: %d bestTotal: %d\n", thisTotal, bestTotal)
        if thisTotal > bestTotal {
            bestTotal = thisTotal
            result = player
        }
    }

    return result
}

func byValue1(player *Player) int {
    return player.value1
}

func byValue2(player *Player) int {
    return player.value2
}

// -------------------------------------

func main() {
    players := []Player{}

    players = append(players, Player{id: 5150, value1: 11, value2: 1})
    players = append(players, Player{id: 6160, value1: 10, value2: 7})

    result1 := findHighest(players, byValue1)
    fmt.Printf("TRACER result1: %v\n", result1)

    result2 := findHighest(players, byValue2)
    fmt.Printf("TRACER result2: %v\n", result2)

    fmt.Println("Ready.")
}
