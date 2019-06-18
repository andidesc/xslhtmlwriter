pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                withMaven(maven_1_2_1){
                    sh 'maven clean compile'
                }
            }
        }
        stage('Test') { 
            steps {
                sh 'date' 
            }
        }
        stage('Deploy') { 
            steps {
                sh 'whoami'
            }
        }
        stage('Decoy') { 
            steps {
                sh 'whoami'
            }
        }
    }
}

