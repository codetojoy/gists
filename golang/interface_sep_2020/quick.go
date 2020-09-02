
package main 

import (
    "fmt"
)

type Foo interface {
    bar() string
}

func emitLog(foo Foo) {
    fmt.Printf("TRACER %v\n", foo.bar())
}

// --------
type BarNone struct {
}

func (barNone BarNone) bar() string {
    return "BarNone"
}

// --------
type Upstreet struct {
}

func (upstreet Upstreet) bar() string {
    return "Upstreet"
}

// --------
type MyTest struct {
    foo Foo
}

func (myTest MyTest) doIt() {
    emitLog(myTest.foo)
}

func main() {
    emitLog(BarNone{})
    emitLog(Upstreet{})
   
    myTest := MyTest{foo: BarNone{}} 
    myTest.doIt()

    fmt.Println("Ready.")
}
