public enum Sym {
  //nombre ou identificateur
  INT, //entier
  IDENT, //chaine de caracteres
  //symboles
  LPAR, // (
  RPAR, // )
  PLUS, //operand +
  MINUS, //operand -
  MULT, //operand *
  DIV, //operand /
  EOF, // fin de fichier $
  COMMA, // ,
  SEMIC, // ;
  EQ, // =
  //mots-clés
  WHILE,
  DO,
  IF,
  THEN,
  ELSE,
  BEGIN,
  END,
  DCIRCLE,
  DRECT,
  FCIRCLE,
  FRECT,
  CONST, //constant
  VAR, //variable
  //code couleurs
  CC; // #hexadecimaux
}
