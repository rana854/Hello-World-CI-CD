pipeline {
    agent any

    environment {
        DOCKER_USERNAME = 'ranatarek'
        DOCKER_PASSWORD = 'Rana3940498'
        IMAGE_NAME = 'pipline_docker_image17'
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

        stage('Setup Minikube') {
            steps {
                script {
                    // Verify Docker service is running
                    bat "docker info || exit 1"

                    // Start Minikube with Docker driver if not running
                    bat """
                        minikube status || (
                            echo "Starting Minikube..."
                            minikube start --driver=docker
                        )
                    """
                }
            }
        }

        stage('Verify Minikube') {
            steps {
                script {
                    // Check Minikube status to ensure all components are running
                    bat """
                        minikube status || (
                            echo "Minikube did not start properly. Attempting restart..."
                            minikube stop
                            minikube start --driver=docker
                        )
                    """

                    // Set kubectl to use Minikube context and confirm node readiness
                    bat "kubectl config use-context minikube"
                    bat "kubectl get nodes || (echo 'Minikube nodes are not ready.' && exit 1)"
                }
            }
        }

        stage('Deploy to Minikube') {
            steps {
                script {
                    // Deploy the application
                    bat "kubectl apply -f \"Django project/myproject/deployment.yaml\" --validate=false"
                    bat "kubectl apply -f \"Django project/myproject/service.yaml\" --validate=false"

                    // Optionally, expose the service
                    bat "kubectl expose deployment ${K8S_DEPLOYMENT_NAME} --type=ClusterIP --name=${K8S_SERVICE_NAME}"
                }
            }
        }
    }

    post {
        always {
            // Output Minikube logs for debugging if necessary
            script {
                bat "minikube logs"
            }
        }
        cleanup {
            // Stop Minikube to release resources
            script {
                bat "minikube stop"
            }
        }
    }
}
