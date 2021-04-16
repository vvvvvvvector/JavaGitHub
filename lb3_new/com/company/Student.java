package com.company;

import java.time.LocalDate;
import java.time.Month;

public class Student extends Account{
    private int startYear;
    private String course;

    public Student(String login, String password, String firstName, String lastName, int startYear, String course) {
        super(login, password, firstName, lastName);
        this.startYear = startYear;
        this.course = course;
    }

    public int semester() {
        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        int year = date.getYear();

        if(month.compareTo(Month.OCTOBER)<0)
            year--;

        int semester = (year - startYear) * 2 + 1;

        if(month.compareTo(Month.FEBRUARY) >= 0 && month.compareTo(Month.OCTOBER)<0)
            semester++;

        return semester;
    }
}
