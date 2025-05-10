package hospital.challenge.helpers

import hospital.challenge.Appointment
import hospital.challenge.Doctor
import hospital.challenge.MedicalOffice

import static constants.HospitalErrorConstants.BUSY_DOCTOR_ERROR
import static constants.HospitalErrorConstants.BUSY_MEDICAL_OFFICE_ERROR
import static constants.HospitalErrorConstants.BUSY_PATIENT_ERROR
import static constants.HospitalErrorConstants.LIMIT_APPOINTMENT_ERROR

class AppointmentHelperService {

    def helperService

    def validateOffice(Appointment appointment) {
        return Appointment.createCriteria().list() {
            medicalOffice {
                eq('id', appointment?.medicalOffice?.id)
            }
            or {
                eq('startDate', appointment.startDate)
                between('startDate', appointment.startDate, appointment.endDate)
                between('endDate', appointment.startDate, appointment.endDate)
            }
            if (appointment?.id) {
                ne('id', appointment?.id)
            }
        }
    }

    def validateDoctor(Appointment appointment) {
        return Appointment.createCriteria().list() {
            doctor {
                eq('id', appointment?.doctor?.id)
            }
            or {
                eq('startDate', appointment.startDate)
                between('startDate', appointment.startDate, appointment.endDate)
                between('endDate', appointment.startDate, appointment.endDate)
            }
            if (appointment?.id) {
                ne('id', appointment?.id)
            }
        }
    }

    def validateTotal(Appointment appointment) {
        if (appointment?.id) {
            return 0
        }
        def range = helperService.createRange(appointment.startDate)

        return Appointment.countByStartDateBetweenAndDoctor(range.startDate, range.endDate, appointment?.doctor)
    }

    def validatePatient(Appointment appointment) {
        Calendar calendarStart = Calendar.getInstance()
        calendarStart.time = appointment.startDate
        calendarStart.add(Calendar.HOUR_OF_DAY, -2)
        Calendar calendarEnd = Calendar.getInstance()
        calendarEnd.time = appointment.endDate
        calendarEnd.add(Calendar.HOUR_OF_DAY, 2)

        Appointment.createCriteria().list() {
            eq('patientName', appointment.patientName)
            or {
                between('startDate', calendarStart.time, calendarEnd.time)
                between('endDate', calendarStart.time, calendarEnd.time)
            }
            if (appointment?.id) {
                ne('id', appointment?.id)
            }
        }
    }

    def buildAppointment(Appointment appointment, params) {
        appointment.startDate = helperService.convertStringToDate(params?.startDate)
        appointment.endDate = helperService.convertStringToDate(params?.endDate)
        appointment.doctor = Doctor.get(params?.doctor as Long)
        appointment.medicalOffice = MedicalOffice.get(params?.medicalOffice as Long)
        appointment.patientName = params?.patientName

        return appointment
    }

    def validateToSave(Appointment appointment) {

        if (validateOffice(appointment)) return [errors: BUSY_MEDICAL_OFFICE_ERROR, appointment: appointment ]

        if (validateDoctor(appointment)) return [ errors: BUSY_DOCTOR_ERROR, appointment: appointment ]

        if (validateTotal(appointment) > 8) return [ errors: LIMIT_APPOINTMENT_ERROR, appointment: appointment ]

        if (validatePatient(appointment)) return [ errors: BUSY_PATIENT_ERROR, appointment: appointment ]

        return [ errors: "" ]
    }
}
