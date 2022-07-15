import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	BufferedImage tube;
	BufferedImage goomba;
	BufferedImage goombaFire;
	BufferedImage fireball;
	Model model;
	BufferedImage[] marios;

	View(Controller c, Model m)
	{
		model = m;
		tube = loadImage("tube.png");
		goomba = loadImage("gumba.png");
		goombaFire = loadImage("gumba_fire.png");
		fireball = loadImage("fireball.png");
		marios = new BufferedImage[5];
		marios[0] = loadImage("mario1.png");
		marios[1] = loadImage("mario2.png");
		marios[2] = loadImage("mario3.png");
		marios[3] = loadImage("mario4.png");
		marios[4] = loadImage("mario5.png");
	}
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}
	
	public void paintComponent(Graphics g)
	{
		try
		{
			g.setColor(new Color(128, 255, 255));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.gray);
			g.drawLine(0, 400, 1000, 400);
			// Tube image iterator
			for(int i = 0; i < model.tubeSprites.size(); i++)
			{
				Sprite t = (Tube) model.tubeSprites.get(i);
				g.drawImage(tube, t.x - model.mario.x + 200, t.y, null);
			}
			// Goomba image iterator
			for(int i = 0; i < model.goombaSprites.size(); i++)
			{
				Sprite goom = (Goomba) model.goombaSprites.get(i);
				for (int ii = 0; ii < model.fireballSprites.size(); ii++)
				{
					Sprite fire = (Fireball) model.fireballSprites.get(ii);
					if (goom.hitsGoomba(fire)) 
					{
						g.drawImage(goombaFire, goom.x - model.mario.x + 200, 400 - goom.height, null);
						try
						{
							Thread.sleep(1000);
							model.goombaSprites.remove(i);
						} catch (Exception e)
						{
							e.printStackTrace();
							System.exit(-1);
						}
					}
					else g.drawImage(goomba, goom.x - model.mario.x + 200, 400 - goom.height, null);
				}
			}
			// Mario image
			g.drawImage(marios[model.mario.frame], 200, model.mario.y - 95, null);
			// Fireball Iterator
			for(int i = 0; i < model.fireballSprites.size(); i++)
			{
				Sprite fire = (Fireball) model.fireballSprites.get(i);
				g.drawImage(fireball, fire.x, fire.y, null);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
