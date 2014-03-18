grammar TuberBasic;
options { tokenVocab=TuberBasicLexer; }

basicfile
    :   statements
    ;

statements
    :   statement*
    ;

statement
    :   print
    ;

print
    :   'PRINT' expression
    ;

expression
    :   NumberLiteral
    ;

NumberLiteral
    :   ('0'..'9')+
    ;
