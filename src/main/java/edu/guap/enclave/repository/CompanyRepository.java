package edu.guap.enclave.repository;

import edu.guap.enclave.model.Company;
import edu.guap.enclave.model.Reliability;
import edu.guap.enclave.model.TypeCompany;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {

    Company save(Company company);

    // false if not found
    boolean delete(int id);

    Optional<Company> get(int id);

    List<Company> getAll();

    List<Company> getAllByRegion(int regionId);

    List<Company> getAllByReliability(Reliability reliability);

    List<Company> getAllByType(TypeCompany type);

    List<Company> getAllByTitle(String title);

    List<Company> getAllByAddress(String address);

    Optional<Company> findByContactPerson( int contactPersonId);

    Optional<Company> findByItb(String itn);

}
