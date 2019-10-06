package edu.guap.enclave.repository;

import edu.guap.enclave.model.Company;

import java.util.List;

public interface CompanyRepository {

    Company save(Company company);

    // false if not found
    boolean delete(int id);

    // null if not found
    Company get(int id);

    List<Company> getAll();

}
