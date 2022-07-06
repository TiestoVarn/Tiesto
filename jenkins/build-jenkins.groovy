
// 1. Import individual variablses
def envName             = "NON-PROD"
def pathToJenkins       = "./jenkins"

pipeline {
    agent any
    stages {
        stage('SonarScanner') {
            steps {
                script {
                    def mylambdas = sh(script: """ls -d */ | tr -d /""", returnStdout: true).trim()
                    def arroflambdas=[]
                            mylambdas.split().each { 
                                arroflambdas << it 
                        }
                    env.arroflambdas = arroflambdas
                    env.tag = tag
                    
                    echo "${arroflambdas}"
                    for (int i = 0; i < arroflambdas.size(); i++) {
                        //echo "${arroflambdas[i]}"
                        //sh "pwd && ls -la"
                        //$(grep "$a" ./1.txt)
                        def checkList = sh(script: "grep ${arroflambdas[i]} ./jenkins/LambdasnonFordeploy", returnStdout: true).trim()
                            if ("${arroflambdas[i]}" == "${checkList}") {
                                sh "cat ./jenkins/LambdasnonFordeploy"
                                echo "BRO JENKINS"
                            }
                            // println("------ Skip this folder ${arroflambdas[i]} because there is no any Lambdafunction ---------")
                            else {  
                               // sh "cat ./jenkins/LambdasnonFordeploy"
                                echo "not BRO JENKINS"                                
                            println("------ Start to do SonarSacanning for the Lambdafunction ${arroflambdas[i]}---------")
                        def pathTocodebase = "${arroflambdas[i]}"
                            dir(pathTocodebase) {
                           println("------ This is final step ---------")
                            }
                        }
                    }
                }
            }
        }
    }
}