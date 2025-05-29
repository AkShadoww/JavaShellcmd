package com.gqt.GUI;

public class UserDetails {
    private String name;
    private String email;
    private String phone;

    public UserDetails(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phone;
    }
}
