package edu.born.overseer.web.rest;

import edu.born.overseer.model.Salary;
import edu.born.overseer.repository.SalaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.SalaryRestController.REST_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class SalaryRestController {

    public static final String REST_URL = "/rest/employees/{employeeId}/salaries";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SalaryRepository salaryRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Salary create(@RequestBody Salary salary,
                         @PathVariable int employeeId) {
        checkNew(salary);
        log.info("create salary {} for employee {}", salary, employeeId);
        return salaryRepository.save(salary, employeeId);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Salary salary,
                       @PathVariable int id,
                       @PathVariable int employeeId) {
        assureIdConsistent(salary, id);
        log.info("update salary {} for employee {}", salary, employeeId);
        salaryRepository.save(salary, employeeId);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Salary> getAllByEmployee(@RequestParam(value = "page", required = false) Integer page,
                                         @PathVariable Integer employeeId) {
        log.info("get salaries by employee {}", employeeId);
        return salaryRepository.getAllByEmployee(page, employeeId);
    }
}