grammar Catechism1;

/** Parse the Project Gutenberg edition of the Baltimore Catechism 1 */

file: .*? catechism .*;

catechism: .*?

WS : [ \T]+ -> channel(HIDDEN);
CRLF : '\r'? '\n';


/** Default token to catch everything else */
WORD: ~(WS | CRLF)+;
