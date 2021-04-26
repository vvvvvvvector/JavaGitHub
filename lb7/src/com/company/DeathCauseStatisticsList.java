package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DeathCauseStatisticsList {
    Map<String, DeathCauseStatistic> causeStats = new HashMap<>();

    public void populateFromCsv(String path) {
        causeStats.clear();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));//scanner = new Scanner(new File(path), "utf-8");

            for(int i=0; i<2; i++) scanner.nextLine();

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                DeathCauseStatistic deathCauseStatistic = DeathCauseStatistic.fromCsvLine(line);
                causeStats.put(deathCauseStatistic.getKey(), deathCauseStatistic);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Set<String> keys() {
        return causeStats.keySet();
    }



    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n) {
        List<DeathCauseStatistic> values = new ArrayList<>(causeStats.values());
        //Collections.sort(values, Comparator.comparingInt((DeathCauseStatistic a) -> a.getDeaths(age).deathCount).reversed());
        values.sort(Comparator.comparingInt((DeathCauseStatistic a) -> a.getDeaths(age).deathCount).reversed());
        return values.subList(0,n);
    }
}
