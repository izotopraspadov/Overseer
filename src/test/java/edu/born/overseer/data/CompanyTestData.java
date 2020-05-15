package edu.born.overseer.data;

import edu.born.overseer.model.Company;

import static edu.born.overseer.data.ContactPersonTestData.*;
import static edu.born.overseer.data.RegionTestData.*;
import static edu.born.overseer.model.CompanyType.*;
import static edu.born.overseer.model.ReliabilityType.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE;

public class CompanyTestData {

    public static final int COMPANY_1_ID = START_SEQUENCE + 13;
    public static final int COMPANY_2_ID = START_SEQUENCE + 14;
    public static final int COMPANY_3_ID = START_SEQUENCE + 15;

    public static final String INVALID_ITN = "001";

    public static final Company COMPANY_1 = new Company()
            .id(COMPANY_1_ID)
            .title("Первая Компания")
            .region(REGION_1)
            .itn("000000000000")
            .address("Никакая ул. 1")
            .reliabilityType(LOW)
            .chatGroupName("Первая")
            .companyType(OUR);

    public static final Company COMPANY_2 = new Company()
            .id(COMPANY_2_ID)
            .title("Вторая Компания")
            .region(REGION_2)
            .itn("0000000001")
            .address("Наша ул. 2")
            .reliabilityType(MIDDLE)
            .chatGroupName("Вторая")
            .companyType(CUSTOMER);

    public static final Company COMPANY_3 = new Company()
            .id(COMPANY_3_ID)
            .title("Третья Компания")
            .region(REGION_13)
            .itn("0000000002")
            .address("Зимняя ул. 89")
            .reliabilityType(HIGH)
            .chatGroupName("Третья")
            .companyType(OTHER);


    // added relationships
    static {
        COMPANY_1.contactPersons(COMPANY_1_CONTACT_PERSONS);
        COMPANY_2.contactPersons(COMPANY_2_CONTACT_PERSONS);
        COMPANY_3.contactPersons(COMPANY_3_CONTACT_PERSONS);
    }

    public static Company getPreparedCreate() {

        return new Company()
                .title("New Company")
                .region(REGION_2)
                .itn("000000000003")
                .address("New Street")
                .reliabilityType(LOW)
                .chatGroupName("New Group")
                .companyType(OUR);
    }

    public static Company getPreparedCreateWithInvalidITN() {

        return getPreparedCreate()
                .itn(INVALID_ITN);
    }

    public static Company getPreparedDuplicate() {

        return getPreparedCreate()
                .itn(COMPANY_1.getItn());
    }

    public static Company getPreparedUpdate() {

        return new Company(COMPANY_1)
                .title("Updated Company");
    }

}
