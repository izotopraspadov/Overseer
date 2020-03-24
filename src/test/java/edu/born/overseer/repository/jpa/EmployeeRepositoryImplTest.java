package edu.born.overseer.repository.jpa;

import edu.born.overseer.model.Employee;
import edu.born.overseer.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/POPULATE_DB.sql", config = @SqlConfig(encoding = "UTF-8"))
class EmployeeRepositoryImplTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void save() {

    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getWithPayments() {
        // employeeRepository.getWithPayments(100026).getPayments().forEach(System.out::println);
    }

    @Test
    void getWithSalary() {
    }

    @Test
    void getWithSalaryAndPhonesAndEmails() {
        Optional<Employee> employee = employeeRepository.getWithSalaryAndPhonesAndEmails(100027);
        employee.get().getSalary().forEach(System.out::println);
    }

    @Test
    void getWithEmails() {
        // employeeRepository.getWithEmails(100026).getEmails().forEach(System.out::println);
    }

    @Test
    void getWithPhones() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllByRegion() {
    }

    @Test
    void findByLogin() {
    }

    @Test
    void findByAddress() {
    }
}