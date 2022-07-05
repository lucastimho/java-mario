public class Tube extends Sprite {
    int x;
    int y;
    int width = 55;
    int height = 400;

    Tube(int xx, int yy)
    {
      x = xx;
      y = yy;
    }
    Tube (Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
    }
    boolean isThatClickInMe(int mouse_x, int mouse_y)
    {
        if(mouse_x < x)
          return false;
        if(mouse_x > x + width)
          return false;
        return true;
    }
    boolean didIClickOnYou(int mouse_x, int mouse_y, Tube t)
    {
      if (mouse_x == t.x && mouse_y == t.y)
            return true;
      else
            return false;
    }
    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
    void update() {}
}
