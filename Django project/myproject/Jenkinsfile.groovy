pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rana854/cicd-project-1.git'
            }
        }
       
        stage('Run Tests') {
            steps {
                bat 'python manage.py test' // Use 'bat' instead of 'sh' for Windows
            }
        }
        stage('Deploy') {
            steps {
               echo 'Deploying the application'
            }
        }
    }
}
