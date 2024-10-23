package main.java.Utilities;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import org.openqa.selenium.By;

public class Locator {
    private By by;
    private String value;
    private Locator.Type type;

    public Locator(By by, String value, Locator.Type type) {
        this.by = by;
        this.value = value;
        this.type = type;
    }

    public By getBy() {
        return this.by;
    }

    public String getValue() {
        return this.value;
    }

    public Locator.Type getType() {
        return this.type;
    }

    public String toString() {
        return this.type + " : " + this.value;
    }

    public static enum Type {
        ID,
        TEXT,
        PARTIAL_TEXT,
        ENCLOSURE,
        CLASS,
        CLASSES,
        CSS,
        TAG,
        NAME,
        VALUE,
        LINK_TEXT,
        PARTIAL_LINK_TEXT,
        LEAF,
        SIBLINGS;

        private Type() {
        }
    }
}
