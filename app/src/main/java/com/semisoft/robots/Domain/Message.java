package com.semisoft.robots.Domain;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("email")
    String[] email;
    @SerializedName("password")
    String[] password;
    @SerializedName("failure")
    String failure;

    public Message(String[] email, String[] password, String failure) {
        this.email = email;
        this.password = password;
        this.failure = failure;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public String[] getPassword() {
        return password;
    }

    public void setPassword(String[] password) {
        this.password = password;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }
}