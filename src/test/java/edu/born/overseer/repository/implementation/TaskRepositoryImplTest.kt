package edu.born.overseer.repository.implementation

import edu.born.overseer.data.EMPLOYEE_1_ID
import edu.born.overseer.data.ORDER_1_ID
import edu.born.overseer.data.OrderData.ORDER_1
import edu.born.overseer.data.TASK_1_ID
import edu.born.overseer.data.TaskData.TASK_1
import edu.born.overseer.data.TaskData.TASK_2
import edu.born.overseer.data.getPreparedTaskCreateSet
import edu.born.overseer.model.ResultType.COMPLETED
import edu.born.overseer.model.Task
import edu.born.overseer.repository.TaskRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.core.IsNot.not
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class TaskRepositoryImplTest : AbstractRepositoryTest() {

    @Autowired
    private lateinit var taskRepository: TaskRepository

    @BeforeEach
    fun setUp() {
        taskRepository.evictCache()
    }

    @Test
    fun create() {
        val prepared = getPreparedTaskCreateSet(ORDER_1).first()
        val savedId = taskRepository.save(prepared, ORDER_1_ID, EMPLOYEE_1_ID).id
        prepared.id = savedId

        assertEquals(taskRepository.getById(savedId), prepared)
    }

    @Test
    fun update() {
        val prepared = Task(TASK_1).apply {
            resultType = COMPLETED
        }
        val updated = taskRepository.save(prepared, ORDER_1_ID, EMPLOYEE_1_ID)

        assertEquals(updated, prepared)
    }

    @Test
    fun delete() {
        taskRepository.delete(TASK_1_ID)
        val tasks = taskRepository.getAllByOrder(1, ORDER_1_ID)

        assertThat(tasks, not(contains(TASK_1)))
    }

    @Test
    fun getById() {
        val received = taskRepository.getById(TASK_1_ID)

        assertEquals(received, TASK_1)
    }

    @Test
    fun getAllByOrder() {
        val tasks = taskRepository.getAllByOrder(1, ORDER_1_ID)

        assertThat(tasks, contains(TASK_1, TASK_2))
    }
}