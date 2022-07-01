import java.util.ArrayList;

class Model {
        Mario mario;
        ArrayList<Tube> tubes;
        int turtle_x;
        int turtle_y;
        int dest_x;
        int dest_y;
    
        Model()
        {
            tubes = new ArrayList<>();
            mario = new Mario();
        }
        Model (Json obj)
        {}
        public void update()
        {
            mario.update();
        }
    
        public void setDestination(int x, int y)
        {
            this.dest_x = x;
            this.dest_y = y;
        }
        public void addNewTube(int mouse_x, int mouse_y)
        {
            Tube t = new Tube(mouse_x, mouse_y);
            tubes.add(t);
        }
        public void removeTube(int index)
        {
            tubes.remove(index);
        }
        Json marshal()
        {
            Json ob = Json.newObject();
            Json jsonTubes = Json.newList();
            ob.add("tubes", jsonTubes);
            for(int i = 0; i < tubes.size(); i++)
                jsonTubes.add(tubes.get(i).marshal());
            return ob;
        }
        void unmarshal(Json ob)
        {
            tubes = new ArrayList<Tube>();
            Json jsonList = ob.get("tubes");
            for(int i = 0; i < jsonList.size(); i++)
                tubes.add(new Tube(jsonList.get(i)));
        }
    
}
class Tube {
    int x;
    int y;
    int width = 55;
    int height = 400;

    Tube(int xx, int yy)
    {
      x = xx;
      y = yy;
    }
    Tube (Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
    }
    boolean isThatClickInMe(int mouse_x, int mouse_y)
    {
        if(mouse_x < x)
          return false;
        if(mouse_x > x + width)
          return false;
        return true;
    }
    boolean didIClickOnYou(int mouse_x, int mouse_y, Tube t)
    {
      if (mouse_x == t.x && mouse_y == t.y)
            return true;
      else
            return false;
    }
    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
}