pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/faizdafedar3/SeleniumProject.git', branch: 'main'
            }
        }

        stage('Parallel Execution') {
            parallel {
                stage('Batch-1') {
                    steps {
                        bat 'mvn clean test -Dgroups=batch1'
                    }
                }
                stage('Batch-2') {
                    steps {
                        bat 'mvn clean test -Dgroups=batch2'
                    }
                }
                stage('Batch-3') {
                    steps {
                        bat 'mvn clean test -Dgroups=batch3'
                    }
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
