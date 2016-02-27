public class Hitbox
{
   private Rect[] boxes;
   private String type;
   public Hitbox(Rect[] boxes,String type)
   {
      this.boxes=boxes;
      this.type=type;
   }
   public Rect[] getBoxes()
   {
      return boxes;
   }
   public void offsetTo(double newX,double newY)
   {
      for(int i=0;i<boxes.length;i++)
      {
         boxes[i].offsetTo(newX,newY);
      }
   }
   public void offsetTo(int newX,int newY)
   {
      offsetTo((double)newX,(double)newY);
   }
   public void offset(double xOff,double yOff)
   {
      for(int i=0;i<boxes.length;i++)
      {
         boxes[i].offset(xOff,yOff);
      }
   }
   public void offset(int xOff,int yOff)
   {
      offset((double)xOff,(double)yOff);
   }
   public static boolean intersects(Hitbox a,Hitbox b)
   {
      Rect[] ra = a.getBoxes();
      Rect[] rb = b.getBoxes();
      for(int i=0;i<ra.length;i++)
      {
         for(int j=0;j<rb.length;j++)
         {
            if(Rect.collides(ra[i],rb[j]))
               return true;
         }
      }
      return false;
   }
   public static boolean intersects(Hitbox a,Rect b)
   {
      Rect[] ra = a.getBoxes();
      for(int i=0;i<ra.length;i++)
      {
         if(Rect.collides(ra[i],b))
            return true;
      }
      return false;
   }
}