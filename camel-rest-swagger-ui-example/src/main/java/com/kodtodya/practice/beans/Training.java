package com.kodtodya.practice.beans;

public class Training {

    private int id;
    private String name;
    private int duration;
    private String prerequisite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public Training(String name, int duration, String prerequisite) {
        this.name = name;
        this.duration = duration;
        this.prerequisite = prerequisite;
    }

    @Override
    public String toString() {
        return "{"
                + "Training: {"
                + "id=" + id
                + ", name='" + name + '\''
                + ", duration=" + duration
                + ", prerequisite='" + prerequisite + '\''
                + "}"
                + "}";
    }
}