
def targetTag = input(
                message: 'tag?',
                parameters: [
                    stringParam(defaultValue: 'none', description: 'tag', name: "targetTag")
                ]) 

stage "checkout"
node {
    checkout scm
}

def ROOT = "easywar_may_2018"

stage "find Artifactory URL"
node {
    println "TRACER Artifactory URL here"
    sh "./${ROOT}/devops/test.sh HELLO FROM PIPELINE"
}

stage "mock Deploy"
node {
    println "TRACER deploy here"
}

println "TRACER hello from deploy. targetTag is ${targetTag}"
