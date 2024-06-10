DROP TABLE IF EXISTS courses;

CREATE TABLE courses (
    code VARCHAR(10) UNIQUE NOT NULL PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    instructor_id INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    inactivated_at TIMESTAMP DEFAULT NULL,
    FOREIGN KEY (instructor_id) REFERENCES users (id)
);
