package edu.born.overseer;

import edu.born.overseer.model.Region;

import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQ;

public class RegionTestData {

    public static final int INVALID_REGION_ID = 1;

    public static final int REGION_1_ID = START_SEQ;
    public static final int REGION_2_ID = START_SEQ + 1;
    public static final int REGION_3_ID = START_SEQ + 2;
    public static final int REGION_4_ID = START_SEQ + 3;
    public static final int REGION_5_ID = START_SEQ + 4;
    public static final int REGION_6_ID = START_SEQ + 5;
    public static final int REGION_7_ID = START_SEQ + 6;
    public static final int REGION_8_ID = START_SEQ + 7;
    public static final int REGION_9_ID = START_SEQ + 8;
    public static final int REGION_10_ID = START_SEQ + 9;
    public static final int REGION_11_ID = START_SEQ + 10;
    public static final int REGION_12_ID = START_SEQ + 11;
    public static final int REGION_13_ID = START_SEQ + 12;
    public static final int NEW_REGION_ID = START_SEQ + 13;

    public static final Region REGION_1 = new Region(REGION_1_ID, "Алтайский край");
    public static final Region REGION_2 = new Region(REGION_2_ID, "Амурская область");
    public static final Region REGION_3 = new Region(REGION_3_ID, "Владимирская область");
    public static final Region REGION_4 = new Region(REGION_4_ID, "г. Москва");
    public static final Region REGION_5 = new Region(REGION_5_ID, "Еврейская автономная область");
    public static final Region REGION_6 = new Region(REGION_6_ID, "Ивановская область");
    public static final Region REGION_7 = new Region(REGION_7_ID, "Камчатский край");
    public static final Region REGION_8 = new Region(REGION_8_ID, "Ленинградская область");
    public static final Region REGION_9 = new Region(REGION_9_ID, "Омская область");
    public static final Region REGION_10 = new Region(REGION_10_ID, "Республика Алтай");
    public static final Region REGION_11 = new Region(REGION_11_ID, "Самарская область");
    public static final Region REGION_12 = new Region(REGION_12_ID, "Санкт-Петербург");
    public static final Region REGION_13 = new Region(REGION_13_ID, "Ярославская область");

    public static Region getCreated() {
        return new Region(NEW_REGION_ID, "New region");
    }


}
