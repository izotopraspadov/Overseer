package edu.born.overseer;

import edu.born.overseer.model.Salary;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static edu.born.overseer.EmployeeTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class SalaryTestData {

    public static final int INVALID_SALARY_ID = START_SEQUENCE - 1;

    public static final int SALARY_1_ID = START_SEQUENCE + 87;
    public static final int SALARY_2_ID = START_SEQUENCE + 88;
    public static final int SALARY_3_ID = START_SEQUENCE + 89;
    public static final int SALARY_4_ID = START_SEQUENCE + 90;
    public static final int SALARY_5_ID = START_SEQUENCE + 91;
    public static final int SALARY_6_ID = START_SEQUENCE + 92;
    public static final int SALARY_7_ID = START_SEQUENCE + 93;

    public static final Salary SALARY_1 =
            new Salary(SALARY_1_ID, EMPLOYEE_1, LocalDate.of(2019, 9, 1),
                    LocalDate.of(2019, 10, 1), BigDecimal.valueOf(35000.00));
    public static final Salary SALARY_2 =
            new Salary(SALARY_2_ID, EMPLOYEE_2, LocalDate.of(2019, 9, 3),
                    null, BigDecimal.valueOf(30000.00));
    public static final Salary SALARY_3 =
            new Salary(SALARY_3_ID, EMPLOYEE_3, LocalDate.of(2019, 9, 5),
                    null, BigDecimal.valueOf(29000.00));
    public static final Salary SALARY_4 =
            new Salary(SALARY_4_ID, EMPLOYEE_4, LocalDate.of(2019, 9, 7),
                    null, BigDecimal.valueOf(40000.00));
    public static final Salary SALARY_5 =
            new Salary(SALARY_5_ID, EMPLOYEE_5, LocalDate.of(2019, 9, 7),
                    null, BigDecimal.valueOf(50000.00));
    public static final Salary SALARY_6 =
            new Salary(SALARY_6_ID, EMPLOYEE_6, LocalDate.of(2019, 9, 7),
                    null, BigDecimal.valueOf(33000.00));
    public static final Salary SALARY_7 =
            new Salary(SALARY_7_ID, EMPLOYEE_1, LocalDate.of(2019, 10, 1),
                    null, BigDecimal.valueOf(45000.00));

    public static final Set<Salary> EMPLOYEE_1_SALARIES = Set.of(SALARY_1, SALARY_7);
    public static final Set<Salary> EMPLOYEE_2_SALARIES = Set.of(SALARY_2);
    public static final Set<Salary> EMPLOYEE_3_SALARIES = Set.of(SALARY_3);
    public static final Set<Salary> EMPLOYEE_4_SALARIES = Set.of(SALARY_4);
    public static final Set<Salary> EMPLOYEE_5_SALARIES = Set.of(SALARY_5);
    public static final Set<Salary> EMPLOYEE_6_SALARIES = Set.of(SALARY_6);

}
