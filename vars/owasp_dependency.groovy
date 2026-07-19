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

    withCredentials([string(credentialsId: 'NVD_API_KEY', variable: 'NVD_API_KEY')]) {

        dependencyCheck(
            odcInstallation: 'OWASP',
            additionalArguments: """
                --scan backend
                --format XML
                --format HTML
                --failOnCVSS 9
                --disableAssembly
                --exclude node_modules
                --exclude .git
                --nvdApiKey=${NVD_API_KEY}
            """
        )

        dependencyCheckPublisher(
            pattern: '**/dependency-check-report.xml'
        )
    }
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