def call() {

    timeout(time: 5, unit: 'MINUTES') {

        def qg = waitForQualityGate(abortPipeline: false)

        echo "SonarQube Quality Gate Status: ${qg.status}"

        if (qg.status != 'OK') {
            echo "WARNING: Quality Gate Failed"
            echo "Pipeline will continue..."
        } else {
            echo "Quality Gate Passed"
        }
    }
}