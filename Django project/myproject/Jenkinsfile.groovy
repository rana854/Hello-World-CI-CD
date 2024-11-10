pipeline {
    agent any

    environment {
        // Docker Hub username and password (insecure, avoid in production)
        DOCKER_USERNAME = 'ranatarek'
        DOCKER_PASSWORD = 'Rana3940498'
        IMAGE_NAME = 'pipline_docker_image8'
      //  MINIKUBE_PROFILE = 'minikube' // Set the name of your Minikube profile if you have one
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
             // Start Docker Desktop
          //   bat 'start "" /b "C:\\Program Files\\Docker\\Docker\\Docker Desktop.exe"'

             // Wait for Docker to fully initialize (add a small delay)
            // sleep(20)

             // Start Minikube
             //bat "minikube start"

             // Set Kubeconfig to point to Minikube
             bat "kubectl config use-context minikube"

             // Deploy the application to Minikube Kubernetes cluster
             bat "kubectl apply -f \"Django project/myproject/deployment.yaml\""
             bat "kubectl apply -f \"Django project/myproject/service.yaml\""

             // Optionally, expose the service
             bat "kubectl expose deployment ${K8S_DEPLOYMENT_NAME} --type=ClusterIP --name=${K8S_SERVICE_NAME}"
         }
     }
 }

    }

    
}
