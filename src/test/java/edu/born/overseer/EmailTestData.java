package edu.born.overseer;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.model.Email;
import edu.born.overseer.model.Employee;

import java.util.Set;

import static edu.born.overseer.ContactPersonTestData.*;
import static edu.born.overseer.EmployeeTestData.*;
import static edu.born.overseer.model.OwnerType.CONTACT_PERSON;
import static edu.born.overseer.model.OwnerType.EMPLOYEE;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class EmailTestData {

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

    public static final int EMPLOYEE_EMAIL_1_ID = START_SEQUENCE + 45;
    public static final int EMPLOYEE_EMAIL_2_ID = START_SEQUENCE + 46;
    public static final int EMPLOYEE_EMAIL_3_ID = START_SEQUENCE + 47;
    public static final int EMPLOYEE_EMAIL_4_ID = START_SEQUENCE + 48;
    public static final int EMPLOYEE_EMAIL_5_ID = START_SEQUENCE + 49;
    public static final int EMPLOYEE_EMAIL_6_ID = START_SEQUENCE + 50;
    public static final int EMPLOYEE_EMAIL_7_ID = START_SEQUENCE + 51;
    public static final int EMPLOYEE_EMAIL_8_ID = START_SEQUENCE + 52;
    public static final int EMPLOYEE_EMAIL_9_ID = START_SEQUENCE + 53;
    public static final int EMPLOYEE_EMAIL_10_ID = START_SEQUENCE + 54;
    public static final int EMPLOYEE_EMAIL_11_ID = START_SEQUENCE + 55;

    public static final Email CONTACT_PERSON_EMAIL_1 = new Email()
            .id(CONTACT_PERSON_EMAIL_1_ID)
            .contactPerson(CONTACT_PERSON_1)
            .ownerType(CONTACT_PERSON)
            .address("1@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_2 = new Email()
            .id(CONTACT_PERSON_EMAIL_2_ID)
            .contactPerson(CONTACT_PERSON_2)
            .ownerType(CONTACT_PERSON)
            .address("2@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_3 = new Email()
            .id(CONTACT_PERSON_EMAIL_3_ID)
            .contactPerson(CONTACT_PERSON_2)
            .ownerType(CONTACT_PERSON)
            .address("3@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_4 = new Email()
            .id(CONTACT_PERSON_EMAIL_4_ID)
            .contactPerson(CONTACT_PERSON_3)
            .ownerType(CONTACT_PERSON)
            .address("4@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_5 = new Email()
            .id(CONTACT_PERSON_EMAIL_5_ID)
            .contactPerson(CONTACT_PERSON_4)
            .ownerType(CONTACT_PERSON)
            .address("5@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_6 = new Email()
            .id(CONTACT_PERSON_EMAIL_6_ID)
            .contactPerson(CONTACT_PERSON_4)
            .ownerType(CONTACT_PERSON)
            .address("6@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_7 = new Email()
            .id(CONTACT_PERSON_EMAIL_7_ID)
            .contactPerson(CONTACT_PERSON_4)
            .ownerType(CONTACT_PERSON)
            .address("7@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_8 = new Email()
            .id(CONTACT_PERSON_EMAIL_8_ID)
            .contactPerson(CONTACT_PERSON_5)
            .ownerType(CONTACT_PERSON)
            .address("8@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_9 = new Email()
            .id(CONTACT_PERSON_EMAIL_9_ID)
            .contactPerson(CONTACT_PERSON_5)
            .ownerType(CONTACT_PERSON)
            .address("9@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_10 = new Email()
            .id(CONTACT_PERSON_EMAIL_10_ID)
            .contactPerson(CONTACT_PERSON_6)
            .ownerType(CONTACT_PERSON)
            .address("10@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_11 = new Email()
            .id(CONTACT_PERSON_EMAIL_11_ID)
            .contactPerson(CONTACT_PERSON_6)
            .ownerType(CONTACT_PERSON)
            .address("11@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_12 = new Email()
            .id(CONTACT_PERSON_EMAIL_12_ID)
            .contactPerson(CONTACT_PERSON_6)
            .ownerType(CONTACT_PERSON)
            .address("12@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_13 = new Email()
            .id(CONTACT_PERSON_EMAIL_13_ID)
            .contactPerson(CONTACT_PERSON_7)
            .ownerType(CONTACT_PERSON)
            .address("13@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_14 = new Email()
            .id(CONTACT_PERSON_EMAIL_14_ID)
            .contactPerson(CONTACT_PERSON_8)
            .ownerType(CONTACT_PERSON)
            .address("14@mail.com");

    public static final Email CONTACT_PERSON_EMAIL_15 = new Email()
            .id(CONTACT_PERSON_EMAIL_15_ID)
            .contactPerson(CONTACT_PERSON_8)
            .ownerType(CONTACT_PERSON)
            .address("15@mail.com");

    public static final Email EMPLOYEE_EMAIL_1 = new Email()
            .id(EMPLOYEE_EMAIL_1_ID)
            .employee(EMPLOYEE_1)
            .ownerType(EMPLOYEE)
            .address("16@mail.com");

    public static final Email EMPLOYEE_EMAIL_2 = new Email()
            .id(EMPLOYEE_EMAIL_2_ID)
            .employee(EMPLOYEE_1)
            .ownerType(EMPLOYEE)
            .address("17@mail.com");

    public static final Email EMPLOYEE_EMAIL_3 = new Email()
            .id(EMPLOYEE_EMAIL_3_ID)
            .employee(EMPLOYEE_2)
            .ownerType(EMPLOYEE)
            .address("18@mail.com");

    public static final Email EMPLOYEE_EMAIL_4 = new Email()
            .id(EMPLOYEE_EMAIL_4_ID)
            .employee(EMPLOYEE_3)
            .ownerType(EMPLOYEE)
            .address("19@mail.com");

    public static final Email EMPLOYEE_EMAIL_5 = new Email()
            .id(EMPLOYEE_EMAIL_5_ID)
            .employee(EMPLOYEE_3)
            .ownerType(EMPLOYEE)
            .address("20@mail.com");

    public static final Email EMPLOYEE_EMAIL_6 = new Email()
            .id(EMPLOYEE_EMAIL_6_ID)
            .employee(EMPLOYEE_3)
            .ownerType(EMPLOYEE)
            .address("21@mail.com");

    public static final Email EMPLOYEE_EMAIL_7 = new Email()
            .id(EMPLOYEE_EMAIL_7_ID)
            .employee(EMPLOYEE_4)
            .ownerType(EMPLOYEE)
            .address("22@mail.com");

    public static final Email EMPLOYEE_EMAIL_8 = new Email()
            .id(EMPLOYEE_EMAIL_8_ID)
            .employee(EMPLOYEE_4)
            .ownerType(EMPLOYEE)
            .address("23@mail.com");

    public static final Email EMPLOYEE_EMAIL_9 = new Email()
            .id(EMPLOYEE_EMAIL_9_ID)
            .employee(EMPLOYEE_5)
            .ownerType(EMPLOYEE)
            .address("24@mail.com");

    public static final Email EMPLOYEE_EMAIL_10 = new Email()
            .id(EMPLOYEE_EMAIL_10_ID)
            .employee(EMPLOYEE_6)
            .ownerType(EMPLOYEE)
            .address("25@mail.com");

    public static final Email EMPLOYEE_EMAIL_11 = new Email()
            .id(EMPLOYEE_EMAIL_11_ID)
            .employee(EMPLOYEE_6)
            .ownerType(EMPLOYEE)
            .address("26@mail.com");

    public static final Set<Email> CONTACT_PERSON_1_EMAILS = Set.of(CONTACT_PERSON_EMAIL_1);
    public static final Set<Email> CONTACT_PERSON_2_EMAILS = Set.of(CONTACT_PERSON_EMAIL_2, CONTACT_PERSON_EMAIL_3);
    public static final Set<Email> CONTACT_PERSON_3_EMAILS = Set.of(CONTACT_PERSON_EMAIL_4);
    public static final Set<Email> CONTACT_PERSON_4_EMAILS = Set.of(CONTACT_PERSON_EMAIL_5, CONTACT_PERSON_EMAIL_6, CONTACT_PERSON_EMAIL_7);
    public static final Set<Email> CONTACT_PERSON_5_EMAILS = Set.of(CONTACT_PERSON_EMAIL_8, CONTACT_PERSON_EMAIL_9);
    public static final Set<Email> CONTACT_PERSON_6_EMAILS = Set.of(CONTACT_PERSON_EMAIL_10, CONTACT_PERSON_EMAIL_11, CONTACT_PERSON_EMAIL_12);
    public static final Set<Email> CONTACT_PERSON_7_EMAILS = Set.of(CONTACT_PERSON_EMAIL_13);
    public static final Set<Email> CONTACT_PERSON_8_EMAILS = Set.of(CONTACT_PERSON_EMAIL_14, CONTACT_PERSON_EMAIL_15);

    public static final Set<Email> EMPLOYEE_1_EMAILS = Set.of(EMPLOYEE_EMAIL_1, EMPLOYEE_EMAIL_2);
    public static final Set<Email> EMPLOYEE_2_EMAILS = Set.of(EMPLOYEE_EMAIL_3);
    public static final Set<Email> EMPLOYEE_3_EMAILS = Set.of(EMPLOYEE_EMAIL_4, EMPLOYEE_EMAIL_5, EMPLOYEE_EMAIL_6);
    public static final Set<Email> EMPLOYEE_4_EMAILS = Set.of(EMPLOYEE_EMAIL_7, EMPLOYEE_EMAIL_8);
    public static final Set<Email> EMPLOYEE_5_EMAILS = Set.of(EMPLOYEE_EMAIL_9);
    public static final Set<Email> EMPLOYEE_6_EMAILS = Set.of(EMPLOYEE_EMAIL_10, EMPLOYEE_EMAIL_11);

    public static Set<Email> getPreparedCreateSet(ContactPerson owner) {

        return Set.of(new Email()
                .contactPerson(owner)
                .ownerType(CONTACT_PERSON)
                .address("901@mail.com")
        );
    }

    public static Set<Email> getPreparedUpdateSetByPerson() {

        return Set.of(new Email(CONTACT_PERSON_EMAIL_1)
                .address("updated@mail.com")
        );
    }

    public static Set<Email> getPreparedCreateSet(Employee owner) {

        return Set.of(new Email()
                .employee(owner)
                .ownerType(EMPLOYEE)
                .address("902@mail.com")
        );
    }

    public static Set<Email> getPreparedUpdateSetByEmployee() {

        return Set.of(new Email(EMPLOYEE_EMAIL_1)
                .address("updated@mail.com")
        );

    }

}
