pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rana854/cicd-project-1.git'
            }
        }
        stage('Run Application') {
            steps {
                echo 'Running Hello World Application'        
                bat 'python app.py' 
        
            }
        }
    }
}
