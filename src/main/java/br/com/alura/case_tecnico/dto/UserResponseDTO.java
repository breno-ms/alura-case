package br.com.alura.case_tecnico.dto;

import br.com.alura.case_tecnico.entity.User;

public class UserResponseDTO {

    private String username;
    private String email;
    private String role;

    public UserResponseDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole().getRoleName();
    }

    public UserResponseDTO(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}