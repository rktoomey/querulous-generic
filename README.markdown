# Querulous (vanilla JDBC fork)

An agreeable way to talk to your database.

## License

Copyright 2010 Twitter, Inc. See included LICENSE file.

## Features

* Handles all the JDBC bullshit so you don't have to: type casting for primitives and collections, exception handling and transactions, and so forth;
* Fault tolerant: configurable strategies such as timeouts, mark-dead thresholds, and retries;
* Designed for operability: rich statistics about your database usage and extensive debug logging;
* Minimalist: minimal code, minimal assumptions, minimal dependencies. You write highly-tuned SQL and we get out of the way;
* Highly modular, highly configurable.

The grandpa Github source repository is [nkallen/queruous][grandpa], although the party appears to have moved to
[twitter/querulous][mothership].

This fork is a generic implementation of querulous that strives to stay current with the latest version of Scala.  Patches
and contributions are welcome.

## Implementation notes

See [nkallen/querulous][grandpa].

## Drop the deps into place

In your `sbt` project:

    val querulous = "com.twitter" % "querulous" % "1.2.0-generic"

    val novusRels = "novus rels" at "http://repo.novus.com/releases"
    val novusSnaps = "novus snaps" at "http://repo.novus.com/snapshots"

In your pom.xml:

    <dependency>
      <groupId>com.twitter</groupId>
      <artifactId>querulous</artifactId>
      <version>1.2.0-generic</version>
    </dependency>

    <repository>
        <id>novus.releases</id>
        <name>Novus Release</name>
        <url>http://repo.novus.com/releases</url>
        <releases><enabled>true</enabled></releases>
        <snapshots><enabled>false</enabled></snapshots>
    </repository>
    <repository>
        <id>novus.snapshots</id>
        <name>Novus Snapshot</name>
        <url>http://repo.novus.com/snapshots</url>
        <releases><enabled>false</enabled></releases>
        <snapshots><enabled>true</enabled></snapshots>
    </repository>

## Check out source

    $ git clone https://github.com/rktoomey/querulous-generic.git

## Build using sbt (recommended)

Using the latest version of [sbt][sbt] is recommended.

    sbt update publish-local

## Build using ant (not recommended)

    ant

Achtung: Using ant should work, and if it is broken I will try to fix it.  However, I haven't used ant since 2008, so I would
happily accept pull requests to fix, update or improve the ant build.

## But my build didn't work...

Example of working sbt build: <https://gist.github.com/797914>
Example of working ant build: <https://gist.github.com/798989>

Compare your output against my output and report what's gone wrong (unsatisfied deps, target misfire, compile error etc).

## Running Tests

The tests were broken when I forked, so...  um.  Well.  I will do my best to merge back from the main branch and fix them.

## Reporting problems

Drop me a line.

## What does the future hold?

Batch processing, pixel perfect repros of old masters suitable for framing, a full 32-volume calf-bound hand-tooled set of
Best of Obfuscated Perl with deckle-edged pages.  No?  Well, OK.  Batch processing it is.

   [sbt]: http://code.google.com/p/simple-build-tool/
   [grandpa]: http://github.com/nkallen/querulous/
   [mothership]: http://github.com/twitter/querulous/