package com.company;

public class Main {

    public static void main(String[] args) {
        FileCommander fileCommander = new FileCommander();
        fileCommander.cd("C:\\Users\\Xiaomi\\Test_lb9");
        System.out.println(fileCommander.ls());
    }
}
