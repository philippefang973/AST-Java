import java.io.*;
class Parser {
  public LookAhead1 reader;
  public Parser(LookAhead1 r) throws Exception{
    reader = r;
  }
  /*
  Grammaire:
  Prog -> InstSuite EOF
  Inst -> Begin InstSuite End
        | If expr Then Inst Else Inst
        | While expr Do Inst
        | DrawCircle ( expr , expr , expr , couleur )
        | FillCircle ( expr , expr , expr , couleur )
        | DrawRect ( expr , expr , expr , expr , couleur )
        | FillRect ( expr , expr , expr , expr , couleur )
        | Const identificateur = expr
        | Var identificateur = expr
        | identificateur = expr
  InstSuite -> Inst ; InstSuite | ε
  expr -> identificateur | nombre |  ( expr operateur expr )

  */

  public AST nontermProg () throws Exception{
    return null;
  }
  public Instruction nontermInst () throws Exception{
    return null;
  }
  public Instruction nontermInstSuite () throws Exception{
    return null;
  }
  public Expression nontermExpr () throws Exception{
    return null;
  }


}
