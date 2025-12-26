pipeline {

    agent any

    tools {
        jdk 'jdk21'
        maven 'maven-3.9.1'
    }

    options {
        skipDefaultCheckout(true)
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-ssh',
                    url: 'git@github.com:faizdafedar3/SeleniumProject.git'
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
            junit '**/target/surefire-reports/*.xml'

            publishHTML(
                target: [
                    allowMissing          : true,
                    alwaysLinkToLastBuild : true,
                    keepAll               : true,
                    reportDir             : 'extent-report',
                    reportFiles           : 'ExtentReport.html',
                    reportName            : 'Extent Automation Report'
                ]
            )
        }
    }
}
