package com.kreitek.editor;

import com.kreitek.editor.print.PrintConsole;
import com.kreitek.editor.print.Printer;

import java.util.ArrayList;

public class PrintFormat {
    private ArrayList<String> textLines;
    private String format;
    private Printer print = new PrintConsole();

    public PrintFormat(ArrayList<String> documentLines) {
        setFormat();
        setDocumentLines(documentLines);
    }
    private void setDocumentLines(ArrayList<String> documentLines) {
        this.textLines = documentLines;
    }
    private void setFormat() {
        this.format = Singleton.getFormat();
    }

    public void printText() throws FormatException {
        switch (format){
            case "text"-> textFormat();
            case "json"-> jsonFormat();
            default -> throw new FormatException();
        }
    }

    private void jsonFormat() {

        print.printLnToConsole("{\n" + "  \"doc\": [");
        for (int index = 0; index < textLines.size(); index++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("    {\"line\":\"");
            stringBuilder.append(index);
            stringBuilder.append("\",\"text\":\"");
            stringBuilder.append(textLines.get(index));
            stringBuilder.append("\"}");
            if (index != textLines.size()-1) stringBuilder.append(",");
            print.printLnToConsole(stringBuilder.toString());
        }
        print.printLnToConsole("  ]\n" + "}");
    }

    private void textFormat() {
        print.printLnToConsole("START DOCUMENT ==>");
        for (int index = 0; index < textLines.size(); index++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(index);
            stringBuilder.append("] ");
            stringBuilder.append(textLines.get(index));
            print.printLnToConsole(stringBuilder.toString());
        }
        print.printLnToConsole("<== END DOCUMENT");
    }
}
