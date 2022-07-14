import java.util.ArrayList;
import java.util.Iterator;

class Model {
    Mario mario;
    ArrayList<Sprite> tubeSprites;
    ArrayList<Sprite> goombaSprites;
    ArrayList<Sprite> fireballSprites;
    int offGrounCount;

    Model()
    {
        tubeSprites = new ArrayList<>();
        mario = new Mario();
        goombaSprites = new ArrayList<>();
        fireballSprites = new ArrayList<>();
    }
    Model (Json obj)
    {}
    public void update()
    {
        mario.update();
        // Iterates through Tubes 
        for(Iterator<Sprite> it = tubeSprites.iterator(); it.hasNext(); )
        {
            Sprite t = it.next(); //Sprite was previously Tube
            if (tubeSprites.size() > 0)
            {
                t.update();
                if(mario.doesCollide(t))
                {
                    getOutOfTheTube(t);
                }
            }
        }
        // Iterates through Goombas
        for(Iterator<Sprite> ig = goombaSprites.iterator(); ig.hasNext(); )
        {
            Sprite g = (Goomba) ig.next(); //Sprite was previously Tube
            if (goombaSprites.size() > 0)
            {
                g.update();
                for(Iterator<Sprite> it = tubeSprites.iterator(); it.hasNext(); )
                {
                    Sprite t = (Tube) it.next();
                    if(g.doesCollide(t)) 
                    {
                        g.horiz_vel *= -1;
                        getGoombaOutofTube(g, t);
                    }
                }
            }
        }
        // Iterates through fireballs
         for(Iterator<Sprite> fi = fireballSprites.iterator(); fi.hasNext();)
        {
            Sprite f = fi.next();
            if (fireballSprites.size() > 0)
            {
                f.update();
                if (f.y + f.height > 400) f.vert_vel = f.bounce_vel;
                else f.vert_vel = f.gravity;
                for(Iterator<Sprite> ig = goombaSprites.iterator(); ig.hasNext(); )
                {
                    Sprite g = (Goomba) ig.next();
                    if (g.hitsGoomba(f))
                    {
                        g.onFire = true;
                    }
                }
            }
        }
    }
    // Code for tube interations / collisions
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
    void getGoombaOutofTube(Sprite goom, Sprite t)
    {
        if(goom.x + goom.width >= t.x && goom.prev_x + goom.width <= t.x) goom.x = t.x - goom.width;
        else if (goom.x <= t.x + t.width && goom.prev_x >= t.x + t.width) goom.x = t.x + t.width;
    }  
    // Adding and removing tubes
    public void addNewTube(int mouse_x, int mouse_y)
    {
        Sprite t = new Tube(mouse_x, mouse_y);
        tubeSprites.add(t);
    }
    public void removeTube(int index)
    {
        tubeSprites.remove(index);
    }
    // Adding and removing Goombas
    public void addNewGoomba(int mouse_x, int mouse_y)
    {
        Sprite t = new Goomba(mouse_x, mouse_y);
        goombaSprites.add(t);
    }
    public void removeGoomba(int index)
    {
        goombaSprites.remove(index);
    }
    // Adding and removing fireballs
    public void addFireball()
    {
        Sprite fire = new Fireball(mario.x + mario.width, mario.y - (mario.height / 2));
        fireballSprites.add(fire);
    }
    public void removeFireball(int index)
    {
        fireballSprites.remove(index);
    }
    // Marshal commands
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
    // State function
    void remember_state()
    {
        mario.remember_state();
        for(Iterator<Sprite> ig = goombaSprites.iterator(); ig.hasNext(); )
            {
                Sprite g = ig.next();
                if(goombaSprites.size() > 0) g.remember_state();
            }
    }
    
}