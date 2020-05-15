package edu.born.overseer.data;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Salary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Set;

import static edu.born.overseer.data.EmployeeTestData.*;
import static edu.born.overseer.data.TestDataUtil.NEXT_ID;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class SalaryTestData {

    public static final int SALARY_1_ID = START_SEQUENCE + 87;
    public static final int SALARY_2_ID = START_SEQUENCE + 88;
    public static final int SALARY_3_ID = START_SEQUENCE + 89;
    public static final int SALARY_4_ID = START_SEQUENCE + 90;
    public static final int SALARY_5_ID = START_SEQUENCE + 91;
    public static final int SALARY_6_ID = START_SEQUENCE + 92;
    public static final int SALARY_7_ID = START_SEQUENCE + 93;

    public static final Salary SALARY_1 = new Salary()
            .id(SALARY_1_ID)
            .employee(EMPLOYEE_1)
            .startDate(LocalDate.of(2019, 9, 1))
            .endDate(LocalDate.of(2019, 10, 1))
            .amount(BigDecimal.valueOf(35000.00)
                    .setScale(2, RoundingMode.DOWN)
            );

    public static final Salary SALARY_2 = new Salary()
            .id(SALARY_2_ID)
            .employee(EMPLOYEE_2)
            .startDate(LocalDate.of(2019, 9, 3))
            .amount(BigDecimal.valueOf(30000.00)
                    .setScale(2, RoundingMode.DOWN)
            );

    public static final Salary SALARY_3 = new Salary()
            .id(SALARY_3_ID)
            .employee(EMPLOYEE_3)
            .startDate(LocalDate.of(2019, 9, 5))
            .amount(BigDecimal.valueOf(29000.00)
                    .setScale(2, RoundingMode.DOWN)
            );

    public static final Salary SALARY_4 = new Salary()
            .id(SALARY_4_ID)
            .employee(EMPLOYEE_4)
            .startDate(LocalDate.of(2019, 9, 7))
            .amount(BigDecimal.valueOf(40000.00)
                    .setScale(2, RoundingMode.DOWN)
            );

    public static final Salary SALARY_5 = new Salary()
            .id(SALARY_5_ID)
            .employee(EMPLOYEE_5)
            .startDate(LocalDate.of(2019, 9, 7))
            .amount(BigDecimal.valueOf(50000.00)
                    .setScale(2, RoundingMode.DOWN)
            );

    public static final Salary SALARY_6 = new Salary()
            .id(SALARY_6_ID)
            .employee(EMPLOYEE_6)
            .startDate(LocalDate.of(2019, 9, 7))
            .amount(BigDecimal.valueOf(33000.00)
                    .setScale(2, RoundingMode.DOWN)
            );

    public static final Salary SALARY_7 = new Salary()
            .id(SALARY_7_ID)
            .employee(EMPLOYEE_1)
            .startDate(LocalDate.of(2019, 10, 1))
            .amount(BigDecimal.valueOf(45000.00)
                    .setScale(2, RoundingMode.DOWN)
            );

    public static final Salary NEXT_SALARY = new Salary()
            .id(NEXT_ID)
            .employee(EMPLOYEE_1)
            .startDate(LocalDate.now())
            .amount(BigDecimal.valueOf(44000.00)
                    .setScale(2, RoundingMode.DOWN)
            );

    public static final Set<Salary> EMPLOYEE_1_SALARIES = Set.of(SALARY_7, SALARY_1);
    public static final Set<Salary> EMPLOYEE_2_SALARIES = Set.of(SALARY_2);
    public static final Set<Salary> EMPLOYEE_3_SALARIES = Set.of(SALARY_3);
    public static final Set<Salary> EMPLOYEE_4_SALARIES = Set.of(SALARY_4);
    public static final Set<Salary> EMPLOYEE_5_SALARIES = Set.of(SALARY_5);
    public static final Set<Salary> EMPLOYEE_6_SALARIES = Set.of(SALARY_6);

    public static Salary getPreparedCreate() {

        return new Salary()
                .employee(EMPLOYEE_1)
                .startDate(LocalDate.now())
                .amount(BigDecimal.valueOf(44000.00));
    }

    public static Salary getPreparedDuplicate() {

        return new Salary()
                .employee(EMPLOYEE_1) // 1 duplicate
                .startDate(LocalDate.of(2019, 11, 1)) // 2 duplicate
                .amount(BigDecimal.valueOf(44000.00));
    }

    public static Salary getPreparedUpdate() {

        return new Salary(SALARY_7)
                .amount(BigDecimal.valueOf(46000.00)); // update
    }

    public static Set<Salary> getPreparedCreatedSet(Employee employee) {

        return Set.of(new Salary()
                .employee(employee)
                .startDate(LocalDate.of(2019, 12, 1))
                .amount(BigDecimal.valueOf(44000.00))
        );
    }

}
