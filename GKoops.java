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
      this.setVelY(-1);
   }
}