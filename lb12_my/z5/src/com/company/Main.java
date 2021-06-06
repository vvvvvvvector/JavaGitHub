package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        list = addAtPosition(list, 10, 1);
        list.forEach(el -> System.out.print(el + " "));
        System.out.println("\n-------------");
        Double[] arr = new Double[]{1.0, 2.0, 3.0, 4.0, 5.1};
        arr = addAtPosition(arr, 10.0, 1);
        for (var i : arr) {
            System.out.print(i + " ");
        }
    }

    // T - "typ szablanowy"
    public static <T> List<T> addAtPosition(List<T> list, T el, int pos) {
        List<T> resultList = new ArrayList<>(list);
        resultList.add(pos, el);
        return resultList;
    }

    public static <T> T[] addAtPosition(T[] arr, T el, int pos) {
        T[] resultArray = Arrays.copyOf(arr, arr.length + 1);
        resultArray[pos] = el;
        for (int i = pos + 1; i < arr.length + 1; i++) {
            resultArray[i] = arr[i - 1];
        }
        return resultArray;
    }
}
