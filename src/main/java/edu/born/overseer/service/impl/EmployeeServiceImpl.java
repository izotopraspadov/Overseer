package edu.born.overseer.service.impl;

import edu.born.overseer.model.Employee;
import edu.born.overseer.repository.EmployeeRepository;
import edu.born.overseer.web.AuthorizedUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static edu.born.overseer.util.SecurityUtil.prepareToSave;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String login) throws UsernameNotFoundException {

        Employee employee = Optional.of(employeeRepository
                .getByLogin(login.toLowerCase()))
                .orElseThrow(() -> new UsernameNotFoundException("Employee " + login + " is not found"));

        return new AuthorizedUser(employee);
    }

    @Override
    public Employee save(Employee employee, int regionId) {
        return employeeRepository.save(prepareToSave(employee, passwordEncoder), regionId);
    }

    @Override
    public boolean delete(int id) {
        return employeeRepository.delete(id);
    }

    @Override
    public Employee getById(int id) {
        return employeeRepository.getById(id);
    }

    @Override
    public Employee getByLogin(String login) {
        return employeeRepository.getByLogin(login);
    }

    @Override
    public List<Employee> getAll(Integer page, Integer regionId, String address, String fullName) {
        return employeeRepository.getAll(page, regionId, address, fullName);
    }
}