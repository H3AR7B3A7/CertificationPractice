package be.dog.d.steven.OracleProgrammingComplete.chapter13.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter13.data.ProductFactory;

public class Shop {
    public static void main(String[] args) {
        var pf = new ProductFactory("en-GB");

        pf.printProductReport(1);
        pf.printProductReport(2);
        pf.printProductReport(3);
        pf.printProductReport(4);

    }
}
