
### Julia Evans

link - https://jvns.ca/categories/git/

### HEAD

link - https://jvns.ca/blog/2024/03/08/how-head-works-in-git/

* HEAD means different things

* file .git/HEAD
* either name of branch or a commit (detached state)
* e.g. `git status`

* git show HEAD
* here it is a revision parameter
* e.g. HEAD^^^^ HEAD@{2} etc
* symbolic for a commit id 

* detached HEAD state
* no current branch 
* any commits will be orphaned and ultimately lost
* to get back: go back to a branch or create a branch

* git log
* inside `(...)` is every reference that points to that commit
* e.g. (HEAD -> main, origin/main, origin/HEAD)
* good examples

* merge conflicts
* HEAD is just confusing
* 




