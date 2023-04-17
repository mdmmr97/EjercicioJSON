package com.kreitek.editor;

import java.util.ArrayList;

public abstract class PrintFormat{
    private ArrayList<String> documentLines;
    private String format;


    public PrintFormat(String format, ArrayList<String> documentLines) {
        setFormat(format);
        setDocumentLines(documentLines);
    }
    private void setDocumentLines(ArrayList<String> documentLines) {
        this.documentLines = documentLines;
    }
    private void setFormat(String format) {
        this.format = format;
    }

    public void printFormat() throws FormatException {
        switch (format){
            case "text"-> textFormat();
            case "json"-> jsonFormat();
            default -> throw new FormatException();
        }
    }

    private void jsonFormat() {
    }

    private void textFormat() {
    }
    private void printLines(){

    }
}
