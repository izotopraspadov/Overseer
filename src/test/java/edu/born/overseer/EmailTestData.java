package edu.born.overseer;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.model.Email;

import java.util.Set;

import static edu.born.overseer.ContactPersonTestData.*;
import static edu.born.overseer.model.TypeOwner.CONTACT_PERSON;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class EmailTestData {

    public static final int INVALID_EMAIL_ID = START_SEQUENCE - 1;

    public static final int CONTACT_PERSON_EMAIL_1_ID = START_SEQUENCE + 30;
    public static final int CONTACT_PERSON_EMAIL_2_ID = START_SEQUENCE + 31;
    public static final int CONTACT_PERSON_EMAIL_3_ID = START_SEQUENCE + 32;
    public static final int CONTACT_PERSON_EMAIL_4_ID = START_SEQUENCE + 33;
    public static final int CONTACT_PERSON_EMAIL_5_ID = START_SEQUENCE + 34;
    public static final int CONTACT_PERSON_EMAIL_6_ID = START_SEQUENCE + 35;
    public static final int CONTACT_PERSON_EMAIL_7_ID = START_SEQUENCE + 36;
    public static final int CONTACT_PERSON_EMAIL_8_ID = START_SEQUENCE + 37;
    public static final int CONTACT_PERSON_EMAIL_9_ID = START_SEQUENCE + 38;
    public static final int CONTACT_PERSON_EMAIL_10_ID = START_SEQUENCE + 39;
    public static final int CONTACT_PERSON_EMAIL_11_ID = START_SEQUENCE + 40;
    public static final int CONTACT_PERSON_EMAIL_12_ID = START_SEQUENCE + 41;
    public static final int CONTACT_PERSON_EMAIL_13_ID = START_SEQUENCE + 42;
    public static final int CONTACT_PERSON_EMAIL_14_ID = START_SEQUENCE + 43;
    public static final int CONTACT_PERSON_EMAIL_15_ID = START_SEQUENCE + 44;

    public static final Email CONTACT_PERSON_EMAIL_1 =
            new Email(CONTACT_PERSON_EMAIL_1_ID, CONTACT_PERSON_1, CONTACT_PERSON, "1@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_2 =
            new Email(CONTACT_PERSON_EMAIL_2_ID, CONTACT_PERSON_2, CONTACT_PERSON, "2@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_3 =
            new Email(CONTACT_PERSON_EMAIL_3_ID, CONTACT_PERSON_2, CONTACT_PERSON, "3@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_4 =
            new Email(CONTACT_PERSON_EMAIL_4_ID, CONTACT_PERSON_3, CONTACT_PERSON, "4@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_5 =
            new Email(CONTACT_PERSON_EMAIL_5_ID, CONTACT_PERSON_4, CONTACT_PERSON, "5@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_6 =
            new Email(CONTACT_PERSON_EMAIL_6_ID, CONTACT_PERSON_4, CONTACT_PERSON, "6@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_7 =
            new Email(CONTACT_PERSON_EMAIL_7_ID, CONTACT_PERSON_4, CONTACT_PERSON, "7@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_8 =
            new Email(CONTACT_PERSON_EMAIL_8_ID, CONTACT_PERSON_5, CONTACT_PERSON, "8@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_9 =
            new Email(CONTACT_PERSON_EMAIL_9_ID, CONTACT_PERSON_5, CONTACT_PERSON, "9@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_10 =
            new Email(CONTACT_PERSON_EMAIL_10_ID, CONTACT_PERSON_6, CONTACT_PERSON, "10@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_11 =
            new Email(CONTACT_PERSON_EMAIL_11_ID, CONTACT_PERSON_6, CONTACT_PERSON, "11@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_12 =
            new Email(CONTACT_PERSON_EMAIL_12_ID, CONTACT_PERSON_6, CONTACT_PERSON, "12@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_13 =
            new Email(CONTACT_PERSON_EMAIL_13_ID, CONTACT_PERSON_7, CONTACT_PERSON, "13@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_14 =
            new Email(CONTACT_PERSON_EMAIL_14_ID, CONTACT_PERSON_8, CONTACT_PERSON, "14@mail.com");
    public static final Email CONTACT_PERSON_EMAIL_15 =
            new Email(CONTACT_PERSON_EMAIL_15_ID, CONTACT_PERSON_8, CONTACT_PERSON, "15@mail.com");

    public static final Set<Email> CONTACT_PERSON_1_EMAILS = Set.of(CONTACT_PERSON_EMAIL_1);
    public static final Set<Email> CONTACT_PERSON_2_EMAILS = Set.of(CONTACT_PERSON_EMAIL_2, CONTACT_PERSON_EMAIL_3);
    public static final Set<Email> CONTACT_PERSON_3_EMAILS = Set.of(CONTACT_PERSON_EMAIL_4);
    public static final Set<Email> CONTACT_PERSON_4_EMAILS = Set.of(CONTACT_PERSON_EMAIL_5, CONTACT_PERSON_EMAIL_6, CONTACT_PERSON_EMAIL_7);
    public static final Set<Email> CONTACT_PERSON_5_EMAILS = Set.of(CONTACT_PERSON_EMAIL_8, CONTACT_PERSON_EMAIL_9);
    public static final Set<Email> CONTACT_PERSON_6_EMAILS = Set.of(CONTACT_PERSON_EMAIL_10, CONTACT_PERSON_EMAIL_11, CONTACT_PERSON_EMAIL_12);
    public static final Set<Email> CONTACT_PERSON_7_EMAILS = Set.of(CONTACT_PERSON_EMAIL_13);
    public static final Set<Email> CONTACT_PERSON_8_EMAILS = Set.of(CONTACT_PERSON_EMAIL_14, CONTACT_PERSON_EMAIL_15);

    public static Set<Email> getPreparedCreateSet(ContactPerson owner) {
        return Set.of(new Email(null, owner, CONTACT_PERSON, "15_1@mail.com"));
    }

    public static Set<Email> getPreparedUpdateSet(ContactPerson owner) {
        return Set.of(new Email(CONTACT_PERSON_EMAIL_1_ID, owner, CONTACT_PERSON, "updated@mail.com"));
    }

}
