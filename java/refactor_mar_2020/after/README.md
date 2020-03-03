
### Usage

* edit build.gradle so that args is `before` or `after` (for mock)
* `gradle run`

### Notes

* `KeyManager`:
    - has same public methods as before
    - delegates to a static `KeyManagerApi`
    - has a setter for alternate implementations (mocks)
* `KeyManagerApi` is not static
    - has same public methods, but not static
    - this facilitates mocks 
* `DatabaseKeyManagerImpl`:
    - implements `KeyManagerApi`
    - uses a static `LegacyKeyManager`
* `LegacyKeyManager` is exactly the same as original `KeyManager`


