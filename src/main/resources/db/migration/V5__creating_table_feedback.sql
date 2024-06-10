DROP TABLE IF EXISTS feedback;

CREATE TABLE feedback (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    enrollment_id INT NOT NULL,
    rating INT NOT NULL,
    comment VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (enrollment_id) REFERENCES enrollment (id)
);
