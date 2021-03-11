package edu.born.overseer.data.kot

import edu.born.overseer.data.kot.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.kot.EmployeeData.EMPLOYEE_3
import edu.born.overseer.data.kot.OrderData.ORDER_1
import edu.born.overseer.model.Order
import edu.born.overseer.model.ResultType.COMPLETED
import edu.born.overseer.model.ResultType.PARTIALLY_COMPLETED
import edu.born.overseer.model.Task
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE
import java.time.LocalDate.now
import java.time.LocalDate.of

const val TASK_1_ID = START_SEQUENCE + 61
const val TASK_2_ID = START_SEQUENCE + 62
const val TASK_3_ID = START_SEQUENCE + 63

object TaskData {
    val TASK_1 = Task(TASK_1_ID, ORDER_1, "Step 1st", EMPLOYEE_3, of(2019, 9, 20), PARTIALLY_COMPLETED, "Step 1st done!")
    val TASK_2 = Task(TASK_2_ID, ORDER_1, "Step 1st", EMPLOYEE_3, of(2019, 9, 20), PARTIALLY_COMPLETED, "Step 1st done!")
    val TASK_3 = Task(TASK_3_ID, ORDER_1, "Step 1st", EMPLOYEE_3, of(2019, 9, 20), PARTIALLY_COMPLETED, "Step 1st done!")

    val ORDER_1_TASKS = setOf(TASK_1)
    val ORDER_2_TASKS = setOf(TASK_2)
    val ORDER_3_TASKS = setOf(TASK_3)

    init {
        TASK_1.apply {
            emails = TASK_1_EMAILS
        }
        TASK_2.apply {
            emails = TASK_2_EMAILS
        }
        TASK_3.apply {
            emails = TASK_3_EMAILS
        }
    }
}

fun getPreparedTaskCreateSet(order: Order) = setOf(
        Task(order, "Created Task", EMPLOYEE_1, now(), COMPLETED, null).apply {
            emails = getPreparedTaskEmailCreateSet(this)
        }
)