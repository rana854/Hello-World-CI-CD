pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rana854/cicd-project-1.git'
            }
        }
        stage('Install Dependencies') {
            steps {
               bat 'pip install -r requirements.txt' // Use 'bat' instead of 'sh' for Windows
            }
        }
        stage('Run Tests') {
            steps {
                bat 'python manage.py test' // Use 'bat' instead of 'sh' for Windows
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
