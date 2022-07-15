public class Mario extends Sprite {
    int frame;
    int offGrounCount;
    
    Mario()
    {
        x = 200;
        y= 400;
        width = 60;
        height = 95;
        vert_vel = 1.2;
        bounce_vel = -13.1;
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
    boolean doesCollide(Sprite tube) //Sprite was previously Tube
    {
        if(x + width <= tube.x)
            return false;
        if(x >= tube.x + tube.width)
            return false;
        if(y <= tube.y ) // assumes bigger is downward
            return false;
        if(y >= tube.y + tube.height) // assumes bigger is downward
            return false;
        return true;
    }
    void jump() 
    {
        y += bounce_vel;
    }
}
