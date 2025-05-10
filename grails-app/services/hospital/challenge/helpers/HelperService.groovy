package hospital.challenge.helpers

import grails.gorm.transactions.Transactional
import hospital.challenge.Doctor
import hospital.challenge.MedicalOffice
import hospital.challenge.Patient

import java.text.SimpleDateFormat

import static constants.HospitalConstants.FORMAT_DATE_INITIAL
import static constants.HospitalConstants.PATTERN_DATE

@Transactional
class HelperService {

    def convertStringToDate(dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE)
        return dateFormat.parse(dateString)
    }

    def createRange(date) {
        SimpleDateFormat dateFormatStart = new SimpleDateFormat(FORMAT_DATE_INITIAL)
        Date startDay = dateFormatStart.parse(dateFormatStart.format(date))

        [ startDate: startDay, endDate: startDay + 1 ]
    }

    def buildDoctors() {
        [
                new Doctor( firstName: 'Saúl', fatherLastName: 'Landa', motherLastName: 'García', specialty: 'Cardiología' ),
                new Doctor( firstName: 'Daniela', fatherLastName: 'Ramirez', motherLastName: 'García', specialty: 'Dermatología' ),
                new Doctor( firstName: 'Felipe', fatherLastName: 'Bustamante', motherLastName: 'Morales', specialty: 'Gastroenterología' ),
                new Doctor( firstName: 'Gustavo', fatherLastName: 'Mendoza', motherLastName: 'Luna', specialty: 'Pediatría' ),
        ]
    }

    def buildMedicalOffices() {
        [
                new MedicalOffice( number: '2-A', floor: 'Nivel 1' ),
                new MedicalOffice( number: '3', floor: 'Nivel 2' ),
                new MedicalOffice( number: '5', floor: 'Nivel 3' ),
                new MedicalOffice( number: '6-A', floor: 'Nivel 1' ),
                new MedicalOffice( number: '12', floor: 'Nivel 2' ),
        ]
    }

    def buildPatients() {
        [
                new Patient( firstName: 'Ricardo', fatherLastName: 'Flores', motherLastName: 'Magón' ),
                new Patient( firstName: 'Gerardo', fatherLastName: 'Gonzalez', motherLastName: 'García' ),
                new Patient( firstName: 'Octavio', fatherLastName: 'Cruz', motherLastName: 'Rivera' ),
                new Patient( firstName: 'Ernesto', fatherLastName: 'Flores', motherLastName: 'Carrillo' ),
        ]
    }
}
