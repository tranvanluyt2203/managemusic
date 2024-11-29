package org.example.user.dtos;

import org.example.user.entities.Profile;
import org.example.user.entities.User;

import jakarta.persistence.Lob;

public class ProfileDTO {
    private String username;
    private String email;
    private String role;
    private String phoneNumber;
    private String address;
    private String fullName;
    @Lob
    private byte[] image;

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public byte[] getImage() {
        return image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProfileDTO(User user, Profile profile) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole().getName();
        this.address = profile.getAddress();
        this.fullName = profile.getFullName();
        this.phoneNumber = profile.getPhoneNumber();
        this.image = profile.getImage();
    }
}
