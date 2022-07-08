import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

class Controller implements MouseListener, KeyListener, SwingConstants
{
	Model model;
	View view;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;
	boolean keyCtrl;
	int queuedSpaces;

	Controller(Model m)
	{
		model = m;
	}

	void setView(View v)
	{
		view = v;
	}

	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == 1)
		{
			Sprite theTubeIClickedOn = (Tube) null;
			int index = 0;
			for(int i = 0; i < model.tubeSprites.size(); i++)
			{
				Sprite t = (Tube) model.tubeSprites.get(i); //Sprite was previously Tube
				if(t.isThatClickInMe(e.getX() + model.mario.x - 200, e.getY()))
				{
					theTubeIClickedOn = t;
					index = i;				
				}
			}
			if(theTubeIClickedOn == null)
				model.addNewTube(e.getX() + model.mario.x - 200, e.getY());
			else
				model.removeTube(index);
		}
		else if (e.getButton() == 3)
		{
			Sprite theGoombaIClickedOn = (Goomba) null;
			int index = 0;
			for(int i = 0; i < model.goombaSprites.size(); i++)
			{
				Sprite t = (Goomba) model.goombaSprites.get(i); //Sprite was previously Tube
				if(t.isThatClickInMe(e.getX() + model.mario.x, e.getY()))
				{
					theGoombaIClickedOn = t;
					index = i;				
				}
			}
			if(theGoombaIClickedOn == null)
				model.addNewGoomba(e.getX() + model.mario.x - 200, e.getY());
			else
				model.removeGoomba(index);
		}
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) 
	{
		if (e.getY() < 100)
		{
			System.out.println("break here");
		}
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_SPACE: keySpace = true; queuedSpaces++; break;
			case KeyEvent.VK_CONTROL: keyCtrl = true; break;
		}
		char c = e.getKeyChar();
		if(c == 's')
		{
			model.marshal().save("model.json");
			System.out.println("Saved");
		}
		else if (c == 'l')
		{
			Json j = Json.load("model.json");
			model.unmarshal(j);
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_SPACE: keySpace = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			// case KeyEvent.VK_CONTROL: keyCtrl = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.remember_state();
		if(keyRight) model.mario.x += 4;
		if(keyLeft) model.mario.x -= 4;
		if((keySpace || queuedSpaces > 0) && model.mario.offGrounCount < 3) model.mario.jump();
		queuedSpaces = 0;
		if(keyCtrl) model.fireball.shoot = true;
	}
}
