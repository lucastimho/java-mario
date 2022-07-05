public class Tube extends Sprite {
    int x;
    int y;
    Model model;

    Tube(int xx, int yy, Model m)
    {
      model = m;
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
    // void drawThyself(Graphics g, int scrollPos)
    // {
    //     int sp = model.mario.x;
    // }
}
