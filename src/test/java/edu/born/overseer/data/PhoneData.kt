package edu.born.overseer.data

import edu.born.overseer.data.ContactPersonData.CONTACT_PERSON_1
import edu.born.overseer.data.ContactPersonData.CONTACT_PERSON_2
import edu.born.overseer.data.ContactPersonData.CONTACT_PERSON_3
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.EmployeeData.EMPLOYEE_2
import edu.born.overseer.data.EmployeeData.EMPLOYEE_3
import edu.born.overseer.model.ContactPerson
import edu.born.overseer.model.Employee
import edu.born.overseer.model.OwnerType.CONTACT_PERSON
import edu.born.overseer.model.OwnerType.EMPLOYEE
import edu.born.overseer.model.Phone
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE
import edu.born.overseer.model.abstraction.AbstractFullNameEntity

const val CONTACT_PERSON_PHONE_1_ID = START_SEQUENCE + 24
const val CONTACT_PERSON_PHONE_2_ID = START_SEQUENCE + 25
const val CONTACT_PERSON_PHONE_3_ID = START_SEQUENCE + 26
const val CONTACT_PERSON_PHONE_4_ID = START_SEQUENCE + 27
const val CONTACT_PERSON_PHONE_5_ID = START_SEQUENCE + 28

const val EMPLOYEE_PHONE_1_ID = START_SEQUENCE + 29
const val EMPLOYEE_PHONE_2_ID = START_SEQUENCE + 30
const val EMPLOYEE_PHONE_3_ID = START_SEQUENCE + 31
const val EMPLOYEE_PHONE_4_ID = START_SEQUENCE + 32
const val EMPLOYEE_PHONE_5_ID = START_SEQUENCE + 33

const val INVALID_PHONE_NUMBER = "0000-000-9999"

val CONTACT_PERSON_PHONE_1 = Phone(CONTACT_PERSON_PHONE_1_ID, CONTACT_PERSON_1, null, CONTACT_PERSON, "+7-000-000-00-01")
val CONTACT_PERSON_PHONE_2 = Phone(CONTACT_PERSON_PHONE_2_ID, CONTACT_PERSON_1, null, CONTACT_PERSON, "+7-000-000-00-02")
val CONTACT_PERSON_PHONE_3 = Phone(CONTACT_PERSON_PHONE_3_ID, CONTACT_PERSON_2, null, CONTACT_PERSON, "+7-000-000-00-03")
val CONTACT_PERSON_PHONE_4 = Phone(CONTACT_PERSON_PHONE_4_ID, CONTACT_PERSON_3, null, CONTACT_PERSON, "+7-000-000-00-04")
val CONTACT_PERSON_PHONE_5 = Phone(CONTACT_PERSON_PHONE_5_ID, CONTACT_PERSON_3, null, CONTACT_PERSON, "+7-000-000-00-05")

val EMPLOYEE_PHONE_1 = Phone(EMPLOYEE_PHONE_1_ID, null, EMPLOYEE_1, EMPLOYEE, "+7-000-000-00-14")
val EMPLOYEE_PHONE_2 = Phone(EMPLOYEE_PHONE_2_ID, null, EMPLOYEE_1, EMPLOYEE, "+7-000-000-00-15")
val EMPLOYEE_PHONE_3 = Phone(EMPLOYEE_PHONE_3_ID, null, EMPLOYEE_1, EMPLOYEE, "+7-000-000-00-16")
val EMPLOYEE_PHONE_4 = Phone(EMPLOYEE_PHONE_4_ID, null, EMPLOYEE_2, EMPLOYEE, "+7-000-000-00-17")
val EMPLOYEE_PHONE_5 = Phone(EMPLOYEE_PHONE_5_ID, null, EMPLOYEE_3, EMPLOYEE, "+7-000-000-00-18")

val CONTACT_PERSON_1_PHONES = setOf(CONTACT_PERSON_PHONE_1, CONTACT_PERSON_PHONE_2)
val CONTACT_PERSON_2_PHONES = setOf(CONTACT_PERSON_PHONE_3)
val CONTACT_PERSON_3_PHONES = setOf(CONTACT_PERSON_PHONE_4, CONTACT_PERSON_PHONE_5)

val EMPLOYEE_1_PHONES = setOf(EMPLOYEE_PHONE_1, EMPLOYEE_PHONE_2, EMPLOYEE_PHONE_3)
val EMPLOYEE_2_PHONES = setOf(EMPLOYEE_PHONE_4)
val EMPLOYEE_3_PHONES = setOf(EMPLOYEE_PHONE_5)

fun getPreparedPhoneCreateSet(owner: AbstractFullNameEntity) = when (owner) {
    is Employee -> setOf(Phone(null, owner, EMPLOYEE, "+7-000-000-99-99"))
    is ContactPerson -> setOf(Phone(owner, null, CONTACT_PERSON, "+7-000-000-99-99"))
    else -> throw IllegalArgumentException()
}

fun getPreparedPhoneUpdateSetByPerson() = setOf(Phone(CONTACT_PERSON_PHONE_1_ID, CONTACT_PERSON_1, null, CONTACT_PERSON, "+7-000-999-99-99"))

fun getPreparedPhoneUpdateSetByEmployee() = setOf(Phone(EMPLOYEE_PHONE_1_ID, null, EMPLOYEE_1, EMPLOYEE, "+7-009-999-99-99"))

/*fun getPreparedCreateInvalidSet(owner: ContactPerson?): kotlin.collections.Set<Phone?>? {
    return Set.of(Phone()
            .contactPerson(owner)
            .ownerType(CONTACT_PERSON)
            .number(PhoneTestData.INVALID_PHONE_NUMBER)
    )
}*/

/*fun getPreparedCreateInvalidSet(owner: Employee?): kotlin.collections.Set<Phone?>? {
    return Set.of(Phone()
            .employee(owner)
            .ownerType(EMPLOYEE)
            .number(PhoneTestData.INVALID_PHONE_NUMBER)
    )
}*/

/*fun getPreparedPhoneCreate() = Phone()
            .employee(EmployeeTestData.EMPLOYEE_1)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-99-99")*/