pipeline {

    agent any

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
                echo FORCING JAVA & MAVEN (WINDOWS FIX)
                echo ===============================

                REM --- Force correct Java ---
                set JAVA_HOME=C:\\ProgramData\\Jenkins\\.jenkins\\tools\\hudson.model.JDK\\jdk21
                set JAVA_EXE=%JAVA_HOME%\\bin\\java.exe

                REM --- Force Maven installed by Jenkins ---
                set MAVEN_HOME=C:\\ProgramData\\Jenkins\\.jenkins\\tools\\hudson.tasks.Maven_MavenInstallation\\maven-3.9.1
                set MVN_CMD=%MAVEN_HOME%\\bin\\mvn.cmd

                echo JAVA_HOME=%JAVA_HOME%
                %JAVA_EXE% -version

                echo MAVEN_HOME=%MAVEN_HOME%
                %MVN_CMD% -version

                %MVN_CMD% clean test
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
