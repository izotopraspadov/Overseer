package edu.born.overseer.data;

import edu.born.overseer.model.Region;

import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class RegionTestData {

    public static final int REGION_1_ID = START_SEQUENCE;
    public static final int REGION_2_ID = START_SEQUENCE + 1;
    public static final int REGION_3_ID = START_SEQUENCE + 2;
    public static final int REGION_4_ID = START_SEQUENCE + 3;
    public static final int REGION_5_ID = START_SEQUENCE + 4;
    public static final int REGION_6_ID = START_SEQUENCE + 5;
    public static final int REGION_7_ID = START_SEQUENCE + 6;
    public static final int REGION_8_ID = START_SEQUENCE + 7;
    public static final int REGION_9_ID = START_SEQUENCE + 8;
    public static final int REGION_10_ID = START_SEQUENCE + 9;
    public static final int REGION_11_ID = START_SEQUENCE + 10;
    public static final int REGION_12_ID = START_SEQUENCE + 11;
    public static final int REGION_13_ID = START_SEQUENCE + 12;

    public static final Region REGION_1 = new Region()
            .id(REGION_1_ID)
            .title("Алтайский край");

    public static final Region REGION_2 = new Region()
            .id(REGION_2_ID)
            .title("Амурская область");

    public static final Region REGION_3 = new Region()
            .id(REGION_3_ID)
            .title("Владимирская область");

    public static final Region REGION_4 = new Region()
            .id(REGION_4_ID)
            .title("г. Москва");

    public static final Region REGION_5 = new Region()
            .id(REGION_5_ID)
            .title("Еврейская автономная область");

    public static final Region REGION_6 = new Region()
            .id(REGION_6_ID)
            .title("Ивановская область");

    public static final Region REGION_7 = new Region()
            .id(REGION_7_ID)
            .title("Камчатский край");

    public static final Region REGION_8 = new Region()
            .id(REGION_8_ID)
            .title("Ленинградская область");

    public static final Region REGION_9 = new Region()
            .id(REGION_9_ID)
            .title("Омская область");

    public static final Region REGION_10 = new Region()
            .id(REGION_10_ID)
            .title("Республика Алтай");

    public static final Region REGION_11 = new Region()
            .id(REGION_11_ID)
            .title("Самарская область");

    public static final Region REGION_12 = new Region()
            .id(REGION_12_ID)
            .title("Санкт-Петербург");

    public static final Region REGION_13 = new Region()
            .id(REGION_13_ID)
            .title("Ярославская область");

    public static Region getPreparedCreate() {

        return new Region()
                .title("New region");
    }

    public static Region getPreparedDuplicate() {

        return new Region()
                .title(REGION_1.getTitle()); // duplicate
    }

    public static Region getPreparedUpdate() {

        return new Region()
                .id(REGION_1_ID)
                .title("Updated region"); // update
    }

}
