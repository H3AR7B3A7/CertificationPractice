package be.dog.d.steven.OracleProgrammingComplete.chapter4.app;

import be.dog.d.steven.OracleProgrammingComplete.chapter4.data.Product;

import java.math.BigDecimal;

public class Shop {
    public static void main(String[] args) {
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Tea");
        p1.setPrice(BigDecimal.valueOf(2.10));

        System.out.println(p1.getId() + ", " + p1.getName() + ", " + p1.getPrice() + ", " + p1.getDiscount());
    }
}
