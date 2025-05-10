package hospital.challenge

import grails.gorm.services.Service

@Service(Patient)
abstract class PatientService implements IPatientService {

    @Override
    def list(params) {
        params.max = params?.max ?: 10
        params?.offset = params?.offset ? params?.offset as Integer : 0

        def patientList = Patient.createCriteria().list(max: params?.max, offset: params?.offset) {
            if(params?.firstName) {
                eq('firstName', params?.firstName)
            }
        }

        return patientList
    }

    @Override
    def findAll() {
        return Patient.findAll()
    }
}
