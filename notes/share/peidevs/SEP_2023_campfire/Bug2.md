
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
