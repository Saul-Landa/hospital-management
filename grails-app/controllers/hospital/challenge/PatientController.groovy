package hospital.challenge

class PatientController {

    def patientService

    def index() {
        def patientList = patientService.list(params)
        [
                patientList: patientList,
                patientListCount: patientList?.getTotalCount()
        ]
    }
}
