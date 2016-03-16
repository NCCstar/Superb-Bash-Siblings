public class Meteor extends Character
{
   public Meteor(int x,int y)
   {
      super(x,y,26,27);//size
      weight=90;
   }
   public void jump()
   {
      this.setInAir(true);
      this.setVelY(-1.2);
   }
   public double[] getAtkPow(int dir,boolean light,boolean kBack)
   {
      if(light)
      {
         switch(dir)
         {
            case 0:
               if(kBack)
                  return new double[]{1,0};//down 1
               return new double[]{4.6};//5%
            case 1:
               if(kBack)
                  return new double[]{.5,1.5};//down .5 right 1.5
               return new double[]{6};//5%
            case 2:
               if(kBack)
                  return new double[]{3,0};//down 3
               return new double[]{11};//10%
            case 3:
               if(kBack)
                  return new double[]{.5,-1.5};//down .5 left 1.5
               return new double[]{6};//5%
            case 4:
               if(kBack)
                  return new double[]{1,0};//down 1
               return new double[]{4.3};//5%
         }
      }
      else
      {
         switch(dir)
         {
            case 0:
               if(kBack)
                  return new double[]{2,0};//down 1
               return new double[]{2};//5%
            case 1:
               if(kBack)
                  return new double[]{1,2};//down .5 right 1.5
               return new double[]{3};//5%
            case 2:
               if(kBack)
                  return new double[]{6,0};//down 3
               return new double[]{5};//10%
            case 3:
               if(kBack)
                  return new double[]{1,-2};//down .5 left 1.5
               return new double[]{3};//5%
            case 4:
               if(kBack)
                  return new double[]{2,0};//down 1
               return new double[]{2};//5%  
         }
      }
      return null;
   }
}