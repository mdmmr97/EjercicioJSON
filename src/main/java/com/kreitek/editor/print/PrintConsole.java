package com.kreitek.editor.print;

import com.kreitek.editor.print.Printer;

public class PrintConsole implements Printer {
    public void printLnToConsole(String message) { System.out.println(message); }

    public void printToConsole(String message) {
        System.out.print(message);
    }
}
