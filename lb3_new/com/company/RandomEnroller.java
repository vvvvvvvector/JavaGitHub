package com.company;

import java.util.*;

public class RandomEnroller implements Enroller {

    private class StudentWithRandomNumber {
        final Student student;

        public Double getValue() {
            return value;
        }

        final Double value;

        private StudentWithRandomNumber(Student student, double value) {
            this.student = student;
            this.value = value;
        }
    }

    List<StudentWithRandomNumber> students = new ArrayList<>();
    SubjectInstance instance;

    @Override
    public void clear() {
        students.clear();
    }

    @Override
    public void setSubjectInstance(SubjectInstance instance) {
        this.instance = instance;
    }

    @Override
    public void addStudent(Student student) {
        students.add(new StudentWithRandomNumber(student, Math.random()));
    }

    @Override
    public void process() {
        //Random Sort
        students.sort(Comparator.comparingDouble(StudentWithRandomNumber::getValue));
        for(int i=0; i<instance.getLimit() && i<students.size(); i++)
            instance.enroll(students.get(i).student);
    }


}
