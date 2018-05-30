
def targetTag = input(
                message: 'tag?',
                parameters: [
                    stringParam(defaultValue: 'none', description: 'tag', name: "targetTag")
                ]) 

stage "checkout"
node {
    checkout scm
}

stage "find Artifactory URL"
node {
    println "TRACER Artifactory URL here"
}

stage "mock Deploy"
node {
    println "TRACER deploy here"
}

println "TRACER hello from deploy. targetTag is ${targetTag}"
