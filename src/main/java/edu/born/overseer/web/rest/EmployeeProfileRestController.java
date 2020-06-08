package edu.born.overseer.web.rest;

import edu.born.overseer.exception.NoEditingRightsException;
import edu.born.overseer.model.Employee;
import edu.born.overseer.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.PageUtil.getFirstByPage;

@RestController
@RequestMapping(value = EmployeeProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeProfileRestController extends AbstractEmployeeController {

    public static final String REST_URL = "/rest/employees";

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Employee employee,
                       @PathVariable int id) {
        var userId = SecurityUtil.authUserId();
        if (userId != id)
            throw new NoEditingRightsException("User with id = " + userId + " does not have right to edit");

        super.update(employee, id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getById(@PathVariable int id) {
        return super.getById(id);
    }

    @GetMapping(params = {"login"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getByLogin(@RequestParam("login") String login) {
        return super.getByLogin(login);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAll(@RequestParam(value = "page", required = false) Integer first) {
        return super.getAll(getFirstByPage(first));
    }

    @GetMapping(params = {"region_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllByRegion(@RequestParam("region_id") int regionId,
                                         @RequestParam(value = "page", required = false) Integer first) {
        return super.getAllByRegion(regionId, getFirstByPage(first));
    }

    @GetMapping(params = {"address"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllByAddress(@RequestParam("address") String address,
                                          @RequestParam(value = "page", required = false) Integer first) {
        return super.getAllByAddress(address, getFirstByPage(first));
    }

    @GetMapping(params = {"full_name"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllByFullName(@RequestParam("full_name") String fullName,
                                           @RequestParam(value = "page", required = false) Integer first) {
        return super.getAllByFullName(fullName, getFirstByPage(first));
    }

}
