package edu.born.overseer.data

import edu.born.overseer.data.ContactPersonData.COMPANY_1_CONTACT_PERSONS
import edu.born.overseer.data.ContactPersonData.COMPANY_2_CONTACT_PERSONS
import edu.born.overseer.data.ContactPersonData.COMPANY_3_CONTACT_PERSONS
import edu.born.overseer.model.Company
import edu.born.overseer.model.CompanyType.*
import edu.born.overseer.model.ReliabilityType.*
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE

const val COMPANY_1_ID = START_SEQUENCE + 3
const val COMPANY_2_ID = START_SEQUENCE + 4
const val COMPANY_3_ID = START_SEQUENCE + 5

object CompanyData {
    val COMPANY_1 = Company(COMPANY_1_ID, "First Company", REGION_1, "000000000000",
            "No st. one", LOW, "First", OUR)
    val COMPANY_2 = Company(COMPANY_2_ID, "Second Company", REGION_2, "0000000001",
            "Our street 2", MIDDLE, "Second", CUSTOMER)
    val COMPANY_3 = Company(COMPANY_3_ID, "Third Company", REGION_3, "0000000002",
            "Winter st. 89", HIGH, "Third", OTHER)

    init {
        COMPANY_1.apply { contactPersons = COMPANY_1_CONTACT_PERSONS }
        COMPANY_2.apply { contactPersons = COMPANY_2_CONTACT_PERSONS }
        COMPANY_3.apply { contactPersons = COMPANY_3_CONTACT_PERSONS }
    }
}

fun getPreparedCompanyCreate() = Company("New Company", REGION_2, "000000000003", "New Street", LOW, "New Group", OUR)