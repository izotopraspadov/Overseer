package edu.born.overseer.web.rest.salary;

import edu.born.overseer.model.Salary;
import edu.born.overseer.repository.SalaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = SalaryRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalaryRestController {

    public static final String REST_URL = "/rest/employees/{employeeId}";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SalaryRepository salaryRepository;

    public SalaryRestController(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Salary create(@RequestBody Salary salary, @PathVariable int employeeId) {
        log.info("create salary {} for employee {}", salary, employeeId);
        return salaryRepository.save(salary, employeeId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Salary salary, @PathVariable int id, @PathVariable int employeeId) {
        log.info("update salary {} for employee {}", salary, employeeId);
        salaryRepository.save(salary, employeeId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        log.info("delete salary {}", id);
        return salaryRepository.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Salary getCurrentByEmployee(@PathVariable int employeeId) {
        log.info("get current salary by employee {}", employeeId);
        return salaryRepository.getCurrentByEmployee(employeeId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Salary> getAllByEmployee(@PathVariable int employeeId) {
        log.info("get salaries by employee {}", employeeId);
        return salaryRepository.getAllByEmployee(employeeId);
    }

}
