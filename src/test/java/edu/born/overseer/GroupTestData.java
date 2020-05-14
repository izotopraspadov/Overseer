package edu.born.overseer;

import edu.born.overseer.model.Group;

import java.util.Set;

import static edu.born.overseer.OrderTypeTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class GroupTestData {

    public static final int GROUP_1_ID = START_SEQUENCE + 98;
    public static final int GROUP_2_ID = START_SEQUENCE + 99;
    public static final int GROUP_3_ID = START_SEQUENCE + 100;
    public static final int GROUP_4_ID = START_SEQUENCE + 101;
    public static final int GROUP_5_ID = START_SEQUENCE + 102;

    public static final Group GROUP_1 = new Group()
            .id(GROUP_1_ID)
            .title("001")
            .types(Set.of(ORDER_TYPE_1))
            .comment("Need to get the result! Now!");

    public static final Group GROUP_2 = new Group()
            .id(GROUP_2_ID)
            .title("002")
            .types(Set.of(ORDER_TYPE_2));

    public static final Group GROUP_3 = new Group()
            .id(GROUP_3_ID)
            .title("003")
            .types(Set.of(ORDER_TYPE_3));

    public static final Group GROUP_4 = new Group()
            .id(GROUP_4_ID)
            .title("004")
            .types(Set.of(ORDER_TYPE_3))
            .comment("Good luck...!");

    public static final Group GROUP_5 = new Group()
            .id(GROUP_5_ID)
            .title("005")
            .types(Set.of(ORDER_TYPE_4));

    public static Group getPreparedCreate() {

        return new Group()
                .title("006")
                .types(Set.of(ORDER_TYPE_1));
    }

    public static Group getPreparedDuplicateCreate() {

        return getPreparedCreate()
                .title(GROUP_1.getTitle()); // duplicate
    }

    public static Group getPreparedUpdate() {

        return new Group(GROUP_1)
                .comment("New Comment!"); // update
    }

}
