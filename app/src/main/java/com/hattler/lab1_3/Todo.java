package com.hattler.lab1_3;

import androidx.annotation.NonNull;

import java.sql.Timestamp;

public class Todo {
    private int id;
    private Timestamp date_start;
    private Timestamp date_finish;
    private String name;
    private String description;

    @NonNull
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", date_start=" + (date_start == null ? "null" : date_start.getTime()) +
                ", date_finish=" + (date_finish == null ? "null" : date_finish.getTime()) +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate_start() {
        return date_start;
    }

    public void setDate_start(Timestamp date_start) {
        this.date_start = date_start;
    }

    public Timestamp getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(Timestamp date_finish) {
        this.date_finish = date_finish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
