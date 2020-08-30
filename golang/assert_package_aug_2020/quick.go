package main

import "fmt"

// https://stackoverflow.com/a/10030772/12704
func Reverse(s string) string {
    runes := []rune(s)
    for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
        runes[i], runes[j] = runes[j], runes[i]
    }
    return string(runes)
}

func main() {
    fmt.Printf("TRACER s1: %v\n", Reverse("abcdef"))
    fmt.Printf("TRACER s2: %v\n", Reverse("baddab"))
    fmt.Println("Ready.")
}
