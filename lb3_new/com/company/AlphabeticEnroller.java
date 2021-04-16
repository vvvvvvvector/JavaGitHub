package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphabeticEnroller implements Enroller {
    private List<Student> students = new ArrayList<>();
    SubjectInstance instance;

    @Override
    public void clear() {
        students.clear();
    }

    public void setSubjectInstance(SubjectInstance instance) {
        this.instance = instance;
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public void process() {
        students.sort((Student a, Student b) -> a.getLastName().compareTo(b.getLastName()));
        instance.students = students.subList(0, instance.getLimit());
    }
}
