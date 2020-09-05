package main

import (
    "fmt"
    "log"
    "net/http"
    "strings"
    "time"
)

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
    body := strings.Builder{}

    // TODO: encode JSON
    body.WriteString("{"); 
    body.WriteString(fmt.Sprintf(`["message": TRACER %v GET},`, now))

    body.WriteString(fmt.Sprintf(`{"foo": [`))
    for i, value := range fooValues {
        body.WriteString(fmt.Sprintf(`"%v"`, value))
        if i < len(fooValues) - 1  { 
            body.WriteString(",")
        } 
    }
    body.WriteString("]},"); 

    body.WriteString(fmt.Sprintf(`{"bar": [`))
    for i, value := range barValues {
        body.WriteString(fmt.Sprintf(`"%v"`, value))
        if i < len(fooValues) - 1  { 
            body.WriteString(",")
        } 
    }

    body.WriteString("]}"); 

    body.WriteString("}"); 
    bodyStr := body.String()
    fmt.Println(bodyStr)
    writer.Write([]byte(bodyStr))
}

func home(w http.ResponseWriter, r *http.Request) {
    w.Header().Set("Content-Type", "application/json")
    switch r.Method {
    case "GET":
        handleGet(w, r)
/*
        w.WriteHeader(http.StatusOK)
        now := getTime()
        message := fmt.Sprintf(`{"message": TRACER %v GET}`, now)
        fmt.Println(message)
        w.Write([]byte(message))
*/
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
