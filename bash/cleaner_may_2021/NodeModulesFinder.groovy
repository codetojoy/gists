
// ------- main

new File(".").eachFileRecurse { file ->
    if (file.isDirectory()) {
        def absPath = file.absolutePath
        def firstMatcher = absPath =~ /^.*node_modules$/
        def doubleMatcher = absPath =~ /^.*node_modules.*node_modules$/
        if (firstMatcher.matches() && (! doubleMatcher.matches())) {
            println file.path
        }
    }
}
