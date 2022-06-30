import java.util.ArrayList;

public class Mario {
    int x;
    int y;
    int width = 55;
    int height = 90
    double vert_vel;
    void update()
    {
        vert_vel += 1.2;
        y += vert_vel;
    }
    boolean isTubeUnderMario(Mario mario, Tube tube)
    {
        if(mario.x < tube.x + tube.width)
            return false;
        if(mario.x > tube.x)
            return false;
        if(mario.height + mario.y < tube.y ) // assumes bigger is downward
            return false;
        if(mario.y > tube.y + tube.height) // assumes bigger is downward
            return false;
        return true;
    }
}
