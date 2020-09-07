package main

import (
    "encoding/json"
    "fmt"
    "log"
    "net/http"
    "time"
)

type Result struct {
    Foos []string `json:"foo"`
    Bars []string `json:"bar"`
    Message string `json:"message"`
}

func NewResult(message string, foos []string, bars []string) Result {
    return Result{Foos: foos, Bars: bars, Message: message}
}

// cribbed from https://dev.to/moficodes/build-your-first-rest-api-with-go-2gcj

func getTime() string {
    t := time.Now()
    result := fmt.Sprintf("%d-%v-%d %v:%v.%v", t.Year(), t.Month(), t.Day(),
                                                t.Hour(), t.Minute(), t.Second())
    return result
}

func handleGet(writer http.ResponseWriter, req *http.Request) {
    writer.Header().Set("Content-Type", "application/json")

    fooValues, ok1 := req.URL.Query()["foo"]
    
    if ok1 {
        for _, value := range fooValues {
            log.Printf("TRACER foo: %v\n", value)
        }
    }

    barValues, ok2 := req.URL.Query()["bar"]
    
    if ok2 {
        for _, value := range barValues {
            log.Printf("TRACER bar: %v\n", value)
        }
    }

    writer.WriteHeader(http.StatusOK)
    now := getTime()
    // body := strings.Builder{}

    message := fmt.Sprintf("TRACER %v GET", now)
    result := NewResult(message, fooValues, barValues) 

    var jsonData []byte
    jsonData, err := json.Marshal(result)
    if err != nil {
        log.Fatal(err)
    }

    bodyStr := string(jsonData)
    fmt.Println(bodyStr)
    writer.Write([]byte(bodyStr))
}

func home(w http.ResponseWriter, r *http.Request) {
    w.Header().Set("Content-Type", "application/json")
    switch r.Method {
    case "GET":
        handleGet(w, r)
    case "POST":
        w.WriteHeader(http.StatusCreated)
        w.Write([]byte(`{"message": "TRACER POST"}`))
    case "PUT":
        w.WriteHeader(http.StatusAccepted)
        w.Write([]byte(`{"message": "TRACER PUT"}`))
    case "DELETE":
        w.WriteHeader(http.StatusOK)
        w.Write([]byte(`{"message": "TRACER DELETE"}`))
    default:
        w.WriteHeader(http.StatusNotFound)
        w.Write([]byte(`{"message": "TRACER not found"}`))
    }
}

func main() {
    http.HandleFunc("/", home)
    const port = 6160
    portStr := fmt.Sprintf(":%d", port)

    fmt.Printf("TRACER running on port %d\n", port)
    log.Fatal(http.ListenAndServe(portStr, nil))
}
