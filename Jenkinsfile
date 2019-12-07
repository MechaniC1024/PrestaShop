pipeline {
  agent any
  stages{
    stage("TEST"){
      steps{
        git url: "https://github.com/MechaniC1024/PrestaShop.git"
        dir("PrestaShop"){
          bat ''' mvn clean test site '''
        }
      }
    }
  }
}    
