package edu.born.overseer.data

import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.EmployeeData.EMPLOYEE_2
import edu.born.overseer.data.EmployeeData.EMPLOYEE_3
import edu.born.overseer.model.Employee
import edu.born.overseer.model.Salary
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE
import java.math.BigDecimal.valueOf
import java.math.RoundingMode.DOWN
import java.time.LocalDate.now
import java.time.LocalDate.of

const val SALARY_1_ID = START_SEQUENCE + 38
const val SALARY_2_ID = START_SEQUENCE + 39
const val SALARY_3_ID = START_SEQUENCE + 40
const val SALARY_4_ID = START_SEQUENCE + 41

val SALARY_1 = Salary(SALARY_1_ID, EMPLOYEE_1,
        of(2019, 9, 1),
        of(2019, 10, 1),
        valueOf(35000.00).setScale(2, DOWN))

val SALARY_2 = Salary(SALARY_2_ID, EMPLOYEE_1,
        of(2019, 9, 3),
        null,
        valueOf(30000.00).setScale(2, DOWN))

val SALARY_3 = Salary(SALARY_3_ID, EMPLOYEE_2,
        of(2019, 9, 5),
        null,
        valueOf(29000.00).setScale(2, DOWN))

val SALARY_4 = Salary(SALARY_4_ID, EMPLOYEE_3,
        of(2019, 9, 7),
        null,
        valueOf(40000.00).setScale(2, DOWN))

val EMPLOYEE_1_SALARIES = setOf(SALARY_1, SALARY_2)
val EMPLOYEE_2_SALARIES = setOf(SALARY_3)
val EMPLOYEE_3_SALARIES = setOf(SALARY_4)

fun getPreparedSalaryCreate() = Salary(EMPLOYEE_1, now(), of(2019, 10, 1), valueOf(44000.00).setScale(2, DOWN))

fun getPreparedSalaryDuplicate() = Salary(EMPLOYEE_1, of(2019, 10, 1), null, valueOf(44000.00).setScale(2, DOWN))

fun getPreparedSalaryUpdate() = Salary(SALARY_1).apply { amount = valueOf(44000.00).setScale(2, DOWN) }

fun getPreparedSalaryCreatedSet(employee: Employee) = setOf(Salary(employee, of(2019, 12, 1), null, valueOf(44000.00).setScale(2, DOWN)))

fun finishCurrentSalary(set: Set<Salary>) {
    set.stream()
            .filter { e: Salary -> e.endDate == null }
            .findFirst()
            .orElseThrow { NullPointerException() }.endDate = now()
}