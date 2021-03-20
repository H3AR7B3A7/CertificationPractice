package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter5;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Extra {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("be.dog.d.steven.exam1Z0_816and1Z0_817.chapter5.messages");

        String offerPattern = rb.getString("offer");
        String s = MessageFormat.format(offerPattern, "Tea", 5, "3%", LocalTime.now());
        System.out.println(s);

        ZonedDateTime now = ZonedDateTime.now();
        String t = now.format(DateTimeFormatter.ofPattern(rb.getString("dateFormat")));
        System.out.println(t);
    }
}

class Logging {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    private static final String TXT = "in a logger";

    public static void main(String[] args) {
        // BASICS
        try {
            throw new Exception();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"Some SEVERE message",e);
            LOGGER.log(Level.WARNING, "Some WARNING");
        }
        LOGGER.log(Level.INFO,"Some INFO");
        LOGGER.info("Some more info");

        // METHODS WITH OTHER PARAMS
        LOGGER.logp(Level.WARNING, Logging.class.getName(),"main","Some message");
        LOGGER.logrb(Level.INFO,ResourceBundle.getBundle("be.dog.d.steven.exam1Z0_816and1Z0_817.chapter5.messages"),"offer","Something",5,1, LocalDate.now());

        // CHANGE LEVEL
        LOGGER.setLevel(Level.FINEST);

        // PUBLISH THIS LEVEL !!!
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.FINER);
        LOGGER.addHandler(handler);

        // SPECIAL FINER LOGGER METHODS
        LOGGER.entering(Logging.class.getName(),"main","test");
        LOGGER.exiting(Logging.class.getName(),"main");
        LOGGER.throwing(Logging.class.getName(),"Throwing",new RuntimeException());

//        LOGGER.setLevel(Level.INFO);

        // PREVENTING WORK IN IGNORED LOGS
        if(LOGGER.isLoggable(Level.FINER)){
            LOGGER.log(Level.FINE, "Some work "+ TXT +" that can be avoided");
        }

        LOGGER.log(Level.FINE, "Some work {0} that can be avoided", TXT);
    }
}
