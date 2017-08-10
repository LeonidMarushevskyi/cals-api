node ('tpt2-slave'){
   def serverArti = Artifactory.server 'CWDS_DEV'
   def rtGradle = Artifactory.newGradleBuild()
   properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '5')), disableConcurrentBuilds(), [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
   parameters([
      string(defaultValue: 'latest', description: '', name: 'APP_VERSION'),
      string(defaultValue: 'development', description: '', name: 'branch'),
      booleanParam(defaultValue: false, description: '', name: 'RELEASE_DOCKER'),
      string(defaultValue: 'inventories/tpt2dev/hosts.yml', description: '', name: 'inventory')
      ]), pipelineTriggers([pollSCM('H/5 * * * *')])])
  try {
   stage('Preparation') {
		  git branch: '$branch', url: 'https://github.com/ca-cwds/cals-api.git'
		  rtGradle.tool = "Gradle_35"
		  rtGradle.resolver repo:'repo', server: serverArti
		  rtGradle.useWrapper = true
   }
   stage('Build'){
		def buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'jar'
   }
   stage('Unit Tests') {
       buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'test jacocoTestReport', switches: '--stacktrace'
	     publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'JUnit Report', reportTitles: 'JUnit tests summary'])
   }
   stage('License Report') {
      		buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'downloadLicenses'
      		publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/license', reportFiles: 'license-dependency.html', reportName: 'License Report', reportTitles: 'License summary'])
      }
   stage('SonarQube analysis'){
		withSonarQubeEnv('Core-SonarQube') {
			buildInfo = rtGradle.run buildFile: 'build.gradle', switches: '--info', tasks: 'sonarqube'
        }
    }

	stage ('Push to artifactory'){
      rtGradle.deployer repo:'libs-snapshot', server: serverArti
	    //rtGradle.deployer repo:'libs-release', server: serverArti
	    rtGradle.deployer.deployArtifacts = true
		buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'artifactoryPublish'
		rtGradle.deployer.deployArtifacts = false
	}
	stage ('Build Docker'){
	   buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'createDockerImage'
	   withDockerRegistry([credentialsId: '6ba8d05c-ca13-4818-8329-15d41a089ec0']) {
           buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'publishDocker -DReleaseDocker=$RELEASE_DOCKER -DBuildNumber=$BUILD_NUMBER'      }
	}
	stage('Clean Workspace') {
		buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'dropDockerImage'
		archiveArtifacts artifacts: '**/cals-api-*.jar,readme.txt', fingerprint: true
		cleanWs()
	}
	stage('Deploy Application'){
	   checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '433ac100-b3c2-4519-b4d6-207c029a103b', url: 'git@github.com:ca-cwds/de-ansible.git']]]
	   sh 'ansible-playbook -vv -e VERSION_NUMBER=v21 -i $inventory deploy-db2-docker.yml  --vault-password-file ~/.ssh/vault.txt'
	   sh 'ansible-playbook -vv -e VERSION_NUMBER=latest -i $inventory deploy-postgres-docker.yml  --vault-password-file ~/.ssh/vault.txt'
	   sh 'ansible-playbook -e CALS_API_VERSION=$APP_VERSION -i $inventory deploy-calsapi.yml --vault-password-file ~/.ssh/vault.txt -vv'
	   cleanWs()
	   sleep (20)
  }
  stage('Smoke Tests') {
      git branch: 'development', url: 'https://github.com/ca-cwds/cals-api.git'
      buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'smokeTest --stacktrace'
      publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/smokeTest', reportFiles: 'index.html', reportName: 'Smoke Tests Report', reportTitles: 'Smoke tests summary'])
  }
  stage('Integration Tests') {
      git branch: 'development', url: 'https://github.com/ca-cwds/cals-api.git'
      buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'integrationTest --stacktrace'
      publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/integrationTest', reportFiles: 'index.html', reportName: 'Integration Tests Report', reportTitles: 'Integration tests summary'])
      cleanWs()
  }
  
  } catch (e)   {
       emailext attachLog: true, body: "Failed: ${e}", recipientProviders: [[$class: 'DevelopersRecipientProvider']],
       subject: "CALS API CI pipeline failed", to: "Leonid.Marushevskiy@osi.ca.gov, Alex.Kuznetsov@osi.ca.gov, Oleg.Korniichuk@osi.ca.gov, alexander.serbin@engagepoint.com, vladimir.petrusha@engagepoint.com"
       slackSend channel: "#cals-api", baseUrl: 'https://hooks.slack.com/services/', tokenCredentialId: 'slackmessagetpt2', message: "CALS API pipeline failed: ${env.JOB_NAME} ${env.BUILD_NUMBER}"
	   publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/smokeTest', reportFiles: 'index.html', reportName: 'Smoke Tests Reports', reportTitles: 'Smoke tests summary'])
	   publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/integrationTest', reportFiles: 'index.html', reportName: 'Integration Tests Reports', reportTitles: 'Integration tests summary'])
	   publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'JUnitReports', reportTitles: 'JUnit tests summary'])
       cleanWs()
  }
}

