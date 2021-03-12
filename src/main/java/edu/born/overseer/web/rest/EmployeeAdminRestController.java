package edu.born.overseer.web.rest;

import edu.born.overseer.model.Employee;
import org.springframework.web.bind.annotation.*;

import static edu.born.overseer.web.rest.EmployeeAdminRestController.REST_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class EmployeeAdminRestController extends AbstractEmployeeController {

    public static final String REST_URL = "/rest/admin/employees";

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Employee create(@RequestBody Employee employee) {
        return super.create(employee);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Employee employee,
                       @PathVariable int id) {
        super.update(employee, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}