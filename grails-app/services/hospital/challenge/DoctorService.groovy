package hospital.challenge

import grails.gorm.services.Service

@Service(Doctor)
abstract class DoctorService implements IDoctorService {

    @Override
    def list(params) {
        params.max = params?.max ?: 10
        params?.offset = params?.offset ? params?.offset as Integer : 0

        def doctorList = Doctor.createCriteria().list(max: params?.max, offset: params?.offset) {
            if(params?.firstName) {
                eq('firstName', params?.firstName)
            }
        }

        return doctorList
    }

    @Override
    def findAll() {
        return Doctor.findAll()
    }
}
