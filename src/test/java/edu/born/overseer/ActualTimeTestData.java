package edu.born.overseer;

import edu.born.overseer.model.ActualTime;
import edu.born.overseer.model.Order;

import java.time.LocalDate;
import java.util.Set;

import static edu.born.overseer.EmployeeTestData.*;
import static edu.born.overseer.OrderTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class ActualTimeTestData {

    public static final int ACTUAL_TIME_1_ID = START_SEQUENCE + 119;
    public static final int ACTUAL_TIME_2_ID = START_SEQUENCE + 120;
    public static final int ACTUAL_TIME_3_ID = START_SEQUENCE + 121;
    public static final int ACTUAL_TIME_4_ID = START_SEQUENCE + 122;

    public static final ActualTime ACTUAL_TIME_1 = new ActualTime()
            .id(ACTUAL_TIME_1_ID)
            .order(ORDER_1)
            .employee(EMPLOYEE_6)
            .date(LocalDate.of(2019, 9, 20))
            .actualManHours(15)
            .accountManHours(30);

    public static final ActualTime ACTUAL_TIME_2 = new ActualTime()
            .id(ACTUAL_TIME_2_ID)
            .order(ORDER_2)
            .employee(EMPLOYEE_5)
            .date(LocalDate.of(2019, 9, 25))
            .actualManHours(7)
            .accountManHours(10);

    public static final ActualTime ACTUAL_TIME_3 = new ActualTime()
            .id(ACTUAL_TIME_3_ID)
            .order(ORDER_3)
            .employee(EMPLOYEE_2)
            .date(LocalDate.of(2019, 9, 23))
            .actualManHours(35)
            .accountManHours(40);

    public static final ActualTime ACTUAL_TIME_4 = new ActualTime()
            .id(ACTUAL_TIME_4_ID)
            .order(ORDER_4)
            .employee(EMPLOYEE_1)
            .date(LocalDate.of(2019, 10, 1))
            .actualManHours(13)
            .accountManHours(15);

    public static final Set<ActualTime> ORDER_1_ACTUAL_TIME = Set.of(ACTUAL_TIME_1);
    public static final Set<ActualTime> ORDER_2_ACTUAL_TIME = Set.of(ACTUAL_TIME_2);
    public static final Set<ActualTime> ORDER_3_ACTUAL_TIME = Set.of(ACTUAL_TIME_3);
    public static final Set<ActualTime> ORDER_4_ACTUAL_TIME = Set.of(ACTUAL_TIME_4);

    public static Set<ActualTime> getPreparedCreateSet(Order order) {

        return Set.of(new ActualTime()
                .order(order)
                .employee(EMPLOYEE_1)
                .date(LocalDate.of(2019, 11, 1))
                .actualManHours(0)
                .accountManHours(100)
        );
    }

    public static Set<ActualTime> getPreparedUpdateSet() {

        return Set.of(new ActualTime(ACTUAL_TIME_1)
                .actualManHours(20)
        );
    }

}
