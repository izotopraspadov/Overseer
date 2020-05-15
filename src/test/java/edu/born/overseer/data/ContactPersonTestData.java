package edu.born.overseer.data;

import edu.born.overseer.model.ContactPerson;

import static edu.born.overseer.data.CompanyTestData.*;
import static edu.born.overseer.data.EmailTestData.*;
import static edu.born.overseer.data.PhoneTestData.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class ContactPersonTestData {

    public static final int CONTACT_PERSON_1_ID = START_SEQUENCE + 16;
    public static final int CONTACT_PERSON_2_ID = START_SEQUENCE + 17;
    public static final int CONTACT_PERSON_3_ID = START_SEQUENCE + 18;
    public static final int CONTACT_PERSON_4_ID = START_SEQUENCE + 19;
    public static final int CONTACT_PERSON_5_ID = START_SEQUENCE + 20;
    public static final int CONTACT_PERSON_6_ID = START_SEQUENCE + 21;
    public static final int CONTACT_PERSON_7_ID = START_SEQUENCE + 22;
    public static final int CONTACT_PERSON_8_ID = START_SEQUENCE + 23;

    public static final ContactPerson CONTACT_PERSON_1 = new ContactPerson()
            .id(CONTACT_PERSON_1_ID)
            .fullName("Иванов Иван Иванович")
            .company(COMPANY_1)
            .phones(CONTACT_PERSON_1_PHONES)
            .emails(CONTACT_PERSON_1_EMAILS);

    public static final ContactPerson CONTACT_PERSON_2 = new ContactPerson()
            .id(CONTACT_PERSON_2_ID)
            .fullName("Семёнов Семён Семёнович")
            .company(COMPANY_1)
            .phones(CONTACT_PERSON_2_PHONES)
            .emails(CONTACT_PERSON_2_EMAILS);


    public static final ContactPerson CONTACT_PERSON_3 = new ContactPerson()
            .id(CONTACT_PERSON_3_ID)
            .fullName("Петров Пётр Петрович")
            .company(COMPANY_1)
            .phones(CONTACT_PERSON_3_PHONES)
            .emails(CONTACT_PERSON_3_EMAILS);


    public static final ContactPerson CONTACT_PERSON_4 = new ContactPerson()
            .id(CONTACT_PERSON_4_ID)
            .fullName("Васильева Галина Васильевна")
            .company(COMPANY_2)
            .phones(CONTACT_PERSON_4_PHONES)
            .emails(CONTACT_PERSON_4_EMAILS);

    public static final ContactPerson CONTACT_PERSON_5 = new ContactPerson()
            .id(CONTACT_PERSON_5_ID)
            .fullName("Иванова Мария Семёновна")
            .company(COMPANY_2)
            .phones(CONTACT_PERSON_5_PHONES)
            .emails(CONTACT_PERSON_5_EMAILS);

    public static final ContactPerson CONTACT_PERSON_6 = new ContactPerson()
            .id(CONTACT_PERSON_6_ID)
            .fullName("Вадимов Вадим Вадимович")
            .company(COMPANY_2)
            .phones(CONTACT_PERSON_6_PHONES)
            .emails(CONTACT_PERSON_6_EMAILS);

    public static final ContactPerson CONTACT_PERSON_7 = new ContactPerson()
            .id(CONTACT_PERSON_7_ID)
            .fullName("Чернышевская Милана Фёдоровная")
            .company(COMPANY_3)
            .phones(CONTACT_PERSON_7_PHONES)
            .emails(CONTACT_PERSON_7_EMAILS);

    public static final ContactPerson CONTACT_PERSON_8 = new ContactPerson()
            .id(CONTACT_PERSON_8_ID)
            .fullName("Андреев Андрей Андреевич")
            .company(COMPANY_3)
            .phones(CONTACT_PERSON_8_PHONES)
            .emails(CONTACT_PERSON_8_EMAILS);

    public static ContactPerson getPreparedCreate() {
        var person = new ContactPerson()
                .fullName("New Person")
                .company(COMPANY_1);

        person.setEmails(EmailTestData.getPreparedCreateSet(person));
        person.setPhones(PhoneTestData.getPreparedCreatedSet(person));
        return person;
    }

    public static ContactPerson getPreparedUpdate() {

        return new ContactPerson(CONTACT_PERSON_1)
                .fullName("Updated person");
    }

}
