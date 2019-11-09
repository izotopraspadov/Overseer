package edu.guap.enclave.web;

import edu.guap.enclave.model.Employee;
import edu.guap.enclave.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractEmployeeController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee create(Employee employee, int regionId) {
        log.info("create employee {}", employee.getId());
        return employeeRepository.save(employee, regionId);
    }

    public Employee update(Employee employee, int id, int regionId) {
        log.info("update employee {}", id);
        return employeeRepository.save(employee, regionId);
    }

    public boolean delete(int id) {
        log.info("delete employee {}", id);
        return employeeRepository.delete(id);
    }

    public Optional<Employee> get(int id) {
        log.info("get employee {}", id);
        return employeeRepository.get(id);
    }

    public Optional<Employee> getWithPayments(int id) {
        log.info("get employee {} with payments", id);
        return employeeRepository.getWithPayments(id);
    }

    public Optional<Employee> getWithSalaryAndPhonesAndEmails(int id) {
        log.info("get employee {} with salary, phones, emails", id);
        return employeeRepository.getWithSalaryAndPhonesAndEmails(id);
    }

    public List<Employee> getAll() {
        log.info("get all employees");
        return employeeRepository.getAll();
    }

    public List<Employee> getAllByRegion(int regionId) {
        log.info("get all employees by region {}", regionId);
        return employeeRepository.getAllByRegion(regionId);
    }

    public List<Employee> getAllByAddress(String address) {
        log.info("get all employees by address {}", address);
        return employeeRepository.getAllByAddress(address);
    }

    public Optional<Employee> findByLogin(String login) {
        log.info("find employee by login {}", login);
        return employeeRepository.findByLogin(login);
    }

    public Optional<Employee> getWithSalary(int id) {
        return employeeRepository.getWithSalary(id);
    }

    public Optional<Employee> getWithEmails(int id) {
        return employeeRepository.getWithEmails(id);
    }

    public Optional<Employee> getWithPhones(int id) {
        return employeeRepository.getWithPhones(id);
    }

    public List<Employee> getAllByFullName(String fullName) {
        return employeeRepository.getAllByFullName(fullName);
    }
}
