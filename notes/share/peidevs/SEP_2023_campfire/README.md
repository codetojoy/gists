
### Intro

* Ron Myers on NDA
* Ground-rules
* Release It! 2nd edition by Michael Nygard [here](https://learning.oreilly.com/library/view/release-it-2nd/9781680504552/)

### Bug 1 

```
public class Loop {
    public static void main(String... args) {
        int n = 10;

        for (int i = 0; i <= n; i++); { 
            System.out.println("|￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
            System.out.println("| Days without TypeScript drama: 7  |");
            System.out.println("|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
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
#include <stdio.h>
int main() {
   printf("Hello, World!");
   return 0;
}
```

```
$ gcc -o write write.c
$ write
usage: write user [tty]
```

<details>
<summary>click to reveal</summary>
<pre>
$ which write
/usr/bin/write
</pre>
</details>

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
<summary>click to reveal</summary>
<pre>
if (loginOk) {
    System.out.println("TRACER login is ok");
    forwardToHomePage();
} else {
    logger.error("login denied. username: " + username);
}
</pre>
</details>
