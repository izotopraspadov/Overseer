package edu.born.overseer.repository.implementation;

import edu.born.overseer.data.PhoneTestData;
import edu.born.overseer.data.SalaryTestData;
import edu.born.overseer.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

import static edu.born.overseer.data.EmailTestData.EMPLOYEE_1_EMAILS;
import static edu.born.overseer.data.EmployeeTestData.*;
import static edu.born.overseer.data.PhoneTestData.EMPLOYEE_1_PHONES;
import static edu.born.overseer.data.TestDataUtil.INVALID_ID;
import static edu.born.overseer.data.TestDataUtil.NEXT_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        employeeRepository.evictCache();
    }

    @Test
    void create() {
        var prepared = getPreparedCreate();

        var savedId = employeeRepository
                .save(prepared, prepared.getRegion().getId())
                .getId();

        var received = employeeRepository.getById(savedId);

        assertEquals(received, prepared);
        assertEquals(List.copyOf(received.getEmails()), List.copyOf(prepared.getEmails()));
        assertEquals(List.copyOf(received.getPhones()), List.copyOf(prepared.getPhones()));
    }

    @Test
    void createDuplicate() {
        var prepared = getPreparedDuplicate();
        assertThrows(DataIntegrityViolationException.class, () -> employeeRepository.save(prepared, prepared.getRegion().getId()));
    }

    @Test
    void createNewPhone() {
        var received = employeeRepository.getById(EMPLOYEE_1_ID);

        // create new
        var newPhone = PhoneTestData.getPreparedCreate();

        received.getPhones().add(newPhone);

        // update
        employeeRepository
                .save(received, received.getRegion().getId());

        var updatedPhoneSet = employeeRepository.getById(EMPLOYEE_1_ID)
                .getPhones();

        assertThat(updatedPhoneSet, contains(newPhone.id(NEXT_ID),
                PhoneTestData.EMPLOYEE_PHONE_3,
                PhoneTestData.EMPLOYEE_PHONE_2,
                PhoneTestData.EMPLOYEE_PHONE_1));
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();

        var updatedId = employeeRepository
                .save(prepared, prepared.getRegion().getId())
                .getId();

        var received = employeeRepository.getById(updatedId);

        assertEquals(received, prepared);
    }

    @Test
    void delete() {
        employeeRepository.delete(EMPLOYEE_1_ID);
        assertNull(employeeRepository.getById(EMPLOYEE_1_ID));
    }

    @Test
    void deleteNotExecute() {
        assertEquals(employeeRepository.delete(INVALID_ID), Boolean.FALSE);
    }

    @Test
    void getById() {
        var received = employeeRepository.getById(EMPLOYEE_1_ID);

        assertEquals(received, EMPLOYEE_1);
        assertEquals(received.getEmails(), EMPLOYEE_1_EMAILS);
        assertEquals(received.getPhones(), EMPLOYEE_1_PHONES);
        assertEquals(received.getSalary(), Set.of(SalaryTestData.SALARY_7));
    }

    @Test
    void getByLogin() {
        assertEquals(employeeRepository.getByLogin(EMPLOYEE_1_LOGIN), EMPLOYEE_1);
    }

    @Test
    void getByLoginNotFound() {
        assertThrows(NoResultException.class, () -> employeeRepository.getByLogin(INVALID_LOGIN));
    }

  /*  @Test
    void getAll() {
        assertThat(employeeRepository.getAll(unlimitedPageLength()), contains(EMPLOYEE_4,
                EMPLOYEE_6,
                EMPLOYEE_5,
                EMPLOYEE_1,
                EMPLOYEE_3,
                EMPLOYEE_2)
        );
    }

    @Test
    void getAllByRegion() {
        assertThat(employeeRepository.getAllByRegion(RegionTestData.REGION_1_ID, unlimitedPageLength()), contains(EMPLOYEE_1, EMPLOYEE_2));
    }

    @Test
    void getAllByAddress() {
        assertThat(employeeRepository.getAllByAddress(EMPLOYEE_1.getAddress(), unlimitedPageLength()), contains(EMPLOYEE_1));
    }

    @Test
    void getAllByAddressPartialMatch() {
        assertThat(employeeRepository.getAllByAddress("ул.", unlimitedPageLength()), contains(EMPLOYEE_4, EMPLOYEE_6));
    }

    @Test
    void getAllByFullName() {
        assertThat(employeeRepository.getAllByFullName(EMPLOYEE_1.getFullName(), unlimitedPageLength()), contains(EMPLOYEE_1));
    }

    @Test
    void getAllByFullNamePartialMatch() {
        assertThat(employeeRepository.getAllByFullName("Роман", unlimitedPageLength()), contains(EMPLOYEE_1, EMPLOYEE_3));
    }
*/
}