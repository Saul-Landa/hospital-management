package hospital.challenge

class DoctorController {

    def doctorService

    def index() {
        def doctorList = doctorService.list(params)
        [
                doctorList: doctorList,
                doctorListCount: doctorList?.getTotalCount()
        ]
    }

}
