package edu.born.overseer;

import edu.born.overseer.model.Order;
import edu.born.overseer.model.Task;

import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.EmployeeTestData.*;
import static edu.born.overseer.OrderTestData.*;
import static edu.born.overseer.TaskEmailTestData.*;
import static edu.born.overseer.model.ResultType.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class TaskTestData {

    public static final int INVALID_TASK_ID = START_SEQUENCE - 1;

    public static final int TASK_1_ID = START_SEQUENCE + 123;
    public static final int TASK_2_ID = START_SEQUENCE + 124;
    public static final int TASK_3_ID = START_SEQUENCE + 125;
    public static final int TASK_4_ID = START_SEQUENCE + 126;
    public static final int TASK_5_ID = START_SEQUENCE + 127;
    public static final int TASK_6_ID = START_SEQUENCE + 128;
    public static final int TASK_7_ID = START_SEQUENCE + 129;
    public static final int TASK_8_ID = START_SEQUENCE + 130;

    public static final Task TASK_1 =
            new Task(TASK_1_ID, ORDER_1, "Step 1st", EMPLOYEE_3, LocalDate.of(2019, 9, 20),
                    PARTIALLY_COMPLETED, "Step 1st done!", TASK_1_EMAILS);
    public static final Task TASK_2 =
            new Task(TASK_2_ID, ORDER_1, "Step 2st", EMPLOYEE_4, LocalDate.of(2019, 9, 24),
                    PARTIALLY_COMPLETED, "Step 2st done!", TASK_2_EMAILS);
    public static final Task TASK_3 =
            new Task(TASK_3_ID, ORDER_2, "Estimate 001", EMPLOYEE_3, LocalDate.of(2019, 9, 25),
                    COMPLETED, null, TASK_3_EMAILS);
    public static final Task TASK_4 =
            new Task(TASK_4_ID, ORDER_3, "001 Second Pr.", EMPLOYEE_6, LocalDate.of(2019, 9, 15),
                    NOT_COMPLETED, null, TASK_4_EMAILS);
    public static final Task TASK_5 =
            new Task(TASK_5_ID, ORDER_3, "002 Second Pr.", EMPLOYEE_5, LocalDate.of(2019, 9, 21),
                    PARTIALLY_COMPLETED, null, TASK_5_EMAILS);
    public static final Task TASK_6 =
            new Task(TASK_6_ID, ORDER_3, "003 Second Pr.", EMPLOYEE_6, LocalDate.of(2019, 9, 26),
                    COMPLETED, null, TASK_6_EMAILS);
    public static final Task TASK_7 =
            new Task(TASK_7_ID, ORDER_4, "Legal Service By Customer 01", EMPLOYEE_4, LocalDate.of(2019, 9, 13),
                    NOT_COMPLETED, "Not Done!", TASK_7_EMAILS);
    public static final Task TASK_8 =
            new Task(TASK_8_ID, ORDER_4, "Legal Service By Customer 02", EMPLOYEE_4, LocalDate.of(2019, 9, 29),
                    COMPLETED, "Done!", TASK_8_EMAILS);

    public static final List<Task> ORDER_1_TASKS = List.of(TASK_1, TASK_2);
    public static final List<Task> ORDER_2_TASKS = List.of(TASK_3);
    public static final List<Task> ORDER_3_TASKS = List.of(TASK_4, TASK_5, TASK_6);
    public static final List<Task> ORDER_4_TASKS = List.of(TASK_7, TASK_8);

    public static List<Task> getPreparedCreateSet(Order order) {
        var task = new Task(null, order, "Created Task", EMPLOYEE_1, LocalDate.now(), COMPLETED, null);
        task.setTaskEmails(TaskEmailTestData.getPreparedCreateSet(task));
        return List.of(task);
    }

}
