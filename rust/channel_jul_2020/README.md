
### Summary

* from [this post](https://medium.com/@polyglot_factotum/rust-concurrency-patterns-communicate-by-sharing-your-sender-11a496ce7791)
* from [this repo](https://travis-ci.org/gterzian/workflow_executor)
* key take-away:
    - for a channel: share senders liberally; keep lone receiver private

##### How to run:

* note: uses `select!` which has been deprecated from Rust
* `curl -sSf https://static.rust-lang.org/rustup.sh | sh`
* `cargo install`
* `./cr.sh`

