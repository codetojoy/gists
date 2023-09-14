
### Release It! 2nd edition

by Michael Nygard [here](https://learning.oreilly.com/library/view/release-it-2nd/9781680504552/)

### Bug 1 

```
public class Loop {
    public static void main(String... args) {
        int n = 10;

        for (int i = 0; i <= n; i++); { 
            System.out.println("|￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
            System.out.println("| Days without TypeScript drama: 7 |");
            System.out.println("|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
            System.out.println("            \\ (•◡•) //             ");
            System.out.println("              \\    //              "); 
            System.out.println("                ——--                ");
            System.out.println("                |  |                ");
            System.out.println("                |_ |_               ");
        }
    }
}
```

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
