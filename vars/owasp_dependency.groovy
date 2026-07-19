// def call() {

//     withCredentials([string(credentialsId: 'NVD_API_KEY', variable: 'NVD_API_KEY')]) {

//         dependencyCheck(
//             odcInstallation: 'OWASP',
//             additionalArguments: """
//                 --scan .
//                 --format XML
//                 --format HTML
//                 --nvdApiKey=${NVD_API_KEY}
//             """
//         )

//         dependencyCheckPublisher(
//             pattern: '**/dependency-check-report.xml'
//         )
//     }
// }




def call() {

    def dcHome = tool 'OWASP'

    sh """
        mkdir -p dependency-check-report
        mkdir -p /var/lib/jenkins/dependency-check-data

        ${dcHome}/bin/dependency-check.sh \
            --scan backend \
            --data /var/lib/jenkins/dependency-check-data \
            --noupdate \
            --format XML \
            --format HTML \
            --out dependency-check-report \
            --failOnCVSS 9 \
            --disableAssembly \
            --exclude node_modules \
            --exclude .git
    """

    dependencyCheckPublisher(
        pattern: 'dependency-check-report/dependency-check-report.xml'
    )

    archiveArtifacts artifacts: 'dependency-check-report/*', fingerprint: true
}













// def call() {

//     withCredentials([
//         string(credentialsId: 'NVD_API_KEY', variable: 'NVD_API_KEY')
//     ]) {

//         dependencyCheck(
//             odcInstallation: 'OWASP',
//             additionalArguments: """
//                 --scan ./ \
//                 --format XML \
//                 --format HTML \
//                 --nvdApiKey ${NVD_API_KEY} \
//                 --noupdate
//             """
//         )

//         dependencyCheckPublisher(
//             pattern: '**/dependency-check-report.xml'
//         )
//     }
// }