package edu.born.overseer.repository;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.CompanyType;
import edu.born.overseer.model.ReliabilityType;

import java.util.List;

public interface CompanyRepository {

    Company save(Company company, int regionId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Company getById(int id);

    List<Company> getAll(Integer page,
                         Integer contactPersonId,
                         Integer regionId,
                         ReliabilityType reliabilityType,
                         CompanyType companyType,
                         String title,
                         String address,
                         String itn);

    void evictCache();
}
