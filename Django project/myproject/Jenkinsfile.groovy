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
                bat 'python -m pip install -r Django project/myproject/requirements.txt' // Use 'python -m pip' instead of 'pip'
            }
        }
        stage('Run Tests') {
            steps {
                // Add your test execution command here
                // For example: bat 'python manage.py test'
            }
        }
        stage('Deploy') {
            steps {
                // Add your deployment steps here
                // For example: echo 'Deploying the application...'
            }
        }
    }
}
