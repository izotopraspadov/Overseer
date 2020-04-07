package edu.born.overseer;

import edu.born.overseer.model.Group;

import java.util.Set;

import static edu.born.overseer.OrderTypeTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class GroupTestData {

    public static final int INVALID_GROUP_ID = START_SEQUENCE - 1;

    public static final int GROUP_1_ID = START_SEQUENCE + 98;
    public static final int GROUP_2_ID = START_SEQUENCE + 99;
    public static final int GROUP_3_ID = START_SEQUENCE + 100;
    public static final int GROUP_4_ID = START_SEQUENCE + 101;
    public static final int GROUP_5_ID = START_SEQUENCE + 102;

    public static final Group GROUP_1 = new Group(GROUP_1_ID, "001", "Need to get the result! Now!", Set.of(ORDER_TYPE_1));
    public static final Group GROUP_2 = new Group(GROUP_2_ID, "002", null, Set.of(ORDER_TYPE_2));
    public static final Group GROUP_3 = new Group(GROUP_3_ID, "003", null, Set.of(ORDER_TYPE_3));
    public static final Group GROUP_4 = new Group(GROUP_4_ID, "004", "Good luck...!", Set.of(ORDER_TYPE_3));
    public static final Group GROUP_5 = new Group(GROUP_5_ID, "005", null, Set.of(ORDER_TYPE_4));

}
