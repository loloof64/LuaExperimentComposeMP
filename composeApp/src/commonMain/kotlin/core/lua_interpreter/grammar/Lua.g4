/*

MIT license

Author: Ken Domino, October 2023

Based on previous work of: Kazunori Sakamoto, Alexander Alexeev

*/

// ------------------------------------
// Parser
// ------------------------------------

// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

/* "silenced" by loloof64
parser grammar LuaParser;
*/

/* "silenced" by loloof64
options {
    tokenVocab = LuaLexer;
}
*/

grammar Lua; // added by loloof64

start_
    : chunk EOF
    ;

chunk
    : block
    ;

block
    /* replaced by loloof64
    : stat* retstat?
    */
    : stat*
    ;

stat
    : ';'
    // replaced by loloof64
    | assign
    /* "silenced" by loloof64
    | functioncall
    */
    /* "silenced" by loloof64
    | label
    */
    /* "silenced" by loloof64
    | 'break'
    */
    /* "silenced" by loloof64
    | 'goto' NAME
    */
    /* "silenced" by loloof64
    | 'do' block 'end'
    */
    /* "silenced" by loloof64
    | 'while' exp 'do' block 'end'
    */
    /* "silenced" by loloof64
    | 'repeat' block 'until' exp
    */
    | ifstat
    /* "silenced" by loloof64
    | 'for' NAME '=' exp ',' exp (',' exp)? 'do' block 'end'
    */
    /* "silenced" by loloof64
    | 'for' namelist 'in' explist 'do' block 'end'
    */
    /* "silenced" by loloof64
    | 'function' funcname funcbody
    | 'local' 'function' NAME funcbody
    | 'local' attnamelist ('=' explist)?
    */
    ;
    
// added by loloof64
assign
	: namelist '=' explist
	;
	
// added by loloof64
ifstat:
	'if' exp 'then' block ('elseif' exp 'then' block)* ('else' block)? 'end'
	;

/* "silenced" by loloof64
attnamelist
    : NAME attrib (',' NAME attrib)*
    ;
*/

/* "silenced" by loloof64
attrib
    : ('<' NAME '>')?
    ;
*/

/* "silenced" by loloof64
retstat
    : ('return' explist? | 'break' | 'continue') ';'?
    ;
*/

/* "silenced" by loloof64
label
    : '::' NAME '::'
    ;
*/

/* "silenced" by loloof64
funcname
    : NAME ('.' NAME)* (':' NAME)?
    ;
*/

/* "silenced" by loloof64
varlist
    : var (',' var)*
    ;
*/

namelist
    : NAME (',' NAME)*
    ;

explist
    : exp (',' exp)*
    ;

exp
	/*  "silenced" by loloof64
    : 'nil'
    */
    : 'false'
    | 'true'
    | number
    /* "silenced" by loloof64
    | string
    | '...'
    | functiondef
    */
    | prefixexp
    /* "silenced" by loloof64
    | tableconstructor
    */
    | <assoc = right> exp ('^') exp
    /* replaced by loloof64
    | ('not' | '#' | '-' | '~') exp
    */
    | ('not' | '-' | '~') exp
    | exp ('*' | '/' | '%' | '//') exp
    | exp ('+' | '-') exp
    /* "silenced" by loloof64
    | <assoc = right> exp ('..') exp
    */
    | exp ('<' | '>' | '<=' | '>=' | '~=' | '==') exp
    | exp ('and') exp
    | exp ('or') exp
    | exp ('&' | '|' | '~' | '<<' | '>>') exp
    ;

/* "silenced" by loloof64
// var ::=  Name | prefixexp '[' exp ']' | prefixexp '.' Name 
var
    : NAME
    | prefixexp ('[' exp ']' | '.' NAME)
    ;
*/

// prefixexp ::= var | functioncall | '(' exp ')'
prefixexp
	/* replaced by loloof64
    : NAME ('[' exp ']' | '.' NAME)*
    */
    : NAME
    /* "silenced" by loloof64
    | functioncall ('[' exp ']' | '.' NAME)*
    */
    /* replaced by loloof64
    | '(' exp ')' ('[' exp ']' | '.' NAME)*
    */
    | '(' exp ')'
    ;

/* "silenced" by loloof64
// functioncall ::=  prefixexp args | prefixexp ':' Name args;
functioncall
    : NAME ('[' exp ']' | '.' NAME)* args
    | functioncall ('[' exp ']' | '.' NAME)* args
    | '(' exp ')' ('[' exp ']' | '.' NAME)* args
    | NAME ('[' exp ']' | '.' NAME)* ':' NAME args
    | functioncall ('[' exp ']' | '.' NAME)* ':' NAME args
    | '(' exp ')' ('[' exp ']' | '.' NAME)* ':' NAME args
    ;
*/

/* "silenced" by loloof64
args
    : '(' explist? ')'
    | tableconstructor
    | string
    ;
*/

/* "silenced" by loloof64
functiondef
    : 'function' funcbody
    ;
*/

/* "silenced" by loloof64
funcbody
    : '(' parlist ')' block 'end'
    ;
*/

/* lparser.c says "is 'parlist' not empty?"
 * That code does so by checking la(1) == ')'.
 * This means that parlist can derive empty.
 */
 /* "silenced" by loloof64
parlist
    : namelist (',' '...')?
    | '...'
    |
    ;
*/

/* "silenced" by loloof64
tableconstructor
    : '{' fieldlist? '}'
    ;
*/

/* "silenced" by loloof64
fieldlist
    : field (fieldsep field)* fieldsep?
    ;
*/

/* "silenced" by loloof64
field
    : '[' exp ']' '=' exp
    | NAME '=' exp
    | exp
    ;
*/

/* "silenced" by loloof64
fieldsep
    : ','
    | ';'
    ;
*/

number
    : INT
    /* "silenced" by loloof64
    | HEX
    | FLOAT
    | HEX_FLOAT
    */
    ;

/* "silenced" by loloof64
string
    : NORMALSTRING
    | CHARSTRING
    | LONGSTRING
    ;
*/

// ------------------------------------
// Lexer
// ------------------------------------

// $antlr-format alignTrailingComments true, columnLimit 150, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine true, allowShortBlocksOnASingleLine true, minEmptyLines 0, alignSemicolons ownLine
// $antlr-format alignColons trailing, singleLineOverrulesHangingColon true, alignLexerCommands true, alignLabels true, alignTrailers true

/* "silenced" by loloof64
lexer grammar LuaLexer;
*/

/* "silenced" by loloof64
options {
    superClass = LuaLexerBase;
}
*/

// Insert here @header for C++ lexer.

SEMI : ';';
EQ   : '=';

/* "silenced" by loloof64
BREAK    : 'break';
GOTO     : 'goto';
DO       : 'do';
*/
END      : 'end';
/* "silenced" by loloof64
WHILE    : 'while';
REPEAT   : 'repeat';
UNTIL    : 'until';
*/
IF       : 'if';
THEN     : 'then';
ELSEIF   : 'elseif';
ELSE     : 'else';
/* "silenced" by loloof64
FOR      : 'for';
*/
COMMA    : ',';
/* "silenced" by loloof64
IN       : 'in';
FUNCTION : 'function';
LOCAL    : 'local';
*/
LT       : '<';
GT       : '>';
/* "silenced" by loloof64
RETURN   : 'return';
CONTINUE : 'continue';
CC       : '::';
NIL      : 'nil';
*/
FALSE    : 'false';
TRUE     : 'true';
DOT      : '.';
SQUIG    : '~';
MINUS    : '-';
/* "silenced" by loloof64
POUND    : '#';
*/
OP       : '(';
CP       : ')';
NOT      : 'not';
LL       : '<<';
GG       : '>>';
AMP      : '&';
SS       : '//';
PER      : '%';
/* "silenced" by loloof64
COL      : ':';
*/
LE       : '<=';
GE       : '>=';
AND      : 'and';
OR       : 'or';
PLUS     : '+';
STAR     : '*';
/* "silenced" by loloof64
OCU      : '{';
CCU      : '}';
OB       : '[';
CB       : ']';
*/
EE       : '==';
/* "silenced" by loloof64
DD       : '..';
*/
PIPE     : '|';
CARET    : '^';
SLASH    : '/';
/* "silenced" by loloof64
DDD      : '...';
*/
SQEQ     : '~=';

NAME: [a-zA-Z_][a-zA-Z_0-9]*;

/* "silenced" by loloof64
NORMALSTRING: '"' ( EscapeSequence | ~('\\' | '"'))* '"';

CHARSTRING: '\'' ( EscapeSequence | ~('\'' | '\\'))* '\'';

LONGSTRING: '[' NESTED_STR ']';

fragment NESTED_STR: '=' NESTED_STR '=' | '[' .*? ']';
*/

INT: Digit+;

/* "silenced" by loloof64
HEX: '0' [xX] HexDigit+;

FLOAT: Digit+ '.' Digit* ExponentPart? | '.' Digit+ ExponentPart? | Digit+ ExponentPart;

HEX_FLOAT:
    '0' [xX] HexDigit+ '.' HexDigit* HexExponentPart?
    | '0' [xX] '.' HexDigit+ HexExponentPart?
    | '0' [xX] HexDigit+ HexExponentPart
;

fragment ExponentPart: [eE] [+-]? Digit+;

fragment HexExponentPart: [pP] [+-]? Digit+;
*/

fragment EscapeSequence:
    /* "silenced" by loloof64
    '\\' [abfnrtvz"'|$#\\] // World of Warcraft Lua additionally escapes |$#
    */
    /* "replaced" by loloof64 
    | '\\' '\r'? '\n'
    */
    '\\' '\r'? '\n'
    /* "silenced" by loloof64
    | DecimalEscape
    | HexEscape
    | UtfEscape
    */
;

/* "silenced" by loloof64
fragment DecimalEscape: '\\' Digit | '\\' Digit Digit | '\\' [0-2] Digit Digit;

fragment HexEscape: '\\' 'x' HexDigit HexDigit;

fragment UtfEscape: '\\' 'u{' HexDigit+ '}';
*/

fragment Digit: [0-9];

/* "silenced" by loloof64
fragment HexDigit: [0-9a-fA-F];
*/

/* "silenced" by loloof64
fragment SingleLineInputCharacter: ~[\r\n\u0085\u2028\u2029];
*/

COMMENT: '--' { this.HandleComment(); } -> channel(HIDDEN);

WS: [ \t\u000C\r]+ -> channel(HIDDEN);

NL: [\n] -> channel(2);

/* "silenced" by loloof64
SHEBANG: '#' { this.IsLine1Col0() }? '!'? SingleLineInputCharacter* -> channel(HIDDEN);
*/
