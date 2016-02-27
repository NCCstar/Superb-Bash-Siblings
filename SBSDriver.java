import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SBSDriver
{
   private static SBSPanel panel;
   public static void main(String[] arg)
   {
      panel = new SBSPanel();
      JFrame frame = new JFrame("Superb Bash Siblings");
      frame.setSize(1200, 1000);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(panel);
      frame.setVisible(true);
      frame.setResizable(true);//may be false
      frame.addKeyListener(new listen());	
   }
   public static class listen implements KeyListener 
   {
      public void keyTyped(KeyEvent e){}   
      public void keyPressed(KeyEvent e)
      {
         panel.keyPressed(e);
      }
      public void keyReleased(KeyEvent e)
      {
         panel.keyReleased(e);
      }
   }
}
//Hitstun drawing
//Attacks other than upL