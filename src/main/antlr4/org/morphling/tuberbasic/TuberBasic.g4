grammar TuberBasic;
/* c.f. https://github.com/antlr/grammars-v4/ */

basicfile
    :   statements
    ;

statements
    :   statement ('\n' statement)*
    ;

statement
    :   print
    |   end
    |   fornext
    |   cond
    ;

fornext
    :   'FOR' variable 'FROM' expression 'TO' expression '\n' statements '\n' 'NEXT'
    ;

cond
    :   'COND' ('\n' 'TEST' expression '\n' statements)+ 'DNOC'
    ;

print
    :   'PRINT' formatstring=stringLiteral (expression)*
    ;

end
    :   'END'
    ;

expression
    :   numberLiteral | stringLiteral | booleanLiteral | variable
    |   expression '=' expression
    |   expression 'AND' expression
    |   twoaryfunction '(' expression ',' expression ')'
    ;

twoaryfunction
    :   'mod'
    ;

booleanLiteral
    :   'TRUE' | 'FALSE'
    ;

numberLiteral
    :   NumberLiteral
    ;

stringLiteral
    :   StringLiteral
    ;

variable
    :   Variable
    ;

Variable
    :   ('a'..'z')('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
    ;

NumberLiteral
    :   '-'?('0'..'9')+
    ;

StringLiteral
    :   '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

WS
	: ( ' '
	| '\t'
	| '\n'
	| '\r'
	) -> channel(HIDDEN)
;

fragment
ESC_SEQ
	:   '\\' ('\"'|'\\'|'/'|'b'|'f'|'n'|'r'|'t')
	|   UNICODE_ESC
    ;

fragment
UNICODE_ESC
	:   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

fragment
HEX_DIGIT
	:   ('0'..'9'|'a'..'f'|'A'..'F')
    ;