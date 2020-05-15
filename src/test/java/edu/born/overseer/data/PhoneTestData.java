package edu.born.overseer.data;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Phone;

import java.util.Set;

import static edu.born.overseer.data.ContactPersonTestData.*;
import static edu.born.overseer.data.EmployeeTestData.*;
import static edu.born.overseer.model.OwnerType.CONTACT_PERSON;
import static edu.born.overseer.model.OwnerType.EMPLOYEE;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class PhoneTestData {

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

    public static final int EMPLOYEE_PHONE_1_ID = START_SEQUENCE + 69;
    public static final int EMPLOYEE_PHONE_2_ID = START_SEQUENCE + 70;
    public static final int EMPLOYEE_PHONE_3_ID = START_SEQUENCE + 71;
    public static final int EMPLOYEE_PHONE_4_ID = START_SEQUENCE + 72;
    public static final int EMPLOYEE_PHONE_5_ID = START_SEQUENCE + 73;
    public static final int EMPLOYEE_PHONE_6_ID = START_SEQUENCE + 74;
    public static final int EMPLOYEE_PHONE_7_ID = START_SEQUENCE + 75;
    public static final int EMPLOYEE_PHONE_8_ID = START_SEQUENCE + 76;
    public static final int EMPLOYEE_PHONE_9_ID = START_SEQUENCE + 77;
    public static final int EMPLOYEE_PHONE_10_ID = START_SEQUENCE + 78;

    public static final String INVALID_PHONE_NUMBER = "0000-000-9999";

    public static final Phone CONTACT_PERSON_PHONE_1 = new Phone()
            .id(CONTACT_PERSON_PHONE_1_ID)
            .contactPerson(CONTACT_PERSON_1)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-01");

    public static final Phone CONTACT_PERSON_PHONE_2 = new Phone()
            .id(CONTACT_PERSON_PHONE_2_ID)
            .contactPerson(CONTACT_PERSON_1)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-02");

    public static final Phone CONTACT_PERSON_PHONE_3 = new Phone()
            .id(CONTACT_PERSON_PHONE_3_ID)
            .contactPerson(CONTACT_PERSON_2)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-03");

    public static final Phone CONTACT_PERSON_PHONE_4 = new Phone()
            .id(CONTACT_PERSON_PHONE_4_ID)
            .contactPerson(CONTACT_PERSON_3)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-04");

    public static final Phone CONTACT_PERSON_PHONE_5 = new Phone()
            .id(CONTACT_PERSON_PHONE_5_ID)
            .contactPerson(CONTACT_PERSON_3)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-05");

    public static final Phone CONTACT_PERSON_PHONE_6 = new Phone()
            .id(CONTACT_PERSON_PHONE_6_ID)
            .contactPerson(CONTACT_PERSON_3)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-06");

    public static final Phone CONTACT_PERSON_PHONE_7 = new Phone()
            .id(CONTACT_PERSON_PHONE_7_ID)
            .contactPerson(CONTACT_PERSON_4)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-07");

    public static final Phone CONTACT_PERSON_PHONE_8 = new Phone()
            .id(CONTACT_PERSON_PHONE_8_ID)
            .contactPerson(CONTACT_PERSON_4)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-08");

    public static final Phone CONTACT_PERSON_PHONE_9 = new Phone()
            .id(CONTACT_PERSON_PHONE_9_ID)
            .contactPerson(CONTACT_PERSON_5)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-09");

    public static final Phone CONTACT_PERSON_PHONE_10 = new Phone()
            .id(CONTACT_PERSON_PHONE_10_ID)
            .contactPerson(CONTACT_PERSON_6)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-10");

    public static final Phone CONTACT_PERSON_PHONE_11 = new Phone()
            .id(CONTACT_PERSON_PHONE_11_ID)
            .contactPerson(CONTACT_PERSON_7)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-11");

    public static final Phone CONTACT_PERSON_PHONE_12 = new Phone()
            .id(CONTACT_PERSON_PHONE_12_ID)
            .contactPerson(CONTACT_PERSON_7)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-12");

    public static final Phone CONTACT_PERSON_PHONE_13 = new Phone()
            .id(CONTACT_PERSON_PHONE_13_ID)
            .contactPerson(CONTACT_PERSON_8)
            .ownerType(CONTACT_PERSON)
            .number("+7-000-000-00-13");


    public static final Phone EMPLOYEE_PHONE_1 = new Phone()
            .id(EMPLOYEE_PHONE_1_ID)
            .employee(EMPLOYEE_1)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-14");

    public static final Phone EMPLOYEE_PHONE_2 = new Phone()
            .id(EMPLOYEE_PHONE_2_ID)
            .employee(EMPLOYEE_1)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-15");

    public static final Phone EMPLOYEE_PHONE_3 = new Phone()
            .id(EMPLOYEE_PHONE_3_ID)
            .employee(EMPLOYEE_1)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-16");

    public static final Phone EMPLOYEE_PHONE_4 = new Phone()
            .id(EMPLOYEE_PHONE_4_ID)
            .employee(EMPLOYEE_2)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-17");

    public static final Phone EMPLOYEE_PHONE_5 = new Phone()
            .id(EMPLOYEE_PHONE_5_ID)
            .employee(EMPLOYEE_3)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-18");

    public static final Phone EMPLOYEE_PHONE_6 = new Phone()
            .id(EMPLOYEE_PHONE_6_ID)
            .employee(EMPLOYEE_4)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-19");

    public static final Phone EMPLOYEE_PHONE_7 = new Phone()
            .id(EMPLOYEE_PHONE_7_ID)
            .employee(EMPLOYEE_4)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-20");

    public static final Phone EMPLOYEE_PHONE_8 = new Phone()
            .id(EMPLOYEE_PHONE_8_ID)
            .employee(EMPLOYEE_5)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-21");

    public static final Phone EMPLOYEE_PHONE_9 = new Phone()
            .id(EMPLOYEE_PHONE_9_ID)
            .employee(EMPLOYEE_6)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-22");

    public static final Phone EMPLOYEE_PHONE_10 = new Phone()
            .id(EMPLOYEE_PHONE_10_ID)
            .employee(EMPLOYEE_6)
            .ownerType(EMPLOYEE)
            .number("+7-000-000-00-23");

    public static final Set<Phone> CONTACT_PERSON_1_PHONES = Set.of(CONTACT_PERSON_PHONE_1, CONTACT_PERSON_PHONE_2);
    public static final Set<Phone> CONTACT_PERSON_2_PHONES = Set.of(CONTACT_PERSON_PHONE_3);
    public static final Set<Phone> CONTACT_PERSON_3_PHONES = Set.of(CONTACT_PERSON_PHONE_4, CONTACT_PERSON_PHONE_5, CONTACT_PERSON_PHONE_6);
    public static final Set<Phone> CONTACT_PERSON_4_PHONES = Set.of(CONTACT_PERSON_PHONE_7, CONTACT_PERSON_PHONE_8);
    public static final Set<Phone> CONTACT_PERSON_5_PHONES = Set.of(CONTACT_PERSON_PHONE_9);
    public static final Set<Phone> CONTACT_PERSON_6_PHONES = Set.of(CONTACT_PERSON_PHONE_10);
    public static final Set<Phone> CONTACT_PERSON_7_PHONES = Set.of(CONTACT_PERSON_PHONE_11, CONTACT_PERSON_PHONE_12);
    public static final Set<Phone> CONTACT_PERSON_8_PHONES = Set.of(CONTACT_PERSON_PHONE_13);

    public static final Set<Phone> EMPLOYEE_1_PHONES = Set.of(EMPLOYEE_PHONE_1, EMPLOYEE_PHONE_2, EMPLOYEE_PHONE_3);
    public static final Set<Phone> EMPLOYEE_2_PHONES = Set.of(EMPLOYEE_PHONE_4);
    public static final Set<Phone> EMPLOYEE_3_PHONES = Set.of(EMPLOYEE_PHONE_5);
    public static final Set<Phone> EMPLOYEE_4_PHONES = Set.of(EMPLOYEE_PHONE_6, EMPLOYEE_PHONE_7);
    public static final Set<Phone> EMPLOYEE_5_PHONES = Set.of(EMPLOYEE_PHONE_8);
    public static final Set<Phone> EMPLOYEE_6_PHONES = Set.of(EMPLOYEE_PHONE_9, EMPLOYEE_PHONE_10);

    public static Set<Phone> getPreparedCreatedSet(ContactPerson owner) {

        return Set.of(new Phone()
                .contactPerson(owner)
                .ownerType(CONTACT_PERSON)
                .number("+7-000-000-99-99")
        );
    }

    public static Set<Phone> getPreparedUpdateSetByPerson() {

        return Set.of(
                new Phone()
                        .id(CONTACT_PERSON_PHONE_1_ID)
                        .contactPerson(CONTACT_PERSON_1)
                        .ownerType(CONTACT_PERSON)
                        .number("+7-000-000-99-97"),
                new Phone()
                        .id(CONTACT_PERSON_PHONE_2_ID)
                        .contactPerson(CONTACT_PERSON_1)
                        .ownerType(CONTACT_PERSON)
                        .number("+7-000-000-99-98")
        );
    }

    public static Set<Phone> getPreparedCreateInvalidSet(ContactPerson owner) {

        return Set.of(new Phone()
                .contactPerson(owner)
                .ownerType(CONTACT_PERSON)
                .number(INVALID_PHONE_NUMBER)
        );
    }

    public static Set<Phone> getPreparedCreatedSet(Employee owner) {

        return Set.of(new Phone()
                .employee(owner)
                .ownerType(EMPLOYEE)
                .number("+7-000-000-99-99")
        );
    }

    public static Set<Phone> getPreparedUpdateSetByEmployee() {

        return Set.of(new Phone()
                        .id(EMPLOYEE_PHONE_1_ID)
                        .employee(EMPLOYEE_1)
                        .ownerType(EMPLOYEE)
                        .number("+7-000-000-99-96"),
                new Phone()
                        .id(EMPLOYEE_PHONE_2_ID)
                        .employee(EMPLOYEE_1)
                        .ownerType(EMPLOYEE)
                        .number("+7-000-000-99-96"),
                new Phone()
                        .id(EMPLOYEE_PHONE_3_ID)
                        .employee(EMPLOYEE_1)
                        .ownerType(EMPLOYEE)
                        .number("+7-000-000-99-98")
        );
    }

    public static Set<Phone> getPreparedCreateInvalidSet(Employee owner) {

        return Set.of(new Phone()
                .employee(owner)
                .ownerType(EMPLOYEE)
                .number(INVALID_PHONE_NUMBER)
        );
    }

    public static Phone getPreparedCreate() {

        return new Phone()
                .employee(EMPLOYEE_1)
                .ownerType(EMPLOYEE)
                .number("+7-000-000-99-99");
    }

}