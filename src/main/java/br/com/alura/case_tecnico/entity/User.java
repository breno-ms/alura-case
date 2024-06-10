package br.com.alura.case_tecnico.entity;

import br.com.alura.case_tecnico.dto.InstructorDTO;
import br.com.alura.case_tecnico.dto.UserResponseDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public User(Integer id, String username, String email, String password, LocalDate createdAt, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.role = role;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public InstructorDTO convertToInstructorDto() {
        return new InstructorDTO(
                this.getUsername(),
                this.getEmail()
        );
    }

    public UserResponseDTO convertToDto() {
        return new UserResponseDTO(this.username, this.email, this.role.getRoleName());
    }

    public boolean isInstructor() {
        return Objects.equals(this.role.getRoleName(), "ROLE_INSTRUCTOR");
    }

    public boolean isStudent() {
        return Objects.equals(this.role.getRoleName(), "ROLE_STUDENT");
    }

}
