
package main 

import (
    "bufio"
    "fmt"
    "log"
    "os"
    "strconv"
    "strings"
)

func main() {
    reader := bufio.NewReader(os.Stdin)
    fmt.Print("Enter text: ")
    s, e1 := reader.ReadString('\n')

    if e1 != nil {
        log.Fatal(e1)
    }

    x, e2 := strconv.Atoi(strings.TrimSpace(s))

    if e2 != nil {
        log.Fatal(e2)
    }

    fmt.Printf("TRACER received: %d\n", x)
}
