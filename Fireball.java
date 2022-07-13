public class Fireball extends Sprite {
    Fireball(int xx, int yy)
    {
        x = xx;
        y = yy;
        width = 47;
        height = 47;
        horiz_vel = 1.1;
        vert_vel = gravity;
    }
    void update()
    {
        x += horiz_vel;
        y += vert_vel;
    }
    boolean doesCollide(Sprite tube) //Sprite was previously Tube
    {
        if(x + width <= tube.x)
            return false;
        if(x >= tube.x + tube.width)
            return false;
        else return true;
    }
}
