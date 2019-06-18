pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                withMaven(maven : 'maven_3_5_0') {
                    sh 'mvn clean compile'
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

