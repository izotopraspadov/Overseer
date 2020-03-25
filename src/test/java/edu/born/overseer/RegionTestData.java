package edu.born.overseer;

import edu.born.overseer.model.Region;

import java.util.List;

import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RegionTestData {

    public static final int REGION_1_ID = START_SEQ;
    public static final int REGION_2_ID = START_SEQ + 1;
   // public static final int NEW_ID = START_SEQ + 13;

    public static final Region REGION_1 = new Region(REGION_1_ID, "Алтайский край");
    public static final Region REGION_2 = new Region(REGION_2_ID, "Амурская область");
    //public static final Region NEW_REGION = new Region(NEW_ID, "Амурская область");

    public static Region getCreated() {
        return null;
    }

    public static void assertMatch(Region actual, Region expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Region> actual, Region... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Region> actual, Iterable<Region> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
