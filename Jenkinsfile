pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/luizkt/crud-mysql-cliente', branch: 'master')
      }
    }

    stage('build') {
      steps {
        sh 'mvn install'
      }
    }

  }
}