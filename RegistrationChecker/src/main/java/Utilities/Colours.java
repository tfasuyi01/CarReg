package main.java.Utilities;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



public enum Colours {
    RESET("\u001b[0m"),
    BLACK("\u001b[0;30m"),
    RED("\u001b[0;31m"),
    GREEN("\u001b[0;32m"),
    YELLOW("\u001b[0;33m"),
    BLUE("\u001b[0;34m"),
    MAGENTA("\u001b[0;35m"),
    CYAN("\u001b[0;36m"),
    WHITE("\u001b[0;37m"),
    ORANGE("\u001b[38;2;40;177;249m"),
    BLACK_BOLD("\u001b[1;30m"),
    RED_BOLD("\u001b[1;31m"),
    GREEN_BOLD("\u001b[1;32m"),
    YELLOW_BOLD("\u001b[1;33m"),
    BLUE_BOLD("\u001b[1;34m"),
    MAGENTA_BOLD("\u001b[1;35m"),
    CYAN_BOLD("\u001b[1;36m"),
    WHITE_BOLD("\u001b[1;37m"),
    BLACK_UNDERLINED("\u001b[4;30m"),
    RED_UNDERLINED("\u001b[4;31m"),
    GREEN_UNDERLINED("\u001b[4;32m"),
    YELLOW_UNDERLINED("\u001b[4;33m"),
    BLUE_UNDERLINED("\u001b[4;34m"),
    MAGENTA_UNDERLINED("\u001b[4;35m"),
    CYAN_UNDERLINED("\u001b[4;36m"),
    WHITE_UNDERLINED("\u001b[4;37m"),
    GRAY_BACKGROUND("\u001b[40m"),
    BLACK_BACKGROUND("\u001b[40m"),
    RED_BACKGROUND("\u001b[41m"),
    GREEN_BACKGROUND("\t\u001b[42m"),
    YELLOW_BACKGROUND("\t\u001b[43m"),
    BLUE_BACKGROUND("\u001b[44m"),
    MAGENTA_BACKGROUND("\u001b[45m"),
    CYAN_BACKGROUND("\t\u001b[46m"),
    WHITE_BACKGROUND("\u001b[47m"),
    GREY_BACKGROUND("\u001b[47m"),
    BLACK_BRIGHT("\u001b[0;90m"),
    RED_BRIGHT("\t\u001b[0;91m"),
    GREEN_BRIGHT("\u001b[0;92m"),
    YELLOW_BRIGHT("\u001b[0;93m"),
    BLUE_BRIGHT("\u001b[0;94m"),
    MAGENTA_BRIGHT("\u001b[0;95m"),
    CYAN_BRIGHT("\u001b[0;96m"),
    WHITE_BRIGHT("\u001b[0;97m"),
    BLACK_BOLD_BRIGHT("\u001b[1;90m"),
    RED_BOLD_BRIGHT("\u001b[1;91m"),
    GREEN_BOLD_BRIGHT("\u001b[1;92m"),
    YELLOW_BOLD_BRIGHT("\u001b[1;93m"),
    BLUE_BOLD_BRIGHT("\u001b[1;94m"),
    MAGENTA_BOLD_BRIGHT("\u001b[1;95m"),
    CYAN_BOLD_BRIGHT("\u001b[1;96m"),
    WHITE_BOLD_BRIGHT("\u001b[1;97m"),
    GREEN_BRIGHT_BACKGROUND("\t\u001b[102m"),
    BLACK_BACKGROUND_BRIGHT("\u001b[0;100m"),
    RED_BACKGROUND_BRIGHT("\u001b[0;101m"),
    GREEN_BACKGROUND_BRIGHT("\u001b[0;102m"),
    YELLOW_BACKGROUND_BRIGHT("\t\u001b[0;103m"),
    BLUE_BACKGROUND_BRIGHT("\u001b[0;104m"),
    MAGENTA_BACKGROUND_BRIGHT("\u001b[0;105m"),
    CYAN_BACKGROUND_BRIGHT("\u001b[0;106m"),
    WHITE_BACKGROUND_BRIGHT("\u001b[0;107m"),
    MAGENTA_WHITE("\u001b[45m\u001b[0;97m"),
    RED_WHITE("\t\u001b[101m\u001b[0;97m"),
    GREY_WHITE("\u001b[47m\u001b[0;97m"),
    TAB("\t");

    private final String code;

    private Colours(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }

    public static void main(String[] args) {
        System.out.println(MAGENTA_WHITE + "***** PARQ Test Framework *****" + RESET);
        System.out.println(GREEN_BRIGHT_BACKGROUND + "Hello World" + RESET);
        System.out.println(CYAN_BACKGROUND_BRIGHT + "Hello World" + RESET);
        System.out.println(GREY_BACKGROUND + "Hello World" + RESET);
        System.out.println(RED_BRIGHT + "Hello World" + RESET);
        System.out.println(CYAN_BACKGROUND_BRIGHT + "Hello World" + RESET);
    }
}
