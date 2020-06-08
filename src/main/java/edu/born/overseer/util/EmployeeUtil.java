package edu.born.overseer.util;

import edu.born.overseer.model.Employee;
import edu.born.overseer.transformed.TransformedEmployee;

public class EmployeeUtil {

    public static TransformedEmployee asTo(Employee employee) {
        return new TransformedEmployee(employee.getId(), employee.getLogin(), employee.getPassword());
    }


}
