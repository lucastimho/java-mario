abstract class Sprite {
    int x;
    int y;
    int width;
    int height;
    abstract void update();
    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
    // abstract void draw();
}
