package edu.born.overseer;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.model.Phone;

import java.util.Set;

import static edu.born.overseer.ContactPersonTestData.*;
import static edu.born.overseer.model.OwnerType.CONTACT_PERSON;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class PhoneTestData {

    public static final int INVALID_PHONE_ID = START_SEQUENCE - 1;

    public static final int CONTACT_PERSON_PHONE_1_ID = START_SEQUENCE + 56;
    public static final int CONTACT_PERSON_PHONE_2_ID = START_SEQUENCE + 57;
    public static final int CONTACT_PERSON_PHONE_3_ID = START_SEQUENCE + 58;
    public static final int CONTACT_PERSON_PHONE_4_ID = START_SEQUENCE + 59;
    public static final int CONTACT_PERSON_PHONE_5_ID = START_SEQUENCE + 60;
    public static final int CONTACT_PERSON_PHONE_6_ID = START_SEQUENCE + 61;
    public static final int CONTACT_PERSON_PHONE_7_ID = START_SEQUENCE + 62;
    public static final int CONTACT_PERSON_PHONE_8_ID = START_SEQUENCE + 63;
    public static final int CONTACT_PERSON_PHONE_9_ID = START_SEQUENCE + 64;
    public static final int CONTACT_PERSON_PHONE_10_ID = START_SEQUENCE + 65;
    public static final int CONTACT_PERSON_PHONE_11_ID = START_SEQUENCE + 66;
    public static final int CONTACT_PERSON_PHONE_12_ID = START_SEQUENCE + 67;
    public static final int CONTACT_PERSON_PHONE_13_ID = START_SEQUENCE + 68;

    public static final int NEW_CONTACT_PERSON_PHONE_13_ID = START_SEQUENCE + 131;

    public static final Phone CONTACT_PERSON_PHONE_1 =
            new Phone(CONTACT_PERSON_PHONE_1_ID, CONTACT_PERSON_1, CONTACT_PERSON, "00000000001");
    public static final Phone CONTACT_PERSON_PHONE_2 =
            new Phone(CONTACT_PERSON_PHONE_2_ID, CONTACT_PERSON_1, CONTACT_PERSON, "00000000002");
    public static final Phone CONTACT_PERSON_PHONE_3 =
            new Phone(CONTACT_PERSON_PHONE_3_ID, CONTACT_PERSON_2, CONTACT_PERSON, "00000000003");
    public static final Phone CONTACT_PERSON_PHONE_4 =
            new Phone(CONTACT_PERSON_PHONE_4_ID, CONTACT_PERSON_3, CONTACT_PERSON, "00000000004");
    public static final Phone CONTACT_PERSON_PHONE_5 =
            new Phone(CONTACT_PERSON_PHONE_5_ID, CONTACT_PERSON_3, CONTACT_PERSON, "00000000005");
    public static final Phone CONTACT_PERSON_PHONE_6 =
            new Phone(CONTACT_PERSON_PHONE_6_ID, CONTACT_PERSON_3, CONTACT_PERSON, "00000000006");
    public static final Phone CONTACT_PERSON_PHONE_7 =
            new Phone(CONTACT_PERSON_PHONE_7_ID, CONTACT_PERSON_4, CONTACT_PERSON, "00000000007");
    public static final Phone CONTACT_PERSON_PHONE_8 =
            new Phone(CONTACT_PERSON_PHONE_8_ID, CONTACT_PERSON_4, CONTACT_PERSON, "00000000008");
    public static final Phone CONTACT_PERSON_PHONE_9 =
            new Phone(CONTACT_PERSON_PHONE_9_ID, CONTACT_PERSON_5, CONTACT_PERSON, "00000000009");
    public static final Phone CONTACT_PERSON_PHONE_10 =
            new Phone(CONTACT_PERSON_PHONE_10_ID, CONTACT_PERSON_6, CONTACT_PERSON, "00000000010");
    public static final Phone CONTACT_PERSON_PHONE_11 =
            new Phone(CONTACT_PERSON_PHONE_11_ID, CONTACT_PERSON_7, CONTACT_PERSON, "00000000011");
    public static final Phone CONTACT_PERSON_PHONE_12 =
            new Phone(CONTACT_PERSON_PHONE_12_ID, CONTACT_PERSON_7, CONTACT_PERSON, "00000000012");
    public static final Phone CONTACT_PERSON_PHONE_13 =
            new Phone(CONTACT_PERSON_PHONE_13_ID, CONTACT_PERSON_8, CONTACT_PERSON, "00000000013");

    public static final Set<Phone> CONTACT_PERSON_1_PHONES = Set.of(CONTACT_PERSON_PHONE_1, CONTACT_PERSON_PHONE_2);
    public static final Set<Phone> CONTACT_PERSON_2_PHONES = Set.of(CONTACT_PERSON_PHONE_3);
    public static final Set<Phone> CONTACT_PERSON_3_PHONES = Set.of(CONTACT_PERSON_PHONE_4, CONTACT_PERSON_PHONE_5, CONTACT_PERSON_PHONE_6);
    public static final Set<Phone> CONTACT_PERSON_4_PHONES = Set.of(CONTACT_PERSON_PHONE_7, CONTACT_PERSON_PHONE_8);
    public static final Set<Phone> CONTACT_PERSON_5_PHONES = Set.of(CONTACT_PERSON_PHONE_9);
    public static final Set<Phone> CONTACT_PERSON_6_PHONES = Set.of(CONTACT_PERSON_PHONE_10);
    public static final Set<Phone> CONTACT_PERSON_7_PHONES = Set.of(CONTACT_PERSON_PHONE_11, CONTACT_PERSON_PHONE_12);
    public static final Set<Phone> CONTACT_PERSON_8_PHONES = Set.of(CONTACT_PERSON_PHONE_13);

    public static Set<Phone> getPreparedCreatedSet(ContactPerson owner) {
        return Set.of(new Phone(null, owner, CONTACT_PERSON, "00000000200"));
    }

    public static Set<Phone> getPreparedUpdateSet(ContactPerson owner) {
        return Set.of(new Phone(CONTACT_PERSON_PHONE_1_ID, owner, CONTACT_PERSON, "00000000999"),
                new Phone(CONTACT_PERSON_PHONE_2_ID, owner, CONTACT_PERSON, "00000000998"));
    }

}
