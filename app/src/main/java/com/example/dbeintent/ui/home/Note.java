package com.example.dbeintent.ui.home;

public class Note {
    private String title;
    private String description;

    public Note() {

    }
    public Note(String title, String description) {
        this.title = title;
        this.description = description;

    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

}