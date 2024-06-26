DROP TABLE IF EXISTS enrollment;

CREATE TABLE enrollment (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    course_code VARCHAR(10) NOT NULL,
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (course_code) REFERENCES courses (code),
    UNIQUE (user_id, course_code)
);
