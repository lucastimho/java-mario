import java.util.ArrayList;
import java.util.Iterator;

class Model {
        Mario mario;
        // ArrayList<Tube> tubes;
        ArrayList<Goomba> goomba;
        ArrayList<Sprite> sprites;
    
        Model()
        {
            sprites = new ArrayList<>();
            mario = new Mario();
        }
        Model (Json obj)
        {}
        public void update()
        {
            mario.update();
            for(Iterator<Sprite> it = sprites.iterator(); it.hasNext(); )
            {
                Sprite s = it.next();
                s.update();
                // if(mario.doesCollide(mario, s))
                // {
                //     getOutOfTheTube(t);
                // }
            }
        }
        void getOutOfTheTube(Tube t)
        {
            if(mario.x + mario.width >= t.x && mario.prev_x + mario.width <= t.x) mario.x = t.x - mario.width;
            else if (mario.x <= t.x + t.width && mario.prev_x >= t.x + t.width) mario.x = t.x + t.width;
            else if(mario.y >= t.y && mario.prev_y <= t.y) mario.y = t.y;
            else if (mario.y - mario.height <= t.y + t.height && mario.prev_y - mario.height >+ t.y + t.height) mario.y = t.y + t.height + mario.height;
            else System.out.println("error");
        }  

        public void addNewTube(int mouse_x, int mouse_y)
        {
            Sprite s = new Sprite(mouse_x, mouse_y);
            sprites.add(s);
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