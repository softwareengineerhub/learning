package com.app.model;

public class TestKey {
    private int id;
    private String title;

    public TestKey() {
    }

    public TestKey(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TestKey{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
