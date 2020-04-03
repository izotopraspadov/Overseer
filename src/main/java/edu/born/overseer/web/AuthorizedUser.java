package edu.born.overseer.web;

import edu.born.overseer.model.Employee;
import edu.born.overseer.transformed.UserTo;
import edu.born.overseer.util.EmployeeUtil;


public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(Employee employee) {
        super(employee.getLogin(), employee.getPassword(), employee.getRoles());
        this.userTo = EmployeeUtil.asTo(employee);
    }

    public int getId() {
        return userTo.getId();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }



    @Override
    public String toString() {
        return userTo.toString();
    }
}
