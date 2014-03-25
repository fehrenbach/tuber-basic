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
    |   forLoop
    |   cond
    ;

forLoop
    :   'FOR' var=Variable 'FROM' from=expression 'TO' to=expression '\n' statements '\n' 'ROF'
    ;

cond
    :   'COND' ('\n' 'TEST' expression '\n' statements)+ '\n' 'DNOC'
    ;

print
    :   'PRINT' formatstring=expression (expression)*
    ;

end
    :   'END'
    ;

expression
    :   NumberLiteral                                                        # NumberLiteral
    |   StringLiteral                                                        # StringLiteral
    |   'TRUE'                                                               # BooleanLiteralTrue
    |   'FALSE'                                                              # BooleanLiteralFalse
    |   Variable                                                             # Variable
    |   left=expression '=' right=expression                                 # Equals
    |   left=expression 'AND' right=expression                               # BooleanAnd
    |   left=expression 'OR' right=expression                                # BooleanOr
    |   function=functionName '(' first=expression ',' second=expression ')' # BinaryFunction
    ;

functionName
    :   'mod'
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