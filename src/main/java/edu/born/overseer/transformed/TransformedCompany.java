package edu.born.overseer.transformed;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.model.Region;

import java.util.List;

import static edu.born.overseer.util.ConvertTypesUtil.*;

public class TransformedCompany extends TransformedBase {

    private String title;

    private Region region;

    private String itn;

    private String address;

    private List<ContactPerson> contactPersons;

    private String reliabilityType;

    private String chatGroupName;

    private String companyType;

    public TransformedCompany(Company company) {
        super(company.getId());
        this.title = company.getTitle();
        this.region = company.getRegion();
        this.itn = company.getItn();
        this.address = company.getAddress();
        this.contactPersons = company.getContactPersons();
        this.reliabilityType = reliabilityTypeToString(company.getReliabilityType());
        this.chatGroupName = company.getChatGroupName();
        this.companyType = companyTypeToString(company.getCompanyType());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getItn() {
        return itn;
    }

    public void setItn(String itn) {
        this.itn = itn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ContactPerson> getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(List<ContactPerson> contactPersons) {
        this.contactPersons = contactPersons;
    }

    public String getReliabilityType() {
        return reliabilityType;
    }

    public void setReliabilityType(String reliabilityType) {
        this.reliabilityType = reliabilityType;
    }

    public String getChatGroupName() {
        return chatGroupName;
    }

    public void setChatGroupName(String chatGroupName) {
        this.chatGroupName = chatGroupName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

}
