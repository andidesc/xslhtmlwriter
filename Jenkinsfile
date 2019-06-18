pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                withMaven(maven : ''){
                    sh  'mvn clean compile'
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
    }
}

