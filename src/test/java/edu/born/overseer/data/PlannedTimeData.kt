package edu.born.overseer.data

import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.EmployeeData.EMPLOYEE_2
import edu.born.overseer.data.EmployeeData.EMPLOYEE_3
import edu.born.overseer.data.OrderData.ORDER_1
import edu.born.overseer.data.OrderData.ORDER_2
import edu.born.overseer.data.OrderData.ORDER_3
import edu.born.overseer.model.Order
import edu.born.overseer.model.PlannedTime
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE

const val PLANNED_TIME_1_ID = START_SEQUENCE + 55
const val PLANNED_TIME_2_ID = START_SEQUENCE + 56
const val PLANNED_TIME_3_ID = START_SEQUENCE + 57

val PLANNED_TIME_1 = PlannedTime(PLANNED_TIME_1_ID, ORDER_1, EMPLOYEE_1, 30)
val PLANNED_TIME_2 = PlannedTime(PLANNED_TIME_2_ID, ORDER_2, EMPLOYEE_2, 10)
val PLANNED_TIME_3 = PlannedTime(PLANNED_TIME_3_ID, ORDER_3, EMPLOYEE_3, 40)

val ORDER_1_PLANNED_TIME = setOf(PLANNED_TIME_1)
val ORDER_2_PLANNED_TIME = setOf(PLANNED_TIME_2)
val ORDER_3_PLANNED_TIME = setOf(PLANNED_TIME_3)

fun getPreparedPlannedTimeCreateSet(order: Order) = setOf(PlannedTime(order, EMPLOYEE_1, 100))