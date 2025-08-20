package com.mycompany.notepad;

import javax.swing.*;

public class Notepad {
    public static void main(String[] args) {
        // Creating instances of Model, View, and Controller
        SwingUtilities.invokeLater(() -> {
            NotepadView view = new NotepadView();               // Create the View
            NotepadModel model = new NotepadModel();     // Create the Model
            new NotepadController(view, model);                // Create and link the Controller with View and Model
            
            view.setVisible(true);                             // Make the View visible
        });
    }
}
