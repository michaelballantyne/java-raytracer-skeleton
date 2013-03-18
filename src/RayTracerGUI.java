import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import raytracer.world.*;

public class RayTracerGUI extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private Image screen;
	private static JFrame frame;
	private int width, height;
	private boolean finished = false;
	private JTextArea log;
	private JFileChooser fc;
	private JMenu menu;
	private Graphics g;
	
	private World world;
	private Thread raytracer;

	public RayTracerGUI () {
		// modify this line if you want to create different scenes
		world = new Chapter3Cover();
		int[] dimensions = world.build();
		width = dimensions[0];
		height = dimensions[1];

		frame = new JFrame("Java Ray Tracer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Container pane = frame.getContentPane();
		pane.add(BorderLayout.CENTER, this);
		frame.setSize(width,height);
//		frame.setJMenuBar(createMenu());

		// Move the frame to center screen
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (dim.width-frame.getSize().width)/2,
	    	y = (dim.height-frame.getSize().height)/2;
	   frame.setLocation(x, y);
	    
		//file save methods
//		log = new JTextArea(5,20);
//		log.setMargin(new Insets(5,5,5,5));
//		log.setEditable(false);
//		fc = new JFileChooser();
//		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);


		// initialize the off-screen rendering buffer
		screen = frame.createImage(width, height);
		frame.setVisible(true);  
		raytracer = new Thread(this);
		raytracer.start();
	}


	// from http://java.sun.com/docs/books/tutorial/uiswing/components/menu.html
//	private JMenuBar createMenu() {
//		//Create the menu bar.
//		JMenuBar menuBar = new JMenuBar();
//
//		//Build the first menu.
//		menu = new JMenu("File");
//		menu.setEnabled(false);
//		menuBar.add(menu);
//
//		//a group of JMenuItems
//		JMenuItem menuItem = new JMenuItem("Save Image As JPG File", KeyEvent.VK_S);
//		//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S));
//		menuItem.addActionListener(new ActionListener() {
//			// Define an action listener to respond to menu item
//			public void actionPerformed(ActionEvent evt) {
//				int returnVal = fc.showSaveDialog(RayTracerGUI.this);
//				if (returnVal == JFileChooser.APPROVE_OPTION) {
//					File file = fc.getSelectedFile();
//					//This is where a real application would save the file.
//					log.append("Saving: " + file.getName() + "." + "\n");
//					log.setCaretPosition(log.getDocument().getLength());
//					try {							
//						//frame.printAll(gr);
//						//File outputfile = new File("output.png");
//						ImageIO.write(world.image, "jpg", file);
//					}
//					catch (IOException e) {
//						System.err.println("Error writing image file");
//					}
//				} else {
//					log.append("Save command cancelled by user." + "\n");
//				}
//				paintComponent(getGraphics());
//			}
//
//		});
//
//		menu.add(menuItem);
//		menu.addSeparator();
//		menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
//		menuItem.addActionListener(new ActionListener() {
//			// Define an action listener to respond to menu item
//			public void actionPerformed(ActionEvent evt) {
//				frame.setVisible(false);
//				frame.dispose();
//			}
//		});
//		menu.add(menuItem);
//		return menuBar;
//	}



	public void paintComponent(Graphics g) {
		if (finished)
			g.drawImage(screen, 0, 0, this);
	}

	public void run() {
		Graphics g = getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		long time = System.currentTimeMillis();
		world.renderScene();
		g.drawImage(world.image, 0, 0, this);        // doing this less often speed things up a bit

		time = System.currentTimeMillis() - time;
		System.out.println("Rendered in "+(time/60000)+":"+((time%60000)*0.001));
		finished = true;
	}


	public static void main (String[] args)
	{
		new RayTracerGUI();
	}

}

