# Hospital Management System - Jenkins Automated Build and Test Setup

This repository contains the code for a Hospital Management System developed using Python and the Django framework. The repository is set up to automatically build and test code changes using Jenkins, following the Git Flow branching model.

## Tools Needed
- Jenkins
- GitHub account
- Git installed on the Jenkins server

## Task Items

### Prepare GitHub Repository:
1. **Create GitHub Repository:**
   - Create a new GitHub repository or select an existing one.
   - Initialize the repository with a README.md.
   - Ensure the repository contains at least two branches:
     - `develop` for ongoing development.
     - `master` (or `main`) for stable releases.
2. **Apply Git Flow model:**
   - Install Git Flow tools if not already installed.
   - Initialize Git Flow in your repository with default branch names.
3. **Add Jenkinsfile:**
   - Add a Jenkinsfile to the root directory of your repository to define the pipeline.

### Configure Jenkins:
1. **Install Necessary Plugins:**
   - Install required plugins in Jenkins, such as Git and Pipeline.
   - Go to "Manage Jenkins" > "Manage Plugins" > "Available" and install "GitHub Integration Plugin".
2. **Connect Jenkins to GitHub:**
   - Set up credentials in Jenkins for GitHub (username and token).
3. **Create Pipeline Job:**
   - Create a new pipeline job and configure it to fetch code from your GitHub repository.
   - Specify the branch to build .

### Implement Webhooks for Continuous Integration:
1. **Set Up Webhooks in GitHub:**
   - Go to your repository settings on GitHub and select "Webhooks".
   - Add a new webhook with the following details:
     - Payload URL: `http://<your-jenkins-url>/github-webhook/`
     - Content type: `application/json`
     - Select "Just the push event".
   - Ensure the webhook is active.

### Testing and Validation:
1. **Push Changes to develop Branch:**
   - Push a change to the `develop` branch of your GitHub repository.
2. **Verify Jenkins Build:**
   - Verify that Jenkins automatically triggers a build and executes the pipeline defined in the Jenkinsfile.
3. **Check Build Output:**
   - Check the Jenkins output to ensure that the build and test stages are executed successfully.

## Jenkinsfile
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
![ci_cd](https://github.com/rana854/cicd-project-1/assets/132678372/4ab7b14d-875b-4ad7-a93b-e1aaacfbbb03)
