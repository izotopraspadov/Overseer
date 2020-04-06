package edu.born.overseer.util;

import edu.born.overseer.model.Employee;
import edu.born.overseer.transformed.TransformedUser;

public class EmployeeUtil {

    public static TransformedUser asTo(Employee employee) {
        return new TransformedUser(employee.getId(), employee.getLogin(), employee.getPassword());
    }


}
