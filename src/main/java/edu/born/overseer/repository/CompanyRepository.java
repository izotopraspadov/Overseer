package edu.born.overseer.repository;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.model.CompanyType;

import java.util.List;

public interface CompanyRepository {

    Company save(Company company);

    // false if not found
    boolean delete(int id);

    Company getById(int id);

    Company getByContactPersonId(int contactPersonId);

    List<Company> getAllByItb(String itn);

    List<Company> getAll();

    List<Company> getAllByRegion(int regionId);

    List<Company> getAllByReliability(ReliabilityType reliabilityType);

    List<Company> getAllByType(CompanyType type);

    List<Company> getAllByTitle(String title);

    List<Company> getAllByAddress(String address);

}
