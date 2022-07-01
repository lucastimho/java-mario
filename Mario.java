import java.util.ArrayList;
import java.util.stream.IntStream;

public class Mario {
    int x;
    int y;
    int width = 60;
    int height = 95;
    double vert_vel;
    int frame;
    Mario()
    {
        x = 200;
        y= 400;
        // vert_vel = 1.2;
    }
    void update()
    {
        // vert_vel += 0.9;
        // y += vert_vel;
        if(y > 600)
        {
            vert_vel = -18;
            y = 600;
        }
        
        frame++;
        if (frame > 4)
          frame = 0;
    }
    // void setTimeout(Runnable runnable, int delay)
    // {
    //     new Thread(() -> {
    //         try {
    //             Thread.sleep(delay);
    //             runnable.run();
    //         }
    //         catch (Exception e) {
    //             System.err.println(e);
    //         }
    //     }).start();
    // }
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
