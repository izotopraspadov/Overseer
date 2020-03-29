package edu.born.overseer;

import edu.born.overseer.model.Phone;

import static edu.born.overseer.ContactPersonTestData.*;
import static edu.born.overseer.model.TypeOwner.CONTACT_PERSON;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQ;

public class PhoneTestData {

    public static final int INVALID_PHONE_ID = START_SEQ - 1;

    public static final int CONTACT_PERSON_PHONE_1_ID = START_SEQ + 56;
    public static final int CONTACT_PERSON_PHONE_2_ID = START_SEQ + 57;
    public static final int CONTACT_PERSON_PHONE_3_ID = START_SEQ + 58;
    public static final int CONTACT_PERSON_PHONE_4_ID = START_SEQ + 59;
    public static final int CONTACT_PERSON_PHONE_5_ID = START_SEQ + 60;
    public static final int CONTACT_PERSON_PHONE_6_ID = START_SEQ + 61;
    public static final int CONTACT_PERSON_PHONE_7_ID = START_SEQ + 62;
    public static final int CONTACT_PERSON_PHONE_8_ID = START_SEQ + 63;
    public static final int CONTACT_PERSON_PHONE_9_ID = START_SEQ + 64;
    public static final int CONTACT_PERSON_PHONE_10_ID = START_SEQ + 65;
    public static final int CONTACT_PERSON_PHONE_11_ID = START_SEQ + 66;
    public static final int CONTACT_PERSON_PHONE_12_ID = START_SEQ + 67;
    public static final int CONTACT_PERSON_PHONE_13_ID = START_SEQ + 68;

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


}
