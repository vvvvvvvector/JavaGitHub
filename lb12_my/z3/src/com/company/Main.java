package com.company;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(countInRange(arr, 5, 10));
    }

    //version 1.0 without stream
    //public static <T extends Comparable> int countInRange(T[] arr, T begin, T end) {
    //    RangePredicate<T> rangePredicate = new RangePredicate<T>(begin, end);
    //    int count = 0;
    //    for (int i = 0; i < arr.length; i++) {
    //        if (rangePredicate.test(arr[i])) {
    //            count++;
    //        }
    //    }
    //    return count;
    //}

    //version 2.0 using stream
    public static <T extends Comparable> int countInRange(T[] arr, T begin, T end) {
        RangePredicate<T> rangePredicate = new RangePredicate<>(begin, end);
        return (int) Arrays
                .stream(arr)
                .filter(rangePredicate)
                .count();
    }
}
