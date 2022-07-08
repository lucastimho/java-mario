public class Fireball extends Sprite {
    double horiz_vel;
    double vert_vel;
    boolean shoot = false;
    Fireball(int xx, int yy)
    {
        x = xx;
        y = yy;
        width = 47;
        height = 47;
        horiz_vel = 1.1;
        vert_vel = 1.7;
    }
    void update()
    {
        x += horiz_vel;
        y += vert_vel;
    };
}
