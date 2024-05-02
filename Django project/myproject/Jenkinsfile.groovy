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
               bat 'C:\Users\AL-fares\AppData\Local\Programs\Python\Python312  -m pip install -r myproject\requirements.txt' // Use 'bat' instead of 'sh' for Windows
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
