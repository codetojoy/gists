
package main 

import (
    "fmt"
)

type Actor struct {
    id int
}

func (a Actor) foo() string {
    return fmt.Sprintf("id: %d :: hello from foo", a.id)
}

func (a Actor) bar() string {
    return fmt.Sprintf("id: %d :: hello from bar", a.id)
}

func doIt(actors []Actor, m func(a Actor) string) {
    for index := range actors {
        actor := &actors[index]
        s := m(*actor)  // same as actor.m()
        fmt.Printf("TRACER %v\n", s)
    }
}

// -------------------------------------

func main() {
    actors := []Actor{}

    actors = append(actors, Actor{id: 5150})
    actors = append(actors, Actor{id: 6160})

    doIt(actors, Actor.foo)
    doIt(actors, Actor.bar)

    fmt.Println("Ready.")
}
