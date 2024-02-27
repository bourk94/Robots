package com.semisoft.robots.Domain;

public class Robot {
    private String name;
    private int batteryHealth;

    public Robot(String name, String type, int batteryHealth) {
        this.name = name;
        this.batteryHealth = batteryHealth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBatteryHealth() {
        return batteryHealth;
    }

    public void setBatteryHealth(int batteryHealth) {
        this.batteryHealth = batteryHealth;
    }
}
