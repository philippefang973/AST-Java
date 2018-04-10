import java.io.*;
class LookAhead1  {
  private Token current;
  private Lexer lexer;

  public LookAhead1(Lexer l) throws Exception {
    lexer=l;
    current=lexer.yylex();
  }

  //Verifie si le token courant est de type s
  public boolean check(Sym s) throws Exception {
    return (current.symbol() == s);
  }

  //Consomme le token courant
  public void eat(Sym s) throws Exception {
    if (!check(s)) {
      throw new Exception("Erreur: token attendu="+s+" mais trouve="+current);
    }
    current=lexer.yylex();
  }

  public int getIntValue() throws Exception {
    if (current instanceof IntToken) {
      IntToken t = (IntToken) current;
      return t.getValue();
    }
    throw new Exception("Erreur: token sans valeur (int)");
  }

  public String getStringValue() throws Exception {
    if (current instanceof IdentToken) {
      IdentToken t = (IdentToken) current;
      return t.getValue();
    }
    throw new Exception("Erreur: token sans valeur (String)");
  }

  public int [] getColorValue() throws Exception {
    if (current instanceof ColorToken) {
      ColorToken t = (ColorToken) current;
      return t.getValue();
    }
    throw new Exception("Erreur: token sans valeur (int [])");
  }

}
