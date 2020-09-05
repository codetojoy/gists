package main

import (
    "bufio"
    "fmt"
    "log"
    "net/http"
    "os"
)

func main() {
    const port = 6160
    baseUrl := fmt.Sprintf("http://localhost:%d", port)

    req, err := http.NewRequest("GET", baseUrl, nil)
    if err != nil {
        log.Print(err)
        os.Exit(1)
    }

    q := req.URL.Query()
    q.Add("foo", "4140")
    q.Add("foo", "5150")
    q.Add("bar", "abc")
    q.Add("bar", "def")
    req.URL.RawQuery = q.Encode()

    // resp, err := http.Get(url)
    resp, err := http.Get(req.URL.String())
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
