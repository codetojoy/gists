

### basics

* `tmux new -s basic`
* `exit`
* `tmux ls`
* `tmux attach`
    - or `tmux attach -t basic`
* `tmux kill-session -t basic`

### windows 

* let `pre`: Ctrl-b
* new window: `pre c`
* rename window: `pre ,`
* next window: `pre n`
* `prev window: pre v`
* `pre i` for i-th window
* kill current window: `pre &`

### panes

* let `pre`: Ctrl-b
* split vertical: `pre %`
* split horizontal: `pre "`
* `pre o` to cycle panes
* `pre ...` (up/down/left/right arrows)
* kill pane: `pre x`
* command mode: `pre :`

