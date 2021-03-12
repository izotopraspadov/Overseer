package edu.born.overseer.web.rest;

import edu.born.overseer.exception.NoEditingRightsException;
import edu.born.overseer.model.Employee;
import edu.born.overseer.util.SecurityUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.web.rest.EmployeeProfileRestController.REST_URL;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class EmployeeProfileRestController extends AbstractEmployeeController {

    public static final String REST_URL = "/rest/employees";

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Employee employee,
                       @PathVariable int id) {
        var userId = SecurityUtil.authUserId();
        if (userId != id)
            throw new NoEditingRightsException("User with id = " + userId + " does not have right to edit");

        super.update(employee, id);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Employee getById(@PathVariable int id) {
        return super.getById(id);
    }

    @GetMapping(params = {"login"}, produces = APPLICATION_JSON_VALUE)
    public Employee getByLogin(@RequestParam("login") String login) {
        return super.getByLogin(login);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Employee> getAll(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "region_id", required = false) Integer regionId,
                                 @RequestParam(value = "address", required = false) String address,
                                 @RequestParam(value = "full_name", required = false) String fullName) {
        return super.getAll(page, regionId, address, fullName);
    }
}