# Hello World CI/CD Project
This project demonstrates a simple Jenkins CI/CD pipeline that checks out a Python application from a Git repository and runs it to print "Hello, World!" to the console.

### Jenkinsfile:
   
   ```pipeline {
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
                bat 'python app.py' // Use 'sh' instead of 'bat' on Linux
            }
        }
    }
}

   ```
### Implement Webhooks for Continuous Integration**
   1. Configure GitHub Webhooks:
      - In GitHub, go to your repository settings and select "Webhooks".
      - Add a new webhook:
        - Payload URL: `http://<your-jenkins-url>/github-webhook/`
        - Content type: `application/json`
        - Select "Just the push event".
        - Ensure the webhook is active.
   
   2. Trigger Builds:
      - With the webhook configured, Jenkins will trigger a new build every time changes are pushed to the connected branch.

Setup
Clone the Repository Clone this repository to your local machine:
git clone https://github.com/rana854/cicd-project-1.git
Jenkins Setup

Add this Git repository in a new Jenkins job.
Ensure that the Python path is configured in Jenkins.
Run the Pipeline Run the Jenkins job to see the pipeline in action. Jenkins will:

Clone the repository.
Run the Python script, which outputs "Hello, World!".


