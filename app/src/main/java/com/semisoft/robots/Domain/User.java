package com.semisoft.robots.Domain;

public class User {
    private String firstname;
    private String lastname;
    private String email;

    private Boolean stayLogged = false;

    public User(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public User(String firstname, String lastname, String email, Boolean stayLogged) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.stayLogged = stayLogged;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
