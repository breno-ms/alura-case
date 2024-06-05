DROP TABLE IF EXISTS ROLES;

CREATE TABLE ROLES (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    role_name VARCHAR(15) NOT NULL
);

INSERT INTO ROLES (role_name) VALUES ("ROLE_ADMIN");
INSERT INTO ROLES (role_name) VALUES ("ROLE_INSTRUCTOR");
INSERT INTO ROLES (role_name) VALUES ("ROLE_STUDENT");
