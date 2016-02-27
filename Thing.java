public abstract class Thing
{
   protected Hitbox hitbox;
   public Thing(Hitbox hitbox)
   {
      this.hitbox=hitbox;
   }
   public Hitbox getHitbox()
   {
      return hitbox;
   }
   public abstract double getGrav();
}