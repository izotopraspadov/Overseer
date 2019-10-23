package edu.guap.enclave.repository;

import edu.guap.enclave.model.Company;
import edu.guap.enclave.model.Reliability;
import edu.guap.enclave.model.TypeCompany;

import java.util.List;

public interface CompanyRepository {

    Company save(Company company);

    // false if not found
    boolean delete(int id);

    // null if not found
    Company get(int id);

    List<Company> getAll();

    List<Company> getAllByRegion(int regionId);

    List<Company> getAllByReliability(Reliability reliability);

    List<Company> getAllByType(TypeCompany type);

    Company getAllByTitle(String title);

    Company findByItb(String itn);

    Company findByAddress(String address);

}
