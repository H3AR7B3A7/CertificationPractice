package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter5;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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
