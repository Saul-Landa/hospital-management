package hospital.challenge

import static constants.HospitalErrorConstants.APPOINTMENT_CANCELED_ERROR
import static constants.HospitalErrorConstants.APPOINTMENT_DONE_ERROR
import static constants.HospitalConstants.FORMAT_DATE
import static constants.HospitalConstants.APPOINTMENT_CANCELED

import grails.gorm.services.Service

import java.text.SimpleDateFormat

@Service(Appointment)
class AppointmentService {

    def messageSource
    def appointmentHelperService

    def list(params) {
        params.max = params?.max ?: 10
        params?.offset = params?.offset ? params?.offset as Integer : 0
        def dateRange = null
        if (params?.startDate) {
            SimpleDateFormat dateFormatStart = new SimpleDateFormat(FORMAT_DATE)
            def date = dateFormatStart.parse(params?.startDate)
            dateRange = createRange(date)
        }

        def appointmentsList = Appointment.createCriteria().list(max: params?.max, offset: params?.offset) {
            if(params?.medicalOffice) {
                medicalOffice {
                    eq('id', params.long('medicalOffice'))
                }
            }
            if(params?.doctor) {
                doctor {
                    eq('id', params.long('doctor'))
                }
            }
            if (dateRange) {
                between('startDate', dateRange.startDate, dateRange.endDate)
            }
        }

        return appointmentsList
    }

    def save(params) {
        Appointment.withTransaction { status ->
            def appointment = appointmentHelperService.buildAppointment(new Appointment(), params)
            def appointmentValidations = appointmentHelperService.validateToSave(appointment)
            if (appointmentValidations.errors != "") return appointmentValidations

            if(!appointment.validate()){
                status.setRollbackOnly()
                def errors = ""
                appointment.errors.allErrors.each {error ->
                    errors += "${ messageSource.getMessage(error, null) }\n"
                }
                return [ errors: errors, appointment: appointment ]
            }

            appointment.save(flush:true)
            return [ appointment: appointment ]
        }
    }

    def update(params, Long appointmentId) {
        def appointment = getById(appointmentId)
        Appointment.withTransaction { status ->
            appointment = appointmentHelperService.buildAppointment(appointment, params)

            def appointmentValidations = appointmentHelperService.validateToSave(appointment)
            if (appointmentValidations.errors != "") return appointmentValidations

            if(!appointment.validate()){
                status.setRollbackOnly()
                def errors = ""
                appointment.errors.allErrors.each {error ->
                    errors += "${ messageSource.getMessage(error, null) }\n"
                }
                return [ errors: errors ]
            }

            appointment.save(flush:true)
            return [ appointment: appointment ]
        }
    }

    def getById(Long appointmentId) {
        return Appointment.get(appointmentId)
    }

    def cancel(Long appointmentId) {
        def appointment = getById(appointmentId)
        if (appointment.isCanceled) {
            return [ errors: APPOINTMENT_CANCELED_ERROR ]
        }

        def currentDate = new Date()
        if (appointment.startDate < currentDate ) {
            return [ errors: APPOINTMENT_DONE_ERROR ]
        }

        Appointment.withTransaction { status ->
            appointment.isCanceled = true
            appointment.save(flush: true)

            return [ message: APPOINTMENT_CANCELED ]
        }
    }
}