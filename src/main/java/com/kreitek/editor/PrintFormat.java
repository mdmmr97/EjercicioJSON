package com.kreitek.editor;

import com.kreitek.editor.print.PrintConsole;
import com.kreitek.editor.print.Printer;

import java.util.ArrayList;

public class PrintFormat implements Formatter{
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
        printTextLine("    {\"line\":\"","\",\"text\":\"","\"}",true);
        print.printLnToConsole("  ]\n" + "}");
    }

    private void textFormat() {
        print.printLnToConsole("START DOCUMENT ==>");
        printTextLine("[","] ","",false);
        print.printLnToConsole("<== END DOCUMENT");
    }
    private void printTextLine(String start, String between, String end, boolean coma){
        for (int index = 0; index < textLines.size(); index++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(start);
            stringBuilder.append(index);
            stringBuilder.append(between);
            stringBuilder.append(textLines.get(index));
            stringBuilder.append(end);
            if (index != textLines.size()-1 && coma) stringBuilder.append(",");
            print.printLnToConsole(stringBuilder.toString());
        }
    }
}
