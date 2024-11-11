pipeline {
    agent any
    
    environment {
        // Docker Hub username and password (insecure, avoid in production)
        DOCKER_USERNAME = 'ranatarek'
        DOCKER_PASSWORD = 'Rana3940498'
        IMAGE_NAME = 'pipline_docker_image28'
       // KUBECONFIG = "${env.USERPROFILE}\\.kube\\config"
        KUBERNETES_DEPLOYMENT_FILE = 'Django project/myproject/deployment.yaml'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/rana854/cicd-project-1.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def imageTag = "${DOCKER_USERNAME}/${IMAGE_NAME}:latest"
                    bat "docker build -t ${imageTag} -f \"Django project/myproject/Dockerfile\" ."
                    
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                  bat  "docker login -u ranatarek -p Rana3940498"

                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    def imageTag = "${DOCKER_USERNAME}/${IMAGE_NAME}:latest"
                    bat "docker push ${imageTag}"
                }
            }
        }
        stage('Deploy to Minikube') {
            steps {
                script {
                    bat "kubectl --kubeconfig=\"C:\\Users\\AL-fares\\.kube\\config\" apply -f \"Django project/myproject/deployment.yaml\""
                    bat "kubectl --kubeconfig=\"C:\\Users\\AL-fares\\.kube\\config\" apply -f \"Django project/myproject/service.yaml\""
                }
            }
    }
       
    }
}


