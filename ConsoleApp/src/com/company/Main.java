package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(0);
        System.out.println(ConvertBinaryArrayToInt(list));
    }

    public static String highAndLow(String numbers) {
        var strArray = numbers.split(" ");
        var intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++)
            intArray[i] = Integer.parseInt(strArray[i]);
        var stat = Arrays.stream(intArray).summaryStatistics();
        return stat.getMax() + " " + stat.getMin();
        //return Arrays.stream(intArray).max().getAsInt() + " " + Arrays.stream(intArray).min().getAsInt();
    }

    public static int reverse(int number) {
        var builder = new StringBuilder(String.valueOf(Math.abs(number)));
        var reversedString = builder.reverse();
        while (reversedString.charAt(0) == '0')
            reversedString.deleteCharAt(0);
        return number > 0 ? Integer.parseInt(reversedString.toString()) : -Integer.parseInt(reversedString.toString());
    }

    public static String reverseLetter(final String str) {
        return new StringBuilder(str.replaceAll("([^a-z])", "")).reverse().toString();
    }

    public static boolean smallEnough(int[] a, int limit) {
        for (int i : a) {
            if (i > limit) return false;
        }
        return true;
    }

    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
        int value = 0;
        for (int i = 0; i < binary.size(); i++)
            value += binary.get(i) << (binary.size() - i - 1);
        return value;
    }
}
