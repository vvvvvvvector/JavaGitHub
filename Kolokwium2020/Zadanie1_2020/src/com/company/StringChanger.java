package com.company;

import java.util.Locale;

public class StringChanger {
    private String str;

    public StringChanger(String str) {
        this.str = str;
    }

    public void methodStar(char first, char last) {
        var words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].toLowerCase().charAt(0) == Character.toLowerCase(first) && words[i].toLowerCase().charAt(words[i].length() - 1) == Character.toLowerCase(last)) {
                var builder = new StringBuilder();
                builder.append(words[i].charAt(0));
                for (int j = 0; j < words[i].length() - 2; j++) {
                    builder.append("*");
                }
                builder.append(words[i].charAt(words[i].length() - 1));
                words[i] = builder.toString();
            }
        }
        System.out.println(String.join(" ", words));
    }

    public void methodToUpper() {
        var words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            var builder = new StringBuilder(words[i]);
            builder.setCharAt(0, Character.toUpperCase(words[i].charAt(0)));
            builder.setCharAt(words[i].length() - 1, Character.toUpperCase(words[i].charAt(words[i].length() - 1)));
            words[i] = builder.toString();
        }
        System.out.println(String.join(" ", words));
    }
}
