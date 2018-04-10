class Token {
  protected Sym symbol;
  public Token (Sym s) {
    symbol = s;
  }
  public String toString () {
    return symbol.toString();
  }
  public Sym symbol() {return symbol;}
}

class IntToken extends Token{
  private int value;
  public IntToken (Sym s, int v) {
    super(s);
    value = v;
  }
  public String toString () {
    return symbol + "("+value+")";
  }
  public int getValue() {return value;}
}

class ColorToken extends Token {
  private int r, g, b;
  public ColorToken (Sym s, String v) {
    super(s);
    r = Integer.parseInt(v.substring(1,3),16);
    g = Integer.parseInt(v.substring(3,5),16);
    b = Integer.parseInt(v.substring(5,7),16);
  }

  public String toString () {
    return symbol + "("+r+","+g+","+b+")";
  }
  public int [] getValue() {
    int [] t = {r,g,b};
    return t;
  }
}

class IdentToken extends Token {
  private String value;
  public IdentToken (Sym s, String v) {
    super(s);
    value = v;
  }
  public String toString () {
    return symbol + "("+value+")";
  }
  public String getValue() {return value;}
}
