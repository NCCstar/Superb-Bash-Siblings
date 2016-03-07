public class Megaman extends Character
{
   public Megaman(int x,int y)
   {
      super(x,y,20,20);
      weight=110;
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
}