package edu.born.overseer.data;

import edu.born.overseer.model.Order;
import edu.born.overseer.model.OrderPayment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.data.CompanyTestData.*;
import static edu.born.overseer.data.OrderTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class OrderPaymentTestData {

    public static final int ORDER_PAYMENT_1_ID = START_SEQUENCE + 107;
    public static final int ORDER_PAYMENT_2_ID = START_SEQUENCE + 108;
    public static final int ORDER_PAYMENT_3_ID = START_SEQUENCE + 109;
    public static final int ORDER_PAYMENT_4_ID = START_SEQUENCE + 110;
    public static final int ORDER_PAYMENT_5_ID = START_SEQUENCE + 111;
    public static final int ORDER_PAYMENT_6_ID = START_SEQUENCE + 112;
    public static final int ORDER_PAYMENT_7_ID = START_SEQUENCE + 113;
    public static final int ORDER_PAYMENT_8_ID = START_SEQUENCE + 114;

    public static final OrderPayment ORDER_PAYMENT_1 = new OrderPayment()
            .id(ORDER_PAYMENT_1_ID)
            .date(LocalDate.of(2019, 9, 11))
            .company(COMPANY_1)
            .order(ORDER_1)
            .ourCompany(COMPANY_1)
            .transaction(BigDecimal.valueOf(100000.00))
            .cashless(true)
            .comment("$$$");

    public static final OrderPayment ORDER_PAYMENT_2 = new OrderPayment()
            .id(ORDER_PAYMENT_2_ID)
            .date(LocalDate.of(2019, 9, 13))
            .company(COMPANY_1)
            .order(ORDER_2)
            .ourCompany(COMPANY_1)
            .transaction(BigDecimal.valueOf(3000.00));

    public static final OrderPayment ORDER_PAYMENT_3 = new OrderPayment()
            .id(ORDER_PAYMENT_3_ID)
            .date(LocalDate.of(2019, 9, 15))
            .company(COMPANY_1)
            .order(ORDER_2)
            .ourCompany(COMPANY_1)
            .transaction(BigDecimal.valueOf(7000.00))
            .comment("%%%");

    public static final OrderPayment ORDER_PAYMENT_4 = new OrderPayment()
            .id(ORDER_PAYMENT_4_ID)
            .date(LocalDate.of(2019, 9, 15))
            .company(COMPANY_2)
            .order(ORDER_3)
            .ourCompany(COMPANY_1)
            .transaction(BigDecimal.valueOf(100000.00))
            .cashless(true);

    public static final OrderPayment ORDER_PAYMENT_5 = new OrderPayment()
            .id(ORDER_PAYMENT_5_ID)
            .date(LocalDate.of(2019, 9, 19))
            .company(COMPANY_2)
            .order(ORDER_3)
            .ourCompany(COMPANY_1)
            .transaction(BigDecimal.valueOf(100000.00))
            .cashless(true);

    public static final OrderPayment ORDER_PAYMENT_6 = new OrderPayment()
            .id(ORDER_PAYMENT_6_ID)
            .date(LocalDate.of(2019, 9, 20))
            .company(COMPANY_3)
            .order(ORDER_4)
            .ourCompany(COMPANY_1)
            .transaction(BigDecimal.valueOf(10000.00));

    public static final OrderPayment ORDER_PAYMENT_7 = new OrderPayment()
            .id(ORDER_PAYMENT_7_ID)
            .date(LocalDate.of(2019, 9, 29))
            .company(COMPANY_3)
            .order(ORDER_4)
            .ourCompany(COMPANY_1)
            .transaction(BigDecimal.valueOf(10000.00))
            .cashless(true)
            .comment("777");

    public static final OrderPayment ORDER_PAYMENT_8 = new OrderPayment()
            .id(ORDER_PAYMENT_8_ID)
            .date(LocalDate.of(2019, 9, 29))
            .company(COMPANY_3)
            .order(ORDER_4)
            .ourCompany(COMPANY_1)
            .transaction(BigDecimal.valueOf(30000.00))
            .comment("777");

    public static final List<OrderPayment> ORDER_1_PAYMENTS = List.of(ORDER_PAYMENT_1);
    public static final List<OrderPayment> ORDER_2_PAYMENTS = List.of(ORDER_PAYMENT_2, ORDER_PAYMENT_3);
    public static final List<OrderPayment> ORDER_3_PAYMENTS = List.of(ORDER_PAYMENT_4, ORDER_PAYMENT_5);
    public static final List<OrderPayment> ORDER_4_PAYMENTS = List.of(ORDER_PAYMENT_6, ORDER_PAYMENT_7, ORDER_PAYMENT_8);

    public static List<OrderPayment> getPreparedCreateList(Order order) {
        return List.of(new OrderPayment()
                .date(LocalDate.now())
                .company(COMPANY_1)
                .order(order)
                .ourCompany(COMPANY_1)
                .transaction(BigDecimal.valueOf(300000.00))
                .comment("big up"));
    }

}