package com.gqt.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class ShellView extends JFrame {
    private JTextPane outputPane;
    private JTextField inputField;
    private JButton clearButton;
    private ShellExecutor shell;

    public ShellView(ShellExecutor executor) {
    	try {
    	    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    	        if ("Nimbus".equals(info.getName())) {
    	            UIManager.setLookAndFeel(info.getClassName());
    	            UIManager.put("control", new Color(40, 40, 40));
    	            UIManager.put("info", new Color(60, 63, 65));
    	            UIManager.put("nimbusBase", new Color(18, 30, 49));
    	            UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
    	            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
    	            UIManager.put("nimbusFocus", new Color(115, 164, 209));
    	            UIManager.put("nimbusGreen", new Color(176, 179, 50));
    	            UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
    	            UIManager.put("nimbusLightBackground", new Color(43, 43, 43));
    	            UIManager.put("nimbusOrange", new Color(191, 98, 4));
    	            UIManager.put("nimbusRed", new Color(169, 46, 34));
    	            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
    	            UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
    	            UIManager.put("text", new Color(230, 230, 230));
    	            break;
    	        }
    	    }
    	} catch (Exception e) {
    	    System.err.println("Dark mode not applied");
    	}

        this.shell = executor;

        setTitle("Simple Java Shell - GUI");
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        outputPane = new JTextPane();
        outputPane.setEditable(false);
        outputPane.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputPane);

        inputField = new JTextField();
        String[] commands = { "pwd", "ls", "cd", "clear", "exit", "echo", "history" };

        inputField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    String input = inputField.getText();
                    for (String cmd : commands) {
                        if (cmd.startsWith(input)) {
                            inputField.setText(cmd);
                            break;
                        }
                    }
                    e.consume(); 
                }
            }
        });

        inputField.setFont(new Font("Consolas", Font.PLAIN, 14));
        inputField.addActionListener(e -> handleInput());

        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> outputPane.setText(""));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(clearButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void handleInput() {
        String input = inputField.getText();
        String result = shell.execute(input);

        if ("\f".equals(result)) {
            outputPane.setText("");
        } else {
            appendOutput("MyShell> " + input + "\n" + stripHtml(result) + "\n");
        }

        inputField.setText("");
    }

    
    private void appendOutput(String text) {
        outputPane.setText(outputPane.getText() + text);
        outputPane.setCaretPosition(outputPane.getDocument().getLength());
        logToFile(text);
    }

    private void logToFile(String text) {
        try (FileWriter fw = new FileWriter("shell_log.txt", true)) {
            fw.write(text);
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }


    private String stripHtml(String html) {
        if (html.startsWith("<html>")) {
            return html.replaceAll("<[^>]+>", "");
        }
        return html;
    }
}
