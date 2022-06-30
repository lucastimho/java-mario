import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	View view;
	Controller controller;

	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		controller.setView(view);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		this.setTitle("Turtle Attack!!!");
		this.setSize(900, 900);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 30 miliseconds
			try
			{
				Thread.sleep(30);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	static void testmytubemarshallerer()
	{
		Tube t = new Tube(23, 77);
		Json j = t.marshal();
		j.save("testtube.json");
	}
	static void testthemodelmarshaler()
	{
		Model m = new Model();
		m.tubes.add(new Tube(31, 17));
		m.tubes.add(new Tube(1, 2));
		m.tubes.add(new Tube(100, 200));
		Json j = m.marshal();
		j.save("testmodel.json");
	}

	public static void main(String[] args)
	{
		// testmytubemarshallerer();
		// testthemodelmarshaler();
		
		Game g = new Game();
		g.run();
	}
}
