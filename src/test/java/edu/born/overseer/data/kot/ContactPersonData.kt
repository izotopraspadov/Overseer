package edu.born.overseer.data.kot

import edu.born.overseer.data.kot.CompanyData.COMPANY_1
import edu.born.overseer.data.kot.ContactPersonData.CONTACT_PERSON_1
import edu.born.overseer.model.ContactPerson
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE

const val CONTACT_PERSON_1_ID = START_SEQUENCE + 6
const val CONTACT_PERSON_2_ID = START_SEQUENCE + 7
const val CONTACT_PERSON_3_ID = START_SEQUENCE + 8

object ContactPersonData {
    val CONTACT_PERSON_1 = ContactPerson(CONTACT_PERSON_1_ID, "Ivanov Ivan Ivanovich", COMPANY_1)
    val CONTACT_PERSON_2 = ContactPerson(CONTACT_PERSON_2_ID, "Semyonov Semyon Semyonovich", COMPANY_1)
    val CONTACT_PERSON_3 = ContactPerson(CONTACT_PERSON_3_ID, "Petrov Petr Petrovich", COMPANY_1)

    val COMPANY_1_CONTACT_PERSONS = setOf(CONTACT_PERSON_1)
    val COMPANY_2_CONTACT_PERSONS = setOf(CONTACT_PERSON_2)
    val COMPANY_3_CONTACT_PERSONS = setOf(CONTACT_PERSON_3)

    init {
        CONTACT_PERSON_1.apply { phones = CONTACT_PERSON_1_PHONES }
        CONTACT_PERSON_1.apply { emails = CONTACT_PERSON_1_EMAILS }
    }
}

fun getPreparedContactPersonCreate() = ContactPerson("New Person", COMPANY_1).apply {
    emails = getPreparedEmailCreateSet(this)
    phones = getPreparedPhoneCreateSet(this)
}

fun getPreparedUpdate() = ContactPerson(CONTACT_PERSON_1).apply { fullName = "Updated person" }