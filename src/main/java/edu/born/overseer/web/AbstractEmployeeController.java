package edu.born.overseer.web;

import edu.born.overseer.model.Employee;
import edu.born.overseer.repository.EmployeeRepository;
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
        log.info("create employee {} by region {}", employee, regionId);
        return employeeRepository.save(employee, regionId);
    }

    public Employee update(Employee employee, int id, int regionId) {
        log.info("update employee {} by region {}", employee, regionId);
        return employeeRepository.save(employee, regionId);
    }

    public boolean delete(int id) {
        log.info("delete employee {}", id);
        return employeeRepository.delete(id);
    }

    public Employee getById(int id) {
        log.info("get employee {}", id);
        return employeeRepository.getById(id);
    }

    public Employee getByLogin(String login) {
        log.info("get all employees by login {}", login);
        return employeeRepository.getByLogin(login);
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

    public List<Employee> getAllByFullName(String fullName) {
        log.info("get all employees by fullName {}", fullName);
        return employeeRepository.getAllByFullName(fullName);
    }

}
