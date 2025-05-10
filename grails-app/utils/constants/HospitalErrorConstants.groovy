package constants

final class HospitalErrorConstants {

    static String BUSY_MEDICAL_OFFICE_ERROR = "Ya existe una cita es este consultorio a esta hora"
    static String BUSY_DOCTOR_ERROR = "El doctor ya tiene una cita esta hora"
    static String LIMIT_APPOINTMENT_ERROR = "El doctor ya tiene 8 citas para este día"
    static String BUSY_PATIENT_ERROR = "El paciente tiene una cita próxima (2 horas)"
    static String APPOINTMENT_CANCELED_ERROR = "La cita ya estaba cancelada"
    static String APPOINTMENT_DONE_ERROR = "La cita ya se llevó acabo, no se puede cancelar"
}
