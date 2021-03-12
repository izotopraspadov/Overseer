package edu.born.overseer.data

import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.EmployeeData.EMPLOYEE_2
import edu.born.overseer.data.EmployeeData.EMPLOYEE_3
import edu.born.overseer.data.OrderData.ORDER_1
import edu.born.overseer.data.OrderData.ORDER_2
import edu.born.overseer.data.OrderData.ORDER_3
import edu.born.overseer.model.ActualTime
import edu.born.overseer.model.Order
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE
import java.time.LocalDate.of

const val ACTUAL_TIME_1_ID = START_SEQUENCE + 58
const val ACTUAL_TIME_2_ID = START_SEQUENCE + 59
const val ACTUAL_TIME_3_ID = START_SEQUENCE + 60

val ACTUAL_TIME_1 = ActualTime(ACTUAL_TIME_1_ID, ORDER_1, EMPLOYEE_1, of(2019, 9, 20), 15, 30)
val ACTUAL_TIME_2 = ActualTime(ACTUAL_TIME_2_ID, ORDER_2, EMPLOYEE_2, of(2019, 9, 25), 7, 10)
val ACTUAL_TIME_3 = ActualTime(ACTUAL_TIME_3_ID, ORDER_3, EMPLOYEE_3, of(2019, 9, 23), 35, 40)

val ORDER_1_ACTUAL_TIME = setOf(ACTUAL_TIME_1)
val ORDER_2_ACTUAL_TIME = setOf(ACTUAL_TIME_2)
val ORDER_3_ACTUAL_TIME = setOf(ACTUAL_TIME_3)

fun getPreparedActualTimeCreateSet(order: Order) = setOf(
        ActualTime(order, EMPLOYEE_1, of(2019, 11, 1), 0, 100)
)

fun getPreparedActualTimeUpdateSet() = setOf(
        ActualTime(ACTUAL_TIME_1).apply {
            actualManHours = 20
        }
)