package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();
        objectList.add("ala");
        objectList.add(1);
        objectList.add("cat");
        objectList.add(23);
        objectList.add(23.311);
        objectList.add(3.14f);
        objectList.add(1.234);
        List<Object> resultTaskA = filter(objectList, Integer.class);
        resultTaskA.forEach(el -> System.out.print(el + " "));
        System.out.println("\n----------------------");
        List<Object> resultTaskB = filterSuperList(objectList, Number.class);
        resultTaskB.forEach(el -> System.out.print(el + " "));
    }

    // (Task 1a) .add() elements that belong to the specified class
    public static List<Object> filter(List<Object> objectList, Class filterClass) {
        List<Object> resultList = new ArrayList<>();
        for (Object obj : objectList) {
            //if (filterClass.isInstance(obj)) { (Task 1b) another solution
            if (obj.getClass().equals(filterClass)) {
                resultList.add(obj);
            }
        }
        return resultList;
    }

    // (Task 1b) .add() elements that inherit from specified class
    public static List<Object> filterSuperList(List<Object> objectList, Class filterClass) {
        return objectList
                .stream()
                .filter(filterClass::isInstance)
                .collect(Collectors.toList());
    }
}
