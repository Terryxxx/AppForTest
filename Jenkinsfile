pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        bat 'gradlew compileDebugSources'
      }
    }
    stage('Unit Test') {
      steps {
        bat 'gradlew testDebugUnitTest testDebugUnitTest'
        // publish html
        publishHTML (target: [
          allowMissing: false,
          alwaysLinkToLastBuild: true,
          keepAll: false,
          reportDir: 'app/build/reports/tests/testDebugUnitTest',
          reportFiles: 'index.html',
          reportName: 'Unit Test Report'      
        ])
      }
    }
  }
}