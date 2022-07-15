abstract class Sprite {
    int x;
    int y;
    int prev_x;
    int prev_y;
    int width;
    int height;
    double horiz_vel;
    double vert_vel;
    double bounce_vel;
    double gravity = 1.7;
    boolean onFire = false;
    boolean doesCollide(Sprite tube) //Sprite was previously Tube
    {
        if(x + width <= tube.x)
            return false;
        if(x >= tube.x + tube.width)
            return false;
        else return true;
    }
    boolean hitsGoomba(Sprite goomba) //Sprite was previously Tube
    {
        if(x + width <= goomba.x)
            return false;
        if(x >= goomba.x + goomba.width)
            return false;
        if(y <= goomba.y ) // assumes bigger is downward
            return false;
        else return true;
    }
    abstract void update();
    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
    boolean isThatClickInMe(int mouse_x, int mouse_y)
    {
        if(mouse_x < x)
          return false;
        if(mouse_x > x + width)
          return false;
        return true;
    }
    void remember_state()
    {
        prev_x = x;
        prev_y = y;
    }
}
