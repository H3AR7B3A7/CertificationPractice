package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter2;

// MARKER ANNOTATION

import java.lang.annotation.*;

@interface MyMarkerAnnotation { }

// REQUIRED ELEMENTS

@interface RequiredElement {
    int requiredNumber();
}

// OPTIONAL ELEMENTS

@interface OptionalElement {
    int optionalNumber() default 1;
}

// IMPLICITLY PUBLIC STATIC FINAL VARIABLES

@interface ProvidingPi {
    double PI = 3.141_592_653_589_793;
}

// ANNOTATIONS ANNOTATIONS

@MyMarkerAnnotation
@RequiredElement(requiredNumber = 1)
@OptionalElement
public class Annotations {
    @MyMarkerAnnotation() @OptionalElement(optionalNumber = 2)
    private int number;

    @MyMarkerAnnotation private String sentence;

    @MyMarkerAnnotation
    public int getNumber() {
        return number;
    }

    @MyMarkerAnnotation
    public static void main(@MyMarkerAnnotation String[] args) {
        @MyMarkerAnnotation Annotations a = new Annotations();
    }
}

// ELEMENT TYPES

@interface ElementTypes {
    double primitiveType();

    String stringType();

    Class classType();

    Seasons enumType();

    RequiredElement annotaionType();
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

// VALUE ELEMENT

@interface Nameless {
    int value();
    int moreOptionalsAllowed() default 1;
}

@Nameless(1)
class NamelessElementDefined { }

// ARRAY TYPE ELEMENT

@interface ArrayType {
    String[] words();
}

@interface ArrayType2 {
    String[] value();
}

@ArrayType(words = "bird")
class ArrayTypeDefined0 { }

@ArrayType(words = {"bird", "is", "the", "word"})
class ArrayTypeDefined1 { }

@ArrayType2("bird")
class ArrayTypeDefined2 { }

@ArrayType2({"bird", "is", "the", "word"})
class ArrayTypeDefined3 { }

// @TARGET

@Target(ElementType.CONSTRUCTOR)
@interface ElementTypeSpecificAnnotation { }

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@interface ElementTypeSpecificAnnotation2 { }

@Target(ElementType.TYPE_USE)
@interface TypeUseAnnotation { }

class TypeUseAnnotations extends @TypeUseAnnotation Annotations {
    public static void main(String[] args) {
        Annotations a = new @TypeUseAnnotation Annotations();
        TypeUseAnnotations ta = (@TypeUseAnnotation TypeUseAnnotations) a;
    }
}

// @RETENTION

@Retention(RetentionPolicy.RUNTIME)
@interface RetentionAnnotation { }

// @DOCUMENTED

@Documented
@interface DocumentedAnnotation { }

// @INHERITED

@Inherited
@interface InheritedAnnotation { }

// @REPEATABLE

@Repeatable(Risks.class)
@interface Risk {
    String value();

    int level() default 1;
}

@interface Risks {
    Risk[] value();
}

@Risk("parachute")
@Risk(value = "fainting", level = 3)
class SkyDive { }

@Risks({
        @Risk("parachute"),
        @Risk(value = "fainting", level = 3)
})
class OldSkyDive { }

// JAVADOC ANNOTATIONS

/**
 * Javadoc annotations example
 *
 * @author steven.dhondt@kenze.be
 */
class Javadoc {
    /**
     * Javadoc annotations example
     *
     * @param a Some parameter
     * @return a Returning the parameter, useful...
     * @since 1.0
     * @deprecated Use someNewMethod() instead.
     */
    @Deprecated(since = "1.1", forRemoval = true)
    public int someMethod(int a) {
        return a;
    }
}

