package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseConnection.connect(); // add Framework support... + dependencies from tg in pom.xml
        try {
            System.out.println(Person.selectAll());
            System.out.println(Person.selectByLastName("Kozemiakina"));
            System.out.println(Person.selectByLastNameStartsWith("Ko"));
            //Person.insertPerson("Блять", "Настя");
            //Person.insertPerson("Когда", "же");
            //Person.insertPerson("ты", "поймешь");
            //Person.insertPerson("что я действительно", "любил тебя");
            //System.out.println(Person.selectAll());
            //System.out.println(Person.insertPersonReturningId("напиши", "мне..."));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        DatabaseConnection.disconnect(); // add Framework support... + dependencies from tg in pom.xml
    }
}
