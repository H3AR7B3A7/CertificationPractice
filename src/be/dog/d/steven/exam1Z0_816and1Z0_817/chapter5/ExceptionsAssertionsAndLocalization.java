package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter5;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

class CannotSwimException extends Exception {
}

class DangerInTheWaterException extends RuntimeException {
}

class SharkInTheWaterException extends DangerInTheWaterException {
}

public class ExceptionsAssertionsAndLocalization {

    public void swim(int a) throws CannotSwimException {
        if (a < 1) {
            throw new CannotSwimException();
        } else if (a < 3) {
            throw new DangerInTheWaterException();
        } else {
            throw new SharkInTheWaterException();
        }
    }

    public static void main(String[] args) {
        try {
            new ExceptionsAssertionsAndLocalization().swim(3);
        } catch (CannotSwimException e) {
            e.printStackTrace();
        } catch (SharkInTheWaterException e) { // Specific
            e.printStackTrace();
        } catch (DangerInTheWaterException e) { // More Broad
            e.printStackTrace();
        }
    }
}

// CUSTOM EXCEPTION CONSTRUCTORS

class ExampleException extends Exception {
    public ExampleException() {
        super(); // Optional, compiler will insert this automatically
    }

    public ExampleException(Exception e) {
        super(e);
    }

    public ExampleException(String message) {
        super(message);
    }
}

class ExampleException2 extends Exception {

    public ExampleException2(Exception e) {
        super("Example exception because of: " + e.toString()); // From exception parameter to message in super call
    }

}

class ExampleUsage {
    public static void main(String[] args) throws ExampleException {
        throw new ExampleException(new SharkInTheWaterException());
    }
}

class ExampleUsage2 {
    public static void main(String[] args) throws ExampleException2 {
        throw new ExampleException2(new SharkInTheWaterException());
    }
}

// AUTOMATING RESOURCE MANAGEMENT

class MyFileReader implements AutoCloseable {
    private final String tag;

    public MyFileReader(String tag) {
        this.tag = tag;
    }

    @Override
    public void close() {
        System.out.println("Closed: " + tag);
    }
}

class UsingMyFileReader {
    public static void main(String[] args) {
        try (MyFileReader myFileReader1 = new MyFileReader("first"); MyFileReader myFileReader2 = new MyFileReader("second")) {
            System.out.println("Try Block");
        } finally {
            System.out.println("Finally Block");
        }
        MyFileReader myFileReader1 = new MyFileReader("first"); // Needs to be effectively final
        MyFileReader myFileReader2 = new MyFileReader("second"); // If this line throws an exception the previous will never be closed
        try (myFileReader1; myFileReader2) {
            System.out.println("Try Block");
        } finally {
            System.out.println("Finally Block");
        }
    }
}

// DATES AND TIMES

class DateAndTimeExamples {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());

        LocalTime thisTime = LocalTime.of(15, 14, 10);
        System.out.println(thisTime.getHour());
        LocalDate today = LocalDate.of(2021, Month.FEBRUARY, 26);
        System.out.println(today.getDayOfWeek());
        System.out.println(today.getDayOfYear());

        LocalDateTime now = LocalDateTime.of(today, thisTime);
        LocalDateTime tomorrow = now.plusDays(1);

        LocalDateTime someDay = LocalDateTime.of(2021, 3, 30, 15, 20);

        System.out.println(someDay.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(someDay.format(DateTimeFormatter.ISO_LOCAL_TIME));

        var myFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
        System.out.println(myFormat.format(someDay));
        System.out.println(someDay.format(myFormat));
    }
}

// INTERNATIONALIZATION

class InternationalizationExamples {
    private static int someLargeNumber = 1_432_200;
    private static double money = 2.45;
    private static double somePreciseDecimal = 2_200.0012;

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale);

        Locale myLocale = new Locale.Builder()
                .setLanguage("nl")
                .setRegion("BE")
                .build();
        System.out.println(myLocale);

        Locale.setDefault(myLocale);
        System.out.println(Locale.getDefault());

        var us = NumberFormat.getInstance(Locale.US);
        var ge = NumberFormat.getInstance(Locale.GERMANY);
        var ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);

        var usC = NumberFormat.getCurrencyInstance(Locale.US);
        var geC = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        var caC = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);

        System.out.println(us.format(someLargeNumber));
        System.out.println(ge.format(someLargeNumber));
        System.out.println(ca.format(someLargeNumber));

        System.out.println(usC.format(money));
        System.out.println(geC.format(money));
        System.out.println(caC.format(money));

        NumberFormat format1 = new DecimalFormat("###,###,###.######");
        System.out.println(format1.format(somePreciseDecimal));
        NumberFormat format2 = new DecimalFormat("000,000,000.000 000");
        System.out.println(format2.format(somePreciseDecimal));
        NumberFormat format3 = new DecimalFormat("€###,###.00");
        System.out.println(format3.format(money));
        System.out.println(format3.format(somePreciseDecimal));

        System.out.println(formatValue(somePreciseDecimal, "€###,###.00"));
    }

    private static String formatValue(Number value, String formatString) {
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        formatSymbols.setDecimalSeparator('.');
        formatSymbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat(formatString, formatSymbols);
        return formatter.format(value);
    }
}

// DATE TIME FORMATTER

class DateTimeFormatterExamples {

    public static void print(DateTimeFormatter dtf, LocalDateTime dateTime) {
        System.out.println(dtf.format(dateTime));
    }

    public static void print(DateTimeFormatter dtf, LocalDateTime dateTime, Locale locale) {
        System.out.println(dtf.withLocale(locale).format(dateTime));
    }

    public static void printCurrency(Locale locale, double amount){
        System.out.println(NumberFormat.getCurrencyInstance(locale).format(amount) +
                ", " + locale.getDisplayLanguage());
    }

    public static void printCurrencyDefault(Locale locale, double amount){
        System.out.println(NumberFormat.getCurrencyInstance().format(amount) +
                ", " + locale.getDisplayLanguage());
    }

    public static void main(String[] args) {
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(LocalDateTime.now()));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.CANADA_FRENCH).format(LocalDateTime.now()));

        print(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG), LocalDateTime.now());
        print(DateTimeFormatter.ISO_ORDINAL_DATE, LocalDateTime.now(), Locale.CANADA_FRENCH);

        printCurrency(new Locale("en","US"),123.45);
        printCurrency(new Locale("es","ES"),123.45);
        printCurrency(new Locale("fr","CA"),123.45);

        Locale.setDefault(new Locale("en","US"));
        printCurrencyDefault(new Locale("en", "US"), 123.45);
        Locale.setDefault(Locale.Category.DISPLAY, new Locale("es","ES"));
        printCurrencyDefault(new Locale("es", "ES"), 123.45);

        Locale.setDefault(new Locale("en","US"));
        Locale.setDefault(Locale.Category.FORMAT, new Locale("es","ES"));
        printCurrencyDefault(new Locale("es", "ES"), 123.45);
        Locale.setDefault(Locale.Category.DISPLAY, new Locale("es","ES"));
        printCurrencyDefault(new Locale("es", "ES"), 123.45);
    }
}