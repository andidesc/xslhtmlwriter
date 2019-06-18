pipeline{
    agent any
    stages {
        stage('Build'){
            steps{
                withMaven(maven : 'maven_3_6_1'){
                    sh 'mvn clean compile'
                }
            }
        }
        stage('Test'){
            steps{
                    sh 'date'
                }
            }
        stage('Deploy'){
            steps{
                    sh 'whoami'
                }
            }
        }
}