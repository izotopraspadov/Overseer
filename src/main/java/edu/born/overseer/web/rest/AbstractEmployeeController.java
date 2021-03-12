package edu.born.overseer.web.rest;

import edu.born.overseer.model.Employee;
import edu.born.overseer.service.impl.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;

public abstract class AbstractEmployeeController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected EmployeeService employeeService;

    public Employee create(Employee employee) {
        checkNew(employee);
        int regionId = employee.getRegion().getId();
        log.info("create employee {} by region {}", employee, regionId);
        return employeeService.save(employee, regionId);
    }

    public void update(Employee employee, int id) {
        assureIdConsistent(employee, id);
        int regionId = employee.getRegion().getId();
        log.info("update employee {} by region {}", employee, regionId);
        employeeService.save(employee, regionId);
    }

    public void delete(int id) {
        log.info("delete employee {}", id);
        employeeService.delete(id);
    }

    public Employee getById(int id) {
        log.info("get employee {}", id);
        return employeeService.getById(id);
    }

    public Employee getByLogin(String login) {
        log.info("get all employees by login {}", login);
        return employeeService.getByLogin(login);
    }

    public List<Employee> getAll(Integer page, Integer regionId, String address, String fullName) {
        log.info("get all employees");
        return employeeService.getAll(page, regionId, address, fullName);
    }
}