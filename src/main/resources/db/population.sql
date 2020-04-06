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
VALUES ('Алтайский край'),               -- id 100000
       ('Амурская область'),             -- id 100001
       ('Владимирская область'),         -- id 100002
       ('г. Москва'),                    -- id 100003
       ('Еврейская автономная область'), -- id 100004
       ('Ивановская область'),           -- id 100005
       ('Камчатский край'),              -- id 100006
       ('Ленинградская область'),        -- id 100007
       ('Омская область'),               -- id 100008
       ('Республика Алтай'),             -- id 100009
       ('Самарская область'),            -- id 100010
       ('Санкт-Петербург'),              -- id 100011
       ('Ярославская область'); -- id 100012

INSERT INTO companies (title, region_id, itn, address, reliability, chat_group_name, type_company)
VALUES -- id 100013
       ('Первая Компания', 100000, '000000000000', 'Никакая ул. 1', 'LOW', 'Первая', 'OUR'),
       -- id 100014
       ('Вторая Компания', 100001, '0000000001', 'Наша ул. 2', 'MIDDLE', 'Вторая', 'CUSTOMER'),
       -- id 100015
       ('Третья Компания', 100012, '0000000002', 'Зимняя ул. 89', 'HIGH', 'Третья', 'OTHER');


INSERT INTO contact_persons (full_name, company_id)
VALUES ('Иванов Иван Иванович', 100013),           -- id 100016
       ('Семёнов Семён Семёнович', 100013),        -- id 100017
       ('Петров Пётр Петрович', 100013),           -- id 100018
       ('Васильева Галина Васильевна', 100014),    -- id 100019
       ('Иванова Мария Семёновна', 100014),        -- id 100020
       ('Вадимов Вадим Вадимович', 100014),        -- id 100021
       ('Чернышевская Милана Фёдоровная', 100015), -- id 100022
       ('Андреев Андрей Андреевич', 100015); -- id 100023

INSERT INTO employees (full_name, region_id, login, password, address)
VALUES ('Романов Роман Романович', 100000, 'user', 'user', 'Заставская ул. 6'),         -- id 100024
       ('Степанов Степан Степанович', 100000, 'stepanov', 'user2', 'Четвёртый пр. 10'), -- id 100025
       ('Романова Дарья Петровна', 100002, 'romanova', 'user3', 'Пр. Отчаянных, 90'),   -- id 100026
       ('Дмитриев Дмитрий Дмитриевич', 100003, 'dmitriev', 'user4', 'Непростая ул. 7'), -- id 100027
       ('Максимов Максим Максимович', 100007, 'admin', 'admin', 'Пр. Дно. 11'),         -- id 100028
       ('Зайцева Мария Иосифовна', 100007, 'zaiceva', 'admin2', 'Малая Опечаленная ул. 14'); -- id 100029

INSERT INTO employee_roles (employee_id, role)
VALUES (100024, 'USER'),
       (100025, 'USER'),
       (100026, 'USER'),
       (100027, 'USER'),
       (100028, 'USER'),
       (100028, 'ADMIN'),
       (100029, 'USER'),
       (100029, 'ADMIN');


INSERT INTO emails (address, type_owner, contact_person_id, employee_id)
VALUES ('1@mail.com', 'CONTACT_PERSON', 100016, null),  -- id 100030

       ('2@mail.com', 'CONTACT_PERSON', 100017, null),  -- id 100031
       ('3@mail.com', 'CONTACT_PERSON', 100017, null),  -- id 100032

       ('4@mail.com', 'CONTACT_PERSON', 100018, null),  -- id 100033

       ('5@mail.com', 'CONTACT_PERSON', 100019, null),  -- id 100034
       ('6@mail.com', 'CONTACT_PERSON', 100019, null),  -- id 100035
       ('7@mail.com', 'CONTACT_PERSON', 100019, null),  -- id 100036

       ('8@mail.com', 'CONTACT_PERSON', 100020, null),  -- id 100037
       ('9@mail.com', 'CONTACT_PERSON', 100020, null),  -- id 100038

       ('10@mail.com', 'CONTACT_PERSON', 100021, null), -- id 100039
       ('11@mail.com', 'CONTACT_PERSON', 100021, null), -- id 100040
       ('12@mail.com', 'CONTACT_PERSON', 100021, null), -- id 100041

       ('13@mail.com', 'CONTACT_PERSON', 100022, null), -- id 100042

       ('14@mail.com', 'CONTACT_PERSON', 100023, null), -- id 100043
       ('15@mail.com', 'CONTACT_PERSON', 100023, null), -- id 100044

       ('16@mail.com', 'EMPLOYEE', null, 100024),       -- id 100045
       ('17@mail.com', 'EMPLOYEE', null, 100024),       -- id 100046

       ('18@mail.com', 'EMPLOYEE', null, 100025),       -- id 100047

       ('19@mail.com', 'EMPLOYEE', null, 100026),       -- id 100048
       ('20@mail.com', 'EMPLOYEE', null, 100026),       -- id 100049
       ('21@mail.com', 'EMPLOYEE', null, 100026),       -- id 100050

       ('22@mail.com', 'EMPLOYEE', null, 100027),       -- id 100051
       ('23@mail.com', 'EMPLOYEE', null, 100027),       -- id 100052

       ('24@mail.com', 'EMPLOYEE', null, 100028),       -- id 100053

       ('25@mail.com', 'EMPLOYEE', null, 100029),       -- id 100054
       ('26@mail.com', 'EMPLOYEE', null, 100029);
-- id 100055

INSERT INTO phones (number, type_owner, contact_person_id, employee_id)
VALUES ('00000000001', 'CONTACT_PERSON', 100016, null), -- id 100056
       ('00000000002', 'CONTACT_PERSON', 100016, null), -- id 100057

       ('00000000003', 'CONTACT_PERSON', 100017, null), -- id 100058

       ('00000000004', 'CONTACT_PERSON', 100018, null), -- id 100059
       ('00000000005', 'CONTACT_PERSON', 100018, null), -- id 100060
       ('00000000006', 'CONTACT_PERSON', 100018, null), -- id 100061

       ('00000000007', 'CONTACT_PERSON', 100019, null), -- id 100062
       ('00000000008', 'CONTACT_PERSON', 100019, null), -- id 100063

       ('00000000009', 'CONTACT_PERSON', 100020, null), -- id 100064

       ('00000000010', 'CONTACT_PERSON', 100021, null), -- id 100065

       ('00000000011', 'CONTACT_PERSON', 100022, null), -- id 100066
       ('00000000012', 'CONTACT_PERSON', 100022, null), -- id 100067

       ('00000000013', 'CONTACT_PERSON', 100023, null), -- id 100068

       ('00000000014', 'EMPLOYEE', null, 100024),       -- id 100069
       ('00000000015', 'EMPLOYEE', null, 100024),       -- id 100070
       ('00000000016', 'EMPLOYEE', null, 100024),       -- id 100071

       ('00000000017', 'EMPLOYEE', null, 100025),       -- id 100072

       ('00000000018', 'EMPLOYEE', null, 100026),       -- id 100073

       ('00000000019', 'EMPLOYEE', null, 100027),       -- id 100074
       ('00000000020', 'EMPLOYEE', null, 100027),       -- id 100075

       ('00000000022', 'EMPLOYEE', null, 100028),       -- id 100076

       ('00000000023', 'EMPLOYEE', null, 100029),       -- id 100077
       ('00000000024', 'EMPLOYEE', null, 100029);
-- id 100078

INSERT INTO employee_payments (date, employee_id, type_counterparty,
                               company_counterparty_id, employee_counterparty_id, transaction, cashless, charge,
                               comment)
VALUES ('2019-10-01', 100024, 'COMPANY', 100013, null, 10000.00, true, true, '$$$'),          -- id 100079
       ('2019-10-01', 100025, 'EMPLOYEE', null, 100024, 20000.00, false, false, 'Give me %'), -- id 100080
       ('2019-10-02', 100024, 'EMPLOYEE', null, 100025, 2000.00, false, false, null),         -- id 100081
       ('2019-10-02', 100026, 'COMPANY', 100014, null, 30000.00, true, false, null),          -- id 100082
       ('2019-10-03', 100027, 'EMPLOYEE', null, 100027, 5000.00, false, true, 'Good Job'),    -- id 100083
       ('2019-10-03', 100026, 'EMPLOYEE', null, 100027, 5000.00, false, true, '$$$'),         -- id 100084
       ('2019-10-04', 100027, 'COMPANY', 100015, null, 7000.00, true, false, null),           -- id 100085
       ('2019-10-04', 100028, 'COMPANY', 100015, null, 9000.00, true, true, null); -- id 100086

INSERT INTO salaries (employee_id, start_date, end_date, amount)
VALUES (100024, '2019-09-01', '2019-10-01', 35000.00), -- id 100087
       (100025, '2019-09-03', null, 30000.00),         -- id 100088
       (100026, '2019-09-05', null, 29000.00),         -- id 100089
       (100027, '2019-09-07', null, 40000.00),         -- id 100090
       (100028, '2019-09-07', null, 50000.00),         -- id 100091
       (100029, '2019-09-07', null, 33000.00),         -- id 100092
       (100024, '2019-10-01', null, 45000.00); -- id 100093

INSERT INTO order_type (title)
VALUES ('Проект'),            -- id 100094
       ('Исп. документация'), -- id 100095
       ('Смета'),             -- id 100096
       ('Юр. Услуги'); -- id 100097

INSERT INTO groups (title, comment)
VALUES ('001', 'Need to get the resultType! Now!'), -- id 100098
       ('002', null),                           -- id 100099
       ('003', null),                           -- id 100100
       ('004', 'Good luck...'),                 -- id 100101
       ('005', null); -- id 100102

INSERT INTO order_type_by_group (group_id, order_type_id)
VALUES (100098, 100094),
       (100099, 100095),
       (100100, 100096),
       (100101, 100096),
       (100102, 100097);

INSERT INTO orders (company_id, title, cashless, contract_is_need, contract_exists, planned_start_date,
                    actual_start_date, planned_end_date, actual_end_date, sum, expected_payment, payment_format,
                    number_of_lines, group_id, manager_id, underway, order_type_id)
VALUES (100013, 'First Project', false, false, true, '2019-09-01', '2019-09-10', '2019-10-01',
        null, 100000.00, 17000.00, '100', null, 100098, 100024, true, 100094),        -- id 100103
       (100013, 'First  Estimate', true, true, false, '2019-09-03', '2019-09-03', '2019-10-05',
        null, 10000.00, 15000.00, '30-70', 50, 100100, 100025, true, 100096),         -- id 100104
       (100014, 'Second Project', false, false, true, '2019-09-01', '2019-09-10', '2019-10-01',
        '2019-10-01', 200000.00, 0.00, '50-50', null, 100102, 100026, false, 100094), -- id 100105
       (100015, 'First Legal Service', true, true, false, '2019-09-07', '2019-09-07', '2019-10-07',
        null, 50000.00, 10000.00, '20-20-60', null, 100102, 100027, true, 100097); -- id 100106

INSERT INTO order_payments (date, company_id, order_id, our_company_id, transaction, cashless,
                            comment)
VALUES ('2019-09-11', 100013, 100103, 100013, 100000.00, true, '$$$'), -- id 100107
       ('2019-09-13', 100013, 100104, 100013, 3000.00, false, null),   -- id 100108
       ('2019-09-15', 100013, 100104, 100013, 7000.00, false, '%%%'),  -- id 100109
       ('2019-09-15', 100014, 100105, 100013, 100000.00, true, null),  -- id 100110
       ('2019-09-19', 100014, 100105, 100013, 100000.00, true, null),  -- id 100111
       ('2019-09-20', 100015, 100106, 100013, 10000.00, false, null),  -- id 100112
       ('2019-09-29', 100015, 100106, 100013, 10000.00, true, '777'),  -- id 100113
       ('2019-09-29', 100015, 100106, 100013, 30000.00, false, 'Done!'); -- id 100114

INSERT INTO planned_time (order_id, employee_id, man_hours)
VALUES (100103, 100029, 30), -- id 100115
       (100104, 100028, 10), -- id 100116
       (100105, 100025, 40), -- id 100117
       (100106, 100024, 15); -- id 100118

INSERT INTO actual_time (order_id, employee_id, date, actual_man_hours, account_man_hours)
VALUES (100103, 100029, '2019-09-20', 15, 30), -- id 100119
       (100104, 100028, '2019-09-25', 7, 10),  -- id 100120
       (100105, 100025, '2019-09-23', 35, 40), -- id 100121
       (100106, 100024, '2019-10-01', 13, 15); -- id 100122

INSERT INTO tasks (order_id, task_description, responsible_id, date_completed, result, comment)
VALUES (100103, 'Step 1st', 100026, '2019-09-20', 'PARTIALLY_COMPLETED', 'Step 1st done!'),          -- id 100123
       (100103, 'Step 2st', 100027, '2019-09-24', 'PARTIALLY_COMPLETED', 'Step 2st done!'),          -- id 100124
       (100104, 'Estimate 001', 100026, '2019-09-25', 'COMPLETED', null),                            -- id 100125
       (100105, '001 Second Pr.', 100029, '2019-09-15', 'NOT_COMPLETED', null),                      -- id 100126
       (100105, '002 Second Pr.', 100028, '2019-09-21', 'PARTIALLY_COMPLETED', null),                -- id 100127
       (100105, '003 Second Pr.', 100029, '2019-09-26', 'COMPLETED', null),                          -- id 100128
       (100106, 'Legal Service By Customer 01', 100027, '2019-09-13', 'NOT_COMPLETED', 'Not Done!'), -- id 100129
       (100106, 'Legal Service By Customer 02', 100027, '2019-09-29', 'COMPLETED', 'Done!'); -- id 100130

INSERT INTO task_emails (task_id, email_id, type_send)
VALUES (100123, 100030, 'TEAM_LEADER'),
       (100123, 100045, 'MANAGER'),

       (100124, 100031, 'TEAM_LEADER'),
       (100124, 100046, 'MANAGER'),

       (100125, 100032, 'TEAM_LEADER'),
       (100125, 100047, 'MANAGER'),

       (100126, 100033, 'TEAM_LEADER'),
       (100126, 100048, 'MANAGER'),

       (100127, 100034, 'TEAM_LEADER'),
       (100127, 100035, 'TEAM_LEADER'),
       (100127, 100039, 'TEAM_LEADER'),
       (100127, 100049, 'MANAGER'),

       (100128, 100050, 'TEAM_LEADER'),
       (100128, 100051, 'MANAGER'),
       (100128, 100040, 'MANAGER'),
       (100128, 100041, 'MANAGER'),

       (100129, 100030, 'TEAM_LEADER'),
       (100129, 100031, 'TEAM_LEADER'),
       (100129, 100032, 'TEAM_LEADER'),
       (100129, 100034, 'MANAGER'),
       (100129, 100035, 'MANAGER'),
       (100129, 100036, 'MANAGER'),

       (100130, 100045, 'TEAM_LEADER'),
       (100130, 100046, 'TEAM_LEADER'),
       (100130, 100047, 'TEAM_LEADER'),
       (100130, 100048, 'MANAGER'),
       (100130, 100049, 'MANAGER');
