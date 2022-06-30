import java.util.ArrayList;

public class Mario {
    int x;
    int y;
    double vert_vel;
    void update()
    {
        vert_vel += 1.2;
        y += vert_vel;
    }
}
