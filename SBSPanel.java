import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SBSPanel extends JPanel
{
   private boolean keyW=false;
   private boolean keyA=false;
   private boolean keyS=false;
   private boolean keyD=false;
   private boolean keySp=false;
   private boolean keySh=false;
   private boolean key4=false;
   private boolean key5=false;
   private boolean key6=false;
   private boolean key8=false;
   private boolean keyEn=false;
   private boolean keyZe=false;
   
   int atks[]=new int[2];
   
   long time=System.currentTimeMillis();
   
   private Level level=new Level();
   private Character[] plys=new Character[2];
   
   public SBSPanel()
   {
      plys[0]=new Character(300,100);
      plys[1]=new Character(700,100);
      atks[0]=5;
      atks[1]=5;
   }
   public void keyPressed(KeyEvent e)
   {
      checkIn(e);
   }
   private void moveCheck()
   {
      if(keyD)
         plys[0].step(true);
      if(keyA)
         plys[0].step(false);
      if(key4)
         plys[1].step(false);
      if(key6)
         plys[1].step(true);
      if(keyW&&(!plys[0].isInAir()||!plys[0].hasJumped()))
         plys[0].jump();
      if(key8&&(!plys[1].isInAir()||!plys[1].hasJumped()))
         plys[1].jump();
      if(keyS)
         plys[0].fastFall();
      if(key5)
         plys[1].fastFall();
      if(keySp)
      {
         if(keyW)
         {
            plys[0].attackL(0);
            atks[0]=0;
         }
         else
            if(keyD)
            {
               plys[0].attackL(1);
               atks[0]=1;
            }
            else
               if(keyS)
               {
                  plys[0].attackL(2);
                  atks[0]=2;
               }
               else
                  if(keyA)
                  {
                     plys[0].attackL(3);
                     atks[0]=3;
                  }
                  else
                  {
                     plys[0].attackL(4);
                     atks[0]=4;
                  }
      }
      if(keyZe)
      {
         if(key8)
            plys[1].attackL(0);
      }
   }
   public void keyReleased(KeyEvent e)
   {
      checkOut(e);
   }
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if(time+100>System.currentTimeMillis())//fps regulation
      {
         time=System.currentTimeMillis();
         g.setColor(Color.white);
         g.fillRect(0,0,getWidth(),getHeight());//background
         g.setColor(Color.black);
         draw(g,level.walls);//floor
         g.setColor(Color.black);
         moveCheck();//check keys
         for(int i=0;i<plys.length;i++)
         {
            int hitstun=plys[i].getHitstun();
            if(hitstun>0)
            {//increment hitstun
               plys[i].setHitstun(--hitstun);
            }
            switch(i)//color based on player number
            {
               case 0:g.setColor(Color.red);
                  break;
               case 1:g.setColor(Color.blue);
                  break;
               default:g.setColor(Color.black);
                  break;
            }
            if(hitstun>0)
            {
               Color a=g.getColor();//hitstun color
               g.setColor(new Color(a.getRed(),a.getBlue(),a.getGreen(),150));
            }
            Rect[] rects = plys[i].getHitbox().getBoxes();
            double vel=plys[i].getVelX();
            if(vel!=0)
            {
               double drag=0.02;
               if(vel>0)
                  if(vel>drag)//resistance(slow to stop)
                     plys[i].setVelX(vel-drag);
                  else
                     plys[i].setVelX(0);
               else
                  if(vel<(-1*drag))
                     plys[i].setVelX(vel+drag);
                  else
                     plys[i].setVelX(0);
            }        
            plys[i].checkVel();//move the player
            while(collide(plys[i].getHitbox().getBoxes(),level.walls))
            {
               plys[i].getHitbox().offset(0,-.01);
            }
            Hitbox floorBox = plys[i].getHitbox();
            floorBox.offset(0,.01);
            if(collide(floorBox.getBoxes(),level.walls))
            {
               plys[i].setInAir(false);
            }
            else
            {
               plys[i].setInAir(true);
            }
            floorBox.offset(0,-.01);
            if(plys[i].isInAir())//if not on floor
            {
               plys[i].fall();//gravity
            }
            else
            {
               plys[i].setVelY(0);//not gravity(won't fall through floor)
            }
            int buffer=50;//buffer space for offscreen blast lines
            if(plys[i].centerX()<buffer*-1||plys[i].centerX()>getWidth()+buffer||plys[i].centerY()<buffer*-1||plys[i].centerY()>getHeight()+buffer)
            {//check blast lines
               plys[i].getHitbox().offsetTo(getWidth()/2,100);//if off screen, reset
               plys[i].setVelY(0);
               plys[i].setVelX(0);
               plys[i].setHitstun(1000);//respawn immunity
               //reduce life count
            }
            draw(g,rects);
            if(plys[i].getAtkUp())
            {
               Color a=g.getColor();
               g.setColor(new Color(a.getRed(),a.getBlue(),a.getGreen(),100));
               draw(g,plys[i].getAtk().getBoxes());
               for(int q=0;q<plys.length;q++)
               {
                  if(i!=q)
                  {
                     if(collide(plys[i].getAtk().getBoxes(),plys[q].getHitbox().getBoxes()))
                     {
                        if(plys[q].getHitstun()<=0)
                        {
                           plys[q].setVelY(plys[q].getAtkPow(atks[i],true)[0]);
                           plys[q].setVelX(plys[q].getAtkPow(atks[i],true)[1]);
                           plys[q].setHitstun(50);
                        }
                     }
                  }
               }
            }
         }
      }
      else
      {
         time=System.currentTimeMillis();
      }
      repaint();
   }
   private boolean collide(Rect[] a,Rect[] b)
   {
      for(int i=0;i<a.length;i++)
      {
         for(int j=0;j<b.length;j++)
         {
            if(Rect.collides(a[i],b[j]))
               return true;
         }
      }
      return false;
   }
   private void draw(Graphics g,Rect[] rects)
   {
      for(int q=0;q<rects.length;q++)
      {
         g.fillRect((int)rects[q].getLeft(),(int)rects[q].getTop(),(int)rects[q].getWidth(),(int)rects[q].getHeight());
      }
   }
   private void checkIn(KeyEvent e)
   {
      switch(e.getKeyCode())
      {
         case KeyEvent.VK_W:keyW=true;
            break;
         case KeyEvent.VK_A:keyA=true;
            break;
         case KeyEvent.VK_S:keyS=true;
            break;
         case KeyEvent.VK_D:keyD=true;
            break;
         case KeyEvent.VK_SPACE:keySp=true;
            break;
         case KeyEvent.VK_SHIFT:keySh=true;
            break;
         case KeyEvent.VK_LEFT:key4=true;
            break;
         case KeyEvent.VK_DOWN:key5=true;
            break;
         case KeyEvent.VK_RIGHT:key6=true;
            break;
         case KeyEvent.VK_UP:key8=true;
            break;
         case KeyEvent.VK_0:keyZe=true;
            break;
         case KeyEvent.VK_ENTER:keyEn=true;
            break;
         default: 
            break;
      }
   }
   private void checkOut(KeyEvent e)
   {
      switch(e.getKeyCode())
      {
         case KeyEvent.VK_W:keyW=false;
            break;
         case KeyEvent.VK_A:keyA=false;
            break;
         case KeyEvent.VK_S:keyS=false;
            break;
         case KeyEvent.VK_D:keyD=false;
            break;
         case KeyEvent.VK_SPACE:keySp=false;
            plys[0].attackL(5);
            break;
         case KeyEvent.VK_SHIFT:keySh=false;
            break;
         case KeyEvent.VK_LEFT:key4=false;
            break;
         case KeyEvent.VK_DOWN:key5=false;
            break;
         case KeyEvent.VK_RIGHT:key6=false;
            break;
         case KeyEvent.VK_UP:key8=false;
            break;
         case KeyEvent.VK_0:keyZe=false;
            plys[1].attackL(5);
            break;
         case KeyEvent.VK_ENTER:keyEn=false;
            break;
         default: 
            break;
      }
   }
}