
def targetTag
def targetEnv

stage "input params"
node {
targetTag = input(
                message: 'tag?',
                parameters: [
                    stringParam(defaultValue: 'none', description: 'tag', name: "targetTag")
                ]) 

targetEnv = input(
                message: 'env?',
                parameters: [
                    stringParam(defaultValue: 'none', description: 'env', name: "targetEnv")
                ]) 
}

stage "checkout"
node {
    checkout scm
}

def ROOT = "easy_war_may_2018"
def ARTIFACTORY_URL_FILE = "${WORKSPACE}/${ROOT}/devops/artifactory.out.log"

// example tag easywar-1.0.0-SNAPSHOT-2018-May-29-0644-angry-shaw

stage "find Artifactory URL"
node {
    println "TRACER Artifactory URL here. workspace: ${WORKSPACE}"
    sh "bash ${WORKSPACE}/${ROOT}/devops/extract.url.from.tag.sh ${targetTag} ${targetEnv} ${ARTIFACTORY_URL_FILE" 
    println "TRACER Artifactory URL. complete"
}

stage "mock Deploy"
node {
    println "TRACER deploy here"
}

