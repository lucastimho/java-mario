import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	BufferedImage tube_image;
	Model model;

	View(Controller c, Model m)
	{
		model = m;
	    try
		{
			this.tube_image = ImageIO.read(new File("tube.png"));
		}
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// g.drawImage(this.turtle_image, model.turtle_x, model.turtle_y, null);
		for(int i = 0; i < model.tubes.size(); i++)
		{
			Tube t = model.tubes.get(i);
			g.drawImage(tube_image, t.x, t.y, null);
		}
	}
}
