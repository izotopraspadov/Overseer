package edu.born.overseer;

import edu.born.overseer.model.Task;
import edu.born.overseer.model.TaskEmail;

import java.util.Set;

import static edu.born.overseer.EmailTestData.*;
import static edu.born.overseer.TaskTestData.*;
import static edu.born.overseer.model.SendType.MANAGER;
import static edu.born.overseer.model.SendType.TEAM_LEADER;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class TaskEmailTestData {

    public static final int INVALID_TASK_EMAIL_ID = START_SEQUENCE - 1;

    public static final Set<TaskEmail> TASK_1_EMAILS = Set.of(
            new TaskEmail(TASK_1, CONTACT_PERSON_EMAIL_1, TEAM_LEADER),
            new TaskEmail(TASK_1, EMPLOYEE_EMAIL_1, MANAGER)
    );
    public static final Set<TaskEmail> TASK_2_EMAILS = Set.of(
            new TaskEmail(TASK_2, CONTACT_PERSON_EMAIL_2, TEAM_LEADER),
            new TaskEmail(TASK_2, EMPLOYEE_EMAIL_2, MANAGER)
    );
    public static final Set<TaskEmail> TASK_3_EMAILS = Set.of(
            new TaskEmail(TASK_3, CONTACT_PERSON_EMAIL_3, TEAM_LEADER),
            new TaskEmail(TASK_3, EMPLOYEE_EMAIL_3, MANAGER)
    );
    public static final Set<TaskEmail> TASK_4_EMAILS = Set.of(
            new TaskEmail(TASK_4, CONTACT_PERSON_EMAIL_4, TEAM_LEADER),
            new TaskEmail(TASK_4, EMPLOYEE_EMAIL_4, MANAGER)
    );
    public static final Set<TaskEmail> TASK_5_EMAILS = Set.of(
            new TaskEmail(TASK_5, CONTACT_PERSON_EMAIL_5, TEAM_LEADER),
            new TaskEmail(TASK_5, CONTACT_PERSON_EMAIL_6, TEAM_LEADER),
            new TaskEmail(TASK_5, CONTACT_PERSON_EMAIL_10, TEAM_LEADER),
            new TaskEmail(TASK_5, EMPLOYEE_EMAIL_5, MANAGER)
    );
    public static final Set<TaskEmail> TASK_6_EMAILS = Set.of(
            new TaskEmail(TASK_6, EMPLOYEE_EMAIL_6, TEAM_LEADER),
            new TaskEmail(TASK_6, EMPLOYEE_EMAIL_7, MANAGER),
            new TaskEmail(TASK_6, CONTACT_PERSON_EMAIL_11, MANAGER),
            new TaskEmail(TASK_6, CONTACT_PERSON_EMAIL_12, MANAGER)
    );
    public static final Set<TaskEmail> TASK_7_EMAILS = Set.of(
            new TaskEmail(TASK_7, CONTACT_PERSON_EMAIL_1, TEAM_LEADER),
            new TaskEmail(TASK_7, CONTACT_PERSON_EMAIL_2, TEAM_LEADER),
            new TaskEmail(TASK_7, CONTACT_PERSON_EMAIL_3, TEAM_LEADER),
            new TaskEmail(TASK_7, CONTACT_PERSON_EMAIL_4, MANAGER),
            new TaskEmail(TASK_7, CONTACT_PERSON_EMAIL_5, MANAGER),
            new TaskEmail(TASK_7, CONTACT_PERSON_EMAIL_6, MANAGER)
    );
    public static final Set<TaskEmail> TASK_8_EMAILS = Set.of(
            new TaskEmail(TASK_8, EMPLOYEE_EMAIL_1, TEAM_LEADER),
            new TaskEmail(TASK_8, EMPLOYEE_EMAIL_2, TEAM_LEADER),
            new TaskEmail(TASK_8, EMPLOYEE_EMAIL_3, TEAM_LEADER),
            new TaskEmail(TASK_8, EMPLOYEE_EMAIL_4, MANAGER),
            new TaskEmail(TASK_8, EMPLOYEE_EMAIL_5, MANAGER)
    );

    public static Set<TaskEmail> getPreparedCreateSet(Task task) {
        return Set.of(new TaskEmail(task, EMPLOYEE_EMAIL_6, TEAM_LEADER));
    }

}
