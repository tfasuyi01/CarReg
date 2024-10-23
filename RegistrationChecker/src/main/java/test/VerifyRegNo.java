package main.java.test;

import main.java.Utilities.Parq;

import static main.java.Utilities.Parq.focus;
import static main.java.Utilities.Parq.show;

public class VerifyRegNo {

    public static String make(final String value) throws Exception {
        focus(Parq.text(value));
        Parq.ascend();
        show();
        Parq.contains(value);
        return  value;

    }

}
