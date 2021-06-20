CREATE TABLE users
(
    id         BIGINT       NOT NULL PRIMARY KEY IDENTITY (1, 1),
    name       VARCHAR(40)  NOT NULL,
    username   VARCHAR(15)  NOT NULL,
    email      VARCHAR(40)  NOT NULL,
    password   varchar(100) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (username),
    UNIQUE (email)
);

CREATE TABLE roles
(
    id   BIGINT      NOT NULL PRIMARY KEY IDENTITY (1, 1),
    name VARCHAR(60) NOT NULL,
    UNIQUE (name)
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE machines
(
    id          BIGINT      NOT NULL PRIMARY KEY IDENTITY (1, 1),
    name        NVARCHAR(60) NOT NULL,
    status      TINYINT,
    statused_at DATETIME DEFAULT NULL,
    description NVARCHAR(1208),
    created_at  DATETIME    NOT NULL,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_machines
(
    user_id    BIGINT NOT NULL,
    machine_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, machine_id),
    CONSTRAINT fk_user_machines_machine_id FOREIGN KEY (machine_id) REFERENCES machines (id),
    CONSTRAINT fk_user_machines_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE history_errors
(
    id         BIGINT      NOT NULL IDENTITY (1, 1),
    machine_id BIGINT      NOT NULL,
    created_by VARCHAR(40) NOT NULL,
    started_at DATETIME    NOT NULL,
    ended_at   DATETIME    NOT NULL,
    cause      NVARCHAR(1208),
    created_at DATETIME    NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id, machine_id),
    CONSTRAINT fk_history_errors_machine_id FOREIGN KEY (machine_id) REFERENCES machines (id),
);

CREATE TABLE volumes
(
    id         BIGINT      NOT NULL IDENTITY (1, 1),
    machine_id BIGINT      NOT NULL,
    input      DECIMAL(15, 3),
    output     DECIMAL(15, 3),
    created_by VARCHAR(40) NOT NULL,
    started_at DATETIME    NOT NULL,
    ended_at   DATETIME    NOT NULL,
    command    VARCHAR(60),
    note       NVARCHAR(1208),
    created_at DATETIME    NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id, machine_id),
    CONSTRAINT fk_volumes_machine_id FOREIGN KEY (machine_id) REFERENCES machines (id)
);

CREATE TABLE timelines
(
    id         BIGINT   NOT NULL IDENTITY (1, 1),
    machine_id BIGINT   NOT NULL,
    started_at DATETIME NOT NULL,
    ended_at   DATETIME NOT NULL,
    status     VARCHAR(25),
    created_at DATETIME NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id, machine_id),
    CONSTRAINT fk_timelines_machine_id FOREIGN KEY (machine_id) REFERENCES machines (id)
);