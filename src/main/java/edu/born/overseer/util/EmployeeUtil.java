package edu.born.overseer.util;

import edu.born.overseer.model.Employee;
import edu.born.overseer.transformed.UserTo;

public class EmployeeUtil {

    public static UserTo asTo(Employee employee) {
        return new UserTo(employee.getId(), employee.getLogin(), employee.getPassword());
    }


}
