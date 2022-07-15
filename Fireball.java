public class Fireball extends Sprite {
    Fireball(int xx, int yy)
    {
        x = xx;
        y = yy;
        width = 47;
        height = 47;
        horiz_vel = 1.1;
        vert_vel = gravity;
        bounce_vel = -7.7;
    }
    void update()
    {
        x += horiz_vel;
        y += vert_vel;
    }
}
