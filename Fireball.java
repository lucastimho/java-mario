public class Fireball extends Sprite {
    double horiz_vel;
    double vert_vel;
    boolean shoot= false;
    Fireball()
    {
        width = 47;
        height = 47;
        horiz_vel = 10.1;
        vert_vel = 5.7;
    }
    void update()
    {
        x += horiz_vel;
        y += vert_vel;
    };
}
