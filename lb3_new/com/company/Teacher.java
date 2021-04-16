package com.company;

public class Teacher extends Account {
    private String title;

    public Teacher(String login, String password, String firstName, String lastName, String title) {
        super (login, password, firstName, lastName);
        this.title=title;
    }

    //public String toString(){
    //    return super.toString() + " " + this.title;
    //}
}
