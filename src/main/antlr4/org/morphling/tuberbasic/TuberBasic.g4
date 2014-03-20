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
    :   'PRINT' expression
    ;

end
    :   'END'
    ;

expression
    :   NumberLiteral
    ;

NumberLiteral
    :   '-'?('0'..'9')+
    ;

WS
	: ( ' '
	| '\t'
	| '\n'
	| '\r'
	) -> channel(HIDDEN)
;