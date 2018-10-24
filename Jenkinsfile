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
      }
    }
  }
}