DO NOT USE!
===========

This was me exploring Truffle and I got so many things wrong!
A proper example of using Truffle is the *simple language* included in their repository.
If you are looking for an example of how to just *use* Truffle, without all the `mx` build system stuff in the Truffle repository, have a look at <https://github.com/fehrenbach/parsers>, which uses maven for fetching dependencies and building. There's stuff about Scala in there, but that should not be needed, have a look at older commits.

tuber-basic
===========

A BASIC on Truffle


Compile and run
---------------

Generate an executable uberjar with maven:

> mvn install

Run with default JVM:

> java -jar target/tuber-basic-1.0-SNAPSHOT.jar fizzbuzz.tuber

Run with graal-enabled JVM: (this runs the program, but I don't think it does any clever compilation...)

> /home/stefan/opt/graalvm-jdk1.8.0/bin/java -G:+TraceTruffleCompilationDetails -G:TruffleCompilationThreshold=1 -jar target/tuber-basic-1.0-SNAPSHOT.jar fizzbuzz.tuber

