package com.company;

public class Main {

    public static void main(String[] args) {
        FileCommander fileCommander = new FileCommander();
        System.out.println(fileCommander.pwd());
        fileCommander.cd("D:\\javaUniGitHub");
        System.out.println(fileCommander.pwd());
    }
}
