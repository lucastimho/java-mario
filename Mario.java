import java.util.ArrayList;
import java.util.stream.IntStream;

public class Mario {
    int x;
    int y;
    int width = 55;
    int height = 90;
    double vert_vel;
    void update()
    {
        vert_vel += 1.2;
        y += vert_vel;
        IntStream.range(0,5).forEach(i -> System.out.println("Hello World!"));
    }
    void setTimeout(Runnable runnable, int delay)
    {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e) {
                System.err.println(e);
            }
        }).start();
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
