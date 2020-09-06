
package main

import (
    "encoding/json" 
    "fmt"
)

type ApiRemoteResult struct {
    Card int 
    Message string 
}

func parseJson(jsonStr string) int {
	var apiRemoteResult ApiRemoteResult
	json.Unmarshal([]byte(jsonStr), &apiRemoteResult)
    return apiRemoteResult.Card
}

func main() {
    jsonStr := `{"card": 5150, "message": ""}`

	result := parseJson(jsonStr)
    fmt.Printf("TRACER result: %d\n", result)
}
