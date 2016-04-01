public class GKoops extends Character
{
   public GKoops(int x,int y)
   {
      super(x,y,40,40);
      weight=200;
   }
   public void jump()
   {
      this.setInAir(true);
      this.setVelY(-4);
   }
   public void step(boolean right)
   {
      double off=0.2;
      if(!right)
      {
         off*=-1;
      }
      velX+=off;
   }
   public double getDrag()
   {
      return 0.1;
   }
}