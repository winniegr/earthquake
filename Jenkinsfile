pipeline{
    agent {
        docker {
            image 'allbears/jenkins-android:1.0.1'
        }
    }
    environment {
        DIR = 'Earthquake'
    }
    stages {
        stage('Setup') {
          steps {
            dir(DIR) {
              sh 'chmod +x ./gradlew'
            }
          }
        }
        stage('Build'){
             steps {
                sh './gradlew clean && rm -rf ./app/build/'
                sh './gradlew assembleRelease'
             }
        }
       stage('UnitTest'){
             steps {
                sh './gradlew test'
             }
        }
        stage('Archive') {  
            steps {
                archiveArtifacts artifacts: 'app/build/outputs/**/*.apk', fingerprint: true 
            }
        }
    }

}
