
### substitution example

* change `myStuff` to `doStuff`
* `2s[Esc]`

### visual mode example 1

* select contents of `"this inner string"`
* `vi"`
* to change: `vi"di` ; to yank, `vi"y`
* replace `"` with appropriate delimiter 

### visual mode example 2

* select contents of <p>this inner string</p>
* `vit`

### visual mode example 3

* `vy` to yank visual selection ; `Vy` to yank lines
* `vx` to cut visual selection ; `Vx` to cut lines

### macro example

* change `myStuff` to `doStuff`, then `myFunc` to `doFunc`
* `qa/my2xido[Esc]q` to record macro in register 'a'
* `@a` to replay macro

