package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ICDCodeTabularOptimizedForMemory implements ICDCodeTabular{
    private String path;

    public ICDCodeTabularOptimizedForMemory(String path) {
        this.path = path;
    }

    @Override
    public String getDescription(String code) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
            for(int i=0; i<87; i++) scanner.nextLine();

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.trim();
                if(line.matches("[A-Z][0-9]{2}.*")) {
                    String codeDescArr[] = line.split(" ",2);
                    if(code.equals(codeDescArr[0]))
                        return codeDescArr[1];
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new IndexOutOfBoundsException("No such code: "+code);
    }
}
