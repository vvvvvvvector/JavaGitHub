package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var person = Person.fromFile("D:\\java_proj\\lb4\\pliki\\plik1.txt");
        //System.out.println(person.toString());
        //var tab = Person.fromCSV("D:\\java_proj\\lb4\\pliki\\plik2.csv");
        //System.out.println(Arrays.deepToString(tab));
        //Person.toFile(person, "D:\\java_proj\\lb4\\pliki\\zad3.txt");
        //Person.toDirectory(tab, "Zadanie4");
        var persons = Person.fromDirectory("Zadanie4");
        System.out.println(Arrays.toString(persons));
    }
}
