import java.util.ArrayList;
import java.util.Iterator;

class Model {
    Mario mario;
    Tube tubes;
    Goomba goombas;
    ArrayList<Sprite> sprites;
    Fireball fireball;
    int offGrounCount;

    Model()
    {
        sprites = new ArrayList<>();
        mario = new Mario();
        goombas = new Goomba(350, 700);
        fireball = new Fireball();
    }
    Model (Json obj)
    {}
    public void update()
    {
        mario.update();
        goombas.update();
        fireball.update();
        for(Iterator<Sprite> it = sprites.iterator(); it.hasNext(); )
        {
            Sprite t = it.next(); //Sprite was previously Tube
            t.update();
            if(mario.doesCollide(t))
            {
                getOutOfTheTube(t);
            }
            if(goombas.doesCollide(t))
                goombas.horiz_vel *= -1;
        }
    }
    void getOutOfTheTube(Sprite t) //Sprite was previously Tube
    {
        if(mario.x + mario.width >= t.x && mario.prev_x + mario.width <= t.x) mario.x = t.x - mario.width;
        else if (mario.x <= t.x + t.width && mario.prev_x >= t.x + t.width) mario.x = t.x + t.width;
        else if(mario.y >= t.y && mario.prev_y <= t.y) 
        {
            mario.y = t.y;
            offGrounCount = 0;
            mario.vert_vel = 0;
        }
        else if (mario.y - mario.height <= t.y + t.height && mario.prev_y - mario.height >= t.y + t.height) 
        {
            mario.y = t.y + t.height + mario.height;
        }
        else System.out.println("error");
    }  

    public void addNewTube(int mouse_x, int mouse_y)
    {
        Tube t = new Tube(mouse_x, mouse_y);
        sprites.add(t);
    }
    public void removeTube(int index)
    {
        sprites.remove(index);
    }
    Json marshal()
    {
        Json ob = Json.newObject();
        Json jsonTubes = Json.newList();
        ob.add("tubes", jsonTubes);
        for(int i = 0; i < sprites.size(); i++)
            jsonTubes.add(sprites.get(i).marshal());
        return ob;
    }
    void unmarshal(Json ob)
    {
        sprites = new ArrayList<Sprite>();
        Json jsonList = ob.get("tubes");
        for(int i = 0; i < jsonList.size(); i++)
            sprites.add(new Tube(jsonList.get(i)));
    }
    void remember_state()
    {
        mario.remember_state();
    }
    
}