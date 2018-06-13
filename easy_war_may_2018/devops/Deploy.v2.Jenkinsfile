
def targetTag
def targetEnv

stage "input params"
node {
def userInput = input(
                message: 'enter info',
                parameters: [
                    stringParam(defaultValue: 'none', description: 'tag', name: "targetTag"),
                    stringParam(defaultValue: 'none', description: 'env', name: "targetEnv")
                ]) 

targetTag = userInput['targetTag']
targetEnv = userInput['targetEnv']

println "TRACER tag: ${targetTag} ENV: ${targetEnv}"
}

stage "checkout"
node {
    checkout scm
}

def ROOT = "easy_war_may_2018"
def ARTIFACTORY_URL

// example tag easywar-1.0.0-SNAPSHOT-2018-May-29-0644-angry-shaw

stage "find Artifactory URL"
node {
    println "TRACER Artifactory URL here. workspace: ${WORKSPACE}"
    script {
        ARTIFACTORY_URL = sh(returnStdout: true, script: "${WORKSPACE}/${ROOT}/devops/extract.url.from.tag.sh ${targetTag} ${targetEnv}")
    }
    println "TRACER Artifactory URL. complete"

    script {
        def result = sh(returnStdout: true, script: "${WORKSPACE}/${ROOT}/devops/test.sh MY_SCRIPT_TEST")
        println "TRACER 31-MAY test: " + result 
    }
}

stage "mock Deploy"
node {
    println "TRACER mock deploy begin"
    script {
        sh script: "${WORKSPACE}/${ROOT}/devops/mock.deploy.sh ${targetTag} ${targetEnv} ${ARTIFACTORY_URL}" 
    }
    println "TRACER mock deploy. complete"
}
