pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                withMaven(maven : 'maven_1_2_1'){
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

