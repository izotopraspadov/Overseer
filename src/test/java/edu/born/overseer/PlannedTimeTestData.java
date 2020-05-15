package edu.born.overseer;

import edu.born.overseer.model.Order;
import edu.born.overseer.model.PlannedTime;

import java.util.Set;

import static edu.born.overseer.EmployeeTestData.*;
import static edu.born.overseer.OrderTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class PlannedTimeTestData {

    public static final int PLANNED_TIME_1_ID = START_SEQUENCE + 115;
    public static final int PLANNED_TIME_2_ID = START_SEQUENCE + 116;
    public static final int PLANNED_TIME_3_ID = START_SEQUENCE + 117;
    public static final int PLANNED_TIME_4_ID = START_SEQUENCE + 118;

    public static final PlannedTime PLANNED_TIME_1 = new PlannedTime()
            .id(PLANNED_TIME_1_ID)
            .order(ORDER_1)
            .employee(EMPLOYEE_6)
            .manHours(30);

    public static final PlannedTime PLANNED_TIME_2 = new PlannedTime()
            .id(PLANNED_TIME_2_ID)
            .order(ORDER_2)
            .employee(EMPLOYEE_5)
            .manHours(10);

    public static final PlannedTime PLANNED_TIME_3 = new PlannedTime()
            .id(PLANNED_TIME_3_ID)
            .order(ORDER_3)
            .employee(EMPLOYEE_2)
            .manHours(40);

    public static final PlannedTime PLANNED_TIME_4 = new PlannedTime()
            .id(PLANNED_TIME_4_ID)
            .order(ORDER_4)
            .employee(EMPLOYEE_1)
            .manHours(15);

    public static final Set<PlannedTime> ORDER_1_PLANNED_TIME = Set.of(PLANNED_TIME_1);
    public static final Set<PlannedTime> ORDER_2_PLANNED_TIME = Set.of(PLANNED_TIME_2);
    public static final Set<PlannedTime> ORDER_3_PLANNED_TIME = Set.of(PLANNED_TIME_3);
    public static final Set<PlannedTime> ORDER_4_PLANNED_TIME = Set.of(PLANNED_TIME_4);

    public static Set<PlannedTime> getPreparedCreateSet(Order order) {

        return Set.of(new PlannedTime()
                .order(order)
                .employee(EMPLOYEE_1)
                .manHours(50)
        );
    }

    public static Set<PlannedTime> getPreparedUpdateSet() {

        return Set.of(new PlannedTime(PLANNED_TIME_1)
                .manHours(35)
        );
    }

}
