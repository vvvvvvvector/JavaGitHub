package com.company;

import java.util.ArrayList;
import java.util.List;

public class SubjectInstance {
    private Subject subject;
    private int year;
    private int limit;
    private Teacher teacher;

    public int getLimit() {  // do zadania 6
        return limit;
    }

    List<Student> students = new ArrayList<Student>();

    public SubjectInstance(Subject subject, int year, int limit, Teacher teacher) {
        this.subject = subject;
        this.year = year;
        this.limit = limit;
        this.teacher = teacher;
    }

    public boolean enroll(Student student) {
        if (students.size() < limit) {
            students.add(student);
            return true;
        } else return false;
    }

    public List<Student> getStudentsCopy() {  // do zadania 5
        return List.copyOf(students);
    }

}
