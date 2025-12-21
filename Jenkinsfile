pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/faizdafedar3/SeleniumProject.git'
            }
        }

        stage('Build & Test') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    bat 'mvn clean test'
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'

            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'extent-report',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Automation Report'
            ])
        }
    }
}
