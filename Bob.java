public class Bob extends Character
{
   public Bob(int x,int y)
   {
      super(x,y,25,25);
   }
   public void subHitstun()
   {
      hitstun--;
      damage-=.02;
   }
}