package edu.born.overseer;

import edu.born.overseer.model.ContactPerson;

import static edu.born.overseer.CompanyTestData.*;
import static edu.born.overseer.EmailTestData.*;
import static edu.born.overseer.PhoneTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class ContactPersonTestData {

    public static final int INVALID_CONTACT_PERSON_ID = START_SEQUENCE - 1;

    public static final int CONTACT_PERSON_1_ID = START_SEQUENCE + 16;
    public static final int CONTACT_PERSON_2_ID = START_SEQUENCE + 17;
    public static final int CONTACT_PERSON_3_ID = START_SEQUENCE + 18;
    public static final int CONTACT_PERSON_4_ID = START_SEQUENCE + 19;
    public static final int CONTACT_PERSON_5_ID = START_SEQUENCE + 20;
    public static final int CONTACT_PERSON_6_ID = START_SEQUENCE + 21;
    public static final int CONTACT_PERSON_7_ID = START_SEQUENCE + 22;
    public static final int CONTACT_PERSON_8_ID = START_SEQUENCE + 23;

    public static final ContactPerson CONTACT_PERSON_1 =
            new ContactPerson(CONTACT_PERSON_1_ID, "Иванов Иван Иванович", COMPANY_1, CONTACT_PERSON_1_PHONES, CONTACT_PERSON_1_EMAILS);
    public static final ContactPerson CONTACT_PERSON_2 =
            new ContactPerson(CONTACT_PERSON_2_ID, "Семёнов Семён Семёнович", COMPANY_1, CONTACT_PERSON_2_PHONES, CONTACT_PERSON_2_EMAILS);
    public static final ContactPerson CONTACT_PERSON_3 =
            new ContactPerson(CONTACT_PERSON_3_ID, "Петров Пётр Петрович", COMPANY_1, CONTACT_PERSON_3_PHONES, CONTACT_PERSON_3_EMAILS);
    public static final ContactPerson CONTACT_PERSON_4 =
            new ContactPerson(CONTACT_PERSON_4_ID, "Васильева Галина Васильевна", COMPANY_2, CONTACT_PERSON_4_PHONES, CONTACT_PERSON_4_EMAILS);
    public static final ContactPerson CONTACT_PERSON_5 =
            new ContactPerson(CONTACT_PERSON_5_ID, "Иванова Мария Семёновна", COMPANY_2, CONTACT_PERSON_5_PHONES, CONTACT_PERSON_5_EMAILS);
    public static final ContactPerson CONTACT_PERSON_6 =
            new ContactPerson(CONTACT_PERSON_6_ID, "Вадимов Вадим Вадимович", COMPANY_2, CONTACT_PERSON_6_PHONES, CONTACT_PERSON_6_EMAILS);
    public static final ContactPerson CONTACT_PERSON_7 =
            new ContactPerson(CONTACT_PERSON_7_ID, "Чернышевская Милана Фёдоровная", COMPANY_3, CONTACT_PERSON_7_PHONES, CONTACT_PERSON_7_EMAILS);
    public static final ContactPerson CONTACT_PERSON_8 =
            new ContactPerson(CONTACT_PERSON_8_ID, "Андреев Андрей Андреевич", COMPANY_3, CONTACT_PERSON_8_PHONES, CONTACT_PERSON_8_EMAILS);

}
