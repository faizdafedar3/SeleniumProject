pipeline {
    agent any

    tools {
        maven 'maven-3.9.1'
        jdk 'jdk21'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Fix JAVA_HOME (Windows Jenkins bug)') {
            steps {
                bat '''
                echo Original JAVA_HOME=%JAVA_HOME%

                REM Find actual JDK directory (jdk-*)
                for /d %%i in ("%JAVA_HOME%\\jdk-*") do (
                    set REAL_JAVA_HOME=%%i
                )

                echo REAL_JAVA_HOME=%REAL_JAVA_HOME%

                setx JAVA_HOME "%REAL_JAVA_HOME%" /M
                set PATH=%REAL_JAVA_HOME%\\bin;%PATH%

                echo Fixed JAVA_HOME=%JAVA_HOME%
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
