public class Goomba extends Sprite {
    Goomba(int xx, int yy)
    {
        x = xx;
        y = yy;
        width = 99;
        height = 118;
        horiz_vel = 1.6;
    }
    boolean doesCollide(Sprite tube) //Sprite was previously Tube
    {
        if(x + width <= tube.x)
            return false;
        if(x >= tube.x + tube.width)
            return false;
        else return true;
    }
    void update() 
    {
      x += horiz_vel;
    }
}
