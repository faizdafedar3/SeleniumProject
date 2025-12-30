pipeline {

    agent any

    tools {
        maven 'maven-3.9.1'   // MUST match Jenkins Maven tool name
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/faizdafedar3/SeleniumProject.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
