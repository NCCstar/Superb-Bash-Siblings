public class Rect
{
   private double top;
   private double left;
   private double bottom;
   private double right;
   private double width;
   private double height;
   public Rect()
   {
      left=0;
      top=0;
      right=0;
      bottom=0;
      width=0;
      height=0;
   }
   public Rect(double l,double t,double r,double b)
   {
      left=l;
      top=t;
      right=r;
      bottom=b;
      width=right-left;
      height=bottom-top;
   }
   public Rect(Rect x)
   {
      this(x.getLeft(),x.getTop(),x.getBottom(),x.getRight());
   }
   public double centerX()
   {
      return (left+right)/2;
   }
   public double centerY()
   {
      return (top+bottom)/2;
   }
   public boolean intersects(Rect ex)
   {
      return false;
   }
   public boolean contains(double xCo,double yCo)
   {
      return xCo>left&&xCo<right&&yCo<bottom&&yCo>top;
   }
   public void offset(double xOff,double yOff)
   {
      left+=xOff;
      right+=xOff;
      top+=yOff;
      bottom+=yOff;
   }
   public void offset(int xOff,int yOff)
   {
      offset((double)xOff,(double)yOff);
   }
   public void offsetTo(double newX,double newY)
   {
      left=newX-width/2;
      right=newX+width/2;
      top=newY-height/2;
      bottom=newY+height/2;
   }
   public static boolean collides(Rect a,Rect b)
   {
      return a.getLeft() < b.getRight() && b.getLeft() < a.getRight() && a.getTop() < b.getBottom() && b.getTop() < a.getBottom();
   }
   public double getLeft(){
      return left;}
   public double getTop(){
      return top;}
   public double getRight(){
      return right;}
   public double getBottom(){
      return bottom;}
   public double getWidth(){
      return width;}
   public double getHeight(){
      return height;}
   public void set(double l, double t, double r,double b)
   {
      left=l;
      top=t;
      right=r;
      bottom=b;
      width=right-left;
      height=bottom-top;
   }
}