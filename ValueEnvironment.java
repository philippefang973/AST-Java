import java.util.*;
import java.awt.*;
class ValueEnvironment {
  private ArrayList<HashMap<String,Integer>> id; //identificateur avec sa valeur
  private ArrayList<HashMap<String,Boolean>> type; //false=variable true=constant
  private Graphics2D g2d; //Pour dessiner les formes
  public ValueEnvironment(ArrayList<HashMap<String,Integer>> id, ArrayList<HashMap<String,Boolean>> type, Graphics2D g2d) {
    this.id = id;
    this.type = type;
    this.g2d = g2d;
  }

  public void addLocalEnvironment() {
    id.add(new HashMap<String,Integer>());
    type.add(new HashMap<String,Boolean>());
  }

  public void removeLocalEnvironment() {
    id.remove(id.size()-1);
    type.remove(type.size()-1);
  }

  public int getValue(String n) throws Exception{
    for (int i = id.size()-1; i>=0; i--) {
      if (id.get(i).get(n)!=null) return id.get(i).get(n);
    }
    throw new Exception("Erreur: la variable " + n + " n'existe pas");
  }

  public void putConst(String n, int k) throws Exception{
    Integer a = id.get(id.size()-1).putIfAbsent(n,k);
    if (a==null) type.get(type.size()-1).put(n,true);
    else throw new Exception ("Erreur: identificateur (constant) "+n+" déjà existant");
  }

  public void putVar(String n, int k) throws Exception{
    Integer a = id.get(id.size()-1).putIfAbsent(n,k);
    if (a==null) type.get(type.size()-1).put(n,false);
    else throw new Exception ("Erreur: identificateur (variable) "+n+" déjà existant");
  }

  public void replaceValue(String n, int k) throws Exception {
    for (int i = id.size()-1; i>=0; i--) {
      if (id.get(i).get(n)!=null) {
        if (type.get(i).get(n)==false) {
          id.get(i).replace(n,k);
          return;
        }
        else throw new Exception("Erreur: l'identificateur "+ n +" est constant");
      }
    }
    throw new Exception("Erreur: l'identificateur " + n + " n'existe pas");
  }

  public void drawRect (int x, int y, int w, int h, int [] color) {
    g2d.setColor(new Color(color[0],color[1],color[2]));
    g2d.drawRect(x,y,w,h);
  }

  public void fillRect (int x, int y, int w, int h, int [] color) {
    g2d.setColor(new Color(color[0],color[1],color[2]));
    g2d.fillRect(x,y,w,h);
  }

  public void drawCircle (int x, int y, int r, int [] color) {
    g2d.setColor(new Color(color[0],color[1],color[2]));
    int cornerX = x - r;
    int cornerY = y - r;
    int width = 2*r;
    g2d.drawOval(cornerX,cornerY,width,width);
  }

  public void fillCircle (int x, int y, int r, int [] color) {
    g2d.setColor(new Color(color[0],color[1],color[2]));
    int cornerX = x - r;
    int cornerY = y - r;
    int width = 2*r;
    g2d.fillOval(cornerX,cornerY,width,width);
  }
}
