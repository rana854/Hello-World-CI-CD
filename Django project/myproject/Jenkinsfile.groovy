pipeline {
    agent any

    environment {
        DOCKER_USERNAME = 'ranatarek'
        DOCKER_PASSWORD = 'Rana3940498'
        IMAGE_NAME = 'pipline_docker_image12'
        K8S_DEPLOYMENT_NAME = 'myapp-deployment'
        K8S_SERVICE_NAME = 'myapp-service'
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
                    bat "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
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

        stage('Setup and Deploy to Minikube') {
            steps {
                script {
                    // Ensure Docker is running before starting Minikube
                    bat "docker info"

                    // Set Minikube to use Docker as the driver
                //    bat "minikube config set driver docker"
                  //  bat "docker run hello-world"

                    // Start Minikube with Docker driver
                //    bat "minikube start --driver=docker"
                  //  bat "docker run hello-world"

                    // Wait for Minikube to be fully started (adjust delay as needed)
                 //   bat "timeout /t 20"

                    // Set Kubeconfig to use Minikube
                    bat "kubectl config use-context minikube"

                    // Deploy application to Minikube
                    bat "kubectl apply -f "Django project/myproject/deployment.yaml" "
                    bat "kubectl apply -f "Django project/myproject/service.yaml" "

                    // Optionally, expose the service
                    bat "kubectl expose deployment ${K8S_DEPLOYMENT_NAME} --type=ClusterIP --name=${K8S_SERVICE_NAME}"
                }
            }
        }
    }
}
