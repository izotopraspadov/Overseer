package edu.born.overseer.service.impl;

import edu.born.overseer.repository.EmployeeRepository;
import edu.born.overseer.web.AuthorizedUser;
import edu.born.overseer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public AuthorizedUser loadUserByUsername(String login) throws UsernameNotFoundException {

        Employee employee = employeeRepository
                .findByLogin(login.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("Employee " + login + " is not found"));

        return new AuthorizedUser(employee);
    }

}
