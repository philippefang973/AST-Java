import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
  //Code issu de moodle
  private static void initAndShow(String filename) {
    JFrame frame = new JFrame("ADS4");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setPreferredSize(new Dimension(800,600));
    frame.getContentPane().add(new MyCanvas(filename));
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        initAndShow(args[0]);
      }
    });
  }
}

@SuppressWarnings("serial")
class MyCanvas extends JComponent {

  String filename;
  public MyCanvas(String fname) {
    filename = fname;
  }

  @Override
  public void paintComponent(Graphics g) {
    if (g instanceof Graphics2D)
    {
      Graphics2D g2d = (Graphics2D)g;
      try {
        File input = new File(filename);
        Reader reader = new FileReader(input);
        Lexer lexer = new Lexer(reader);
        LookAhead1 look = new LookAhead1(lexer);
        Parser parser = new Parser(look);
        AST ast = parser.nontermProg();
        ast.exec(g2d);
      }
      catch (FileNotFoundException e) {System.out.println("Erreur: fichier indisponible");}
      catch (Exception e) {System.out.println(e);}
    }
  }
}
