package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String login, password, firstName, lastName;
    private LocalDate lastLogin;
    static private List<Account> accounts = new ArrayList<>();

    public Account(String login, String password, String firstName, String lastName) {
        while (isLoginUsed(login)) {
            login += "1";
        }
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLogin = LocalDate.of(1970, 1, 1);
        accounts.add(this); //dodajemy ten obiekt do tablicy
    }

    //konstruktor kopiujacy
    public Account(Account other) {
        this(other.login, other.password, other.firstName, other.lastName);
        this.lastLogin = other.lastLogin;
    }

    public String getLastName() { // do zadania 6
        return lastName;
    }

    public String getLogin() {  // do zadania 3
        return login;
    }

    private boolean isLoginUsed(String login) {
        for (var account : accounts) {
            if (account.login.equals(login))
                return true;
        }
        return false;
    }

    public boolean authorize(String login, String password) {
        if (this.login.equals(login) && this.password.equals(password)) {
            lastLogin = LocalDate.now();
            return true;
        } else return false;
    }

    final public String toString() {
        return firstName + " " + lastName;
    }
}


