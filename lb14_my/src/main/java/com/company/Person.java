package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Person {
    private int id;
    private String firstName;
    private String lastName;

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    private static List<Person> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Person> personList = new LinkedList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            Person person = new Person(id, firstName, lastName);
            personList.add(person);
        }
        return personList;
    }

    public static List<Person> selectAll() throws SQLException {
        Statement statement = DatabaseConnection.getConnection().createStatement();
        return resultSetToList(statement.executeQuery("SELECT * FROM person;"));
    }

    public static List<Person> selectByLastName(String lastName) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getConnection()
                .prepareStatement("SELECT * FROM person WHERE last_name = ?;");
        statement.setString(1, lastName); // replace ? with String lastName
        statement.execute();
        return resultSetToList(statement.getResultSet());
    }

    public static List<Person> selectByLastNameStartsWith(String startsWith) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getConnection()
                .prepareStatement("SELECT * FROM person WHERE last_name LIKE ?;");
        statement.setString(1, startsWith + "%"); // replace ? with String startsWith%
        statement.execute();
        return resultSetToList(statement.getResultSet());
    }

    public static void insertPerson(String firstName, String lastName) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getConnection()
                .prepareStatement("INSERT INTO person(first_name, last_name) VALUES(?, ?);");
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.executeUpdate();
    }

    public static int insertPersonReturningId(String firstName, String lastName) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getConnection()
                .prepareStatement("INSERT INTO person(first_name, last_name) VALUES(?, ?);");
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
    }
}
