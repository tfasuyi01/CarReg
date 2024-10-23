package main.java.Utilities;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Logger {
    private static int stepCounter = 1;

    public Logger() {
    }

    public static void logger(String... params) {
        List array = Arrays.asList(params);

        try {
            StackTraceElement testLevel = Thread.currentThread().getStackTrace()[4];
            StackTraceElement pageObjectLevel = Thread.currentThread().getStackTrace()[3];
            String parqMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
            String poClassName = java.lang.Class.forName(pageObjectLevel.getClassName()).getSimpleName();
            String poMethod = pageObjectLevel.getMethodName();
            String poFile = pageObjectLevel.getFileName();
            Integer poLine = pageObjectLevel.getLineNumber();
            String testMethod = testLevel.getMethodName();
            String testFile = testLevel.getFileName();
            Integer testLine = testLevel.getLineNumber();
            String time = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
            StringBuffer stringBuffer = new StringBuffer(time + " ");
            stringBuffer.append(" " + testFile + " : " + testLine + "  " + poClassName + "." + poMethod + "  (" + poFile + ":" + poLine + ") " + Colours.GREEN_BOLD + stepCounter++ + Colours.RESET);
            String indent;
            if (stepCounter < 11) {
                indent = "\t\t\t";
            } else {
                indent = "\t\t";
            }

            if (array == null) {
                stringBuffer.append(indent + " -\t" + Colours.GREEN_BOLD + parqMethod.substring(0, 1).toUpperCase() + parqMethod.substring(1) + Colours.RESET);
            }

            if (array.size() == 1) {
                stringBuffer.append(indent + " - " + Colours.GREEN_BRIGHT_BACKGROUND + (String)array.get(0) + Colours.RESET + " ");
            } else {
                stringBuffer.append(indent + " - \t" + Colours.BLUE_BOLD + parqMethod.substring(0, 1).toUpperCase() + parqMethod.substring(1) + Colours.RESET + " : ");
                stringBuffer.append((String)array.get(0) + " : " + Colours.CYAN_BACKGROUND_BRIGHT + (String)array.get(1) + Colours.RESET);
            }

            System.out.println(stringBuffer.toString());
        } catch (Exception var15) {
        }

    }

    public static void logMethodSuccess(String message) {
    }

    public static void logWarn(String message) {
        try {
            StackTraceElement testLevel = Thread.currentThread().getStackTrace()[4];
            StackTraceElement pageObjectLevel = Thread.currentThread().getStackTrace()[3];
            String parqMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
            String poClassName = java.lang.Class.forName(pageObjectLevel.getClassName()).getSimpleName();
            String poMethod = pageObjectLevel.getMethodName();
            String poFile = pageObjectLevel.getFileName();
            Integer poLine = pageObjectLevel.getLineNumber();
            String testMethod = testLevel.getMethodName();
            String testFile = testLevel.getFileName();
            Integer testLine = testLevel.getLineNumber();
            String time = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
            StringBuffer stringBuffer = new StringBuffer(time + " ");
            stringBuffer.append(" " + testFile + " : " + testLine + "  " + poClassName + "." + poMethod + "  (" + poFile + ":" + poLine + ") " + Colours.GREEN_BOLD + stepCounter++ + Colours.RESET);
            stringBuffer.append(Colours.TAB + "" + Colours.TAB + " - " + Colours.YELLOW_BACKGROUND_BRIGHT + message + Colours.RESET);
            System.out.println(stringBuffer.toString());
        } catch (Exception var13) {
        }

    }

    public static void logError(String message) {
        try {
            StackTraceElement testLevel = Thread.currentThread().getStackTrace()[4];
            StackTraceElement pageObjectLevel = Thread.currentThread().getStackTrace()[3];
            String parqMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
            String poClassName = java.lang.Class.forName(pageObjectLevel.getClassName()).getSimpleName();
            String poMethod = pageObjectLevel.getMethodName();
            String poFile = pageObjectLevel.getFileName();
            Integer poLine = pageObjectLevel.getLineNumber();
            String testMethod = testLevel.getMethodName();
            String testFile = testLevel.getFileName();
            Integer testLine = testLevel.getLineNumber();
            String time = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
            StringBuffer stringBuffer = new StringBuffer(time + " ");
            stringBuffer.append(" " + testFile + ":" + testLine + " " + poClassName + "." + poMethod + " (" + poFile + ":" + poLine + ")");
            stringBuffer.append(Colours.RED_BACKGROUND_BRIGHT + message + Colours.RESET);
        } catch (Exception var13) {
        }

    }

    private static String compileLog() {
        return (new SimpleDateFormat("HH:mm:ss")).format(new Date()) + " ";
    }

    public static enum Colour {
        GREEN("\t\u001b[102m"),
        ORANGE("\t\u001b[0;103m"),
        CYAN("\t\u001b[0;106m"),
        RED("\t\u001b[0;91m");

        String value;

        private Colour(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum Level {
        INFO,
        WARN,
        ERRIR;

        private Level() {
        }
    }
}
