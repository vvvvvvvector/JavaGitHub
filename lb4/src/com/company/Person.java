package com.company;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Person {
    private String name;
    private LocalDate birth;
    private LocalDate death;

    private Person(String name, LocalDate birth, LocalDate death) {
        this.name = name;
        this.birth = birth;
        this.death = death;
    }

    public static Person fromFile(String path) {
        String name = null;
        LocalDate birth = null;
        LocalDate death = null;
        try {
            var bufReader = new BufferedReader(new FileReader(path));
            name = bufReader.readLine();
            birth = parseDate(bufReader.readLine());
            death = parseDate(bufReader.readLine());
            bufReader.close();
        } catch (IOException | NullPointerException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return new Person(name, birth, death);
    }

    private static LocalDate parseDate(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static String parseDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                ", death=" + death +
                '}';
    }

    public static Person[] fromCSV(String path) {
        List<Person> people = new ArrayList<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                String name = tokenizer.nextToken();
                LocalDate birth = parseDate(tokenizer.nextToken());
                LocalDate death = null;
                if (tokenizer.hasMoreTokens()) death = parseDate(tokenizer.nextToken());
                people.add(new Person(name, birth, death));
            }
            reader.close();
        } catch (IOException | NullPointerException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return people.toArray(new Person[0]);
    }

    public static void toFile(Person person, String path) {
        try {
            var fileWriter = new BufferedWriter(new FileWriter(path));
            fileWriter.write(person.name + "\n");
            fileWriter.write(parseDate(person.birth) + "\n");
            if (person.death != null) {
                fileWriter.write(parseDate(person.death));
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toCSV(Person[] people, String path) {
        try {
            var fileWriter = new BufferedWriter(new FileWriter(path));
            for (Person i : people) {
                fileWriter.write(i.name + ";");
                fileWriter.write(parseDate(i.birth) + ";");
                if (i.death != null) {
                    fileWriter.write(parseDate(i.death));
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sortCSV(String path) {
        var people = Person.fromCSV(path);
        Arrays.sort(people, new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return p1.birth.compareTo(p2.birth);
            }
        });
        Person.toCSV(people, path);
    }

    public static void toDirectory(Person[] people, String path) {
        var dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            File[] tab = dir.listFiles();
            for (File f : tab) {
                f.delete();
            }
        } else {
            try {
                Files.createDirectory(dir.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Person p : people) {
            Person.toFile(p, path + "\\" + p.name + ".txt");
        }
    }

    public static Person[] fromDirectory(String path) {
        File[] tab = new File(path).listFiles();
        Person[] res = new Person[tab.length];
        for (int i = 0; i < tab.length; i++) {
            res[i] = fromFile(tab[i].getPath());
        }
        return res;
    }
}
