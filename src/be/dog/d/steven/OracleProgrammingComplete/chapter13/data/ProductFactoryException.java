package be.dog.d.steven.OracleProgrammingComplete.chapter13.data;

public class ProductFactoryException extends Exception {
    public ProductFactoryException() {
        super();
    }

    public ProductFactoryException(String message) {
        super(message);
    }

    public ProductFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
