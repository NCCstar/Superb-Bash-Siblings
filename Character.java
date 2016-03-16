public abstract class Character extends Thing
{
   public double getGrav(){
      return (double).007;}
   private boolean jumped=false;
   private boolean inAir=true;
   private double velY=0;
   private double velX=0;
   private double damage=0;
   protected boolean atkUp=false;
   protected Hitbox atk;
   private int hitstun=0;
   protected double weight;
   protected int atkDir=5;
   public Character(int x,int y,int xDim,int yDim)
   {
      super(new Hitbox(new Rect[]{new Rect(x-xDim/2,y-yDim,x+xDim/2,y)},"char"));
      weight=100;
   }
   public int getAtkDir()
   {
      return atkDir;
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
   public double getWeight()
   {
      return weight;
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
   public void attack(int dir,boolean light)
   {
      Rect rect=hitbox.getBoxes()[0];
      if(light)
      {
         switch(dir)
         {
            case 0://different for each fighter
               atk=new Hitbox(new Rect[]{new Rect(rect.getLeft(),rect.getTop()-rect.getHeight(),rect.getRight(),rect.getTop())},"atk");
               atkUp=true;
               atkDir=0;
               break;
            case 1:
               atk=new Hitbox(new Rect[]{new Rect(rect.getRight(),rect.getTop(),rect.getRight()+rect.getWidth(),rect.getBottom())},"atk");
               atkUp=true;
               atkDir=1;
               break;
            case 2:
               atk=new Hitbox(new Rect[]{new Rect(rect.getLeft(),rect.getBottom()-1,rect.getRight(),rect.getBottom()+rect.getHeight()+1)},"atk");
               atkUp=true;
               atkDir=2;
               break;
            case 3:
               atk=new Hitbox(new Rect[]{new Rect(rect.getLeft()-rect.getWidth(),rect.getTop(),rect.getLeft(),rect.getBottom())},"atk");
               atkUp=true;
               atkDir=3;
               break;
            case 4:
               atk=new Hitbox(new Rect[]{new Rect(rect.getLeft()-rect.getWidth()/4,rect.getTop()-rect.getHeight()/4,rect.getRight()+rect.getWidth()/4,rect.getBottom()+rect.getHeight()/4)},"atk");
               atkUp=true;
               atkDir=4;
               break;
            default: 
               atk=null;
               atkUp=false;
               atkDir=5;
               break;
         }
      }
      else
      {
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
               atk=new Hitbox(new Rect[]{new Rect(rect.getLeft(),rect.getBottom()+1,rect.getRight(),rect.getBottom()+rect.getHeight()+1)},"atk");
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
   }
   public double[] getAtkPow(int dir,boolean light,boolean kBack)
   {
      if(light)
      {
         switch(dir)
         {
            case 0:
               if(kBack)
                  return new double[]{-1,0};//up 1
               return new double[]{4.6};//5%
            case 1:
               if(kBack)
                  return new double[]{-.5,1.5};//up .5 right 1.5
               return new double[]{6};//5%
            case 2:
               if(kBack)
                  return new double[]{1.5,0};//down 1.5
               return new double[]{11};//10%
            case 3:
               if(kBack)
                  return new double[]{-.5,-1.5};//up .5 left 1.5
               return new double[]{6};//5%
            case 4:
               if(kBack)
                  return new double[]{-1,0};//up 1
               return new double[]{4.3};//5%
         }
      }
      else
      {
         switch(dir)
         {
            case 0:
               if(kBack)
                  return new double[]{-2,0};//up 2
               return new double[]{2};//5%
            case 1:
               if(kBack)
                  return new double[]{-.5,2.5};//up .5 right 2.5
               return new double[]{3};//5%
            case 2:
               if(kBack)
                  return new double[]{5,0};//down 5
               return new double[]{5};//10%
            case 3:
               if(kBack)
                  return new double[]{-.5,-2.5};//up .5 left 2.5
               return new double[]{3};//5%
            case 4:
               if(kBack)
                  return new double[]{-2,0};//up 2
               return new double[]{1.5};//5%
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
   public abstract void step(boolean right);

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