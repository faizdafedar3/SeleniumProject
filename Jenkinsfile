pipeline {
    agent any

    tools {
        maven 'maven-3.9.1'
    }

    environment {
        // HARD FIX for Windows Jenkins JDK structure
        JAVA_HOME = 'C:\\ProgramData\\Jenkins\\.jenkins\\tools\\hudson.model.JDK\\jdk21\\jdk-21+35'
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
