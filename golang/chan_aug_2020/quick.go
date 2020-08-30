
package main 

import (
    "fmt"
)

func emitter(myChannel chan string) {
    myChannel <- "TRACER hello from emitter"
}

func main() {
    myChannel := make(chan string)
    go emitter(myChannel)
    fmt.Println(<- myChannel)
    fmt.Println("Ready.")
}
