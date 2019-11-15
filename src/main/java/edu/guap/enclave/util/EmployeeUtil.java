package edu.guap.enclave.util;

import edu.guap.enclave.model.Employee;
import edu.guap.enclave.to.UserTo;

public class EmployeeUtil {

    public static UserTo asTo(Employee employee) {
        return new UserTo(employee.getId(), employee.getLogin(), employee.getPassword());
    }


}
