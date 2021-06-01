package com.company;

public class Main {

    public static void main(String[] args) {
        SortedList<Integer> sortedList = new SortedList<>();
        sortedList.add(5);
        sortedList.add(15);
        sortedList.add(55);
        sortedList.add(4);
        sortedList.add(17);
        for (int i = 0; i < sortedList.size(); i++) {
            System.out.print(sortedList.get(i) + " ");
        }
    }
}
