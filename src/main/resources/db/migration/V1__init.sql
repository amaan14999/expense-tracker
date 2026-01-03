-- Initial schema will be added here
CREATE TABLE users (
    id              UUID            PRIMARY KEY,
    email           VARCHAR(255)    NOT NULL,
    password_hash   VARCHAR(255)    NOT NULL,
    name            VARCHAR(120)   NOT NULL,
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT now(),

    CONSTRAINT uk_users_mail  UNIQUE (email)
);



CREATE TABLE categories (
    id          UUID            PRIMARY KEY,
    user_id     UUID            NOT NULL,
    name        VARCHAR(80)     NOT NULL,
    type        VARCHAR(20)     NOT NULL,
    created_at  TIMESTAMPTZ     NOT NULL DEFAULT now(),

    CONSTRAINT fk_categories_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,

--  A user should not have duplicate categories
    CONSTRAINT uk_categories_user_name UNIQUE (user_id, name),

--  Optional guardrail so type doesn't drift
    CONSTRAINT ck_categories_type CHECK (type IN ('EXPENSE', 'INCOME'))
);



CREATE TABLE EXPENSES (
    id              UUID            PRIMARY KEY,
    user_id         UUID            NOT NULL,
    category_id     UUID            NOT NULL,
    amount          NUMERIC(12,2)   NOT NULL,
    description     VARCHAR(500),
    expense_date    DATE            NOT NULL,
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT now(),

    CONSTRAINT fk_expenses_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,

    CONSTRAINT fk_expenses_category
        FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT,

    CONSTRAINT ck_expenses_amount_positive CHECK (amount > 0)
);


--  Helpful indexes for common queries (date range + category filters per user)
CREATE INDEX idx_expenses_user_data ON expenses(user_id, expense_date);
CREATE INDEX idx_expenses_user_category ON expenses(user_id, category_id);