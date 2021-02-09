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

**Enhanced for-loop**:
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



