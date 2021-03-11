package edu.born.overseer.data.kot

import edu.born.overseer.data.kot.CompanyData.COMPANY_1
import edu.born.overseer.data.kot.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.kot.EmployeeData.EMPLOYEE_2
import edu.born.overseer.data.kot.EmployeeData.EMPLOYEE_3
import edu.born.overseer.model.CounterpartyType.COMPANY
import edu.born.overseer.model.CounterpartyType.EMPLOYEE
import edu.born.overseer.model.Employee
import edu.born.overseer.model.EmployeePayment
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE
import java.math.BigDecimal.valueOf
import java.time.LocalDate.now
import java.time.LocalDate.of

const val EMPLOYEE_PAYMENT_1_ID = START_SEQUENCE + 34
const val EMPLOYEE_PAYMENT_2_ID = START_SEQUENCE + 35
const val EMPLOYEE_PAYMENT_3_ID = START_SEQUENCE + 36
const val EMPLOYEE_PAYMENT_4_ID = START_SEQUENCE + 37

val EMPLOYEE_PAYMENT_1 = EmployeePayment(EMPLOYEE_PAYMENT_1_ID, of(2019, 10, 1), valueOf(10000.00), true,
        EMPLOYEE_1, COMPANY, COMPANY_1, null, true, "$$$")

val EMPLOYEE_PAYMENT_2 = EmployeePayment(EMPLOYEE_PAYMENT_2_ID, of(2019, 10, 1), valueOf(20000.00), true,
        EMPLOYEE_2, EMPLOYEE, null, EMPLOYEE_1, true, "Give me %")

val EMPLOYEE_PAYMENT_3 = EmployeePayment(EMPLOYEE_PAYMENT_3_ID, of(2019, 10, 2), valueOf(2000.00), true,
        EMPLOYEE_1, EMPLOYEE, null, EMPLOYEE_2, true, null)

val EMPLOYEE_PAYMENT_4 = EmployeePayment(EMPLOYEE_PAYMENT_4_ID, of(2019, 10, 2), valueOf(30000.00), true,
        EMPLOYEE_3, COMPANY, null, EMPLOYEE_3, true, null)

val EMPLOYEE_1_PAYMENTS = setOf(EMPLOYEE_PAYMENT_1, EMPLOYEE_PAYMENT_2)
val EMPLOYEE_2_PAYMENTS = setOf(EMPLOYEE_PAYMENT_3)
val EMPLOYEE_3_PAYMENTS = setOf(EMPLOYEE_PAYMENT_4)

fun getPreparedEmployeeCreateSet(employee: Employee) = setOf(
        EmployeePayment(now(), valueOf(100000.00), true, EMPLOYEE_1, COMPANY, null, EMPLOYEE_1, false, null)
)