package org.example.user.dtos;

import org.example.user.entities.User;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String role;

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString(){
        return String.format("UserDTO{id=%d, username='%s', email='%s',role='%s'}", id, username, email, role);
    }
    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole().getName();
    }
}
