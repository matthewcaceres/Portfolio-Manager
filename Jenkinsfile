def projectName = 'portfoliomanager'
def projectName2 = 'portfoliomanager-prod'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"
def dockerImageTag2 = "${projectName2}:${version}-prod"

pipeline {
  agent any

  stages {
     stage('Build docker image') {
          // this stage also builds and tests the Java project using Maven
          steps {
            sh "docker build -t ${dockerImageTag} ."
             sh "docker build -t ${dockerImageTag2} ."
          }
      }
    stage('Deploy Containers To Openshift') {
      steps {
        sh "oc login https://localhost:8443 --username admin --password admin --insecure-skip-tls-verify=true"
        sh "oc project ${projectName} || oc new-project ${projectName}"
        sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
        sh "oc new-app ${dockerImageTag} -l version=${version}"
        sh "oc expose svc/${projectName}"

         sh "oc login https://localhost:8443 --username admin --password admin --insecure-skip-tls-verify=true"
         sh "oc project ${projectName2} || oc new-project ${projectName2}"
         sh "oc delete all --selector app=${projectName2} || echo 'Unable to delete all previous openshift resources'"
         sh "oc new-app ${dockerImageTag2} -l version=${version}"
          sh "oc expose svc/${projectName2}"
      }
    }
  }
}