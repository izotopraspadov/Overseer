package edu.born.overseer.data;

import edu.born.overseer.model.Order;
import edu.born.overseer.model.Task;

import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.data.EmployeeTestData.*;
import static edu.born.overseer.data.OrderTestData.*;
import static edu.born.overseer.model.ResultType.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class TaskTestData {

    public static final int TASK_1_ID = START_SEQUENCE + 123;
    public static final int TASK_2_ID = START_SEQUENCE + 124;
    public static final int TASK_3_ID = START_SEQUENCE + 125;
    public static final int TASK_4_ID = START_SEQUENCE + 126;
    public static final int TASK_5_ID = START_SEQUENCE + 127;
    public static final int TASK_6_ID = START_SEQUENCE + 128;
    public static final int TASK_7_ID = START_SEQUENCE + 129;
    public static final int TASK_8_ID = START_SEQUENCE + 130;

    public static final Task TASK_1 = new Task()
            .id(TASK_1_ID)
            .order(ORDER_1)
            .description("Step 1st")
            .responsible(EMPLOYEE_3)
            .dateCompleted(LocalDate.of(2019, 9, 20))
            .resultType(PARTIALLY_COMPLETED)
            .comment("Step 1st done!");

    // TaskEmail will assign itself when created

    public static final Task TASK_2 = new Task()
            .id(TASK_2_ID)
            .order(ORDER_1)
            .description("Step 2st")
            .responsible(EMPLOYEE_4)
            .dateCompleted(LocalDate.of(2019, 9, 24))
            .resultType(PARTIALLY_COMPLETED)
            .comment("Step 2st done!");

    public static final Task TASK_3 = new Task()
            .id(TASK_3_ID)
            .order(ORDER_2)
            .description("Estimate 001")
            .responsible(EMPLOYEE_3)
            .dateCompleted(LocalDate.of(2019, 9, 25))
            .resultType(COMPLETED);

    public static final Task TASK_4 = new Task()
            .id(TASK_4_ID)
            .order(ORDER_3)
            .description("001 Second Pr.")
            .responsible(EMPLOYEE_6)
            .dateCompleted(LocalDate.of(2019, 9, 15))
            .resultType(NOT_COMPLETED);

    public static final Task TASK_5 = new Task()
            .id(TASK_5_ID)
            .order(ORDER_3)
            .description("002 Second Pr.")
            .responsible(EMPLOYEE_5)
            .dateCompleted(LocalDate.of(2019, 9, 21))
            .resultType(PARTIALLY_COMPLETED);

    public static final Task TASK_6 = new Task()
            .id(TASK_6_ID)
            .order(ORDER_3)
            .description("003 Second Pr.")
            .responsible(EMPLOYEE_6)
            .dateCompleted(LocalDate.of(2019, 9, 26))
            .resultType(COMPLETED);

    public static final Task TASK_7 = new Task()
            .id(TASK_7_ID)
            .order(ORDER_4)
            .description("Legal Service By Customer 01")
            .responsible(EMPLOYEE_4)
            .dateCompleted(LocalDate.of(2019, 9, 13))
            .resultType(NOT_COMPLETED)
            .comment("Not Done!");

    public static final Task TASK_8 = new Task()
            .id(TASK_8_ID)
            .order(ORDER_4)
            .description("Legal Service By Customer 02")
            .responsible(EMPLOYEE_4)
            .dateCompleted(LocalDate.of(2019, 9, 29))
            .resultType(COMPLETED)
            .comment("Done!");

    public static final List<Task> ORDER_1_TASKS = List.of(TASK_1);
    public static final List<Task> ORDER_2_TASKS = List.of(TASK_3);
    public static final List<Task> ORDER_3_TASKS = List.of(TASK_4, TASK_5, TASK_6);
    public static final List<Task> ORDER_4_TASKS = List.of(TASK_7, TASK_8);

    public static List<Task> getPreparedCreateSet(Order order) {
        var task = new Task()
                .order(order)
                .description("Created Task")
                .responsible(EMPLOYEE_1)
                .dateCompleted(LocalDate.now())
                .resultType(COMPLETED);

        task.setEmails(TaskEmailTestData.getPreparedCreateSet(task));

        return List.of(task);
    }

}
