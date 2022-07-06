public class Tube extends Sprite {
    Tube(int xx, int yy)
    {
      x = xx;
      y = yy;
      width = 55;
      height = 400;
    }
    Tube (Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
    }
    void update() {}    
}
