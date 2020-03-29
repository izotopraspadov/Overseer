package edu.born.overseer;

import edu.born.overseer.model.Company;

import static edu.born.overseer.RegionTestData.*;
import static edu.born.overseer.model.Reliability.*;
import static edu.born.overseer.model.TypeCompany.*;
import static edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQ;

public class CompanyTestData {

    public static final int INVALID_COMPANY_ID = START_SEQ - 1;

    public static final int COMPANY_1_ID = START_SEQ + 13;
    public static final int COMPANY_2_ID = START_SEQ + 14;
    public static final int COMPANY_3_ID = START_SEQ + 15;

    public static final int NEW_COMPANY_ID = START_SEQ + 131;

    public static final Company COMPANY_1 = new Company(COMPANY_1_ID, "Первая Компания", REGION_1, "000000000000",
            "Никакая ул. 1", LOW, "Первая", OUR);
    public static final Company COMPANY_2 = new Company(COMPANY_2_ID, "Вторая Компания", REGION_2, "0000000001",
            "Наша ул. 2", MIDDLE, "Вторая", CUSTOMER);
    public static final Company COMPANY_3 = new Company(COMPANY_3_ID, "Третья Компания", REGION_13, "0000000002",
            "Зимняя ул. 89", HIGH, "Третья", OTHER);

    public static Company getCreated() {
        return new Company(NEW_COMPANY_ID, "New Company", REGION_2, "000000000111", "New Street", LOW, "New Group", OUR);
    }

    public static Company getDuplicate() {
        return new Company(NEW_COMPANY_ID, "New Company", REGION_2, COMPANY_1.getItn(), "New Street", LOW, "New Group", OUR);
    }

    public static Company getUpdated() {
        return new Company(COMPANY_1.getId(), "Updated Company", COMPANY_1.getRegion(), COMPANY_1.getItn(),
                COMPANY_1.getAddress(), COMPANY_1.getReliability(), COMPANY_1.getWhatsAppGroupName(), COMPANY_1.getTypeCompany());
    }

}
