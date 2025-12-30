pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven-3.9.1'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                script {
                    def jdkHome = tool 'jdk21'
                    env.JAVA_HOME = jdkHome
                    env.PATH = "${jdkHome}\\bin;${env.PATH}"
                }
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
