package main

import (
    "bufio"
    "fmt"
    "net/http"
)

func main() {
    const port = 6160
    url := fmt.Sprintf("http://localhost:%d", port)

    resp, err := http.Get(url)
    if err != nil {
        panic(err)
    }
    defer resp.Body.Close()

    fmt.Println("Response status:", resp.Status)

    scanner := bufio.NewScanner(resp.Body)
    for i := 0; scanner.Scan() && i < 5; i++ {
        fmt.Println(scanner.Text())
    }

    if err := scanner.Err(); err != nil {
        panic(err)
    }
}
