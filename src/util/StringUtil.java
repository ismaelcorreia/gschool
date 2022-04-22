package util;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.Duration;
import java.util.Locale;

public class StringUtil {

    public static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00#");


    public static String getNumberFormatted(double number) {

        return decimalFormat.format(
                number
        );
    }

    public static String replaceAll(String value,String exp, String newExp) {
        if(value.contains(exp))
            return replaceAll(value.replace(exp, newExp), exp, newExp);
        return value;
    }


    public static double getValueFromString(String number) {
        if(number.trim().isEmpty())  return 0.0;
        String stringValue = replaceAll(number, ".", "").replace(",",".");
        return Double.parseDouble(stringValue);
    }
    public static int getIntFromString(String number) {
        return (int) getValueFromString(number);
    }
}
