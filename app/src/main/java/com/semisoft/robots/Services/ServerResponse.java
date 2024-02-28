package com.semisoft.robots.Services;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("isValide")
    boolean isValide;
    @SerializedName("message")
    String message;
    @SerializedName("email")
    String email;
    @SerializedName("firstname")
    String firstname;
    @SerializedName("lastname")
    String lastname;

    public ServerResponse(boolean isValide, String message, String email, String firstname, String lastname) {
        this.isValide = isValide;
        this.message = message;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public boolean isValide() {
        return isValide;
    }

    public void setValide(boolean valide) {
        isValide = valide;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
