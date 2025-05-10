package hospital.challenge

class Appointment {

    Date startDate
    Date endDate
    Patient patient
    Doctor doctor
    MedicalOffice medicalOffice
    Boolean isCanceled = 0

    static belongsTo = [ Doctor, MedicalOffice, Patient ]

    static constraints = {
        startDate nullable: false
        endDate nullable: false
        patient nullable: false
        doctor nullable: false
        medicalOffice nullable: false
    }
}
