package edu.guap.enclave.web;

import edu.guap.enclave.model.Company;
import edu.guap.enclave.model.Reliability;
import edu.guap.enclave.model.TypeCompany;
import edu.guap.enclave.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCompanyController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanyRepository companyRepository;

    public Company create(Company company) {
        log.info("create company {}", company.getId());
        return companyRepository.save(company);
    }

    public Company update(Company company, int id) {
        log.info("update company {}", id);
        return companyRepository.save(company);
    }

    public boolean delete(int id) {
        log.info("delete company {}", id);
        return companyRepository.delete(id);
    }

    public Optional<Company> get(int id) {
        log.info("get company {}", id);
        return companyRepository.get(id);
    }

    public List<Company> getAll() {
        log.info("get all companies");
        return companyRepository.getAll();
    }

    public List<Company> getAllByRegion(int regionId) {
        log.info("get all companies by region {}", regionId);
        return companyRepository.getAllByRegion(regionId);
    }

    public List<Company> getAllByReliability(Reliability reliability) {
        log.info("get all companies by reliability {}", reliability);
        return companyRepository.getAllByReliability(reliability);
    }

    public List<Company> getAllByType(TypeCompany type) {
        log.info("get all companies by type company {}", type);
        return companyRepository.getAllByType(type);
    }

    public List<Company> getAllByTitle(String title) {
        log.info("get all companies by title {}", title);
        return companyRepository.getAllByTitle(title);
    }

    public List<Company> getAllAddress(String address) {
        log.info("get all companies by address {}", address);
        return companyRepository.getAllByAddress(address);
    }

    public Optional<Company> findByContactPerson(int contactPersonId) {
        log.info("get all companies by contact person {}", contactPersonId);
        return companyRepository.findByContactPerson(contactPersonId);
    }

    public Optional<Company> findByItb(String itn) {
        log.info("find company by itn {}", itn);
        return companyRepository.findByItb(itn);
    }

}
