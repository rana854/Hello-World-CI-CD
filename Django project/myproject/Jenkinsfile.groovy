pipeline {
    agent any

    environment {
        // Docker Hub username and password (insecure, avoid in production)
        DOCKER_USERNAME = 'ranatarek'
        DOCKER_PASSWORD = 'Rana3940498'
        IMAGE_NAME = 'pipline_docker_image2'
        MINIKUBE_PROFILE = 'minikube' // Set the name of your Minikube profile if you have one
        K8S_DEPLOYMENT_NAME = 'myapp-deployment'  // Change to your Kubernetes deployment name
        K8S_SERVICE_NAME = 'myapp-service'        // Change to your Kubernetes service name
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Clone your repository
                git branch: 'main', url: 'https://github.com/rana854/cicd-project-1.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image with a specific tag
                    def imageTag = "${DOCKER_USERNAME}/${IMAGE_NAME}:latest"
                    bat "docker build -t ${imageTag} -f \"Django project/myproject/Dockerfile\" ."
                    
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    // Login to Docker Hub using the environment variables (insecure)
                    
                  bat  "docker login -u ranatarek -p Rana3940498"

                }
            }
        }

        stage('Pubat Docker Image to Docker Hub') {
            steps {
                script {
                    def imageTag = "${DOCKER_USERNAME}/${IMAGE_NAME}:latest"
                    // Pubat the Docker image to Docker Hub
                    bat "docker push ${imageTag}"
                }
            }
        }

         stage('Deploy to Minikube') {
            steps {
                script {
                    // Set the Minikube environment
                    bat "minikube start --driver=docker --profile ${MINIKUBE_PROFILE}" // Ensure Minikube is started
                    
                    // Set Kubeconfig to point to Minikube
                    bat "kubectl config use-context minikube"

                    // Deploy the application to Minikube Kubernetes cluster
                    // Define Kubernetes deployment and service YAML files
                    bat "kubectl apply -f "Django project/myproject/deployment.yaml" " // Path to your deployment.yaml file
                    bat "kubectl apply -f k8s/service.yaml"     // Path to your service.yaml file
                    
                    // Optionally, expose the service (you can skip this if you already exposed the service)
                    bat "kubectl expose deployment ${K8S_DEPLOYMENT_NAME} --type=ClusterIP --name=${K8S_SERVICE_NAME}"

                }
            }
        }
    }

    
}
