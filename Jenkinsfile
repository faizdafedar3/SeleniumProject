pipeline {
    agent any

    tools {
        maven 'maven-3.9.1'
        jdk 'jdk21'
    }

    environment {
        // Fix for Windows + Jenkins JDK auto-install
        JAVA_HOME = "${tool 'jdk21'}\\jdk-21+35"
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                bat '''
                echo JAVA_HOME=%JAVA_HOME%
                java -version
                mvn -version
                '''
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        success {
            echo 'BUILD SUCCESS'
        }
        failure {
            echo 'BUILD FAILED'
        }
    }
}
