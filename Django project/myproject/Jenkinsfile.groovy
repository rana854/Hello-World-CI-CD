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
              pip install -r "Django project/myproject/requirements.txt"
            }
        }
        stage('Run Tests') {
            steps {
              python manage.py test
            }
        }
        stage('Run Tests') {
            steps {
                bat 'python cicd-project-1/Django project/myproject/manage.py test'
            }
        }
        stage('Deploy') {
            steps {
               echo 'Deploying the application'
            }
        }
    }
}
