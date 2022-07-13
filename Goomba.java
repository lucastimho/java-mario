public class Goomba extends Sprite {
    Goomba(int xx, int yy)
    {
        x = xx;
        y = yy;
        width = 99;
        height = 118;
        horiz_vel = 1.6;
    }
    void update() 
    {
      x += horiz_vel;
    }
}
