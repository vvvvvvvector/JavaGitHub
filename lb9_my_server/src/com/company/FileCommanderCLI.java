package com.company;

import java.io.*;

public class FileCommanderCLI {
    private FileCommander fileCommander;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public FileCommanderCLI(InputStream in, OutputStream out) {
        this.fileCommander = new FileCommander();
        this.bufferedReader = new BufferedReader(new InputStreamReader(in));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));
    }

    public void eventLoop() {
        while (true) {
            try {
                String command = bufferedReader.readLine();
                runCommand(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void runCommand(String command) {
        String[] commandArg = command.split(" ");
        switch (commandArg[0]) {
            case "pwd" -> System.out.println(fileCommander.pwd());
            case "cd" -> fileCommander.cd(commandArg[1]);
            case "ls" -> System.out.println(fileCommander.ls());
            case "find" -> System.out.println(fileCommander.find(commandArg[1]));
            default -> System.out.println("Unknown command");
        }
    }
}
