package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter5;

class CannotSwimException extends Exception { }

class DangerInTheWaterException extends RuntimeException { }

class SharkInTheWaterException extends DangerInTheWaterException { }

public class ExceptionsAssertionsAndLocalization {

    public void swim(int a) throws CannotSwimException {
        if(a < 1){
            throw new CannotSwimException();
        } else if(a < 3){
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
        } catch (SharkInTheWaterException e){ // Specific
            e.printStackTrace();
        } catch (DangerInTheWaterException e){ // More Broad
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
        try (MyFileReader myFileReader1 = new MyFileReader("first"); MyFileReader myFileReader2 = new MyFileReader("second")){
            System.out.println("Try Block");
        } finally {
            System.out.println("Finally Block");
        }
        MyFileReader myFileReader1 = new MyFileReader("first"); // Needs to be effectively final
        MyFileReader myFileReader2 = new MyFileReader("second");
        try (myFileReader1; myFileReader2){
            System.out.println("Try Block");
        } finally {
            System.out.println("Finally Block");
        }
    }
}

