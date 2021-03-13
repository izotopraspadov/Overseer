package edu.born.overseer.data

import edu.born.overseer.data.ContactPersonData.CONTACT_PERSON_1
import edu.born.overseer.data.ContactPersonData.CONTACT_PERSON_2
import edu.born.overseer.data.ContactPersonData.CONTACT_PERSON_3
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.EmployeeData.EMPLOYEE_2
import edu.born.overseer.data.EmployeeData.EMPLOYEE_3
import edu.born.overseer.model.ContactPerson
import edu.born.overseer.model.Email
import edu.born.overseer.model.Employee
import edu.born.overseer.model.OwnerType.CONTACT_PERSON
import edu.born.overseer.model.OwnerType.EMPLOYEE
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE
import edu.born.overseer.model.abstraction.AbstractFullNameEntity

const val CONTACT_PERSON_EMAIL_1_ID = START_SEQUENCE + 12
const val CONTACT_PERSON_EMAIL_2_ID = START_SEQUENCE + 13
const val CONTACT_PERSON_EMAIL_3_ID = START_SEQUENCE + 14
const val CONTACT_PERSON_EMAIL_4_ID = START_SEQUENCE + 15
const val CONTACT_PERSON_EMAIL_5_ID = START_SEQUENCE + 16
const val CONTACT_PERSON_EMAIL_6_ID = START_SEQUENCE + 17

const val EMPLOYEE_EMAIL_1_ID = START_SEQUENCE + 18
const val EMPLOYEE_EMAIL_2_ID = START_SEQUENCE + 19
const val EMPLOYEE_EMAIL_3_ID = START_SEQUENCE + 20
const val EMPLOYEE_EMAIL_4_ID = START_SEQUENCE + 21
const val EMPLOYEE_EMAIL_5_ID = START_SEQUENCE + 22
const val EMPLOYEE_EMAIL_6_ID = START_SEQUENCE + 23

val CONTACT_PERSON_EMAIL_1 = Email(CONTACT_PERSON_EMAIL_1_ID, CONTACT_PERSON_1, null, CONTACT_PERSON, "1@mail.com")
val CONTACT_PERSON_EMAIL_2 = Email(CONTACT_PERSON_EMAIL_2_ID, CONTACT_PERSON_2, null, CONTACT_PERSON, "2@mail.com")
val CONTACT_PERSON_EMAIL_3 = Email(CONTACT_PERSON_EMAIL_3_ID, CONTACT_PERSON_2, null, CONTACT_PERSON, "3@mail.com")
val CONTACT_PERSON_EMAIL_4 = Email(CONTACT_PERSON_EMAIL_4_ID, CONTACT_PERSON_3, null, CONTACT_PERSON, "4@mail.com")
val CONTACT_PERSON_EMAIL_5 = Email(CONTACT_PERSON_EMAIL_5_ID, CONTACT_PERSON_3, null, CONTACT_PERSON, "5@mail.com")
val CONTACT_PERSON_EMAIL_6 = Email(CONTACT_PERSON_EMAIL_6_ID, CONTACT_PERSON_3, null, CONTACT_PERSON, "6@mail.com")

val EMPLOYEE_EMAIL_1 = Email(EMPLOYEE_EMAIL_1_ID, null, EMPLOYEE_1, EMPLOYEE, "7@mail.com")
val EMPLOYEE_EMAIL_2 = Email(EMPLOYEE_EMAIL_2_ID, null, EMPLOYEE_1, EMPLOYEE, "8@mail.com")
val EMPLOYEE_EMAIL_3 = Email(EMPLOYEE_EMAIL_3_ID, null, EMPLOYEE_2, EMPLOYEE, "9@mail.com")
val EMPLOYEE_EMAIL_4 = Email(EMPLOYEE_EMAIL_4_ID, null, EMPLOYEE_3, EMPLOYEE, "10@mail.com")
val EMPLOYEE_EMAIL_5 = Email(EMPLOYEE_EMAIL_5_ID, null, EMPLOYEE_3, EMPLOYEE, "11@mail.com")
val EMPLOYEE_EMAIL_6 = Email(EMPLOYEE_EMAIL_6_ID, null, EMPLOYEE_3, EMPLOYEE, "12@mail.com")

val CONTACT_PERSON_1_EMAILS = setOf(CONTACT_PERSON_EMAIL_1)
val CONTACT_PERSON_2_EMAILS = setOf(CONTACT_PERSON_EMAIL_2, CONTACT_PERSON_EMAIL_3)
val CONTACT_PERSON_3_EMAILS = setOf(CONTACT_PERSON_EMAIL_4, CONTACT_PERSON_EMAIL_5, CONTACT_PERSON_EMAIL_6)

val EMPLOYEE_1_EMAILS = setOf(EMPLOYEE_EMAIL_1, EMPLOYEE_EMAIL_2)
val EMPLOYEE_2_EMAILS = setOf(EMPLOYEE_EMAIL_3)
val EMPLOYEE_3_EMAILS = setOf(EMPLOYEE_EMAIL_4, EMPLOYEE_EMAIL_5, EMPLOYEE_EMAIL_6)

fun getPreparedEmailCreateSet(owner: AbstractFullNameEntity) = when (owner) {
    is Employee -> setOf(Email(null, owner, EMPLOYEE, "902@mail.com"))
    is ContactPerson -> setOf(Email(owner, null, CONTACT_PERSON, "901@mail.com"))
    else -> throw IllegalArgumentException()
}