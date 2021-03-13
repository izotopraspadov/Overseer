package edu.born.overseer.data

import edu.born.overseer.data.CompanyData.COMPANY_1
import edu.born.overseer.data.CompanyData.COMPANY_2
import edu.born.overseer.data.CompanyData.COMPANY_3
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.EmployeeData.EMPLOYEE_2
import edu.born.overseer.data.EmployeeData.EMPLOYEE_3
import edu.born.overseer.data.TaskData.ORDER_1_TASKS
import edu.born.overseer.data.TaskData.ORDER_2_TASKS
import edu.born.overseer.data.TaskData.ORDER_3_TASKS
import edu.born.overseer.model.Order
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE
import java.math.BigDecimal.valueOf
import java.time.LocalDate.of

const val ORDER_1_ID = START_SEQUENCE + 49
const val ORDER_2_ID = START_SEQUENCE + 50
const val ORDER_3_ID = START_SEQUENCE + 51

const val INVALID_PAYMENT_FORMAT = "100-100"

object OrderData {
    val ORDER_1 = Order(ORDER_1_ID, COMPANY_1,
            "First Project",
            false,
            false,
            true,
            of(2019, 9, 1),
            of(2019, 9, 10),
            of(2019, 10, 1),
            null,
            valueOf(100000.00),
            valueOf(17000.00),
            "100",
            null,
            GROUP_1,
            EMPLOYEE_1,
            true,
            ORDER_TYPE_1)

    val ORDER_2 = Order(ORDER_2_ID, COMPANY_2,
            "Second Project",
            false,
            false,
            true,
            of(2019, 9, 1),
            of(2019, 9, 10),
            of(2019, 10, 1),
            of(2019, 10, 1),
            valueOf(200000.00),
            valueOf(0.00),
            "50-50",
            null,
            GROUP_2,
            EMPLOYEE_2,
            false,
            ORDER_TYPE_2)

    val ORDER_3 = Order(ORDER_3_ID, COMPANY_3,
            "First Legal Service",
            true,
            true,
            false,
            of(2019, 9, 7),
            of(2019, 9, 7),
            of(2019, 10, 7),
            null,
            valueOf(50000.00),
            valueOf(10000.00),
            "20-20-60",
            null,
            GROUP_3,
            EMPLOYEE_3,
            true,
            ORDER_TYPE_3)

    init {
        ORDER_1.apply {
            payments = ORDER_1_PAYMENTS
            tasks = ORDER_1_TASKS
            actualTime = ORDER_1_ACTUAL_TIME
            plannedTime = ORDER_1_PLANNED_TIME
        }

        ORDER_2.apply {
            payments = ORDER_2_PAYMENTS
            tasks = ORDER_2_TASKS
            actualTime = ORDER_2_ACTUAL_TIME
            plannedTime = ORDER_2_PLANNED_TIME
        }

        ORDER_3.apply {
            payments = ORDER_3_PAYMENTS
            tasks = ORDER_3_TASKS
            actualTime = ORDER_3_ACTUAL_TIME
            plannedTime = ORDER_3_PLANNED_TIME
        }
    }
}

fun getPreparedOrderCreate() = Order(
        COMPANY_1,
        "Created Project",
        false,
        false,
        true,
        of(2019, 9, 1),
        of(2019, 9, 10),
        of(2019, 10, 1),
        of(2019, 10, 1),
        valueOf(100000.00),
        valueOf(17000.00),
        "50-50",
        null,
        GROUP_1,
        EMPLOYEE_1,
        true,
        ORDER_TYPE_1
).apply {
    payments = getPreparedOrderPaymentCreateList(this)
    tasks = getPreparedTaskCreateSet(this)
    actualTime = getPreparedActualTimeCreateSet(this)
    plannedTime = getPreparedPlannedTimeCreateSet(this)
}