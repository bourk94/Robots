package com.semisoft.robots.Domain;

public class Action {
    private String label;
    private String name;
    private String robot; // "jamal" or "dawgmobile"

    public Action(String label, String name, String owner) {
        this.label = label;
        this.name = name;
        this.robot = owner;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRobot() {
        return robot;
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }
}
