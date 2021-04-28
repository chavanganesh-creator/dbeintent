package com.example.dbeintent.ui.home;

public class Note {
    private String task_id;
    private String cli_name;
    private String task_address;
    private String cli_description;


    public Note() {

    }
    public Note(String task_id, String cli_name, String task_address, String cli_description) {
        this.task_id = task_id;
        this.cli_name = cli_name;
        this.task_address = task_address;
        this.cli_description = cli_description;
    }

    public String getTask_id() {
        return task_id;
    }

    public String getCli_name() {
        return cli_name;
    }

    public String getTask_address() {
        return task_address;
    }

    public String getCli_description() {
        return cli_description;
    }




}
