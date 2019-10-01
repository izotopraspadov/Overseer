DROP TABLE IF EXISTS contact_persons;
DROP TABLE IF EXISTS company_payments;
DROP TABLE IF EXISTS planned_time;
DROP TABLE IF EXISTS actual_time;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS objects;
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
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    full_name     VARCHAR(255)    NOT NULL,
    company_id    INTEGER         NOT NULL,
    phone_numbers VARCHAR(15)[3]  NOT NULL,
    emails        VARCHAR(255)[3] NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE
);

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
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    full_name     VARCHAR(255)    NOT NULL,
    region_id     INTEGER         NOT NULL,
    phone_numbers VARCHAR(15)[3]  NOT NULL,
    emails        VARCHAR(255)[3] NOT NULL,
    address       VARCHAR(255),
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
    date_start  DATE    NOT NULL,
    date_end    DATE    NOT NULL,
    amount      MONEY   NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE groups
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title      VARCHAR(255) NOT NULL,
    group_type VARCHAR(255) NOT NULL,
    comment    TEXT
);

CREATE TABLE objects
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
    type               VARCHAR(255) NOT NULL,
    payment_order      VARCHAR(255) NOT NULL,
    quantity_string    SMALLINT     NOT NULL,
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
    FOREIGN KEY (object_id) REFERENCES objects (id) ON DELETE CASCADE,
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
    FOREIGN KEY (object_id) REFERENCES objects (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE tasks
(
    id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    object_id      INTEGER      NOT NULL,
    task           TEXT         NOT NULL,
    employee_id    INTEGER      NOT NULL,
    date_completed DATE         NOT NULL,
    result         VARCHAR(255) NOT NULL,
    comment        TEXT,
    for_rg         TEXT, -- NEED EDIT
    for_manager    TEXT, -- NEED EDIT
    FOREIGN KEY (object_id) REFERENCES objects (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);