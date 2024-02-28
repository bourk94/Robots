package com.semisoft.robots.Services;

import com.google.gson.annotations.SerializedName;
import com.semisoft.robots.Domain.User;

public class ServerResponse {
    @SerializedName("isValide")
    boolean isValide;
    @SerializedName("message")
    String message;
    @SerializedName("user")
    User user;

    public ServerResponse(boolean isValide, String message, User user) {
        this.isValide = isValide;
        this.message = message;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
