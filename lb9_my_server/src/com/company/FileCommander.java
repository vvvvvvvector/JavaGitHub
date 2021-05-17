package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCommander {
    private Path path;

    public FileCommander() {
        this.path = Path.of(System.getProperty("user.home"));
    }

    public String pwd() {
        return this.path.toString();
    }

    public void cd(String path) {
        this.path = this.path.resolve(path);
    }

    public List<String> ls() {
        Comparator<Path> comparator = (p1, p2) ->
                Boolean.compare(Files.isDirectory(p2), Files.isDirectory(p1)); // directories first
        comparator.thenComparing(p -> p.getFileName().toString()); // sort in alphabetical order
        try (Stream<Path> stream = Files.list(this.path)) {
            return stream
                    .sorted(comparator) // sort by 2 conditions(directories first, alphabetical order)
                    .map(path -> { // converting Path to String and adding [] to directories
                        String strPath = path.getFileName().toString();
                        if (Files.isDirectory(path)) {
                            return "[" + strPath + "]";
                        } else {
                            return strPath;
                        }
                    })
                    .collect(Collectors.toList()); // converting Stream to List
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
