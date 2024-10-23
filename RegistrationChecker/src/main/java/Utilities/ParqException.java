package main.java.Utilities;





public class ParqException extends RuntimeException {
    public ParqException(String message) {
        super("ParqException: " + message + returnStackTrace());
        System.out.println(this.getMessage());
    }

    private static String returnStackTrace() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StringBuffer stackTrace = new StringBuffer();
        StackTraceElement ste = stackTraceElements[3];
        stackTrace.append("\n" + ste.getClassName() + " > " + ste.getMethodName() + " > " + ste.getLineNumber());
        ste = stackTraceElements[4];
        stackTrace.append("\n" + ste.getClassName() + " > " + ste.getMethodName() + " > " + ste.getLineNumber());
        ste = stackTraceElements[5];
        stackTrace.append("\n" + ste.getClassName() + " > " + ste.getMethodName() + " > " + ste.getLineNumber());
        return stackTrace.toString();
    }
}
