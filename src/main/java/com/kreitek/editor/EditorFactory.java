package com.kreitek.editor;

public class EditorFactory {
    public Editor getEditor(String format) {
        return new ConsoleEditor(format);
    }
}
