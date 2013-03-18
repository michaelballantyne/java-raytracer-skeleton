import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import raytracer.world.*;

public class RayTracerGUI extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private int width, height;
	private boolean finished = false;
	private JTextArea log;
	private JFileChooser fc;
	private JMenu menu;
	
	private World world;
	private Thread raytracer;
	private Timer timer;

	public RayTracerGUI () {
		// modify this line if you want to create different scenes
		world = new Chapter3Cover();
		int[] dimensions = world.build();
		width = dimensions[0];
		height = dimensions[1];

		frame = new JFrame("Java Ray Tracer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setJMenuBar(createMenu());
		
		Container pane = frame.getContentPane();
		pane.add(BorderLayout.CENTER, this);
		this.setPreferredSize(new Dimension(width, height));

		// Move the frame to center screen
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (dim.width-frame.getSize().width)/2,
	    	y = (dim.height-frame.getSize().height)/2;
	    frame.setLocation(x, y);
	    
	    // file save methods
		log = new JTextArea(5,20);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
	    JScrollPane sp = new JScrollPane(log);
		pane.add(BorderLayout.SOUTH, sp);
		
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		frame.pack();
		frame.setVisible(true);
		
		// Set up to show how far we've progressed each second.
		class Repainter implements ActionListener
		{
		    private JFrame frame;
		    public Repainter(JFrame frame){
		        this.frame = frame;
		    }
		    public void actionPerformed(ActionEvent event)
		    {
		    	getGraphics().drawImage(world.image, 0, 0, RayTracerGUI.this);
		    }
		}
		
		ActionListener listener = new Repainter(frame);
		timer = new Timer(1000, listener);
        timer.start();
		
		// initialize the off-screen rendering buffer
		raytracer = new Thread(this);
		raytracer.start();
	}


	// from http://java.sun.com/docs/books/tutorial/uiswing/components/menu.html
	private JMenuBar createMenu() {
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("File");
		menuBar.add(menu);

		//a group of JMenuItems
		JMenuItem menuItem = new JMenuItem("Save Image As JPG File", KeyEvent.VK_S);
		menuItem.addActionListener(new ActionListener() {
			// Define an action listener to respond to menu item
			public void actionPerformed(ActionEvent evt) {
				int returnVal = fc.showSaveDialog(RayTracerGUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					log.append("Saving " + file.getName() + "." + "\n");
					log.setCaretPosition(log.getDocument().getLength());
					try {							
						ImageIO.write(world.image, "jpg", file);
					}
					catch (IOException e) {
						System.err.println("Error writing image file.\n");
					}
					log.append("Saved.\n");
				} else {
					log.append("Save command cancelled by user." + "\n");
				}
				paintComponent(getGraphics());
			}
		});

		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
		menuItem.addActionListener(new ActionListener() {
			// Define an action listener to respond to menu item
			public void actionPerformed(ActionEvent evt) {
				timer.stop();
				frame.setVisible(false);
				frame.dispose();
			}
		});
		menu.add(menuItem);
		return menuBar;
	}

	public void paintComponent(Graphics g) {
		if (finished) {
			g.drawImage(world.image, 0, 0, this);
		}
	}

	public void run() {
		Graphics g = getGraphics();

		long time = System.currentTimeMillis();
		world.renderScene();
		g.drawImage(world.image, 0, 0, this); // doing this less often speed things up a bit

		time = System.currentTimeMillis() - time;
		log.append("Rendered in " + (time / 60000) + ":" + ((time % 60000) * 0.001) + ".\n");
		finished = true;
	}

	public static void main (String[] args)
	{
		new RayTracerGUI();
	}
}
