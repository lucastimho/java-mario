import java.util.ArrayList;
import java.util.stream.IntStream;

public class Mario extends Sprite {
    int x;
    int y;
    int width = 60;
    int height = 95;
    double vert_vel;
    int frame;
    int prev_x;
    int prev_y;
    
    Mario()
    {
        x = 200;
        y= 400;
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
        }
        
        frame++;
        if (frame > 4)
          frame = 0;
    }
    boolean doesCollide(Mario mario, Sprite sprite)
    {
        if(mario.x + mario.width <= sprite.x)
            return false;
        if(mario.x >= sprite.x + sprite.width)
            return false;
        if(mario.y <= sprite.y ) // assumes bigger is downward
            return false;
        if(mario.y >= sprite.y + sprite.height) // assumes bigger is downward
            return false;
        return true;
    }
    void jump() 
    {
        vert_vel = -20.1;
    }
    void remember_state()
    {
        prev_x = x;
        prev_y = y;
    }
}
