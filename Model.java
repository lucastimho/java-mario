import java.util.ArrayList;
import java.util.Iterator;

class Model {
    Mario mario;
    ArrayList<Sprite> tubeSprites;
    ArrayList<Sprite> goombaSprites;
    Fireball fireball;
    int offGrounCount;

    Model()
    {
        tubeSprites = new ArrayList<>();
        mario = new Mario();
        goombaSprites = new ArrayList<>();
        fireball = new Fireball();
    }
    Model (Json obj)
    {}
    public void update()
    {
        mario.update();
        fireball.update();
        for(Iterator<Sprite> it = tubeSprites.iterator(); it.hasNext(); )
        {
            Sprite t = it.next(); //Sprite was previously Tube
            t.update();
            if(mario.doesCollide(t))
            {
                getOutOfTheTube(t);
            }
            for(Iterator<Sprite> ig = goombaSprites.iterator(); ig.hasNext(); )
            {
                Sprite g = ig.next(); //Sprite was previously Tube
                if(goombaSprites.size() > 0) 
                {
                    g.update();
                    if(g.doesCollide(t)) g.horiz_vel *= -1;
                }
            }
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
        Sprite t = new Tube(mouse_x, mouse_y);
        tubeSprites.add(t);
    }
    public void removeTube(int index)
    {
        tubeSprites.remove(index);
    }
    public void addNewGoomba(int mouse_x, int mouse_y)
    {
        Sprite t = new Goomba(mouse_x, mouse_y);
        goombaSprites.add(t);
    }
    public void removeGoomba(int index)
    {
        goombaSprites.remove(index);
    }
    Json marshal()
    {
        Json ob = Json.newObject();
        Json jsonTubes = Json.newList();
        ob.add("tubes", jsonTubes);
        for(int i = 0; i < tubeSprites.size(); i++)
            jsonTubes.add(tubeSprites.get(i).marshal());
        return ob;
    }
    void unmarshal(Json ob)
    {
        tubeSprites = new ArrayList<Sprite>();
        Json jsonList = ob.get("tubes");
        for(int i = 0; i < jsonList.size(); i++)
            tubeSprites.add(new Tube(jsonList.get(i)));
    }
    void remember_state()
    {
        mario.remember_state();
        for(Iterator<Sprite> ig = goombaSprites.iterator(); ig.hasNext(); )
            {
                Sprite g = ig.next(); //Sprite was previously Tube
                if(goombaSprites.size() > 0) g.remember_state();
            }
    }
    
}