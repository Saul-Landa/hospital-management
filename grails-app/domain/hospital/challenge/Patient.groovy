package hospital.challenge

class Patient {
    String firstName
    String fatherLastName
    String motherLastName
    String fullName

    static mapping = {
        fullName formula: "CONCAT( IFNULL(first_name,''),' ',IFNULL(father_last_name,''),' ',IFNULL(mother_last_name,''))"
    }

    static constraints = {
        firstName nullable: false, blank: false
        fatherLastName nullable: false, blank: false
    }
}
