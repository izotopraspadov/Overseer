package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static edu.born.overseer.TestUtil.unlimitedPageLength;
import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_1_ID;
import static edu.born.overseer.data.OrderTestData.ORDER_1_ID;
import static edu.born.overseer.data.TaskTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void create() {
        var prepared = getPreparedCreate();
        var savedId = taskRepository.save(prepared, ORDER_1_ID, EMPLOYEE_1_ID).getId();
        prepared.setId(savedId);

        assertEquals(taskRepository.getById(savedId), prepared);
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();
        var updated = taskRepository.save(prepared, ORDER_1_ID, EMPLOYEE_1_ID);
        assertEquals(updated, prepared);
    }

    @Test
    void delete() {
        taskRepository.delete(TASK_1_ID);
        assertThat(taskRepository.getAllByOrder(ORDER_1_ID, unlimitedPageLength()), not(contains(TASK_1)));
    }

    @Test
    void getById() {
        var received = taskRepository.getById(TASK_1_ID);
        assertEquals(received, TASK_1);
    }

    @Test
    void getAllByOrder() {
        assertThat(taskRepository.getAllByOrder(ORDER_1_ID, unlimitedPageLength()), contains(TASK_1, TASK_2));
    }

}