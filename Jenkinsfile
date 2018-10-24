pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''./gradelw clean
./gradlew assembleDebug'''
      }
    }
  }
}