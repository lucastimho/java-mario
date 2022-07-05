public class Tube extends Sprite {
    int x;
    int y;

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
    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
    void update() {}
}
