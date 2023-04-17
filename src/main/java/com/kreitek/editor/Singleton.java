package com.kreitek.editor;

public class Singleton {
    private static String format;
    private static Singleton instance;

    private Singleton (String format) {this.format = format;}

    public static String getFormat() {
        return format;
    }
    public static Singleton getInstance(String format) {
        if (instance == null) new Singleton(format);
        return instance;
    }
}
