package edu.born.overseer.web;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.CompanyType;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractCompanyController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanyRepository companyRepository;

    public Company create(Company company, int regionId) {
        log.info("create company {} by region {}", company, regionId);
        return companyRepository.save(company, regionId);
    }

    public Company update(Company company, int id, int regionId) {
        log.info("create company {} by region {}", company, regionId);
        return companyRepository.save(company, regionId);
    }

    public boolean delete(int id) {
        log.info("delete company {}", id);
        return companyRepository.delete(id);
    }

    public Company getById(int id) {
        log.info("get company {}", id);
        return companyRepository.getById(id);
    }

    public Company getByContactPersonId(int contactPersonId) {
        log.info("get company by contact person {}", contactPersonId);
        return companyRepository.getByContactPersonId(contactPersonId);
    }

    public List<Company> getAll() {
        log.info("get all companies");
        return companyRepository.getAll();
    }

    public List<Company> getAllByRegion(int regionId) {
        log.info("get all companies by region {}", regionId);
        return companyRepository.getAllByRegion(regionId);
    }

    public List<Company> getAllByReliability(ReliabilityType reliabilityType) {
        log.info("get all companies by reliability {}", reliabilityType);
        return companyRepository.getAllByReliability(reliabilityType);
    }

    public List<Company> getAllByType(CompanyType type) {
        log.info("get all companies by type company {}", type);
        return companyRepository.getAllByType(type);
    }

    public List<Company> getAllByTitle(String title) {
        log.info("get all companies by title {}", title);
        return companyRepository.getAllByTitle(title);
    }

    public List<Company> getAllByAddress(String address) {
        log.info("get all companies by address {}", address);
        return companyRepository.getAllByAddress(address);
    }

    public List<Company> getAllByItb(String itn) {
        log.info("get company by itn {}", itn);
        return companyRepository.getAllByItb(itn);
    }

}
