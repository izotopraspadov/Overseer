package edu.born.overseer.web;

import edu.born.overseer.model.Employee;
import edu.born.overseer.transformed.TransformedEmployee;
import edu.born.overseer.util.EmployeeUtil;


public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private TransformedEmployee userTo;

    public AuthorizedUser(Employee employee) {
        super(employee.getLogin(), employee.getPassword(), employee.getRoles());
        this.userTo = EmployeeUtil.asTo(employee);
    }

    public int getId() {
        return userTo.getId();
    }

    public void update(TransformedEmployee newTo) {
        userTo = newTo;
    }

    public TransformedEmployee getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
