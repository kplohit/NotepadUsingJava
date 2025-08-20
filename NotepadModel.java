package com.mycompany.notepad;

public class NotepadModel {
    private String text = "";
    private boolean wordWrapEnabled = false;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isWordWrapEnabled() {
        return wordWrapEnabled;
    }

    public void setWordWrapEnabled(boolean wordWrapEnabled) {
        this.wordWrapEnabled = wordWrapEnabled;
    }
}
