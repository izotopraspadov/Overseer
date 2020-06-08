package edu.born.overseer.data;

import edu.born.overseer.model.Employee;

import java.util.Set;

import static edu.born.overseer.data.EmailTestData.*;
import static edu.born.overseer.data.EmployeePaymentTestData.*;
import static edu.born.overseer.data.PhoneTestData.*;
import static edu.born.overseer.data.RegionTestData.*;
import static edu.born.overseer.data.SalaryTestData.*;
import static edu.born.overseer.model.Role.ROLE_ADMIN;
import static edu.born.overseer.model.Role.ROLE_USER;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class EmployeeTestData {

    public static final int EMPLOYEE_1_ID = START_SEQUENCE + 24;
    public static final int EMPLOYEE_2_ID = START_SEQUENCE + 25;
    public static final int EMPLOYEE_3_ID = START_SEQUENCE + 26;
    public static final int EMPLOYEE_4_ID = START_SEQUENCE + 27;
    public static final int EMPLOYEE_5_ID = START_SEQUENCE + 28;
    public static final int EMPLOYEE_6_ID = START_SEQUENCE + 29;

    public static final String EMPLOYEE_1_LOGIN = "user";
    public static final String INVALID_LOGIN = "unknown";

    public static final Employee EMPLOYEE_1 = new Employee()
            .id(EMPLOYEE_1_ID)
            .fullName("Романов Роман Романович")
            .region(REGION_1)
            .login("user")
            .password("user")
            .address("Заставская ул. 6")
            .roles(Set.of(ROLE_USER));

    public static final Employee EMPLOYEE_2 = new Employee()
            .id(EMPLOYEE_2_ID)
            .fullName("Степанов Степан Степанович")
            .region(REGION_1)
            .login("stepanov")
            .password("user2")
            .address("Четвёртый пр. 10")
            .roles(Set.of(ROLE_USER));

    public static final Employee EMPLOYEE_3 = new Employee()
            .id(EMPLOYEE_3_ID)
            .fullName("Романова Дарья Петровна")
            .region(REGION_3)
            .login("romanova")
            .password("user3")
            .address("Пр. Отчаянных, 90")
            .roles(Set.of(ROLE_USER));

    public static final Employee EMPLOYEE_4 = new Employee()
            .id(EMPLOYEE_4_ID)
            .fullName("Дмитриев Дмитрий Дмитриевич")
            .region(REGION_4)
            .login("dmitriev")
            .password("user4")
            .address("Непростая ул. 7")
            .roles(Set.of(ROLE_USER));

    public static final Employee EMPLOYEE_5 = new Employee()
            .id(EMPLOYEE_5_ID)
            .fullName("Максимов Максим Максимович")
            .region(REGION_8)
            .login("admin")
            .password("admin")
            .address("Пр. Дно. 11")
            .roles(Set.of(ROLE_USER, ROLE_ADMIN));

    public static final Employee EMPLOYEE_6 = new Employee()
            .id(EMPLOYEE_6_ID)
            .fullName("Зайцева Мария Иосифовна")
            .region(REGION_8)
            .login("zaiceva")
            .password("admin2")
            .address("Малая Опечаленная ул. 14")
            .roles(Set.of(ROLE_USER, ROLE_ADMIN));

    // added relationships
    static {
        EMPLOYEE_1.payments(EMPLOYEE_1_PAYMENTS);
        EMPLOYEE_1.salary(EMPLOYEE_1_SALARIES);
        EMPLOYEE_1.phones(EMPLOYEE_1_PHONES);
        EMPLOYEE_1.emails(EMPLOYEE_1_EMAILS);

        EMPLOYEE_2.payments(EMPLOYEE_2_PAYMENTS);
        EMPLOYEE_2.salary(EMPLOYEE_2_SALARIES);
        EMPLOYEE_2.phones(EMPLOYEE_2_PHONES);
        EMPLOYEE_2.emails(EMPLOYEE_2_EMAILS);

        EMPLOYEE_3.payments(EMPLOYEE_3_PAYMENTS);
        EMPLOYEE_3.salary(EMPLOYEE_3_SALARIES);
        EMPLOYEE_3.phones(EMPLOYEE_3_PHONES);
        EMPLOYEE_3.emails(EMPLOYEE_3_EMAILS);

        EMPLOYEE_4.payments(EMPLOYEE_4_PAYMENTS);
        EMPLOYEE_4.salary(EMPLOYEE_4_SALARIES);
        EMPLOYEE_4.phones(EMPLOYEE_4_PHONES);
        EMPLOYEE_4.emails(EMPLOYEE_4_EMAILS);

        EMPLOYEE_5.payments(EMPLOYEE_5_PAYMENTS);
        EMPLOYEE_5.salary(EMPLOYEE_5_SALARIES);
        EMPLOYEE_5.phones(EMPLOYEE_5_PHONES);
        EMPLOYEE_5.emails(EMPLOYEE_5_EMAILS);

        EMPLOYEE_6.payments(EMPLOYEE_6_PAYMENTS);
        EMPLOYEE_6.salary(EMPLOYEE_6_SALARIES);
        EMPLOYEE_6.phones(EMPLOYEE_6_PHONES);
        EMPLOYEE_6.emails(EMPLOYEE_6_EMAILS);
    }

    public static Employee getPreparedCreate() {
        var employee = new Employee()
                .fullName("New Employee")
                .region(REGION_1)
                .login("new_login")
                .password("new_password")
                .address("New Address")
                .roles(Set.of(ROLE_USER));

        employee.setPayments(EmployeePaymentTestData.getPreparedCreateSet(employee));
        employee.setSalary(SalaryTestData.getPreparedCreatedSet(employee));
        employee.setEmails(EmailTestData.getPreparedCreateSet(employee));
        employee.setPhones(PhoneTestData.getPreparedCreatedSet(employee));

        return employee;
    }

    public static Employee getPreparedDuplicate() {

        return getPreparedCreate()
                .login(EMPLOYEE_1.getLogin()); // duplicate
    }

    public static Employee getPreparedUpdate() {

        return new Employee(EMPLOYEE_1)
                .fullName("Updated employee"); // update
    }

}
