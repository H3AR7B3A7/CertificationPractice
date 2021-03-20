package be.dog.d.steven.OracleProgrammingComplete.chapter12.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter12.data.ProductFactory;
import be.dog.d.steven.OracleProgrammingComplete.chapter12.data.Rating;

public class Shop {
    public static void main(String[] args) {
        var pf = new ProductFactory("en-GB");

        pf.parseProduct("F,1,Pizza,8.50,2021-03-22");
        pf.parseReview("1,5,Perfect");
        pf.parseReview("1,4,To lick your fingers");
        pf.parseReview("1,3,It was oké");
        pf.parseReview("1,4,Tasted truly Italian");
        pf.printProductReport(1);

        pf.parseProduct("D,2,Coffee,2.10,");
        pf.parseReview("2,1,Tasted like water");
        pf.parseReview("2,2,Is this coffee? I ordered tea");
        pf.parseReview("2,3,It was oké");
        pf.printProductReport(2);

        pf.parseProduct("D,3,Tea,1.80,");
        pf.parseReview("3,1,Tasted like dish water");
        pf.parseReview("3,1,Is this tea? I ordered coffee");
        pf.parseReview("3,3,It was oké");
//        pf.printProductReport(3);

        pf.parseProduct("F,4,Cake,3.50,2021-03-20");
        pf.parseReview("4,5,Perfect");
        pf.parseReview("4,4,To lick your fingers");
        pf.parseReview("4,3,It was oké");
        pf.parseReview("4,5,Best cake ever");
//        pf.printProductReport(4);

        pf.printProductReport(69);
        pf.reviewProduct(69, Rating.FIVE_STAR, "OMG!!!");

        pf.parseReview("69,5,Best cake ever");
        pf.parseReview("45,Best cake ever"); // Handled by parse
        pf.parseReview("4,x,Best cake ever"); // Not handled

        pf.parseProduct("F,5,Chocolate,2.50");
        pf.parseProduct("F,4,Cake,3.50,2021-69-69");
    }
}
