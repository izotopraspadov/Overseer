DROP TABLE IF EXISTS contact_person_phones;
DROP TABLE IF EXISTS contact_person_emails;
DROP TABLE IF EXISTS employee_phones;
DROP TABLE IF EXISTS employee_emails;
DROP TABLE IF EXISTS contact_persons;
DROP TABLE IF EXISTS company_payments;
DROP TABLE IF EXISTS planned_time;
DROP TABLE IF EXISTS actual_time;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS ordered_objects;
DROP TABLE IF EXISTS employee_payments;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS salaries;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS regions;
DROP TABLE IF EXISTS groups;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE regions
(
    id    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX regions_unique_title_idx ON regions (title);

CREATE TABLE companies
(
    id                  INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title               VARCHAR(255) NOT NULL,
    region_id           INTEGER      NOT NULL,
    itn                 VARCHAR(12)  NOT NULL,
    address             VARCHAR(255) NOT NULL,
    reliability         VARCHAR(255) NOT NULL,
    whatsapp_group_name VARCHAR(255) NOT NULL,
    type_company        VARCHAR(255) NOT NULL,
    FOREIGN KEY (region_id) REFERENCES regions (id) ON DELETE CASCADE
);

CREATE TABLE contact_persons
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    full_name  VARCHAR(255) NOT NULL,
    company_id INTEGER      NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX contact_persons_unique_id_company_id_idx ON contact_persons (id, company_id);

CREATE TABLE company_payments
(
    id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date           DATE                DEFAULT now() NOT NULL,
    company_id     INTEGER                           NOT NULL,
    our_company_id INTEGER                           NOT NULL,
    transaction    MONEY                             NOT NULL,
    cashless       BOOL                              NOT NULL,
    comment        TEXT,
    FOREIGN KEY (our_company_id) REFERENCES companies (id) ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE
);

CREATE TABLE employees
(
    id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    full_name VARCHAR(255) NOT NULL,
    region_id INTEGER      NOT NULL,
    address   VARCHAR(255),
    FOREIGN KEY (region_id) REFERENCES regions (id) ON DELETE CASCADE
);

CREATE TABLE employee_payments
(
    id                       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date                     DATE                DEFAULT now() NOT NULL,
    employee_id              INTEGER                           NOT NULL,
    type_counterparty        VARCHAR(255)                      NOT NULL,
    company_counterparty_id  INTEGER,
    employee_counterparty_id INTEGER,
    transaction              MONEY                             NOT NULL,
    type_payment             VARCHAR(255)                      NOT NULL,
    comment                  TEXT,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_counterparty_id) REFERENCES employees (id) ON DELETE CASCADE,
    FOREIGN KEY (company_counterparty_id) REFERENCES companies (id) ON DELETE CASCADE
);

CREATE TABLE salaries
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    employee_id INTEGER NOT NULL,
    start_date  DATE    NOT NULL,
    end_date    DATE    NOT NULL,
    amount      MONEY   NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE groups
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title       VARCHAR(255) NOT NULL,
    object_type VARCHAR(255) NOT NULL,
    comment     TEXT
);

CREATE UNIQUE INDEX groups_unique_title_idx ON groups (title);

CREATE TABLE ordered_objects
(
    id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    company_id         INTEGER      NOT NULL,
    title              VARCHAR(255) NOT NULL,
    cashless           BOOL         NOT NULL,
    contract_is_need   BOOL         NOT NULL,
    contract_exists    BOOL         NOT NULL,
    planned_start_date DATE         NOT NULL,
    actual_start_date  DATE         NOT NULL,
    planned_end_date   DATE         NOT NULL,
    actual_end_date    DATE         NOT NULL,
    sum                MONEY        NOT NULL,
    object_type        VARCHAR(255) NOT NULL,
    payment_order      VARCHAR(255) NOT NULL,
    number_of_lines    SMALLINT     NOT NULL,
    group_id           INTEGER      NOT NULL,
    manager_id         INTEGER      NOT NULL,
    underway           BOOL         NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE,
    FOREIGN KEY (manager_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE planned_time
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    object_id   INTEGER  NOT NULL,
    employee_id INTEGER  NOT NULL,
    time        SMALLINT NOT NULL,
    FOREIGN KEY (object_id) REFERENCES ordered_objects (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE actual_time
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    object_id       INTEGER  NOT NULL,
    employee_id     INTEGER  NOT NULL,
    date            DATE     NOT NULL,
    actual_time     SMALLINT NOT NULL,
    accounting_time SMALLINT NOT NULL,
    FOREIGN KEY (object_id) REFERENCES ordered_objects (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE tasks
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    object_id        INTEGER      NOT NULL,
    task_description TEXT         NOT NULL,
    employee_id      INTEGER      NOT NULL,
    date_completed   DATE         NOT NULL,
    result           VARCHAR(255) NOT NULL,
    comment          TEXT,
    for_rg           TEXT, -- NEED EDIT
    for_manager      TEXT, -- NEED EDIT
    FOREIGN KEY (object_id) REFERENCES ordered_objects (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE contact_person_phones
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    contact_person_id INTEGER     NOT NULL,
    number            VARCHAR(15) NOT NULL,
    FOREIGN KEY (contact_person_id) REFERENCES contact_persons (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX contact_person_phones_unique_number_idx ON contact_person_phones (number);

CREATE TABLE contact_person_emails
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    contact_person_id INTEGER      NOT NULL,
    email             VARCHAR(100) NOT NULL,
    FOREIGN KEY (contact_person_id) REFERENCES contact_persons (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX contact_person_emails_unique_email_idx ON contact_person_emails (email);

CREATE TABLE employee_phones
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    employee_id INTEGER     NOT NULL,
    number      VARCHAR(15) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX employee_phones_unique_number_idx ON employee_phones (number);

CREATE TABLE employee_emails
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    employee_id INTEGER      NOT NULL,
    email       VARCHAR(100) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX employee_emails_unique_email_idx ON employee_emails (email);

