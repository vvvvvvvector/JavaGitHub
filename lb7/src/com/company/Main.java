package com.company;

import java.util.List;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        //Zadanie 1
        DeathCauseStatistic statistic=DeathCauseStatistic.fromCsvLine("A02.1          ,5,-,-,-,-,-,-,-,-,-,-,-,-,1,2,-,1,1,-,-,-");
        System.out.println(statistic.key);
        int[] arr=statistic.getAgeBracket();
        for(var a: arr){
            System.out.println(a);
        }

        //Zadanie 3a
        DeathCauseStatisticsList statisticsList=new DeathCauseStatisticsList();
        statisticsList.populateFromCsv("zgony.csv");
        Set<String> keys=statisticsList.keys();
        for( var a: keys)
            System.out.printf(a);
        System.out.println(keys.contains("B00.1"));

        List<DeathCauseStatistic> mostDeadly= statisticsList.mostDeadlyDiseases(1,10);
        for(var diseases : mostDeadly)
            System.out.println(diseases.key);

        //Zadanie 4
        DeathCauseStatisticsList deaths = new DeathCauseStatisticsList();
        deaths.populateFromCsv("zgony.csv");

        ICDCodeTabular codes = new ICDCodeTabularOptimizedForTime("icd10.txt");
        System.out.println(codes.getDescription("A17.0"));





    }
}
