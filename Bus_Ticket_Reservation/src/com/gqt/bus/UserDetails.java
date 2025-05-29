package com.gqt.bus;

public class UserDetails {

	String name;
    String email;
    String phone;

    public UserDetails(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;

    }

    public void displayUserInfo() {
        System.out.println("\nWelcome, " + name + "!");
    }
}
