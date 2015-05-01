grammar Catechism1;

/** Parse the Project Gutenberg edition of the Baltimore Catechism 1 */

file: text catechism  MORNING_PRAYERS text;

catechism: CRLF CATECHISM CRLF+ lesson+;

lesson: CRLF LESSON ordinal CRLF title question+;
question: CRLF+  qNum QQ text CRLF AA text list? CRLF+;
qNum: QNUM;
list: listItem+;
listItem: CRLF lnum text;
lnum: QNUM;
ordinal: ORDINAL;
title:   ~CRLF+;
CATECHISM: 'CATECHISM';
LESSON:    'LESSON';
MORNING_PRAYERS: 'MORNING PRAYERS';
ORDINAL: 'FIRST'
    | 'SECOND'
    | 'THIRD'
    | 'FOURTH'
    | 'FIFTH'
    | 'SIXTH'
    | 'SEVENTH'
    | 'EIGHTH'
    | 'NINTH'
    | 'TENTH'
    | 'ELEVENTH'
    | 'TWELFTH'
    | 'THIRTEENTH'
    | 'FOURTEENTH'
    | 'FIFTEENTH'
    | 'SIXTEENTH'
    | 'SEVENTEENTH'
    | 'EIGHTEENTH'
    | 'NINTEENTH'
    | 'TWENTIETH'
    | 'TWENTY-FIRST'
    | 'TWENTY-SECOND'
    | 'TWENTY-THIRD'
    | 'TWENTY-FOURTH'
    | 'TWENTY-FIFTH'
    | 'TWENTY-SIXTH'
    | 'TWENTY-SEVENTH'
    | 'TWENTY-EIGHTH'
    | 'TWENTY-NINTH'
    | 'THIRTIETH'
    | 'THIRTY-FIRST'
    | 'THIRTY-SECOND'
    | 'THIRTY-THIRD'
    ;

WS : [ \t]+ -> channel(HIDDEN);
CRLF : '\r'? '\n';

QNUM: [0-9]+ [a-z]* '.';
QQ: 'Q.';
AA: 'A.';

/** Default token to catch everything else */
WORD: ~[ \t\r\n]+;

text: .*?;
