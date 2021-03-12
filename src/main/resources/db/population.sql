TRUNCATE regions,
    companies,
    contact_persons,
    employees,
    emails,
    phones,
    employee_payments,
    employee_roles,
    salaries,
    order_type,
    groups,
    order_type_by_group,
    orders,
    order_payments,
    planned_time,
    actual_time,
    tasks,
    task_emails;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO regions (title)
VALUES ('Vladimirskaya oblast'),
       ('Moscow'),
       ('St. Petersburg');

INSERT INTO companies (title, region_id, itn, address, reliability_type, chat_group_name, company_type)
VALUES ('First Company', 100000, '000000000000', 'No st. one', 'LOW', 'First', 'OUR'),
       ('Second Company', 100001, '0000000001', 'Our street 2', 'MIDDLE', 'Second', 'CUSTOMER'),
       ('Third Company', 100002, '0000000002', 'Winter st. 89', 'HIGH', 'Third', 'OTHER');

INSERT INTO contact_persons (full_name, company_id)
VALUES ('Ivanov Ivan Ivanovich', 100003),
       ('Semyonov Semyon Semyonovich', 100004),
       ('Petrov Petr Petrovich', 100005);

INSERT INTO employees (full_name, region_id, login, password, address)
VALUES ('Romanov Roman Romanovich', 100000, 'admin', '{noop}admin', 'Zastavskaya st. 6'),
       ('Stepanov Stepan Stepanovich', 100001, 'stepanov', '{noop}user1', 'Fourth pr. 10'),
       ('Romanova Daria Petrovna', 100002, 'user', '{noop}user', 'Etc. Desperate, 90');

INSERT INTO employee_roles (employee_id, role)
VALUES (100009, 'ROLE_USER'),
       (100010, 'ROLE_USER'),
       (100011, 'ROLE_USER'),
       (100009, 'ROLE_ADMIN'),
       (100010, 'ROLE_ADMIN');

INSERT INTO emails (address, owner_type, contact_person_id, employee_id)
VALUES ('1@mail.com', 'CONTACT_PERSON', 100006, null),

       ('2@mail.com', 'CONTACT_PERSON', 100007, null),
       ('3@mail.com', 'CONTACT_PERSON', 100007, null),

       ('4@mail.com', 'CONTACT_PERSON', 100008, null),
       ('5@mail.com', 'CONTACT_PERSON', 100008, null),
       ('6@mail.com', 'CONTACT_PERSON', 100008, null),

       ('7@mail.com', 'EMPLOYEE', null, 100009),
       ('8@mail.com', 'EMPLOYEE', null, 100009),

       ('9@mail.com', 'EMPLOYEE', null, 100010),

       ('10@mail.com', 'EMPLOYEE', null, 100011),
       ('11@mail.com', 'EMPLOYEE', null, 100011),
       ('12@mail.com', 'EMPLOYEE', null, 100011);

INSERT INTO phones (number, owner_type, contact_person_id, employee_id)
VALUES ('+7-000-000-00-01', 'CONTACT_PERSON', 100006, null),
       ('+7-000-000-00-02', 'CONTACT_PERSON', 100006, null),

       ('+7-000-000-00-03', 'CONTACT_PERSON', 100007, null),

       ('+7-000-000-00-04', 'CONTACT_PERSON', 100008, null),
       ('+7-000-000-00-05', 'CONTACT_PERSON', 100008, null),

       ('+7-000-000-00-14', 'EMPLOYEE', null, 100009),
       ('+7-000-000-00-15', 'EMPLOYEE', null, 100009),
       ('+7-000-000-00-16', 'EMPLOYEE', null, 100009),

       ('+7-000-000-00-17', 'EMPLOYEE', null, 100010),

       ('+7-000-000-00-18', 'EMPLOYEE', null, 100011);

INSERT INTO employee_payments (date, employee_id, counterparty_type,
                               company_counterparty_id, employee_counterparty_id, transaction, cashless, charge,
                               comment)
VALUES ('2019-10-01', 100009, 'COMPANY', 100003, null, 10000.00, true, true, '$$$'),
       ('2019-10-01', 100009, 'EMPLOYEE', null, 100010, 20000.00, false, false, 'Give me %'),
       ('2019-10-02', 100010, 'EMPLOYEE', null, 100011, 2000.00, false, false, null),
       ('2019-10-02', 100011, 'COMPANY', 100004, null, 30000.00, true, false, null);

INSERT INTO salaries (employee_id, start_date, end_date, amount)
VALUES (100009, '2019-09-01', '2019-10-01', 35000.00),
       (100009, '2019-09-03', null, 30000.00),
       (100010, '2019-09-05', null, 29000.00),
       (100011, '2019-09-07', null, 40000.00);

INSERT INTO order_type (title)
VALUES ('Project'),
       ('Executive documentation'),
       ('Estimate'),
       ('Legal services');

INSERT INTO groups (title, comment)
VALUES ('001', 'Need to get the result! Now!'),
       ('002', null),
       ('003', 'Good luck...');

INSERT INTO order_type_by_group (group_id, order_type_id)
VALUES (100046, 100042),
       (100046, 100043),
       (100047, 100044),
       (100048, 100045);

INSERT INTO orders (company_id, title, cashless, contract_is_need, contract_exists, planned_start_date,
                    actual_start_date, planned_end_date, actual_end_date, sum, expected_payment, payment_format,
                    number_of_lines, group_id, manager_id, underway, order_type_id)
VALUES (100003, 'First Project', false, false, true, '2019-09-01', '2019-09-10', '2019-10-01',
        null, 100000.00, 17000.00, '100', null, 100046, 100009, true, 100042),
       (100004, 'Second Project', false, false, true, '2019-09-01', '2019-09-10', '2019-10-01',
        '2019-10-01', 200000.00, 0.00, '50-50', null, 100047, 100010, false, 100043),
       (100005, 'First Legal Service', true, true, false, '2019-09-07', '2019-09-07', '2019-10-07',
        null, 50000.00, 10000.00, '20-20-60', null, 100048, 100011, true, 100044);

INSERT INTO order_payments (date, company_id, order_id, our_company_id, transaction, cashless, comment)
VALUES ('2019-09-11', 100003, 100049, 100003, 100000.00, true, '$$$'),
       ('2019-09-19', 100004, 100051, 100003, 100000.00, true, null),
       ('2019-09-29', 100005, 100049, 100003, 30000.00, false, 'Done!');

INSERT INTO planned_time (order_id, employee_id, man_hours)
VALUES (100049, 100009, 30),
       (100050, 100010, 10),
       (100051, 100011, 40);

INSERT INTO actual_time (order_id, employee_id, date, actual_man_hours, account_man_hours)
VALUES (100049, 100009, '2019-09-20', 15, 30),
       (100050, 100010, '2019-09-25', 7, 10),
       (100051, 100011, '2019-09-23', 35, 40);

INSERT INTO tasks (order_id, description, responsible_id, date_completed, result_type, comment)
VALUES (100049, 'Step 1st', 100009, '2019-09-20', 'PARTIALLY_COMPLETED', 'Step 1st done!'),
       (100050, 'Estimate 001', 100010, '2019-09-25', 'COMPLETED', null),
       (100051, '001 Second Pr.', 100011, '2019-09-15', 'NOT_COMPLETED', null);

INSERT INTO task_emails (task_id, email_id, send_type)
VALUES (100061, 100018, 'TEAM_LEADER'),

       (100062, 100019, 'MANAGER'),

       (100063, 100020, 'TEAM_LEADER'),
       (100063, 100021, 'MANAGER'),
       (100063, 100022, 'TEAM_LEADER');