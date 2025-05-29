package com.gqt.GUI;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ShellExecutor {
    private File currentDirectory = new File(System.getProperty("user.dir"));
    private List<String> history = new ArrayList<>();

    public String execute(String input) {
        history.add(input);
        input = input.trim();

        if (input.isEmpty()) return "";

        String[] parts = input.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "pwd":
                return currentDirectory.getAbsolutePath();

            case "ls":
                File[] files = currentDirectory.listFiles();
                if (files != null) {
                    return Arrays.stream(files)
                                 .map(File::getName)
                                 .sorted()
                                 .collect(Collectors.joining("\n"));
                }
                return "";

            case "cd":
                if (parts.length < 2) {
                    return "<html><font color='red'>Usage: cd &lt;directory&gt;</font></html>";
                }
                File newDir = new File(currentDirectory, parts[1]);
                if (newDir.exists() && newDir.isDirectory()) {
                    currentDirectory = newDir;
                    return "";
                } else {
                    return "<html><font color='red'>Directory not found.</font></html>";
                }

            case "clear":
                return "\f";

            case "exit":
                System.exit(0);
                return "";

            case "history":
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < history.size(); i++) {
                    sb.append((i + 1)).append(": ").append(history.get(i)).append("\n");
                }
                return sb.toString();

            case "echo":
                return input.length() >= 5 ? input.substring(5) : "";

            default:
                return runSystemCommand(parts);
        }
    }

    private String runSystemCommand(String[] commandParts) {
        try {
            ProcessBuilder builder = new ProcessBuilder(commandParts);
            builder.directory(currentDirectory);
            builder.redirectErrorStream(true);

            Process process = builder.start();
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();
            return output.toString();
        } catch (IOException | InterruptedException e) {
            return "<html><font color='red'>Error: " + e.getMessage() + "</font></html>";
        }
    }
}

