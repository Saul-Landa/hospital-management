package hospital.challenge

class DoctorController {

    def doctorService

    def index() {
        def doctorList = doctorService.findAll(params)
        [
                doctorList: doctorList,
                doctorListCount: doctorList?.getTotalCount()
        ]
    }

}
