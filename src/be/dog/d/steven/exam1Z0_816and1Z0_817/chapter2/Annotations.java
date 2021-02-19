package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter2;

@interface MyMarkerAnnotation { }

@interface RequiredElement {
    int requiredNumber();
}

@interface OptionalElement {
    int optionalNumber() default 1;
}

@interface ProvidingPi {
    double PI = 3.141_592_653_589_793;
}

@interface ElementTypes {
    double primitiveType();

    String stringType();

    Class classType();

    Seasons enumType();

    RequiredElement annotaionType();
}

@MyMarkerAnnotation
@RequiredElement(requiredNumber = 1)
@OptionalElement
public class Annotations {
    @MyMarkerAnnotation()
    @OptionalElement(optionalNumber = 2)
    private int number;

    @MyMarkerAnnotation private String sentence;

    @MyMarkerAnnotation
    public int getNumber() {
        return number;
    }

    @MyMarkerAnnotation
    public static void main(String[] args) {

    }
}

enum Seasons {
    SUMMER, FALL, WINTER, SPRING
}

@ElementTypes(primitiveType = 1,
        stringType = "",
        classType = Annotations.class,
        enumType = Seasons.SUMMER,
        annotaionType = @RequiredElement(requiredNumber = 1))
class ElementTypesDefined { }