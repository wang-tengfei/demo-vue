pipeline {
    agent {
        label "java-8"
    }
    stages {
        stage('Build') {
            docker {
                image 'maven:3-alpine'
                args '-v /root/.m2:/root/.m2'
            }
            steps {
                echo "begin building...."
                sh '''
                rm -rf target/
                mvn clean install -Dmaven.test.skip=true -U
                '''
                //收集构建产物，这一步成功，我们就可以在平台上看到构建产物
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Build Image') {
            steps {
                ssh '''
                ls -la ./target
                COMMIT=`echo $GIT_COMMIT | head -c 7`
                BRANCH=`echo $BRANCH_NAME | tr "/" "-" | cut -d "_" -f1`
                tag=`echo $BRANCH-$COMMIT-$BUILD_ID`
                echo begin build image...
                '''
            }
        }
    }
}