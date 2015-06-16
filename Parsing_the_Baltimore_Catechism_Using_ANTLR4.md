The Baltimore Catechism was the official American catholic catechism from long 
before my parents were young.  It was a clear and thorough introduction to the 
Faith, noted for its clear questions with simple but profound answers.  It is 
also delightfully public domain, so it seems worthwhile to convert the text 
into a machine-readable format, such as JSON.  This would make it possible to
write flash cards or games using the questions and answers.

The [text of the catechism](GutenbergBaltimoreCatechism1.txt) is taken from the Project Gutenberg edition found at [archive.org](http://archive.org/stream/baltimorecatechi14551gut/14551.txt).  It is quite regular, so one can hope to write a grammar that will parse it correctly.  For instance, here is lesson 26.

    LESSON TWENTY-SIXTH
    ON THE FIRST COMMANDMENT
    
    
    315. Q. What is the first Commandment?
    A. The first Commandment is: I am the Lord thy God: thou shalt not have
    strange gods before Me.
    
    317. Q. How do we adore God?
    A. We adore God by faith, hope, and love, by prayer and sacrifice.
    
    318. Q. How may the first Commandment be broken?
    A. The first Commandment may be broken by giving to a creature the honor
    which belongs to God alone; by false worship; and by attributing to a
    creature a perfection which belongs to God alone.
    
    320. Q. Are sins against faith, hope, and charity also sins against the
    first Commandment?
    A. Sins against faith, hope, and charity are also sins against the first
    Commandment.
    
    321. Q. How does a person sin against faith?
    A. A person sins against faith: 1st, by not trying to know what God has
    taught; 2d, by refusing to believe all that God has taught; 3d, by
    neglecting to profess his belief in what God has taught.
    
    326. Q. Are we obliged to make open profession of our faith?
    A. We are obliged to make open profession of our faith as often as God's
    honor, our neighbor's spiritual good, or our own requires it.
    
    327. Q. Which are the sins against hope?
    A. The sins against hope are presumption and despair.
    
    328. Q. What is presumption?
    A. Presumption is a rash expectation of salvation without making proper
    use of the necessary means to obtain it.
    
    329. Q. What is despair?
    A. Despair is the loss of hope in God's mercy.

# Building the project using Gradle

In order to make ANTLR a workable part of a big project, it is necessary to integrate it with a build tool, which in our case means [Gradle](http://gradle.org/).   There is an ANTLR4 plugin for Gradle, but I have never had any success with it, so we will create the file from scratch, following a [template](https://github.com/ae6rt/gradle-antlr4-template/blob/master/build.gradle) from GitHub.
