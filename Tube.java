public class Tube extends Sprite {
    int x;
    int y;
    Model model;

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
    boolean isThatClickInMe(int mouse_x, int mouse_y)
    {
        if(mouse_x < x)
          return false;
        if(mouse_x > x + width)
          return false;
        return true;
    }
    // boolean didIClickOnYou(int mouse_x, int mouse_y, Tube t)
    // {
    //   if (mouse_x == t.x && mouse_y == t.y)
    //         return true;
    //   else
    //         return false;
    // }
    
}
