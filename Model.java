import java.util.ArrayList;
import java.util.Iterator;

class Model {
        Mario mario;
        Tube tube;
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
                if(mario.doesCollide(mario, s))
                {
                    getOutOfTheTube(s);
                }
            }
        }
        void getOutOfTheTube(Sprite s)
        {
            if(mario.x + mario.width >= s.x && mario.prev_x + mario.width <= s.x) mario.x = s.x - mario.width;
            else if (mario.x <= s.x + s.width && mario.prev_x >= s.x + s.width) mario.x = s.x + s.width;
            else if(mario.y >= s.y && mario.prev_y <= s.y) mario.y = s.y;
            else if (mario.y - mario.height <= s.y + s.height && mario.prev_y - mario.height >+ s.y + s.height) mario.y = s.y + s.height + mario.height;
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