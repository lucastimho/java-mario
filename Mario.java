public class Mario extends Sprite {
    double vert_vel;
    int frame;
    int prev_x;
    int prev_y;
    int offGrounCount;
    
    Mario()
    {
        x = 200;
        y= 400;
        width = 60;
        height = 95;
        vert_vel = 1.2;
    }

    void update()
    {
        vert_vel += 0.9;
        y += vert_vel;
        if(y > 400)
        {
            // vert_vel = -18;
            y = 400;
            offGrounCount = 0;
        }
        
        frame++;
        if (frame > 4)
          frame = 0;
        offGrounCount++;
    }
    boolean doesCollide(Mario mario, Sprite tube) //Sprite was previously Tube
    {
        if(mario.x + mario.width <= tube.x)
            return false;
        if(mario.x >= tube.x + tube.width)
            return false;
        if(mario.y <= tube.y ) // assumes bigger is downward
            return false;
        if(mario.y >= tube.y + tube.height) // assumes bigger is downward
            return false;
        return true;
    }
    void jump() 
    {
        vert_vel -= 13.1;
    }
    void remember_state()
    {
        prev_x = x;
        prev_y = y;
    }
}
