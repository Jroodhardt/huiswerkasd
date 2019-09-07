grammar ICSS;

//--- LEXER: ---
//Literals
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;

//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];
MIN: '-';

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z][a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//--- PARSER: ---

stylesheet:(variableAssignment)* (stylerule)+;
stylerule: selector body;
body: OPEN_BRACE (declaration | stylerule | variableAssignment)* CLOSE_BRACE;
selector: LOWER_IDENT|CLASS_IDENT|ID_IDENT;
//expressions
expression: (operation | value);
operation: value MUL expression | (value PLUS | MIN expression);
//declaration and values
literal: COLOR|PERCENTAGE|PIXELSIZE|SCALAR;
value: (literal | variableReference);
declaration: propertyName COLON expression SEMICOLON;
propertyName: LOWER_IDENT;
//variables
variableAssignment:  variableReference ASSIGNMENT_OPERATOR literal SEMICOLON;
variableReference: CAPITAL_IDENT;