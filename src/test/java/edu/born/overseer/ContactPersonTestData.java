package edu.born.overseer;

import edu.born.overseer.model.ContactPerson;

import static edu.born.overseer.CompanyTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQ;

public class ContactPersonTestData {

    public static final int INVALID_CONTACT_PERSON_ID = 1;

    public static final int CONTACT_PERSON_1_ID = START_SEQ + 16;
    public static final int CONTACT_PERSON_2_ID = START_SEQ + 17;
    public static final int CONTACT_PERSON_3_ID = START_SEQ + 18;
    public static final int CONTACT_PERSON_4_ID = START_SEQ + 19;
    public static final int CONTACT_PERSON_5_ID = START_SEQ + 20;
    public static final int CONTACT_PERSON_6_ID = START_SEQ + 21;
    public static final int CONTACT_PERSON_7_ID = START_SEQ + 22;
    public static final int CONTACT_PERSON_8_ID = START_SEQ + 23;

    public static final ContactPerson CONTACT_PERSON_1 =
            new ContactPerson(CONTACT_PERSON_1_ID, "Иванов Иван Иванович", COMPANY_1);
    public static final ContactPerson CONTACT_PERSON_2 =
            new ContactPerson(CONTACT_PERSON_2_ID, "Семёнов Семён Семёнович", COMPANY_1);
    public static final ContactPerson CONTACT_PERSON_3 =
            new ContactPerson(CONTACT_PERSON_3_ID, "Петров Пётр Петрович", COMPANY_1);
    public static final ContactPerson CONTACT_PERSON_4 =
            new ContactPerson(CONTACT_PERSON_4_ID, "Васильева Галина Васильевна", COMPANY_2);
    public static final ContactPerson CONTACT_PERSON_5 =
            new ContactPerson(CONTACT_PERSON_5_ID, "Иванова Мария Семёновна", COMPANY_2);
    public static final ContactPerson CONTACT_PERSON_6 =
            new ContactPerson(CONTACT_PERSON_6_ID, "Вадимов Вадим Вадимович", COMPANY_2);
    public static final ContactPerson CONTACT_PERSON_7 =
            new ContactPerson(CONTACT_PERSON_7_ID, "Чернышевская Милана Фёдоровная", COMPANY_3);
    public static final ContactPerson CONTACT_PERSON_8 =
            new ContactPerson(CONTACT_PERSON_8_ID, "Андреев Андрей Андреевич", COMPANY_3);

}
