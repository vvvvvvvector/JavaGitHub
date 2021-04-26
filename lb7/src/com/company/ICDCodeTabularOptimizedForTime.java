package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ICDCodeTabularOptimizedForTime implements ICDCodeTabular{
    Map<String, String> descriptions = new HashMap<>();

    public ICDCodeTabularOptimizedForTime(String path) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
            for(int i=0; i<87; i++) scanner.nextLine();

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.trim();
                //String code = line.substring(0,3);
                if(line.matches("[A-Z][0-9]{2}.*")) {
                    String codeDescArr[] = line.split(" ",2);
                    descriptions.put(codeDescArr[0].trim(), codeDescArr[1]);
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getDescription(String code) throws IndexOutOfBoundsException {
        if(!descriptions.containsKey(code))
            throw new IndexOutOfBoundsException("No such code: "+code);
        return descriptions.get(code);
    }
}
