tuber-basic
===========

A BASIC on Truffle


Compile and run
---------------

Generate an executable uberjar with maven:

> mvn package

Run with default JVM:

> java -jar target/tuber-basic-1.0-SNAPSHOT.jar fizzbuzz.tuber

Run with graal-enabled JVM: (this runs the program, but I don't think it does any clever compilation...)

> /home/stefan/opt/graalvm-jdk1.8.0/bin/java -G:+TraceTruffleCompilationDetails -G:TruffleCompilationThreshold=1 -jar target/tuber-basic-1.0-SNAPSHOT.jar fizzbuzz.tuber

