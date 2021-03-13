package edu.born.overseer.data

import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.model.Employee
import edu.born.overseer.model.Role.ROLE_ADMIN
import edu.born.overseer.model.Role.ROLE_USER
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE

const val EMPLOYEE_1_ID = START_SEQUENCE + 9
const val EMPLOYEE_2_ID = START_SEQUENCE + 10
const val EMPLOYEE_3_ID = START_SEQUENCE + 11

object EmployeeData {
    val EMPLOYEE_1 = Employee(EMPLOYEE_1_ID, "Romanov Roman Romanovich",
            REGION_1, "admin", "admin", "Zastavskaya st. 6", setOf(ROLE_USER, ROLE_ADMIN))
    val EMPLOYEE_2 = Employee(EMPLOYEE_2_ID, "Stepanov Stepan Stepanovich",
            REGION_2, "stepanov", "user1", "Fourth pr. 10", setOf(ROLE_USER, ROLE_ADMIN))
    val EMPLOYEE_3 = Employee(EMPLOYEE_3_ID, "Romanova Daria Petrovna",
            REGION_3, "user", "user", "Etc. Desperate, 90", setOf(ROLE_USER))

    init {
        EMPLOYEE_1.apply {
            payments = EMPLOYEE_1_PAYMENTS
            salary = EMPLOYEE_1_SALARIES
            phones = EMPLOYEE_1_PHONES
            emails = EMPLOYEE_1_EMAILS
        }
        EMPLOYEE_2.apply {
            payments = EMPLOYEE_2_PAYMENTS
            salary = EMPLOYEE_2_SALARIES
            phones = EMPLOYEE_2_PHONES
            emails = EMPLOYEE_2_EMAILS
        }
        EMPLOYEE_3.apply {
            payments = EMPLOYEE_3_PAYMENTS
            salary = EMPLOYEE_3_SALARIES
            phones = EMPLOYEE_3_PHONES
            emails = EMPLOYEE_3_EMAILS
        }
    }
}

fun getPreparedEmployeeCreate() = Employee("New Employee", REGION_1, "new_login", "new_password", "New Address", setOf(ROLE_USER)).apply {
    payments = getPreparedEmployeeCreateSet(this)
    salary = getPreparedSalaryCreatedSet(this)
    emails = getPreparedEmailCreateSet(this)
    phones = getPreparedPhoneCreateSet(this)
}