
package main 

import (
    "fmt"
)

type Foo struct {
    id int
    message string
}

func (foo Foo) String() string {
    return fmt.Sprintf("id: %d, message: %v", foo.id, foo.message)
}

func update(foos []Foo) {
    foos[0].message = "from update 1"
    foos[1].message = "from update 2"
}

func log(foos []Foo) {
    for _, foo := range foos {
        fmt.Println(foo.String())
    } 
}

func main() {
    // this is a slice literal !
    // https://blog.golang.org/slices-intro
    foos := []Foo{}

    foos = append(foos, Foo{id: 5150})
    foos = append(foos, Foo{id: 6160})

    update(foos)
    log(foos)

    fmt.Println("Ready.")
}
