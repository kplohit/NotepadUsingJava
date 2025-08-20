package com.mycompany.notepad;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.text.*;
import java.awt.print.PrinterException;

public class NotepadController {
    private NotepadView view;
    private NotepadModel model;

    public NotepadController(NotepadView view, NotepadModel model) {
        this.view = view;
        this.model = model;

        view.getNewFile().addActionListener(e -> createNewFile());
        view.getOpenFile().addActionListener(e -> openFile());
        view.getSaveFile().addActionListener(e -> saveFile());
        view.getSaveAsFile().addActionListener(e -> saveAsFile());
        view.getPrintFile().addActionListener(e -> printFile());
        view.getCloseFile().addActionListener(e -> closeFile());
        view.getAbout().addActionListener(e -> showAboutDialog());
        view.getWordWrap().addActionListener(e -> toggleWordWrap());
        view.getFind().addActionListener(e -> showFindDialog());
        view.getReplace().addActionListener(e -> showReplaceDialog());

        view.getTextArea().addCaretListener(e -> updateStatusBar());
        view.getTextArea().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateStatusBar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateStatusBar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateStatusBar(); }
        });
    }

    private void createNewFile() {
        model.setText("");
        view.getTextArea().setText("");
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                model.setText(content.toString());
                view.getTextArea().setText(content.toString());
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(model.getText());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveAsFile() {
        saveFile();
    }

    private void printFile() {
        try {
            view.getTextArea().print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    private void closeFile() {
        System.exit(0);
    }

    private void toggleWordWrap() {
        boolean wordWrapEnabled = !model.isWordWrapEnabled();
        model.setWordWrapEnabled(wordWrapEnabled);
        view.getTextArea().setLineWrap(wordWrapEnabled);
        view.getTextArea().setWrapStyleWord(wordWrapEnabled);
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(view, "Notepad\nAuthor: Your Name\nCompany: Your Company", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateStatusBar() {
        int caretPosition = view.getTextArea().getCaretPosition();
        int row = 1;
        int col = caretPosition + 1;
        try {
            row = view.getTextArea().getLineOfOffset(caretPosition) + 1;
            col = caretPosition - view.getTextArea().getLineStartOffset(row - 1) + 1;
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        String text = view.getTextArea().getText();
        String[] lines = text.split("\n");
        int totalLines = lines.length;
        int totalWords = text.split("\\s+").length;
        int totalCharacters = text.length();

        view.getStatusBar().setText("Row: " + row + ", Col: " + col + " | Lines: " + totalLines + " | Words: " + totalWords + " | Characters: " + totalCharacters);
    }

    private void showFindDialog() {
        String searchTerm = JOptionPane.showInputDialog(view, "Find:", "Find", JOptionPane.PLAIN_MESSAGE);
        if (searchTerm != null && !searchTerm.isEmpty()) {
            String text = view.getTextArea().getText();
            int startIndex = text.indexOf(searchTerm);
            if (startIndex != -1) {
                view.getTextArea().setCaretPosition(startIndex);
                view.getTextArea().select(startIndex, startIndex + searchTerm.length());
            } else {
                JOptionPane.showMessageDialog(view, "Text not found.", "Find", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void showReplaceDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField findField = new JTextField(20);
        JTextField replaceField = new JTextField(20);

        panel.add(new JLabel("Find:"));
        panel.add(findField);
        panel.add(new JLabel("Replace:"));
        panel.add(replaceField);

        int option = JOptionPane.showConfirmDialog(view, panel, "Find and Replace", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String findText = findField.getText();
            String replaceText = replaceField.getText();

            String text = view.getTextArea().getText();
            text = text.replaceAll(findText, replaceText);
            view.getTextArea().setText(text);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NotepadView view = new NotepadView();
            NotepadModel model = new NotepadModel();
            new NotepadController(view, model);
            view.setVisible(true);
        });
    }
}
