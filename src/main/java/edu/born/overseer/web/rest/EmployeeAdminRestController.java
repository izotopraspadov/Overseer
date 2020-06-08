package edu.born.overseer.web.rest;

import edu.born.overseer.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = EmployeeAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeAdminRestController extends AbstractEmployeeController {

    public static final String REST_URL = "/rest/admin/employees";

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee create(@RequestBody Employee employee) {
        return super.create(employee);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Employee employee,
                       @PathVariable int id) {
        super.update(employee, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

}
