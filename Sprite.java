abstract class Sprite {
    int x;
    int y;
    int width;
    int height;
    abstract void update();
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
    // abstract void drawThyself();
}
