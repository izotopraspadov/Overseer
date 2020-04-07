package edu.born.overseer;

import edu.born.overseer.model.Employee;

import java.util.Set;

import static edu.born.overseer.EmailTestData.*;
import static edu.born.overseer.EmployeePaymentTestData.*;
import static edu.born.overseer.PhoneTestData.*;
import static edu.born.overseer.RegionTestData.*;
import static edu.born.overseer.SalaryTestData.*;
import static edu.born.overseer.model.Role.ADMIN;
import static edu.born.overseer.model.Role.USER;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class EmployeeTestData {

    public static final int INVALID_EMPLOYEE_ID = START_SEQUENCE - 1;

    public static final int EMPLOYEE_1_ID = START_SEQUENCE + 24;
    public static final int EMPLOYEE_2_ID = START_SEQUENCE + 25;
    public static final int EMPLOYEE_3_ID = START_SEQUENCE + 26;
    public static final int EMPLOYEE_4_ID = START_SEQUENCE + 27;
    public static final int EMPLOYEE_5_ID = START_SEQUENCE + 28;
    public static final int EMPLOYEE_6_ID = START_SEQUENCE + 29;

    public static final Employee EMPLOYEE_1 =
            new Employee(EMPLOYEE_1_ID, "Романов Роман Романович", REGION_1, "user", "user", "Заставская ул. 6",
                    Set.of(USER), EMPLOYEE_1_PAYMENTS, EMPLOYEE_1_SALARIES, EMPLOYEE_1_PHONES, EMPLOYEE_1_EMAILS);
    public static final Employee EMPLOYEE_2 =
            new Employee(EMPLOYEE_2_ID, "Степанов Степан Степанови", REGION_1, "stepanov", "user2", "Четвёртый пр. 10",
                    Set.of(USER), EMPLOYEE_2_PAYMENTS, EMPLOYEE_2_SALARIES, EMPLOYEE_2_PHONES, EMPLOYEE_2_EMAILS);
    public static final Employee EMPLOYEE_3 =
            new Employee(EMPLOYEE_3_ID, "Романова Дарья Петровна", REGION_3, "romanova", "user3", "Пр. Отчаянных, 90",
                    Set.of(USER), EMPLOYEE_3_PAYMENTS, EMPLOYEE_3_SALARIES, EMPLOYEE_3_PHONES, EMPLOYEE_3_EMAILS);
    public static final Employee EMPLOYEE_4 =
            new Employee(EMPLOYEE_4_ID, "Дмитриев Дмитрий Дмитриевич", REGION_4, "dmitriev", "user4", "Непростая ул. 7",
                    Set.of(USER), EMPLOYEE_4_PAYMENTS, EMPLOYEE_4_SALARIES, EMPLOYEE_4_PHONES, EMPLOYEE_4_EMAILS);
    public static final Employee EMPLOYEE_5 =
            new Employee(EMPLOYEE_5_ID, "Максимов Максим Максимович", REGION_8, "admin", "admin", "Пр. Дно. 11",
                    Set.of(USER, ADMIN), EMPLOYEE_5_PAYMENTS, EMPLOYEE_5_SALARIES, EMPLOYEE_5_PHONES, EMPLOYEE_5_EMAILS);
    public static final Employee EMPLOYEE_6 =
            new Employee(EMPLOYEE_6_ID, "Зайцева Мария Иосифовн", REGION_8, "zaiceva", "admi2", "Малая Опечаленная ул. 14",
                    Set.of(USER, ADMIN), EMPLOYEE_6_PAYMENTS, EMPLOYEE_6_SALARIES, EMPLOYEE_6_PHONES, EMPLOYEE_6_EMAILS);

}
