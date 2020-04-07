package edu.born.overseer;

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

    public static final int INVALID_EMPLOYEE_PAYMENT_ID = START_SEQUENCE - 1;

    public static final int EMPLOYEE_PAYMENT_1_ID = START_SEQUENCE + 79;
    public static final int EMPLOYEE_PAYMENT_2_ID = START_SEQUENCE + 80;
    public static final int EMPLOYEE_PAYMENT_3_ID = START_SEQUENCE + 81;
    public static final int EMPLOYEE_PAYMENT_4_ID = START_SEQUENCE + 82;
    public static final int EMPLOYEE_PAYMENT_5_ID = START_SEQUENCE + 83;
    public static final int EMPLOYEE_PAYMENT_6_ID = START_SEQUENCE + 84;
    public static final int EMPLOYEE_PAYMENT_7_ID = START_SEQUENCE + 85;
    public static final int EMPLOYEE_PAYMENT_8_ID = START_SEQUENCE + 86;

    public static final EmployeePayment EMPLOYEE_PAYMENT_1 =
            new EmployeePayment(EMPLOYEE_PAYMENT_1_ID, LocalDate.of(2019, 10, 1),
                    EMPLOYEE_1, COMPANY, COMPANY_1, null, BigDecimal.valueOf(10000.00),
                    true, true, "$$$");
    public static final EmployeePayment EMPLOYEE_PAYMENT_2 =
            new EmployeePayment(EMPLOYEE_PAYMENT_2_ID, LocalDate.of(2019, 10, 1),
                    EMPLOYEE_2, EMPLOYEE, null, EMPLOYEE_1, BigDecimal.valueOf(20000.00),
                    false, false, "Give me %");
    public static final EmployeePayment EMPLOYEE_PAYMENT_3 =
            new EmployeePayment(EMPLOYEE_PAYMENT_3_ID, LocalDate.of(2019, 10, 2),
                    EMPLOYEE_1, EMPLOYEE, null, EMPLOYEE_2, BigDecimal.valueOf(2000.00),
                    false, false, null);
    public static final EmployeePayment EMPLOYEE_PAYMENT_4 =
            new EmployeePayment(EMPLOYEE_PAYMENT_4_ID, LocalDate.of(2019, 10, 2),
                    EMPLOYEE_3, COMPANY, COMPANY_2, null, BigDecimal.valueOf(30000.00),
                    true, false, null);
    public static final EmployeePayment EMPLOYEE_PAYMENT_5 =
            new EmployeePayment(EMPLOYEE_PAYMENT_5_ID, LocalDate.of(2019, 10, 3),
                    EMPLOYEE_4, EMPLOYEE, null, EMPLOYEE_1, BigDecimal.valueOf(5000.00),
                    false, true, "Good Job");
    public static final EmployeePayment EMPLOYEE_PAYMENT_6 =
            new EmployeePayment(EMPLOYEE_PAYMENT_6_ID, LocalDate.of(2019, 10, 3),
                    EMPLOYEE_3, EMPLOYEE, null, EMPLOYEE_5, BigDecimal.valueOf(5000.00),
                    false, true, "$$$");
    public static final EmployeePayment EMPLOYEE_PAYMENT_7 =
            new EmployeePayment(EMPLOYEE_PAYMENT_7_ID, LocalDate.of(2019, 10, 4),
                    EMPLOYEE_4, COMPANY, COMPANY_3, null, BigDecimal.valueOf(7000.00),
                    true, false, null);
    public static final EmployeePayment EMPLOYEE_PAYMENT_8 =
            new EmployeePayment(EMPLOYEE_PAYMENT_8_ID, LocalDate.of(2019, 10, 4),
                    EMPLOYEE_5, COMPANY, COMPANY_3, null, BigDecimal.valueOf(9000.00),
                    true, true, null);

    public static final List<EmployeePayment> EMPLOYEE_1_PAYMENTS = List.of(EMPLOYEE_PAYMENT_1, EMPLOYEE_PAYMENT_3);
    public static final List<EmployeePayment> EMPLOYEE_2_PAYMENTS = List.of(EMPLOYEE_PAYMENT_3);
    public static final List<EmployeePayment> EMPLOYEE_3_PAYMENTS = List.of(EMPLOYEE_PAYMENT_4, EMPLOYEE_PAYMENT_6);
    public static final List<EmployeePayment> EMPLOYEE_4_PAYMENTS = List.of(EMPLOYEE_PAYMENT_5, EMPLOYEE_PAYMENT_7);
    public static final List<EmployeePayment> EMPLOYEE_5_PAYMENTS = List.of(EMPLOYEE_PAYMENT_8);
    public static final List<EmployeePayment> EMPLOYEE_6_PAYMENTS = List.of();

}
