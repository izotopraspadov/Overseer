package edu.born.overseer.repository;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.model.CompanyType;

import java.util.List;

public interface CompanyRepository {

    Company save(Company company);

    // false if not found
    boolean delete(int id);

    // null if not found
    Company getById(int id);

    // null if not found
    Company getByContactPersonId(int contactPersonId);

    List<Company> getAll();

    List<Company> getAllByRegion(int regionId);

    List<Company> getAllByReliability(ReliabilityType reliabilityType);

    List<Company> getAllByType(CompanyType type);

    // partial match using 'like'
    List<Company> getAllByTitle(String title);

    // partial match using 'like'
    List<Company> getAllByAddress(String address);

    // partial match using 'like'
    List<Company> getAllByItb(String itn);

}
