pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        bat 'mvn clean test site'
        git 'https://github.com/MechaniC1024/PrestaShop'
      }
    }

  }
}