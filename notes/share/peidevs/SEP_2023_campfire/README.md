
### Release It! 2nd edition

by Michael Nygard [here](https://learning.oreilly.com/library/view/release-it-2nd/9781680504552/)

### Bug 1 

### Bug 2 

```
if (loginOk) {
    System.out.println("TRACER login is ok");
    forwardToHomePage();
} else {
    logger.error("login denied. username: " + username);
}
```

<details>
<summary>click for response</summary>
<pre>
if (loginOk) {
    System.out.println("TRACER login is ok");
    forwardToHomePage();
} else {
    logger.error("login denied. username: " + username);
}
</pre>
</details>
