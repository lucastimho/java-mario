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
    boolean hitsGoomba(Sprite fire) //Sprite was previously Tube
    {
        if(x + width <= fire.x)
            return false;
        if(x >= fire.x + fire.width)
            return false;
            if(y <= fire.y ) // assumes bigger is downward
            return false;
        if(y >= fire.y + fire.height) // assumes bigger is downward
            return false;
        return true;
    }
    void update() 
    {
      x += horiz_vel;
    }
}
