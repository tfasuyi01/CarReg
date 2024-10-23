package main.java.Utilities;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
import main.java.Utilities.Parq;
import main.java.Utilities.Locator;




public class Html {
    public static final Locator ROOT = Parq.tag("html");
    public static final Locator BUTTON = Parq.tag("button");
    public static final Locator ANCHOR = Parq.tag("a");
    public static final Locator LIST_ITEM = Parq.tag("li");
    public static final Locator UNORDERED_LIST = Parq.tag("ul");
    public static final Locator INPUT = Parq.tag("input");
    public static final Locator LABEL = Parq.tag("label");
    public static final Locator SPAN = Parq.tag("span");
    public static final Locator H2 = Parq.tag("h2");
    public static final Locator H3 = Parq.tag("h3");
    public static final Locator SECTION = Parq.tag("section");

    public Html() {
    }

    public static enum Tag {
        SPAN("span"),
        INPUT("input"),
        UNORDERED_LIST("ul"),
        ORDERED_LIST("ol"),
        DIV("div"),
        ANCHOR("a"),
        BUTTON("button"),
        TEXTAREA("textarea"),
        LABEL("label"),
        PARAGRAPH("p"),
        H1("h1"),
        H2("h2"),
        H3("h3"),
        H4("h4"),
        H5("h5"),
        H6("h6"),
        LIST_ITEM("li"),
        SELECT("select"),
        IMG("img"),
        MAP("map"),
        OPTION("option");

        String value;

        private Tag(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum Attribute {
        TITLE("title"),
        PLACEHOLDER("placeholder"),
        VALUE("value"),
        NAME("name"),
        ID("id"),
        CLASS("class"),
        SOURCE("src"),
        WIDTH("width"),
        HEIGHT("height"),
        ALT("alt"),
        LANG("lang"),
        IMG("img"),
        HREF("href");

        String value;

        private Attribute(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
