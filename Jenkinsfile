pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/luizkt/crud-mysql-cliente', branch: 'master')
      }
    }

    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'mvn install'
          }
        }

        stage('Build Docker') {
          steps {
            sh 'docker build . -t luizkt/crud-mysql-cliente-jenkins:latest'
          }
        }

      }
    }

    stage('Publish container') {
      steps {
        sh 'docker push luizkt/crud-mysql-cliente-jenkins:latest'
      }
    }

    stage('Run container') {
      steps {
        sh 'docker run -p 8080:8080 -it luizkt/crud-mysql-cliente-jenkins:latest'
      }
    }

  }
}