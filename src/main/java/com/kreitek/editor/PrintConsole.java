package com.kreitek.editor;

public class PrintConsole implements Printer{
    public void printLnToConsole(String message) { System.out.println(message); }

    public void printToConsole(String message) {
        System.out.print(message);
    }
}
