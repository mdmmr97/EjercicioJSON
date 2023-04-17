package com.kreitek.editor;

import com.kreitek.editor.commands.CommandFactory;
import com.kreitek.editor.print.Formatter;
import com.kreitek.editor.print.PrintConsole;
import com.kreitek.editor.print.PrintFormat;
import com.kreitek.editor.print.Printer;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleEditor implements Editor {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    private final CommandFactory commandFactory = new CommandFactory();
    private ArrayList<String> documentLines = new ArrayList<String>();
    private Printer print = new PrintConsole();

    @Override
    public void run() {
        boolean exit = false;
        while (!exit) {
            String commandLine = waitForNewCommand();
            try {
                Command command = commandFactory.getCommand(commandLine);
                command.execute(documentLines);
            } catch (BadCommandException e) {
                printErrorToConsole("Bad command");
            } catch (ExitException e) {
                exit = true;
            }
            showDocumentLines(documentLines);
            showHelp();
        }
    }

    private void showDocumentLines(ArrayList<String> textLines) {
        if (textLines.size() > 0){
            setTextColor(TEXT_YELLOW);
            try {
                Formatter format = new PrintFormat(documentLines);
                format.printText();
            } catch (FormatException e) {
                throw new RuntimeException(e);
            }
            setTextColor(TEXT_RESET);
        }
    }

    private String waitForNewCommand() {
        print.printToConsole("Enter a command : ");
        Scanner scanner = new Scanner(System. in);
        return scanner.nextLine();
    }

    private void showHelp() {
        print.printLnToConsole("To add new line -> a \"your text\"");
        print.printLnToConsole("To update line  -> u [line number] \"your text\"");
        print.printLnToConsole("To delete line  -> d [line number]");
    }

    private void printErrorToConsole(String message) {
        setTextColor(TEXT_RED);
        print.printToConsole(message);
        setTextColor(TEXT_RESET);
    }

    private void setTextColor(String color) {
        System.out.println(color);
    }

}
