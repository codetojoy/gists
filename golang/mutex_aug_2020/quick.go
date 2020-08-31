
package main

import (
    "fmt"
    "math/rand"
    "strings"
    "sync"
    "time"
)

type Payload struct {
    id int
    message string
    table *map[string]string
    tableLock *sync.RWMutex
}

func (payload *Payload) String() string {
    var result = strings.Builder{}

    result.WriteString(fmt.Sprintf("\nid: %d message: %v\n", payload.id, payload.message))

    for key, value := range (*payload.table) {
        result.WriteString(fmt.Sprintf("[%v] %v\n", key, value))
    }

    return result.String()
}

func mySleep() {
    const min = 100
    const max = 500
    value := time.Duration(rand.Intn(max-min) + min)
    time.Sleep(value * time.Millisecond)
}

func (payload *Payload) update(key string, id int) {
    (*payload.tableLock).Lock()
    defer (*payload.tableLock).Unlock()

    if _, ok := (*payload.table)[key]; ! ok {
        mySleep()
        if val2, ok2 := (*payload.table)[key]; ok2 {
            fmt.Printf("TRACER contention ... id: %d value: %v\n", payload.id, val2)
        }
        (*payload.table)[key] = fmt.Sprintf("from %d", id)
    }
}

func (payload *Payload) update_RW(key string, id int) {
    /*
    (*payload.tableLock).RLock()
    defer (*payload.tableLock).RUnlock()

    if _, ok := (*payload.table)[key]; ! ok {
        time.Sleep(400 * time.Millisecond)
        (*payload.tableLock).Lock()
        (*payload.table)[key] = fmt.Sprintf("from %d", id)
        (*payload.tableLock).Unlock()
    }
    */
}

func worker(id int, table *map[string]string, tableLock *sync.RWMutex, myChannel chan Payload) {
    t := time.Now()
    message := fmt.Sprintf("TRACER %d-%02d-%02d %02d:%02d:%02d nano: %03d",
                t.Year(), t.Month(), t.Day(),
                t.Hour(), t.Minute(), t.Second(), t.UnixNano())
    payload := Payload{id: id, message: message, table: table, tableLock: tableLock}

    payload.update("abc", id)
    payload.update("def", id)
    payload.update("xyz", id)

    myChannel <- payload
}

func main() {
    myChannel := make(chan Payload)

    tableLock := sync.RWMutex{}
    table := make(map[string]string)
    numIterations := 5000

    for i := 0; i < numIterations; i++ {
        fmt.Printf("TRACER iter: %d\n", i)
        go worker(5150, &table, &tableLock, myChannel)
        go worker(6160, &table, &tableLock, myChannel)
        go worker(7170, &table, &tableLock, myChannel)

        val := <- myChannel
        fmt.Printf("%v\n", val.String())
        val = <- myChannel
        fmt.Printf("%v\n", val.String())
        val = <- myChannel
        fmt.Printf("%v\n", val.String())
    }

    fmt.Println("TRACER sleeping...")
    time.Sleep(10 * time.Second)
    fmt.Println("Ready.")
}
