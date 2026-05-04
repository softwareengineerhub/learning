package org.example.consumer;

public class MyKey {
    private long id;
    private String title;

    public MyKey(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public MyKey() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
