DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    role_name VARCHAR(15) NOT NULL
);

INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role_name) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO roles (role_name) VALUES ('ROLE_STUDENT');
