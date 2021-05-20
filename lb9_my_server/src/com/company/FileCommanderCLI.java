package com.company;

import java.io.*;
import java.util.Arrays;

public class FileCommanderCLI {
    private FileCommander fileCommander;
    private BufferedReader reader;
    private BufferedWriter writer;
    private BufferedOutputStream outputStream;

    public FileCommanderCLI(InputStream in, OutputStream out) {
        this.fileCommander = new FileCommander();
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.writer = new BufferedWriter(new OutputStreamWriter(out));
        this.outputStream = new BufferedOutputStream(out);
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
        String[] commandArg = command.split(" ", 2);
        switch (commandArg[0]) { // writer.write(...) - sends message to client
            case "pwd" -> writer.write(fileCommander.pwd() + "\n");
            case "cd" -> fileCommander.cd(commandArg[1]);
            case "ls" -> writer.write(fileCommander.ls().toString() + "\n");
            case "find" -> writer.write(fileCommander.find(commandArg[1]).toString() + "\n");
            case "archive" -> {
                File zip = fileCommander.archive(Arrays.asList(commandArg[1].split(" ")));
                System.out.println(zip.length());
                writer.write("archive " + Long.toString(zip.length()) + "\n"); // send file size to client
                writer.flush();
                sendFile(zip);
            }
            default -> writer.write("Unknown command\n");
        }
    }

    private void sendFile(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            byte[] buffer = new byte[64];
            int count;
            DataOutputStream out = new DataOutputStream(outputStream);
            while ((count = fileIn.read(buffer)) >= 0) {
                out.write(buffer, 0, count);
            }
            out.flush();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();
    }
}
