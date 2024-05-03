# Jenkins pipeline
The Jenkins pipeline for our Python project is designed to automate essential development processes, including building, testing, and validation. By integrating Jenkins, a powerful CI/CD tool, into our workflow, we aim to streamline the deployment of code changes, ensuring efficiency and reliability throughout the development lifecycle.

## Tools Needed
- Jenkins
- Docker
- Python
- git
- GitHub account
- Git installed on the Jenkins server
Sure, here's a step-by-step guide on how to use the Jenkins pipeline for a Python project:

### Prerequisites:
1. **Jenkins Installed:** Ensure that Jenkins is installed and configured on your server or local machine.
2. **Python Installed:** Make sure Python is installed on the Jenkins server or node where the pipeline will be executed.
3. **GitHub Repository:** Have your Python project hosted on a GitHub repository.

### Steps to Use Jenkins Pipeline for Python Project:

1. **Set Up Jenkins:**
   - Install necessary plugins: Ensure that Jenkins has plugins for Git integration and Pipeline.
   - Configure Jenkins credentials: Add credentials for accessing your GitHub repository if it's private.

2. **Create Jenkins Pipeline:**
   - Go to Jenkins dashboard and select "New Item".
   - Enter a name for your pipeline job and choose "Pipeline" as the job type.
   - In the pipeline configuration, select "Pipeline script from SCM".
   - Choose Git as the SCM and provide the repository URL.
   - Specify the branch to build (e.g., `*/develop`).

3. **Write Jenkinsfile:**
   - Create a `Jenkinsfile` in the root directory of your Python project.
   - Define the stages of your pipeline in the Jenkinsfile.
   - ## Jenkinsfile
```groovy
pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rana854/cicd-project-1.git'![Uploading ci_cd.jpg…]()

            }
        }
        stage('Install Dependencies') {
            steps {
                bat 'pip install -r "Django project/myproject/requirements.txt"'
            }
        }
        stage('Run Tests') {
            steps {
                bat 'python "Django project/myproject/manage.py" test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application'
            }
        }
    }
}
```

4. **Implement Webhooks for Continuous Integration**
1- Configure GitHub Webhooks:
In GitHub, go to your repository settings and select "Webhooks".
Add a new webhook:
Payload URL: http://<your-jenkins-url>/github-webhook/
Content type: application/json
Select "Just the push event".
Ensure the webhook is active.
2-  Trigger Builds:
With the webhook configured, Jenkins will trigger a new build every time changes are pushed to the connected branch.
4. Testing and Validation
4.1 Push Changes:

Push a change to the develop branch of your GitHub repository.
4.2 Verify Jenkins Triggers:

Verify that Jenkins automatically triggers a build and processes according to the steps defined in the Jenkinsfile.
4.3 Check Output:

Check the output in Jenkins to ensure the build and test stages are executed successfully.
5. **Run Pipeline:**
   - Save your pipeline configuration and trigger a build manually to test the pipeline.
   - Jenkins will automatically fetch the code from your GitHub repository, execute the pipeline stages defined in the Jenkinsfile, and display the build status and output.



![ci_cd](https://github.com/rana854/cicd-project-1/assets/132678372/4ab7b14d-875b-4ad7-a93b-e1aaacfbbb03)
