
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
def ARTIFACTORY_URL_FILE = null

// example tag easywar-1.0.0-SNAPSHOT-2018-May-29-0644-angry-shaw

stage "find Artifactory URL"
node {
    ARTIFACTORY_URL_FILE = "${WORKSPACE}/${ROOT}/devops/artifactory.out.log"
    println "TRACER Artifactory URL here. workspace: ${WORKSPACE}"
    sh "bash ${WORKSPACE}/${ROOT}/devops/extract.url.from.tag.sh ${targetTag} ${targetEnv} ${ARTIFACTORY_URL_FILE}" 
    println "TRACER Artifactory URL. complete"

    script {
        def result = sh(returnStdout: true, script: "${WORKSPACE}/${ROOT}/devops/test.sh MY_SCRIPT_TEST")
        println "TRACER 31-MAY test: " + result 
    }
}

stage "mock Deploy"
node {
    ARTIFACTORY_URL_FILE = "${WORKSPACE}/${ROOT}/devops/artifactory.out.log"
    println "TRACER mock deploy begin"
    sh "bash ${WORKSPACE}/${ROOT}/devops/mock.deploy.sh ${targetTag} ${targetEnv} ${ARTIFACTORY_URL_FILE}" 
    println "TRACER mock deploy. complete"
}
