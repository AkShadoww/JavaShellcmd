package com.gqt.console;

import java.io.*;
import java.util.*;

public class Shell {
    private File currentDirectory;
    private List<String> history = new ArrayList<>();
    
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";


    public Shell() {
        currentDirectory = new File(System.getProperty("user.dir"));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("MyShell> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            history.add(input); 

            if (input.contains("|")) {
                System.out.println(RED + "Piping not fully supported yet.\n"+ RESET +"Ignoring pipe and running first command." );
                input = input.split("\\|")[0].trim();
            }

            String[] parts = input.split("\\s+");
            String command = parts[0];

            switch (command) {
                case "pwd":
                    System.out.println(currentDirectory.getAbsolutePath());
                    break;

                case "ls":
                    File[] files = currentDirectory.listFiles();
                    if (files != null) {
                        Arrays.stream(files)
                              .sorted(Comparator.comparing(File::getName))
                              .forEach(f -> System.out.println(f.getName()));
                    }
                    break;

                case "cd":
                    if (parts.length < 2) {
                        System.out.println("Usage: cd <directory>");
                        break;
                    }
                    File newDir = new File(currentDirectory, parts[1]);
                    if (newDir.exists() && newDir.isDirectory()) {
                        currentDirectory = newDir;
                    } else {
                    	System.out.println(RED + "Directory not found." + RESET);
                    }
                    break;

                case "clear":
                    for (int i = 0; i < 50; i++) System.out.println();
                    break;

                case "exit":
                    System.out.println("Exiting shell...");
                    scanner.close();
                    return;

                case "history":
                    for (int i = 0; i < history.size(); i++) {
                        System.out.println((i + 1) + ": " + history.get(i));
                    }
                    break;
                    
                case "echo":
                    String message = input.substring(5); 
                    System.out.println(message);
                    break;


                default:
                    runSystemCommand(parts);
            }
        }
    }

    private void runSystemCommand(String[] commandParts) {
        try {
            ProcessBuilder builder = new ProcessBuilder(commandParts);
            builder.directory(currentDirectory);
            builder.redirectErrorStream(true);

            Process process = builder.start();
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println(RED +"Error running command: " + e.getMessage() + RESET);
        }
    }
}

