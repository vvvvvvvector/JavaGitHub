package com.company;

import java.io.*;

public class FileCommanderCLI {
    private FileCommander fileCommander;
    private BufferedReader reader;
    private BufferedWriter writer;

    public FileCommanderCLI(InputStream in, OutputStream out) {
        this.fileCommander = new FileCommander();
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.writer = new BufferedWriter(new OutputStreamWriter(out));
    }

    public void eventLoop() {
        while (true) {
            try {
                String command = reader.readLine(); // server reads message from client
                runCommand(command);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void runCommand(String command) throws IOException {
        String[] commandArg = command.split(" ");
        switch (commandArg[0]) { // writer.write(...) - sends message to client
            case "pwd" -> writer.write(fileCommander.pwd() + "\n");
            case "cd" -> fileCommander.cd(commandArg[1]);
            case "ls" -> writer.write(fileCommander.ls().toString() + "\n");
            case "find" -> writer.write(fileCommander.find(commandArg[1]).toString() + "\n");
            default -> writer.write("Unknown command\n");
        }
    }
}
