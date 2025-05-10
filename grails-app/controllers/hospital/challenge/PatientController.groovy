package hospital.challenge

class PatientController {

    def patientService

    def index() {
        def patientList = patientService.findAll(params)
        [
                patientList: patientList,
                patientListCount: patientList?.getTotalCount()
        ]
    }
}
