package com.mycompany.notepad;

import javax.swing.*;
import java.awt.*;

public class NotepadView extends JFrame {
    private JTextArea textArea;
    private JLabel statusBar;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, formatMenu, helpMenu;
    private JMenuItem newFile, openFile, saveFile, saveAsFile, printFile, closeFile;
    private JMenuItem undo, redo, cut, copy, paste, find, replace;
    private JCheckBoxMenuItem wordWrap;  // Change this to JCheckBoxMenuItem
    private JMenuItem about;

    public NotepadView() {
        setTitle("Notepad");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Text Area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Status Bar
        statusBar = new JLabel("Row: 1, Col: 1 | Lines: 0 | Words: 0 | Characters: 0");
        add(statusBar, BorderLayout.SOUTH);

        // Menu Bar
        menuBar = new JMenuBar();

        // File Menu
        fileMenu = new JMenu("File");
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        saveAsFile = new JMenuItem("Save As");
        printFile = new JMenuItem("Print");
        closeFile = new JMenuItem("Close");

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        fileMenu.add(printFile);
        fileMenu.add(closeFile);

        // Edit Menu
        editMenu = new JMenu("Edit");
        undo = new JMenuItem("Undo");
        redo = new JMenuItem("Redo");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        find = new JMenuItem("Find");
        replace = new JMenuItem("Find & Replace");

        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.add(find);
        editMenu.add(replace);

        // Format Menu
        formatMenu = new JMenu("Format");
        wordWrap = new JCheckBoxMenuItem("Word Wrap");  // Initialize as JCheckBoxMenuItem

        formatMenu.add(wordWrap);

        // Help Menu
        helpMenu = new JMenu("Help");
        about = new JMenuItem("About");

        helpMenu.add(about);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    // Getter for word wrap
    public JCheckBoxMenuItem getWordWrap() {
        return wordWrap;
    }

    // Other getters for menu items
    public JTextArea getTextArea() {
        return textArea;
    }

    public JLabel getStatusBar() {
        return statusBar;
    }

    public JMenuItem getNewFile() {
        return newFile;
    }

    public JMenuItem getOpenFile() {
        return openFile;
    }

    public JMenuItem getSaveFile() {
        return saveFile;
    }

    public JMenuItem getSaveAsFile() {
        return saveAsFile;
    }

    public JMenuItem getPrintFile() {
        return printFile;
    }

    public JMenuItem getCloseFile() {
        return closeFile;
    }

    public JMenuItem getUndo() {
        return undo;
    }

    public JMenuItem getRedo() {
        return redo;
    }

    public JMenuItem getCut() {
        return cut;
    }

    public JMenuItem getCopy() {
        return copy;
    }

    public JMenuItem getPaste() {
        return paste;
    }

    public JMenuItem getFind() {
        return find;
    }

    public JMenuItem getReplace() {
        return replace;
    }

    public JMenuItem getAbout() {
        return about;
    }
}
