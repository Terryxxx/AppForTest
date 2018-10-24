pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        bat 'gradlew clean'
      }
    }
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
    stage('Build Debug APK') {
      steps {
        bat 'gradlew assembleDebug'
        // publish apk
        archiveArtifacts 'app/build/outputs/apk/debug/*.apk'
      }
    }
  }
}