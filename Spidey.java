public class Spidey extends Character
{
   public Spidey(int x,int y)
   {
      super(x,y,20,20);
      weight=85;
   }
   public void jump()
   {
      inAir=true;
      velY=-7;
   }
   public void step(boolean right)
   {
      double off=5.6;
      if(!right)
      {
         off*=-1;
      }
      velX+=off;
   }
   public double getDrag()
   {
      return 5;
   }
   public void setHitstun(int h)
   {
      hitstun=h*2;
   }
   public void addDamage(double d)
   {
      damage+=d*2;
   }
   public double getGrav()
   {
      return .4;
   }
   public void fastFall()
   {
      velY+=getGrav()*4;
   }
}