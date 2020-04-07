package edu.born.overseer;

import edu.born.overseer.model.OrderType;

import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class OrderTypeTestData {

    public static final int ORDER_TYPE_1_ID = START_SEQUENCE + 94;
    public static final int ORDER_TYPE_2_ID = START_SEQUENCE + 95;
    public static final int ORDER_TYPE_3_ID = START_SEQUENCE + 96;
    public static final int ORDER_TYPE_4_ID = START_SEQUENCE + 97;

    public static final OrderType ORDER_TYPE_1 = new OrderType(ORDER_TYPE_1_ID, "Проект");
    public static final OrderType ORDER_TYPE_2 = new OrderType(ORDER_TYPE_2_ID, "Исп. документация");
    public static final OrderType ORDER_TYPE_3 = new OrderType(ORDER_TYPE_3_ID, "Смета");
    public static final OrderType ORDER_TYPE_4 = new OrderType(ORDER_TYPE_4_ID, "Юр. Услуги");

}
