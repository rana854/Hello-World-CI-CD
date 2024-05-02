pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rana854/cicd-project-1.git'
            }
        }
        stage('Install Dependencies') {
            steps {
               bat 'pip install -r /myproject/requirements.txt'
            }
        }
        stage('Run Tests') {
            steps {
                bat 'python manage.py test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Your deployment steps here
            }
        }
    }
}
