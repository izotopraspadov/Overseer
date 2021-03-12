package edu.born.overseer.data

import edu.born.overseer.data.TaskData.TASK_1
import edu.born.overseer.data.TaskData.TASK_2
import edu.born.overseer.data.TaskData.TASK_3
import edu.born.overseer.model.SendType.MANAGER
import edu.born.overseer.model.SendType.TEAM_LEADER
import edu.born.overseer.model.Task
import edu.born.overseer.model.TaskEmail

val TASK_1_EMAILS = setOf(TaskEmail(TEAM_LEADER, TASK_1, EMPLOYEE_EMAIL_1))

val TASK_2_EMAILS = setOf(TaskEmail(MANAGER, TASK_2, EMPLOYEE_EMAIL_2))

val TASK_3_EMAILS = setOf(
        TaskEmail(TEAM_LEADER, TASK_3, EMPLOYEE_EMAIL_3),
        TaskEmail(MANAGER, TASK_3, EMPLOYEE_EMAIL_4),
        TaskEmail(TEAM_LEADER, TASK_3, EMPLOYEE_EMAIL_2)
)

fun getPreparedTaskEmailCreateSet(task: Task) = setOf(TaskEmail(TEAM_LEADER, task, EMPLOYEE_EMAIL_1))