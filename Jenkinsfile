pipeline {
    agent any

    tools {
        maven 'Maven 3.9.0'
        jdk 'JDK 11'
    }

    parameters {
        choice(name: 'TEST_SUITE', choices: ['all', 'smoke', 'regression', 'e2e'], description: 'Select test suite to run')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select browser for test execution')
        booleanParam(name: 'HEADLESS', defaultValue: false, description: 'Run tests in headless mode')
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
        TEST_RUNNER = "${params.TEST_SUITE == 'smoke' ? 'SmokeTestRunner' : params.TEST_SUITE == 'regression' ? 'RegressionTestRunner' : params.TEST_SUITE == 'e2e' ? 'E2ETestRunner' : 'TestRunner'}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                dir('automation-framework') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    echo "Running ${params.TEST_SUITE} test suite on ${params.BROWSER} browser"
                    dir('automation-framework') {
                        sh """
                            mvn test \
                            -Dtest=${TEST_RUNNER} \
                            -Dbrowser=${params.BROWSER} \
                            -Dheadless=${params.HEADLESS}
                        """
                    }
                }
            }
        }

        stage('Generate Reports') {
            steps {
                echo 'Generating test reports...'
                dir('automation-framework') {
                    sh 'mvn verify'
                }
            }
        }
    }

    post {
        always {
            echo 'Publishing test results...'
            dir('automation-framework') {
                junit allowEmptyResults: true, testResults: '**/target/cucumber-reports/*.xml'

                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'cucumber.html',
                    reportName: 'Cucumber HTML Report',
                    reportTitles: 'Test Execution Report'
                ])

                archiveArtifacts artifacts: '**/target/screenshots/*.png', allowEmptyArchive: true
                archiveArtifacts artifacts: '**/target/cucumber-reports/*', allowEmptyArchive: true
                archiveArtifacts artifacts: '**/target/logs/*.log', allowEmptyArchive: true
            }
        }

        success {
            echo 'Test execution completed successfully!'
            emailext(
                subject: "SUCCESS: Test Execution - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    Test execution completed successfully!

                    Job: ${env.JOB_NAME}
                    Build Number: ${env.BUILD_NUMBER}
                    Test Suite: ${params.TEST_SUITE}
                    Browser: ${params.BROWSER}

                    Check console output at: ${env.BUILD_URL}
                    View reports at: ${env.BUILD_URL}Cucumber_20HTML_20Report/
                """,
                to: 'team@example.com'
            )
        }

        failure {
            echo 'Test execution failed!'
            emailext(
                subject: "FAILURE: Test Execution - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    Test execution failed!

                    Job: ${env.JOB_NAME}
                    Build Number: ${env.BUILD_NUMBER}
                    Test Suite: ${params.TEST_SUITE}
                    Browser: ${params.BROWSER}

                    Check console output at: ${env.BUILD_URL}
                    View screenshots at: ${env.BUILD_URL}artifact/target/screenshots/
                """,
                to: 'team@example.com',
                attachLog: true
            )
        }
    }
}
