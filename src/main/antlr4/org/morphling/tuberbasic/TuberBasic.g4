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
    ;

print
    :   'PRINT' formatstring=stringLiteral (expression)*
    ;

end
    :   'END'
    ;

expression
    :   numberLiteral | stringLiteral
    ;

numberLiteral
    :   NumberLiteral
    ;

stringLiteral
    :   StringLiteral
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