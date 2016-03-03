public class Character extends Thing
{
   public double getGrav(){
      return (double).007;}
   private boolean jumped=false;
   private boolean inAir=true;
   private double velY=0;
   private double velX=0;
   private double damage=0;
   private boolean atkUp=false;
   private Hitbox atk;
   private int hitstun=0;
   public Character(int x,int y)
   {
      super(new Hitbox(new Rect[]{new Rect(x-10,y-20,x+10,y)},"char"));
   }
   public double centerX()
   {
      double ans=0;
      Rect[] boxes=hitbox.getBoxes();
      for(int i=0;i<boxes.length;i++)
      {
         ans+=boxes[i].centerX();
      }
      return ans;
   }
   public int getHitstun()
   {
      return hitstun;
   }
   public void setHitstun(int h)
   {
      hitstun=h;
   }
   public double centerY()
   {
      double ans=0;
      Rect[] boxes=hitbox.getBoxes();
      for(int i=0;i<boxes.length;i++)
      {
         ans+=boxes[i].centerY();
      }
      return ans;
   }
   public void attackL(int dir)
   {
      Rect rect=hitbox.getBoxes()[0];
      switch(dir)
      {
         case 0://different for each fighter
            atk=new Hitbox(new Rect[]{new Rect(rect.getLeft(),rect.getTop()-rect.getHeight(),rect.getRight(),rect.getTop())},"atk");
            atkUp=true;
            break;
         case 1:
            atk=new Hitbox(new Rect[]{new Rect(rect.getRight(),rect.getTop(),rect.getRight()+rect.getWidth(),rect.getBottom())},"atk");
            atkUp=true;
            break;
         case 2:
            atk=new Hitbox(new Rect[]{new Rect(rect.getLeft(),rect.getBottom(),rect.getRight(),rect.getBottom()+rect.getHeight())},"atk");
            atkUp=true;
            break;
         case 3:
            atk=new Hitbox(new Rect[]{new Rect(rect.getLeft()-rect.getWidth(),rect.getTop(),rect.getLeft(),rect.getBottom())},"atk");
            atkUp=true;
            break;
         case 4:
            atk=new Hitbox(new Rect[]{new Rect(rect.getLeft()-rect.getWidth()/4,rect.getTop()-rect.getHeight()/4,rect.getRight()+rect.getWidth()/4,rect.getBottom()+rect.getHeight()/4)},"atk");
            atkUp=true;
            break;
         default: 
            atk=null;
            atkUp=false;
            break;
      }
   }
   public double[] getAtkPow(int dir,boolean light,boolean kBack)
   {
      if(light)
      {
         switch(dir)
         {
            case 0:
               if(kBack)
                  return new double[]{-2,0};//up 2
               return new double[]{5};//5%
            case 1:
               if(kBack)
                  return new double[]{-1,2};//up 1 right 2
               return new double[]{5};//5%
            case 2:
               if(kBack)
                  return new double[]{2,0};//down 2
               return new double[]{10};//10%
            case 3:
               if(kBack)
                  return new double[]{-1,-2};//up 1 left 2
               return new double[]{5};//5%
            case 4:
               if(kBack)
                  return new double[]{-2,0};//up 2
               return new double[]{5};//5%
         }
      }
      return null;
   }
   public Hitbox getAtk()
   {
      return atk;
   }
   public boolean getAtkUp()
   {
      return atkUp;
   }
   public double getVelY()
   {
      if(velY>5)
         velY=5;
      return velY;
   }
   public void setVelY(double vel)
   {
      this.velY=vel;
   }
   public double getVelX()
   {
      if(velX>5)
         velX=5;
      return velX;
   }
   public void setVelX(double vel)
   {
      this.velX=vel;
   }
   public void step(boolean right)
   {
      double off=(double)(.033);
      if(!right)
      {
         off*=-1;
      }
      velX+=off;
   }
   public void fall()
   {
      velY+=getGrav();
   }
   public void fastFall()
   {
      velY+=getGrav()*2;
   }
   public void checkVel()
   {
      for(Rect rect: hitbox.getBoxes())
      {
         rect.offset(velX,velY);
      }
   }
   public boolean hasJumped()
   {
      return jumped;
   }
   public void setJumped(boolean j)
   {
      jumped=j;
   }
   public void jump()
   {
      inAir=true;
      velY=-1.6;
   }
   public boolean isInAir()
   {
      return inAir;
   }
   public void setInAir(boolean a)
   {
      inAir=a;
   }
   public double getDamage()
   {
      return damage;
   }
   public void addDamage(double d)
   {
      damage+=d;
   }
}