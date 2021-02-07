# Java Certification: Exercises & Examples

Practicing Java oddities and outliers for Oracle Certification.

**WARNING**: Some syntax in these exercises should never be used in real projects. It might be easily misinterpreted and won't make you any friends among your colleagues. It is only useful to understand when undertaking the Oracle Certification Test.

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

## Chapter 3: Operators

## Chapter 4: Making Decisions

## Chapter 5: Core Java APIs

