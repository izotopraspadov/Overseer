DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
CREATE SEQUENCE global_seq START 100000;

CREATE TABLE regions
(
    id    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title VARCHAR(255) NOT NULL,
    CONSTRAINT region_unique_title_idx UNIQUE (title)
);

CREATE TABLE order_type
(
    id    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title VARCHAR(255) NOT NULL,
    CONSTRAINT order_type_unique_title_idx UNIQUE (title)
);

CREATE TABLE groups
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title   VARCHAR(255) NOT NULL,
    comment TEXT,
    CONSTRAINT group_unique_title_idx UNIQUE (title)
);

CREATE TABLE order_type_by_group
(
    group_id      INTEGER NOT NULL,
    order_type_id INTEGER NOT NULL,
    CONSTRAINT group_order_unique_type_idx UNIQUE (group_id, order_type_id),
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE,
    FOREIGN KEY (order_type_id) REFERENCES order_type (id) ON DELETE CASCADE
);

CREATE TABLE companies
(
    id                   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title                VARCHAR(255) NOT NULL,
    region_id            INTEGER      NOT NULL,
    itn                  VARCHAR(12)  NOT NULL,
    address              VARCHAR(255) NOT NULL,
    reliability          VARCHAR(255) NOT NULL,
    chat_group_name VARCHAR(255) NOT NULL,
    type_company         VARCHAR(255) NOT NULL,
    CONSTRAINT companies_unique_itn_idx UNIQUE (itn),
    FOREIGN KEY (region_id) REFERENCES regions (id) ON DELETE CASCADE
);

CREATE TABLE employees
(
    id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    full_name VARCHAR(255) NOT NULL,
    region_id INTEGER      NOT NULL,
    login     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    address   VARCHAR(255),
    CONSTRAINT employees_unique_login_idx UNIQUE (login),
    FOREIGN KEY (region_id) REFERENCES regions (id) ON DELETE CASCADE
);

CREATE TABLE employee_roles
(
    employee_id INTEGER      NOT NULL,
    role        VARCHAR(255) NOT NULL,
    CONSTRAINT employee_roles_unique_emp_role_idx UNIQUE (employee_id, role),
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE employee_payments
(
    id                       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date                     DATE                DEFAULT now() NOT NULL,
    employee_id              INTEGER                           NOT NULL,
    type_counterparty        VARCHAR(255)                      NOT NULL,
    company_counterparty_id  INTEGER,
    employee_counterparty_id INTEGER,
    transaction              NUMERIC(13, 2)                    NOT NULL,
    cashless                 BOOL                              NOT NULL,
    charge                   BOOL                              NOT NULL,
    comment                  TEXT,
    CHECK ((company_counterparty_id IS NOT NULL AND employee_counterparty_id IS NULL)
        OR (company_counterparty_id IS NULL AND employee_counterparty_id IS NOT NULL)),
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_counterparty_id) REFERENCES employees (id) ON DELETE CASCADE,
    FOREIGN KEY (company_counterparty_id) REFERENCES companies (id) ON DELETE CASCADE
);

CREATE TABLE salaries
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    employee_id INTEGER        NOT NULL,
    start_date  DATE           NOT NULL,
    end_date    DATE,
    amount      NUMERIC(13, 2) NOT NULL,
    CONSTRAINT salaries_unique_employee_start_date_idx UNIQUE (employee_id, start_date),
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE contact_persons
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    full_name  VARCHAR(255) NOT NULL,
    company_id INTEGER      NOT NULL,
    CONSTRAINT persons_unique_company_idx UNIQUE (id, company_id),
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE
);

CREATE TABLE emails
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    email             VARCHAR(100) NOT NULL,
    type_owner        VARCHAR(100) NOT NULL,
    contact_person_id INTEGER,
    employee_id       INTEGER,
    CONSTRAINT email_unique_idx UNIQUE (email),
    CHECK ((contact_person_id IS NOT NULL AND employee_id IS NULL)
        OR (contact_person_id IS NULL AND employee_id IS NOT NULL)),
    FOREIGN KEY (contact_person_id) REFERENCES contact_persons (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE phones
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    number            VARCHAR(15)  NOT NULL,
    type_owner        VARCHAR(100) NOT NULL,
    contact_person_id INTEGER,
    employee_id       INTEGER,
    CONSTRAINT phone_unique_idx UNIQUE (number),
    CHECK ((contact_person_id IS NOT NULL AND employee_id IS NULL)
        OR (contact_person_id IS NULL AND employee_id IS NOT NULL)),
    FOREIGN KEY (contact_person_id) REFERENCES contact_persons (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    company_id         INTEGER        NOT NULL,
    title              VARCHAR(255)   NOT NULL,
    cashless           BOOL           NOT NULL,
    contract_is_need   BOOL           NOT NULL,
    contract_exists    BOOL           NOT NULL,
    planned_start_date DATE           NOT NULL,
    actual_start_date  DATE,
    planned_end_date   DATE           NOT NULL,
    actual_end_date    DATE,
    sum                NUMERIC(13, 2) NOT NULL,
    expected_payment   NUMERIC(13, 2) NOT NULL,
    payment_order      VARCHAR(255)   NOT NULL,
    number_of_lines    SMALLINT,
    group_id           INTEGER        NOT NULL,
    manager_id         INTEGER        NOT NULL,
    underway           BOOL           NOT NULL,
    order_type_id      INTEGER        NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE,
    FOREIGN KEY (manager_id) REFERENCES employees (id) ON DELETE CASCADE,
    FOREIGN KEY (order_type_id) REFERENCES order_type (id) ON DELETE CASCADE
);

CREATE TABLE order_payments
(
    id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date           DATE                DEFAULT now() NOT NULL,
    company_id     INTEGER                           NOT NULL,
    order_id       INTEGER                           NOT NULL,
    our_company_id INTEGER                           NOT NULL,
    transaction    NUMERIC(13, 2)                    NOT NULL,
    cashless       BOOL                              NOT NULL,
    comment        TEXT,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (our_company_id) REFERENCES companies (id) ON DELETE CASCADE
);

CREATE TABLE planned_time
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    order_id    INTEGER  NOT NULL,
    employee_id INTEGER  NOT NULL,
    man_hours   SMALLINT NOT NULL,
    CONSTRAINT planned_time_unique_pt_object_idx UNIQUE (id, order_id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE actual_time
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    order_id          INTEGER  NOT NULL,
    employee_id       INTEGER  NOT NULL,
    date              DATE     NOT NULL,
    actual_man_hours  SMALLINT NOT NULL,
    account_man_hours SMALLINT NOT NULL,
    CONSTRAINT actual_time_unique_at_object_idx UNIQUE (id, order_id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE tasks
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    order_id         INTEGER      NOT NULL,
    task_description TEXT         NOT NULL,
    employee_id      INTEGER      NOT NULL,
    date_completed   DATE         NOT NULL,
    result           VARCHAR(255) NOT NULL,
    comment          TEXT,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE task_emails
(
    task_id   INTEGER      NOT NULL,
    email_id  INTEGER      NOT NULL,
    type_send VARCHAR(255) NOT NULL,
    CONSTRAINT task_email_idx UNIQUE (task_id, email_id),
    FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE,
    FOREIGN KEY (email_id) REFERENCES emails (id) ON DELETE CASCADE
);



