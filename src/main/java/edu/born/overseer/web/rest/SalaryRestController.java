package edu.born.overseer.web.rest;

import edu.born.overseer.model.Salary;
import edu.born.overseer.repository.SalaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.util.PageUtil.getFirstByPage;

@RestController
@RequestMapping(value = SalaryRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SalaryRestController {

    public static final String REST_URL = "/rest/employees/{employeeId}/salaries";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SalaryRepository salaryRepository;

    public SalaryRestController(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Salary create(@RequestBody Salary salary,
                         @PathVariable int employeeId) {
        checkNew(salary);
        log.info("create salary {} for employee {}", salary, employeeId);
        return salaryRepository.save(salary, employeeId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Salary salary,
                       @PathVariable int id,
                       @PathVariable int employeeId) {
        assureIdConsistent(salary, id);
        log.info("update salary {} for employee {}", salary, employeeId);
        salaryRepository.save(salary, employeeId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Salary> getAllByEmployee(@PathVariable int employeeId,
                                         @RequestParam(value = "page", required = false) Integer page) {
        log.info("get salaries by employee {}", employeeId);
        return salaryRepository.getAllByEmployee(employeeId, getFirstByPage(page));
    }

}
