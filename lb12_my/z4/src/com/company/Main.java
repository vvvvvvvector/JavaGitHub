package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));
        List<Integer> list2 = new ArrayList<>(List.of(1, 2, 3));
        CountComparator<List<Integer>> countComparator = new CountComparator<>();
        System.out.println(countComparator.compare(list1, list2));

        SumComparator<List<Integer>, Integer> sumComparator = new SumComparator<>();
        System.out.println(sumComparator.compare(list2, list1));
    }
}
