package edu.born.overseer.web;

import edu.born.overseer.model.Salary;
import edu.born.overseer.repository.SalaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractSalaryController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SalaryRepository salaryRepository;

    public Salary create(Salary salary, int employeeId) {
        log.info("create salary {} for employee {}", salary, employeeId);
        return salaryRepository.save(salary, employeeId);
    }

    public Salary update(Salary salary, int id, int employeeId) {
        log.info("update salary {} for employee {}", salary, employeeId);
        return salaryRepository.save(salary, employeeId);
    }

    public boolean delete(int id) {
        log.info("delete salary {}", id);
        return salaryRepository.delete(id);
    }

    public Salary getCurrentByEmployee(int employeeId) {
        log.info("get current salary by employee {}", employeeId);
        return salaryRepository.getCurrentByEmployee(employeeId);
    }

    public List<Salary> getAllByEmployee(int employeeId) {
        log.info("get salaries by employee {}", employeeId);
        return salaryRepository.getAllByEmployee(employeeId);
    }

}
