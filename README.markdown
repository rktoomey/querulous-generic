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

The grandpa Github source repository is {here}[http://github.com/nkallen/querulous/]. Patches and contributions are
welcome.

This fork is a generic implementation of querulous that strives to stay current with the latest version of Scala.

## Implementation notes

See {here}[http://github.com/nkallen/querulous/].

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

## Installation

Checkout source:

    $ git clone https://github.com/rktoomey/querulous-generic.git

Build via:

Using the latest version of {sbt}[http://code.google.com/p/simple-build-tool/] is recommended.

    sbt update publish-local

Use of `ant` should work, and if it is broken I will try to fix it.  However, I haven't used `ant` since 2008, so I would
happily accept pull requests to fix, update or improve the ant build.

## But my build didn't work...

Compare your sbt output against my output at [https://gist.github.com/797914] and file an issue explaining how the two
differ.

## Running Tests

The tests were broken when I forked, so...  um.  Well.  I will do my best to merge back from the main branch and fix them.

## Reporting problems

The Github issue tracker is {here}[http://github.com/rktoomey/querulous-generic/issues].