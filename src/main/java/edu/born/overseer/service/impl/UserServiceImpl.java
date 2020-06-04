package edu.born.overseer.service.impl;

import edu.born.overseer.model.Employee;
import edu.born.overseer.repository.EmployeeRepository;
import edu.born.overseer.web.AuthorizedUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public UserServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String login) throws UsernameNotFoundException {

        Employee employee = Optional.of(employeeRepository
                .getByLogin(login.toLowerCase()))
                .orElseThrow(() -> new UsernameNotFoundException("Employee " + login + " is not found"));

        return new AuthorizedUser(employee);
    }

}
