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
   - Define the stages of your pipeline in the Jenkinsfile, including checkout, build, test, etc.
   - Use the appropriate syntax (e.g., Groovy DSL for declarative pipeline or Scripted pipeline).
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

4. **Configure Jenkins Pipeline:**
   - In the Jenkins pipeline configuration, specify the path to your Jenkinsfile (e.g., `Jenkinsfile`).
   - Optionally, configure additional settings such as triggers, post-build actions, etc.

5. **Run Pipeline:**
   - Save your pipeline configuration and trigger a build manually to test the pipeline.
   - Jenkins will automatically fetch the code from your GitHub repository, execute the pipeline stages defined in the Jenkinsfile, and display the build status and output.

6. **Monitor Pipeline Execution:**
   - Monitor the progress of your pipeline on the Jenkins dashboard.
   - View the console output to troubleshoot any issues encountered during the build and test stages.

7. **Customize Pipeline:**
   - Customize your Jenkinsfile and pipeline configuration according to your project requirements.
   - Add additional stages, steps, or integrations as needed to enhance automation and functionality.


![ci_cd](https://github.com/rana854/cicd-project-1/assets/132678372/4ab7b14d-875b-4ad7-a93b-e1aaacfbbb03)
