pipeline {

    agent any

    tools {
        jdk 'jdk21'              // must match Jenkins → Tools → JDK name
        maven 'maven-3.9.1'      // must match Jenkins → Tools → Maven name
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
                bat '''
                echo ===============================
                echo SETTING JAVA_HOME EXPLICITLY
                echo ===============================

                set JAVA_HOME=C:\\ProgramData\\Jenkins\\.jenkins\\tools\\hudson.model.JDK\\jdk21
                set PATH=%JAVA_HOME%\\bin;%PATH%

                echo JAVA_HOME=%JAVA_HOME%
                java -version
                mvn -version

                mvn clean test
                '''
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
