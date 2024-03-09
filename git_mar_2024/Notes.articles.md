
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
* for merge: HEAD is same as what it was when you ran the merge
* rebase: different
* "ours" and "theirs" are flipped

###

* link:  https://jvns.ca/blog/2023/11/01/confusing-git-terminology/

* confusing terminology
* HEAD, detached (see above)  
* "ours" vs "theirs"
* MEGA: branch is up to date 
* MEGA: HEAD^^^ vs HEAD~~~ vs HEAD~3
    * HEAD^ first parent of merge, HEAD^2 second parent of merge
    * HEAD~3 is parent, parent, parent 
* `..` vs `...` in `git log` and `git diff`
* MEGA: can be fast-forwarded 
* references
* revert vs reset vs restore
* remote-tracking branch vs branch that tracks a remote
* checkout
    * 2 usages which are now switch and restore
    * see [this](https://tanzu.vmware.com/content/tanzu-developer-blog/blog-git-switch-and-restore-an-improved-user-experience)
* reflog
    * reference is a base class, umbrella term
    * reflog history of references
* merge vs rebase vs cherry-pick
* much more 

### misc facts

* link: https://jvns.ca/blog/2023/10/20/some-miscellaneous-git-facts/

* index, cached, --staging-area are synonymous 
* git stash: makes some commits and labels them with a ref called stash
    * stash is a reference (as are branch and tag)
    * reference is a generalization
* git merge commit can look 'empty'

### resources

* fixing diverged branches [here](https://wizardzines.com/comics/fixing-diverged-branches/)
* mistakes [here](https://wizardzines.com/comics/git-mistakes-cant-fix/)
* wrong branch [here](https://wizardzines.com/comics/oh-shit-wrong-branch/)




