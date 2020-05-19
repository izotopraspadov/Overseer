package edu.born.overseer.web.rest;

import edu.born.overseer.model.Employee;
import edu.born.overseer.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = EmployeeRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeRestController {

    public static final String REST_URL = "/rest/employees";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final EmployeeRepository employeeRepository;

    public EmployeeRestController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee create(@RequestBody Employee employee) {
        int regionId = employee.getRegion().getId();
        log.info("create employee {} by region {}", employee, regionId);
        return employeeRepository.save(employee, regionId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Employee employee, @PathVariable int id) {
        int regionId = employee.getRegion().getId();
        log.info("update employee {} by region {}", employee, regionId);
        employeeRepository.save(employee, regionId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        log.info("delete employee {}", id);
        return employeeRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getById(@PathVariable int id) {
        log.info("get employee {}", id);
        return employeeRepository.getById(id);
    }

    @GetMapping(params = {"login"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getByLogin(@RequestParam("login") String login) {
        log.info("get all employees by login {}", login);
        return employeeRepository.getByLogin(login);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAll() {
        log.info("get all employees");
        return employeeRepository.getAll();
    }

    @GetMapping(params = {"region_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllByRegion(@RequestParam("region_id") int regionId) {
        log.info("get all employees by region {}", regionId);
        return employeeRepository.getAllByRegion(regionId);
    }

    @GetMapping(params = {"address"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllByAddress(@RequestParam("address") String address) {
        log.info("get all employees by address {}", address);
        return employeeRepository.getAllByAddress(address);
    }

    @GetMapping(params = {"full_name"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllByFullName(@RequestParam("full_name") String fullName) {
        log.info("get all employees by fullName {}", fullName);
        return employeeRepository.getAllByFullName(fullName);
    }

}
