
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
    (*payload.tableLock).RLock()

    fmt.Printf("TRACER id: %d in RLock\n", id)

    if _, ok := (*payload.table)[key]; ! ok {
        mySleep()
        (*payload.tableLock).RUnlock()
        (*payload.tableLock).Lock()
        fmt.Printf("TRACER id: %d in full Lock\n", id)
        (*payload.table)[key] = fmt.Sprintf("from %d", id)
        (*payload.tableLock).Unlock()
    } else {
        (*payload.tableLock).RUnlock()
    }
}

func worker(id int, table *map[string]string, tableLock *sync.RWMutex, myChannel chan Payload) {
    t := time.Now()
    message := fmt.Sprintf("TRACER %d-%02d-%02d %02d:%02d:%02d nano: %03d",
                t.Year(), t.Month(), t.Day(),
                t.Hour(), t.Minute(), t.Second(), t.UnixNano())
    payload := Payload{id: id, message: message, table: table, tableLock: tableLock}

    payload.update_RW("abc", id)
    payload.update_RW("def", id)
    payload.update_RW("xyz", id)

    myChannel <- payload
}

func main() {
    const numChannels = 5
    channel1 := make(chan Payload)
    channel2 := make(chan Payload)
    channel3 := make(chan Payload)
    channel4 := make(chan Payload)
    channel5 := make(chan Payload)

    tableLock := sync.RWMutex{}
    table := make(map[string]string)
    numIterations := 5000

    for i := 0; i < numIterations; i++ {
        fmt.Printf("TRACER iter: %d\n", i)

        index := (i % numChannels) + 1;
        channel := channel1
        if index == 2 { channel = channel2 }
        if index == 3 { channel = channel3 }
        if index == 4 { channel = channel4 }
        if index == 5 { channel = channel5 }

        go worker(5150, &table, &tableLock, channel)
        go worker(6160, &table, &tableLock, channel)
        go worker(7170, &table, &tableLock, channel)

    }

    isDone := false

    // TODO: I'm not convinced this is entirely correct
    // I think it possible for all channels to have nothing happening, and yet
    // the workload is not yet complete.
    // Stack O threads illustrate a dedicated 'quit' channel.
    // e.g. https://stackoverflow.com/questions/11117382
    for ! isDone {
        select {
        case val1 := <- channel1:
            fmt.Printf("TRACER 1 %v\n", val1.String())
        case val2 := <- channel2:
            fmt.Printf("TRACER 2 %v\n", val2.String())
        case val3 := <- channel3:
            fmt.Printf("TRACER 3 %v\n", val3.String())
        case val4 := <- channel4:
            fmt.Printf("TRACER 4 %v\n", val4.String())
        case val5 := <- channel5:
            fmt.Printf("TRACER 5 %v\n", val5.String())
        default:
            fmt.Printf("TRACER default on select")
            isDone = true
        }
    }

    fmt.Println("TRACER sleeping...")
    time.Sleep(5 * time.Second)
    fmt.Println("Ready.")
}
