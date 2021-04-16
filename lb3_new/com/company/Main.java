package com.company;

public class Main {

    public static void main(String[] args) {
        // 1
        Account a1 = new Account("jkowalski", "1234", "Jan", "Kowalski");
        System.out.println(a1.authorize("jkowalski", "1234"));
        System.out.println(a1.authorize("akowalska", "1234"));

        // 2
        Account a2 = new Teacher("anowak", "1234", "Adam", "Nowak", "dr");
        System.out.println(a2);

        // 3
        Account a3 = new Teacher("anowak", "1234", "Anna", "Nowak", "prof.");
        Account a4 = new Teacher("anowak", "1234", "Andrzej", "Nowak", "dr");
        System.out.println(a3.getLogin());
        System.out.println(a4.getLogin());

        // 4
        Student a5 = new Student("ab", "1234", "A", "B", 2020, "informatyka");
        Student a6 = new Student("cd", "1234", "C", "D", 2018, "informatyka");
        System.out.println(a5.semester());
        System.out.println(a6.semester());

        // 5
        Subject s = new Subject("Programowanie obiektowe", "informatyka", 2);
        SubjectInstance si1 = new SubjectInstance(s, 2020, 3, (Teacher) a2);
        Student a7 = new Student("ef", "1234", "E", "F", 2019, "informatyka");
        System.out.println(a7.semester());
        si1.enroll(a5);
        si1.enroll(a6);
        si1.enroll(a7);

        //overridden toString() -> result: firstName + lastName
        System.out.println(si1.getStudentsCopy());

        // 6(random)
        SubjectInstance si2 = new SubjectInstance(s, 2020, 2, (Teacher) a2);
        Enroller enroller = new RandomEnroller();
        enroller.setSubjectInstance(si2);
        enroller.addStudent(a7);
        enroller.addStudent(a6);
        enroller.addStudent(a5);
        enroller.process();
        System.out.println(si2.getStudentsCopy());

        //6(alphabetic)
        SubjectInstance si3 = new SubjectInstance(s, 2020, 2, (Teacher) a2);
        Enroller enroller1 = new AlphabeticEnroller();
        enroller1.setSubjectInstance(si3);
        enroller1.addStudent(a7);
        enroller1.addStudent(a6);
        enroller1.addStudent(a5);
        enroller1.process();
        System.out.println(si3.getStudentsCopy());
    }
}
