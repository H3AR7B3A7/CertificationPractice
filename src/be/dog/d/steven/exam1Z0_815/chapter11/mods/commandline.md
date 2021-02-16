# Creating and Running a Modular Program
## Creating the Files
See the feeding folder for module zoo.animal.feeding
## Compiling our First Module
Compile program
```
javac --module-path mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java
```
Alternate forms of javac command
```
javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/*.java

javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java

javac -p mods -d feeding feeding/zoo/animal/feeding/Task.java feeding/module-info.java

javac -p mods -d feeding feeding/zoo/animal/feeding/Task.java feeding/*.java
```
## Running our First Module
Run program
```
java --module-path feeding --module zoo.animal.feeding/zoo.animal.feeding.Task
```
Alternate form of java command
```
java -p feeding -m zoo.animal.feeding/zoo.animal.feeding.Task
```
## Packaging our First Module

Create jar
```
jar -cvf ./out/zoo.animal.feeding.jar -C feeding/ .
```
Run program from jar
```
java -p out -m zoo.animal.feeding/zoo.animal.feeding.Task
```

# Updating our Example for Multiple Modules

## Updating the Feeding Module
See the feeding folder for module zoo.animal.feeding. Uncomment the commented out line in module-info.java

Re-compile:
```
javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java
```

Re-package
```
jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .
```

## Creating a Care Module
See the care folder for module zoo.animal.care

Compile:
```
javac -p out -d care care/zoo/animal/care/details/*.java care/zoo/animal/care/medical/*.java care/module-info.java
```

Package:
```
jar -cvf ./out/zoo.animals.care.jar -C care/ .
```

## Creating the Talks Module
See the talks folder for module zoo.animal.talks.

Compile:
```
javac -p out -d talks talks/zoo/animal/talks/content/*.java talks/zoo/animal/talks/media/*.java talks/zoo/animal/talks/schedule/*.java talks/module-info.java
```
Package:
```
jar -cvf ./out/zoo.animal.talks.jar -C talks/ .
```

## Creating the Staff Module
See the staff folder for module zoo.staff.

Compile:
```
javac -p out -d staff staff/zoo/staff/*.java staff/module-info.java
```
Package:
```
jar -cvf  ./out/zoo.staff.jar -C staff/ .
```

# Diving into the module-info file

## Exports
In the module-info.java in the talks folder:
1. Uncomment ```exports zoo.animal.talks.content to zoo.staff;```
2. Comment out ```exports zoo.animal.talks.content;```

## Requires
In the module-info.java in the care folder:
1. Uncomment ```requires transitive zoo.animal.feeding;```
1. Comment out ```requires zoo.animal.feeding;```

In the module-info.java in the talks folder:
1. Uncomment ```requires transitive zoo.animal.care;```
1. Comment out ```requires zoo.animal.feeding;```
1. Comment out ```requires zoo.animal.care;```

In the module-info.java in the staff folder:
1. Comment out ```requires zoo.animal.feeding;```
1. Comment out ```requires zoo.animal.care;```

## Provides, uses and opens
Don't need to be able to use in examples for this exam

# Discovering Modules

## The java Command
Describing a module:
```
java -p out -d zoo.animal.feeding
```

Alternate form of describing a module
```
java -p out --describe-module zoo.animal.feeding
```
Listing available modules in JDK:
```
java --list-modules
```
Listing available modules including ours:
```
java -p mods --list-modules
```

Showing Module Resolution:
```
java --show-module-resolution -p feeding -m zoo.animal.feeding/zoo.animal.feeding.Task
```

## The jar Command
Describing a module:
```
jar -f mods/zoo.animal.feeding.jar -d
```
Alternate form of describing a module:
```
jar --file mods/zoo.animal.feeding.jar --describe-module
```
## The jdeps Command
Listing dependencies
```
jdeps -s mods/zoo.animal.feeding.jar
```
Alternate form of listing dependencies:
```
jdeps -summary mods/zoo.animal.feeding.jar
```

Listing dependencies with module path
```
jdeps -s --module-path mods mods/zoo.animal.care.jar
```
Alternate form of listing dependencies with module path
```
jdeps -summary --module-path mods mods/zoo.animal.care.jar
```