pipeline {
    agent any

    tools {
        maven 'maven-3.9.1'
        jdk 'jdk21'
    }

    parameters {
        string(
            name: 'TAGS',
            defaultValue: '',
            description: 'Cucumber tags (optional, example: @smoke)'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    if (fileExists('src/test/resources/features')) {
                        echo 'Cucumber project detected'
                        if (params.TAGS?.trim()) {
                            bat "mvn clean test -Dcucumber.filter.tags=${params.TAGS}"
                        } else {
                            bat "mvn clean test"
                        }
                    } else {
                        echo 'Selenium / RestAssured project detected'
                        bat 'mvn clean test'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed'
        }
        success {
            echo 'BUILD SUCCESS'
        }
        failure {
            echo 'BUILD FAILED'
        }
    }
}
