grammar Catechism1;

/** Parse the Project Gutenberg edition of the Baltimore Catechism 1 */

file: .*? catechism .*;

catechism: .*?;

WS : [ \t]+ -> channel(HIDDEN);
CRLF : '\r'? '\n';


/** Default token to catch everything else */
WORD: [^ \t\r\n]+;
