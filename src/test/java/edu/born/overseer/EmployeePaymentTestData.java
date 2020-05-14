package edu.born.overseer;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.EmployeePayment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.CompanyTestData.*;
import static edu.born.overseer.EmployeeTestData.*;
import static edu.born.overseer.model.CounterpartyType.COMPANY;
import static edu.born.overseer.model.CounterpartyType.EMPLOYEE;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class EmployeePaymentTestData {

    public static final int EMPLOYEE_PAYMENT_1_ID = START_SEQUENCE + 79;
    public static final int EMPLOYEE_PAYMENT_2_ID = START_SEQUENCE + 80;
    public static final int EMPLOYEE_PAYMENT_3_ID = START_SEQUENCE + 81;
    public static final int EMPLOYEE_PAYMENT_4_ID = START_SEQUENCE + 82;
    public static final int EMPLOYEE_PAYMENT_5_ID = START_SEQUENCE + 83;
    public static final int EMPLOYEE_PAYMENT_6_ID = START_SEQUENCE + 84;
    public static final int EMPLOYEE_PAYMENT_7_ID = START_SEQUENCE + 85;
    public static final int EMPLOYEE_PAYMENT_8_ID = START_SEQUENCE + 86;

    public static final EmployeePayment EMPLOYEE_PAYMENT_1 = new EmployeePayment()
            .id(EMPLOYEE_PAYMENT_1_ID)
            .date(LocalDate.of(2019, 10, 1))
            .transaction(BigDecimal.valueOf(10000.00))
            .cashless(true)
            .charge(true)
            .employee(EMPLOYEE_1)
            .counterpartyType(COMPANY)
            .companyCounterparty(COMPANY_1)
            .comment("$$$");

    public static final EmployeePayment EMPLOYEE_PAYMENT_2 = new EmployeePayment()
            .id(EMPLOYEE_PAYMENT_2_ID)
            .date(LocalDate.of(2019, 10, 1))
            .transaction(BigDecimal.valueOf(20000.00))
            .employee(EMPLOYEE_2)
            .counterpartyType(EMPLOYEE)
            .employeeCounterparty(EMPLOYEE_1)
            .comment("Give me %");

    public static final EmployeePayment EMPLOYEE_PAYMENT_3 = new EmployeePayment()
            .id(EMPLOYEE_PAYMENT_3_ID)
            .date(LocalDate.of(2019, 10, 2))
            .transaction(BigDecimal.valueOf(2000.00))
            .employee(EMPLOYEE_1)
            .counterpartyType(EMPLOYEE)
            .employeeCounterparty(EMPLOYEE_2);

    public static final EmployeePayment EMPLOYEE_PAYMENT_4 = new EmployeePayment()
            .id(EMPLOYEE_PAYMENT_4_ID)
            .date(LocalDate.of(2019, 10, 2))
            .transaction(BigDecimal.valueOf(30000.00))
            .cashless(true)
            .employee(EMPLOYEE_3)
            .counterpartyType(COMPANY)
            .companyCounterparty(COMPANY_2);

    public static final EmployeePayment EMPLOYEE_PAYMENT_5 = new EmployeePayment()
            .id(EMPLOYEE_PAYMENT_5_ID)
            .date(LocalDate.of(2019, 10, 3))
            .transaction(BigDecimal.valueOf(5000.00))
            .charge(true)
            .employee(EMPLOYEE_4)
            .counterpartyType(EMPLOYEE)
            .employeeCounterparty(EMPLOYEE_1)
            .comment("Good Job");

    public static final EmployeePayment EMPLOYEE_PAYMENT_6 = new EmployeePayment()
            .id(EMPLOYEE_PAYMENT_6_ID)
            .date(LocalDate.of(2019, 10, 3))
            .transaction(BigDecimal.valueOf(5000.00))
            .charge(true)
            .employee(EMPLOYEE_3)
            .counterpartyType(EMPLOYEE)
            .employeeCounterparty(EMPLOYEE_5)
            .comment("$$$");

    public static final EmployeePayment EMPLOYEE_PAYMENT_7 = new EmployeePayment()
            .id(EMPLOYEE_PAYMENT_7_ID)
            .date(LocalDate.of(2019, 10, 4))
            .transaction(BigDecimal.valueOf(10000.00))
            .cashless(true)
            .employee(EMPLOYEE_4)
            .counterpartyType(COMPANY)
            .companyCounterparty(COMPANY_3);

    public static final EmployeePayment EMPLOYEE_PAYMENT_8 = new EmployeePayment()
            .id(EMPLOYEE_PAYMENT_8_ID)
            .date(LocalDate.of(2019, 10, 4))
            .transaction(BigDecimal.valueOf(9000.00))
            .cashless(true)
            .charge(true)
            .employee(EMPLOYEE_5)
            .counterpartyType(COMPANY)
            .companyCounterparty(COMPANY_3);

    public static final List<EmployeePayment> EMPLOYEE_1_PAYMENTS = List.of(EMPLOYEE_PAYMENT_1, EMPLOYEE_PAYMENT_3);
    public static final List<EmployeePayment> EMPLOYEE_2_PAYMENTS = List.of(EMPLOYEE_PAYMENT_3);
    public static final List<EmployeePayment> EMPLOYEE_3_PAYMENTS = List.of(EMPLOYEE_PAYMENT_4, EMPLOYEE_PAYMENT_6);
    public static final List<EmployeePayment> EMPLOYEE_4_PAYMENTS = List.of(EMPLOYEE_PAYMENT_5, EMPLOYEE_PAYMENT_7);
    public static final List<EmployeePayment> EMPLOYEE_5_PAYMENTS = List.of(EMPLOYEE_PAYMENT_8);
    public static final List<EmployeePayment> EMPLOYEE_6_PAYMENTS = List.of();

    public static List<EmployeePayment> getPreparedCreateList(Employee employee) {
        return List.of(new EmployeePayment()
                .transaction(BigDecimal.valueOf(100000.00))
                .charge(true)
                .employee(EMPLOYEE_1)
                .counterpartyType(COMPANY)
                .companyCounterparty(COMPANY_1));
    }

}
