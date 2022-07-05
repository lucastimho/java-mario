import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	BufferedImage tube;
	Model model;
	BufferedImage[] marios;

	View(Controller c, Model m)
	{
		model = m;
		tube = loadImage("tube.png");
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
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.gray);
		g.drawLine(0, 400, 1000, 400);
		for(int i = 0; i < model.tubes.size(); i++)
		{
			Sprite s = model.sprites.get(i);
			s.drawThyself(g);
			g.drawImage(tube, t.x - model.mario.x + 200, t.y, null);
		}
		g.drawImage(marios[model.mario.frame], 200, model.mario.y - 95, null);
	}
}
