%%
%public
%class Lexer
%type Token
%unicode
%line
%column
%yylexthrow Exception
%debug

nombre = [0-9]+
hex = [0-9A-F]
blank = [ \r\t\n]
identificateur = [a-z][a-zA-Z_]*

%%
#{hex}{hex}{hex}{hex}{hex}{hex} {return new ColorToken(Sym.CC,yytext());}
{nombre} {return new IntToken(Sym.INT,Integer.parseInt(yytext()));}
{identificateur} {return new IdentToken(Sym.IDENT,yytext());}
"DrawRect" {return new Token(Sym.DRECT);}
"DrawCircle" {return new Token(Sym.DCIRCLE);}
"FillRect" {return new Token(Sym.FRECT);}
"FillCircle" {return new Token(Sym.FCIRCLE);}
"While" {return new Token(Sym.WHILE);}
"Do" {return new Token(Sym.DO);}
"If" {return new Token(Sym.IF);}
"Then" {return new Token(Sym.THEN);}
"Else" {return new Token(Sym.ELSE);}
"Begin" {return new Token(Sym.BEGIN);}
"End" {return new Token(Sym.END);}
"Const" {return new Token(Sym.CONST);}
"Var" {return new Token(Sym.VAR);}
"+" {return new Token(Sym.PLUS);}
"-" {return new Token(Sym.MINUS);}
"*" {return new Token(Sym.MULT);}
"/" {return new Token(Sym.DIV);}
"(" {return new Token(Sym.LPAR);}
")" {return new Token(Sym.RPAR);}
"=" {return new Token(Sym.EQ);}
";" {return new Token(Sym.SEMIC);}
"," {return new Token(Sym.COMMA);}
{blank} {}
<<EOF>> {return new Token(Sym.EOF);}
[^] {throw new Exception("Token non identifié à la ligne="+yyline+" colonne="+yycolumn+")");}
