
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
