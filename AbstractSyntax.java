import java.util.*;
import java.awt.*;

//Programme constituant une liste d'instruction (Arbre de syntaxe)
class AST extends ArrayList<Instruction>{
  public void exec(Graphics2D g2d) throws Exception{
    ArrayList<HashMap<String,Integer>> a1 = new ArrayList<HashMap<String,Integer>>();
    ArrayList<HashMap<String,Boolean>> a2 = new ArrayList<HashMap<String,Boolean>>();
    a1.add(new HashMap<String,Integer>());
    a2.add(new HashMap<String,Boolean>());
    ValueEnvironment env = new ValueEnvironment(a1,a2,g2d);
    for (Instruction i : this) i.exec(env); //Execute les instructions une par une
  }
}

//types d'instruction
abstract class Instruction {
  abstract void exec (ValueEnvironment env) throws Exception;
}

//Declaration constant
class DeclarationConst extends Instruction{
  private String name;
  private Expression value;
  public DeclarationConst (String n, Expression v) {
    name = n;
    value = v;
  }
  public void exec (ValueEnvironment env) throws Exception{
    env.putConst(name,value.eval(env));
  }
}

//Declaration variable
class DeclarationVar extends Instruction {
  private String name;
  private Expression value;
  public DeclarationVar (String n, Expression v) {
    name = n;
    value = v;
  }
  public void exec (ValueEnvironment env) throws Exception{
    env.putVar(name,value.eval(env));
  }
}

//Affecter une valeur à un variable
class Assignment extends Instruction {
  private String id;
  private Expression value;
  public Assignment (String n, Expression v) {
    id = n;
    value = v;
  }
  public void exec (ValueEnvironment env) throws Exception{
    env.replaceValue(id,value.eval(env));
  }

}

//Executer une instruction si une condition est vérifiée
class Condition extends Instruction {
  private Expression b;
  private Instruction i1,i2;
  public Condition(Expression b, Instruction i1, Instruction i2) {
    this.b = b;
    this.i1 = i1;
    this.i2 = i2;
  }
  public void exec (ValueEnvironment env) throws Exception{
    if (b.eval(env)!=0) i1.exec(env);
    else i2.exec(env);
  }
}

//Boucle
class Loop extends Instruction {
  private Expression e;
  private Instruction i;
  public Loop(Expression e,Instruction i) {
    this.e = e;
    this.i = i;
  }
  public void exec (ValueEnvironment env) throws Exception{
    for(int x = 0; x<e.eval(env); x++) {
      env.addLocalEnvironment();
      i.exec(env);
      env.removeLocalEnvironment();
    }
  }

}


//Dessine un rectangle
class DrawRect extends Instruction {
  private Expression x,y,w,h;
  private int [] color;
  public DrawRect(Expression x, Expression y, Expression w, Expression h, int [] c) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    color = c;
  }
  public void exec (ValueEnvironment env) throws Exception{
    env.drawRect(x.eval(env),y.eval(env),w.eval(env),h.eval(env),color);
  }
}

//Dessine un rectangle plein
class FillRect extends Instruction {
  private Expression x,y,w,h;
  private int [] color;
  public FillRect(Expression x, Expression y, Expression w, Expression h, int [] c) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    color = c;
  }
  public void exec (ValueEnvironment env) throws Exception{
    env.fillRect(x.eval(env),y.eval(env),w.eval(env),h.eval(env),color);
  }
}

//Dessine un cercle
class DrawCircle extends Instruction {
  private Expression x,y,r;
  private int [] color;
  public DrawCircle(Expression x, Expression y, Expression r, int [] c) {
    this.x = x;
    this.y = y;
    this.r = r;
    color = c;
  }
  public void exec (ValueEnvironment env) throws Exception{
    env.drawCircle(x.eval(env),y.eval(env),r.eval(env),color);
  }
}

//Dessine un cercle plein
class FillCircle extends Instruction {
  private Expression x,y,r;
  private int [] color;
  public FillCircle(Expression x, Expression y, Expression r, int [] c) {
    this.x = x;
    this.y = y;
    this.r = r;
    color = c;
  }
  public void exec (ValueEnvironment env) throws Exception{
    env.fillCircle(x.eval(env),y.eval(env),r.eval(env),color);
  }
}

//Formes d'expression arithmetiques
abstract class Expression {
  abstract int eval(ValueEnvironment env) throws Exception;
}

//entier
class Int extends Expression {
  private int value;
  public Int(int i) {value = i;}
  public int eval(ValueEnvironment env) throws Exception{return value;}
}

//identificateur
class Ident extends Expression {
  private String name;
  public Ident(String s) {name=s;}
  public int eval(ValueEnvironment env) throws Exception{return env.getValue(name);}
}

//Somme
class Sum extends Expression {
  private Expression left,right;
  public Sum (Expression l, Expression r) {
    left = l;
    right = r;
  }
  public int eval(ValueEnvironment env) throws Exception{
    return left.eval(env) + right.eval(env);
  }
}

//Difference
class Difference extends Expression {
  private Expression left,right;
  public Difference (Expression l, Expression r) {
    left = l;
    right = r;
  }
  public int eval(ValueEnvironment env) throws Exception{
    return left.eval(env) - right.eval(env);
  }
}

//Produit
class Product extends Expression {
  private Expression left,right;
  public Product (Expression l,Expression r) {
    left = l;
    right = r;
  }
  public int eval(ValueEnvironment env) throws Exception{
    return left.eval(env) * right.eval(env);
  }
}

//Division
class Division extends Expression {
  private Expression left,right;
  public Division (Expression l,Expression r) {
    left = l;
    right = r;
  }

  public int eval(ValueEnvironment env) throws Exception{
    return left.eval(env) / right.eval(env);
  }
}
