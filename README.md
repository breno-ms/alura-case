# Technical Case - Alura

## Technologies that were used
- Java 21
- Spring Framework with the following dependencies:
    - Web
    - Data JPA
    - Flyway
    - Security
    - Java JWT
    - Validation
    - Dev Tools
    - Lombok
    - Java Mail
- MySQL
- Gmail SMPT
## How to run the project locally
##### Run the following command in the terminal:
```shell
DATABASE_PASSWORD={database_password} \
DATASOURCE_USERNAME={datasource_username} \
DATASOURCE_PASSWORD={datasource_password} \
API_SECRET_KEY={api_secret_key} \
EMAIL_SENDER_USERNAME={email_sender_email} \
EMAIL_SENDER_PASSWORD={email_sender_password} \
docker-compose up
```
Don't forget to use a valid account to use Gmail SMTP.
## API documentation
### Login and Registration
##### Register:
```http
  POST /auth/register
```
| Parameter  | Type       |
|:-----------| :--------- |
| `username` | `String` |
| `email`    | `String` |
| `password` | `String` |
| `role`     | `String` |

##### Login:
```http
  POST /auth/login
```
| Parameter       | Type       |
|:----------------| :--------- |
| `username`      | `String` |
| `password`      | `String` |

### Users:
##### Find all users:
```http
  GET /user/find-all
```
##### Find user by username:
```http
  GET /user/find-by-username/{username}
```

### Courses:
##### Find all courses:
```http
  GET /course/find-all-or-by-status
```
##### Find all active courses:
```http
  GET /course/find-all-or-by-status?page=0&size=10&sort=code&status=1
```
##### Find all inactive courses:
```http
  GET /course/find-all-or-by-status?page=0&size=10&sort=code&status=0
```

##### Create course:
```http
  POST /course/create-course
```
| Parameter   | Type       |
| :---------- | :--------- |
| `courseName` | `String` |
| `courseCode` | `String` |
| `instructorUsername` | `String` |
| `instructorEmail` | `String` |
| `description` | `String` |

### Enrollment:
##### Create enrollment:
```http
  POST /enrollment/create-enrollment
```
| Parameter   | Type       |
| :---------- | :--------- |
| `username` | `String` |
| `courseCode` | `String` |

### Feedback:
##### Create feedback:
```http
  POST /feedback/create-feedback
```
| Parameter   | Type       |
| :---------- | :--------- |
| `enrollmentId` | `Integer` |
| `rating` | `Integer` |
| `comment` | `String` |

### NPS Report:
##### List NPS report:
```http
  GET /report/list-report
```

### Postman collection:
Link to the Postman Collection: [Click here](https://www.postman.com/lunar-sunset-33386/workspace/alura-case/collection/21327501-5c9a82b5-3970-4bf2-914a-191d5b80df22?action=share&creator=21327501)

