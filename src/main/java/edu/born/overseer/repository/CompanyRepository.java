package edu.born.overseer.repository;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.model.CompanyType;

import java.util.List;

public interface CompanyRepository {

    Company save(Company company, int regionId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Company getById(int id);

    // null if not found
    Company getByContactPersonId(int contactPersonId);

    List<Company> getAll(int first);

    List<Company> getAllByRegion(int regionId, int first);

    List<Company> getAllByReliability(ReliabilityType reliabilityType, int first);

    List<Company> getAllByType(CompanyType type, int first);

    // partial match using 'like'
    List<Company> getAllByTitle(String title, int first);

    // partial match using 'like'
    List<Company> getAllByAddress(String address, int first);

    // partial match using 'like'
    List<Company> getAllByItb(String itn, int first);

}
