package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseConnection.connect(); // add Framework support... + dependencies from tg in pom.xml
        try {
            System.out.println(Person.selectAll());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        DatabaseConnection.disconnect(); // add Framework support... + dependencies from tg in pom.xml
    }
}
