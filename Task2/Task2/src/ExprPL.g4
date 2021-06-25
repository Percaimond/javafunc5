grammar ExprPL;

// Lexer rules

True: 'true';
False: 'false';
Number: [1-9][0-9]*;
Not: '!';
And: '&&';
Plus: '+';
Equal: '=';
If: 'if';
Then: 'then';
Else: 'else';

// Parser rules

WS: [ \t\r\n]+ -> skip;

expr:
    True
    | False
    | Number    
    | Not expr
    | expr Plus expr
    | expr Equal expr
    | expr And expr
    | If expr Then expr Else expr;
