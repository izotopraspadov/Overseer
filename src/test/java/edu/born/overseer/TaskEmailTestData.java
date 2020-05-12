package edu.born.overseer;

import edu.born.overseer.model.Task;
import edu.born.overseer.model.TaskEmail;

import java.util.Set;

import static edu.born.overseer.EmailTestData.*;
import static edu.born.overseer.TaskTestData.*;
import static edu.born.overseer.model.SendType.MANAGER;
import static edu.born.overseer.model.SendType.TEAM_LEADER;

public class TaskEmailTestData {

    public static final Set<TaskEmail> TASK_1_EMAILS = Set.of(
            new TaskEmail().task(TASK_1).email(CONTACT_PERSON_EMAIL_1).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_1).email(EMPLOYEE_EMAIL_1).sendType(MANAGER)
    );

    public static final Set<TaskEmail> TASK_2_EMAILS = Set.of(
            new TaskEmail().task(TASK_2).email(CONTACT_PERSON_EMAIL_2).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_2).email(EMPLOYEE_EMAIL_2).sendType(MANAGER)
    );

    public static final Set<TaskEmail> TASK_3_EMAILS = Set.of(
            new TaskEmail().task(TASK_3).email(CONTACT_PERSON_EMAIL_3).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_3).email(EMPLOYEE_EMAIL_3).sendType(MANAGER)
    );

    public static final Set<TaskEmail> TASK_4_EMAILS = Set.of(
            new TaskEmail().task(TASK_4).email(CONTACT_PERSON_EMAIL_4).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_4).email(EMPLOYEE_EMAIL_4).sendType(MANAGER)
    );

    public static final Set<TaskEmail> TASK_5_EMAILS = Set.of(
            new TaskEmail().task(TASK_5).email(CONTACT_PERSON_EMAIL_5).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_5).email(CONTACT_PERSON_EMAIL_6).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_5).email(CONTACT_PERSON_EMAIL_10).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_5).email(EMPLOYEE_EMAIL_5).sendType(MANAGER)
    );

    public static final Set<TaskEmail> TASK_6_EMAILS = Set.of(
            new TaskEmail().task(TASK_6).email(EMPLOYEE_EMAIL_6).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_6).email(EMPLOYEE_EMAIL_7).sendType(MANAGER),
            new TaskEmail().task(TASK_6).email(CONTACT_PERSON_EMAIL_11).sendType(MANAGER),
            new TaskEmail().task(TASK_6).email(CONTACT_PERSON_EMAIL_12).sendType(MANAGER)
    );

    public static final Set<TaskEmail> TASK_7_EMAILS = Set.of(
            new TaskEmail().task(TASK_7).email(CONTACT_PERSON_EMAIL_1).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_7).email(CONTACT_PERSON_EMAIL_2).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_7).email(CONTACT_PERSON_EMAIL_3).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_7).email(CONTACT_PERSON_EMAIL_4).sendType(MANAGER),
            new TaskEmail().task(TASK_7).email(CONTACT_PERSON_EMAIL_5).sendType(MANAGER),
            new TaskEmail().task(TASK_7).email(CONTACT_PERSON_EMAIL_6).sendType(MANAGER)
    );

    public static final Set<TaskEmail> TASK_8_EMAILS = Set.of(
            new TaskEmail().task(TASK_8).email(EMPLOYEE_EMAIL_1).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_8).email(EMPLOYEE_EMAIL_2).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_8).email(EMPLOYEE_EMAIL_3).sendType(TEAM_LEADER),
            new TaskEmail().task(TASK_8).email(EMPLOYEE_EMAIL_4).sendType(MANAGER),
            new TaskEmail().task(TASK_8).email(EMPLOYEE_EMAIL_5).sendType(MANAGER)
    );

    public static Set<TaskEmail> getPreparedCreateSet(Task task) {
        return Set.of(new TaskEmail().task(task).email(EMPLOYEE_EMAIL_6).sendType(TEAM_LEADER));
    }

}
