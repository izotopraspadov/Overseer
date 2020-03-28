package edu.born.overseer.repository;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Reliability;
import edu.born.overseer.model.TypeCompany;

import java.util.List;

public interface CompanyRepository {

    Company save(Company company);

    // false if not found
    boolean delete(int id);

    Company getById(int id);

    Company getByContactPerson(int contactPersonId);

    Company getByItb(String itn);

    List<Company> getAll();

    List<Company> getAllByRegion(int regionId);

    List<Company> getAllByReliability(Reliability reliability);

    List<Company> getAllByType(TypeCompany type);

    List<Company> getAllByTitle(String title);

    List<Company> getAllByAddress(String address);

}
