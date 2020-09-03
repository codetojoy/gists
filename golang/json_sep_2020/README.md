
### Summary

* many brain cells died to determine:
    - struct fields must be exported
    - in struct tag the double-quotes are vital 
* see below

```
type Config struct {
    NumCards int `json:"num_cards"`
    NumGames int `json:"num_games"`
    IsVerbose bool `json:"is_verbose"`
...
}
```
