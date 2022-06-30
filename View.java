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
		g.setColor(Color.gray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// g.drawImage(this.turtle_image, model.turtle_x, model.turtle_y, null);
		for(int i = 0; i < model.tubes.size(); i++)
		{
			Tube t = model.tubes.get(i);
			g.drawImage(tube, t.x, t.y, null);
		}
		g.drawImage(marios[4], 100, 400, null);
		g.drawLine(0, 596, 2000, 596);
	}
}
