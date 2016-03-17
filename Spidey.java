public class Spidey extends Character
{
   public Spidey(int x,int y)
   {
      super(x,y,20,20);
      weight=85;
   }
   public void jump()
   {
      this.setInAir(true);
      this.setVelY(-1.6);
   }
   public void step(boolean right)
   {
      double off=.04;
      if(!right)
      {
         off*=-1;
      }
      setVelX(getVelX()+off);
   }
   public void setHitstun(int h)
   {
      hitstun=h*2;
   }
   public void addDamage(double d)
   {
      damage+=d*2;
   }
   public void fastFall()
   {
      velY+=getGrav()*4;
   }
}