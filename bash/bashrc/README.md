
### Examples

* `cb` stands for clipboard

### Show clipboard variables

```
$ cbs
CB is 
CB1 is 
CB2 is
CB3 is
CB4 is
CB5 is
CB6 is
CB7 is
```

### Capture a folder into a clipboard variable

```
$ cd /Users/measter/tmp
$ cb1
CB is 
CB1 is /Users/measter/tmp
CB2 is
CB3 is
CB4 is
CB5 is
CB6 is
CB7 is
```

### 'Goto' to a folder contained in a clipboard variable

```
$ cd /random/dir
$ gcb1
CB is 
CB1 is /Users/measter/tmp
CB2 is
CB3 is
CB4 is
CB5 is
CB6 is
CB7 is
$ pwd
/Users/measter/tmp
```

### Reference a folder via clipboard variable

```
$ cd /random/dir
$ cp $CB1/file.txt .
```

### Misc (aside from clipboard vars)

* to go "up" 4 directories: `4u`

```
$ pwd
/Users/measter/abc/def/ijk/xyz
$ 4u
total 504
drwxr-xr-x+   5 measter  staff     170 22 Jun  2015 Public
drwx------+   3 measter  staff     102 20 Nov  2016 Documents
drwx------@  59 measter  staff    2006  9 Mar  2017 Library
drwx------    5 measter  staff     170 26 Mar  2017 Applications
```
