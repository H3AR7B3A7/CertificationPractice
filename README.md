# Java Certification: Exercises & Examples

Practicing Java oddities and outliers for Oracle Certification.

**WARNING**: Some syntax in these exercises should never be used in real projects.
It might be easily misinterpreted and won't make you any friends among your colleagues.
It is only useful to understand when undertaking the Oracle Certification Test.

## Chapter 1: Welcome to Java

### Benefits of Java

- Object-oriented
- Encapsulation
- Platform independent
- Robust
- Simple
- Secure
- Multithreaded
- Backward Compatibility

### JDK

- JRE (no longer separately available):
    - JVM
    - core classes
    - supporting classes
- Interpreter (java)
- Compiler (javac)
- Archiver (jar)
- Documentation generator (javadoc)
- ...

### Compilation

Multiple files:
>javac FileName.java  
>java Filename

One file (in memory):
>java FileName.java

Multiple files in multiple packages
>javac -d classes packagea/ClassA.java packageb/ClassB.java  
>javac -d classes packagea/*.java packageb/*.java  
>java -cp classes packageb.ClassB  

### Javac command options

- -cp <classpath>
- -classpath <classpath>
- --class-path <classpath>
- -d <dir>

### Java command options

- -cp <classpath>
- -classpath <classpath>
- --class-path <classpath>

### Jar command options

- -c
- --create
- -v
- --verbose
- -f <fileName>
- --file <fileName>
- -C <directory>

## Chapter 2: Java Building Blocks

Primitives:
- boolean
- byte (8-bit)
- short (16-bit)
- int (32-bit)
- long (64-bit)
- float (32-bit)
- double (64-bit)
- char (16-bit)

Literals can have '_' to make reading big numbers more easy, but not:
- At the beginning
- At the end
- Near a comma

Identifiers:
- Start with a letter, $ or _
- Can hold numbers but not start with them
- Can't be a single '_'
- Can't be a reserved word

Var can only be used with single local variables:
- Not with instance~ or class variables
- Not in constructor or method parameters
- Not in multiple variable declarations

Scopes:
- Local variables: in scope from declaration to end of block, also in nested blocks
- Instance variables: in scope from declaration until the object is eligible for garbage collection
- Class variable: in scope from declaration until the program ends

An object is eligible for garbage collection:
- when it no longer has any references
- when all references have gone out of scope

Garbage collection:
- Can delete objects from the heap to release memory
- Can only be suggested (System.gc())
- Might never run

Finalize():
- Can run zero or one time(s)
- Was deprecated as of Java 9


## Chapter 3: Operators

Operator precedence:
- Post-unary
- Pre-unary
- Unary
- Multiplication, division & modulus
- Addition & subtraction
- Shift
- Relational
- Equal~ & not equal to
- Logical
- Short-circuit logical
- Ternary
- Assignment

Primitive numeric promotion in arithmetic operations:
- Will always promote primitives smaller than integers to an int
- Will promote the smallest of 2 operands to the larger one
- Will promote an integral data type to its floating-point equivalent when one of the operands is a floating-point

When assigning values:
- Casting to a larger datatype is automatic
- Casting to a smaller data type requires a cast, with possible loss of data

## Chapter 4: Making Decisions

**If-statements**:
- Can have only one else statement each
- Can be chained

**Switch-statements**:
- Can be of these data types:
  - Integer & int
  - Byte & byte
  - Short & short
  - Character & char
  - String
  - Enum
  - Var
- Case values should be:
  - Compile-time constant values (literal, enum or final constant)
  - Of the same data type as the switch value
  
**While-statements**:
- Might never run if condition is not met

**Do/while-statements**:
- Always run at least once, even if condition is not met

**For-loop**:
- Optional
  - Initialization statements
  - Boolean expressions
  - Update statements
- Code block

**Enhanced For-loop**:
- Iterable collection or array
- Single member data type and handle

*Flow statements*:  
break, continue & return

*Labels*:  
Optional in front of statements that allow flow to jump to or break from that statement

## Chapter 5: Core Java APIs

- String, StringBuilder & The String Pool
- Array
- List
- Wrapper class, Autoboxing & Unboxing  
- Set & Map  
- Math API

## Chapter 6: Lambda's and Functional Interfaces

**Functional Interfaces**:  
*Simplified definition*: Functional intefaces have only one abstract method.

- **Predicate**:  
A function used to return a boolean value.

      public interface Predicate {
        boolean test(T t);
      }

- **Consumer**:  
A function which takes in one argument and produces a result.
However, it doesn't return any value.

      public interface Consumer {
        void accept(T t);
      }

- **Supplier**:  
A function which does not take in any argument, but produces a value of type T.

      public interface Supplier {
        T get(); 
      }

- **Comparator**:  
A function used to compare 2 objects

      public interface Comparator(T o1, T o2) {
        int compare(T o1, T o2)
      }

  Has many other static and default methods for writing complex comparators.

**Lambda**:
- Are only allowed to reference effectively final values

Short notation:
  
      a -> a.System.out.println(a)

Full notation:

      (String a, String b) -> { return a.compareTo(b); }

Some API that take lambda's:
- removeIf(): Takes a Predicate
- sort(): Takes a Comparator
- forEach(): Takes a Consumer

## Chapter 7: Methods and Encapsulation

**Method Signature**:
- Access modifier
- Optional specifier
- Return type
- Method name
- Parameter list
- Optional exception list
- Method body

**Access Modifiers**:
- Private
- None (Package-private)
- Protected
- Public

**Optional Specifiers**:
- Static
- Abstract
- Final
- Synchronized
- Native
- Scriptfp

With **Method overloading**:
- Multiple methods can have the same name with different parameters.
- Type erasure will enforce type constraints only at compile time and discards the element type information at runtime.

Java handles method parameters as **pass-by-value**, which means we can't reassign parameter values inside a method.
However, we can call methods on them.

## Chapter 8: Class Design

Java only allows **single inheritance**. However, we can use multiple interface implementations,
or inner classes that inherit from a parent to get around this 'limitation'.

The **this** keyword helps us to reference an instance variable of the current instance.
This is useful when a method or constructor param share the instance variable name.

The **super** keyword helps us to refer to variables in the scope of a parent class.

**Constructors**:
- Do not have a return type
- Match name with the name of the class
- Can't have 'var' as params
- Can be overloaded, like methods
- Are called when creating a **new** object
- Will be provided by the compiler, when we do not (*default constructor*)
- Final instance fields must be initialized before the end of the constructor
  - In the same line
  - In an instance initializer block
  - In the constructor itself

We can use **this()** to prevent code duplication when overloading constructors.
- The call to this() has to be first in the constructor
- There can only be one
- Calling on other constructors has to end in a constructor not calling this() at some point (no infinite loops)

We can use **super()** to call parent constructors when inheriting.
- The call to super() has to be first in the constructor
- There can only be one

When **overriding** a method:
- It should have the same signature (if it doesn't, we are overloading or creating a new method)
- It has to be at least as accessible as the method in the parent class
- It should return the same type, or a subtype of the method in the parent class
- It can not declare exceptions that are broader than or new those of the parents

When overriding *generic* methods:
- The collection type has to match (or else you are overloading, instead of overriding)
- The generic type params have to match

When **hiding** static methods:
- It should have the same signature (if it doesn't, we are overloading or creating a new method)
- It has to be at least as accessible as the method in the parent class
- It should return the same type, or a subtype of the method in the parent class
- It can not declare exceptions that are broader than or new those of the parents
- Both methods should be static

*Final methods can't be overridden nor hidden!*

Because of **polymorphism** an object can be accessed using a reference of:
- The same type as the object
- A superclass of the object
- An interface that the object implements


## Chapter 9: Advanced Class Design

**Abstract classes**: 
- Do not have to contain abstract methods
- Can not be instantiated
- Can hold all members a regular class can (variables, constructor, ...)
- Can not be final
- Can not be private or protected

**Abstract methods**: 
- Can only exist in abstract classes or interfaces
- Can not be more accessible than their concrete implementations (*Overriding rules apply!)
- Because it prevents the ability to be overridden:
  - They can not be final
  - They can not be private
  - They can not be static

**Implementing an abstract method = overriding.*

**Concrete classes**  
The first concrete class to inherit from an abstract class:
- Must implement all inherited abstract methods from abstract classes
- Must implement all abstract methods from inherited interfaces

