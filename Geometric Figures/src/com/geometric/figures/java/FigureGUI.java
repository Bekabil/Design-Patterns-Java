package com.geometric.figures.java;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.*;
/**
 * Serves as a type for all figures in the simple
 * drawing program. Some implementation might be added 
 * at a future date.
 * @author Brahma Dathan
 * @modified by Bekabil Tolassa
 * Class: ICS 372
 * Date: May 28, 2015
 * Program: Assignment 2
 * Description: This class FigureGUI is a graphical user interface originally
 * 				 designed by Brahma Dathan and later modified by Bekabil Tolassa.
 * 				 The purpose of this class is to be able to draw square and 
 * 				  circle figures of 3 colors on to the GUI by using buttons.
 * 				 This class extends JFrame and implements Serializable. 
 * 				 This class uses two inner classes FiguresPanel and 
 * 				  PressedButtonListener to accomplish its task.
 * 				 This class writes string representation objects of figure to a disk
 * 				  and reads the objects from the disk and uses to display the figures.		 	
 *
 */
public class FigureGUI extends JFrame implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
 	* fileName is to store string representation of figures in figures.dat
 	* redButton, greenButton, blueButton, rectangleButton, ciricleButton, and
 	* exitButton are type JButton. Action is enum type.
 	* several data field private to this class will be used.
 	* figuresStringRepresentation is array list stores string representation
 	* of figure objects.
 	* 
*/	
	private String fileName = "figures.dat";
	private static enum Action {RECTANGLE, CIRCLE, NOTHING};
	private JButton redButton = new JButton("Red");
	private JButton greenButton = new JButton("Green");
	private JButton blueButton = new JButton("Blue");
	
	private JButton rectangleButton = new JButton("Rectangle");
	private JButton circleButton = new JButton("Circle");
	private JButton exitButton = new JButton("Exit");
	
	private JTextArea listArea = new JTextArea(21, 50);
	
	Graphics graphics;
	private Color color = Color.LIGHT_GRAY;
	
	private GregorianCalendar currentDate;
	private FiguresPanel figuresPanel;
	//initially action is NOTHING
	private Action action= Action.NOTHING; 
	private Rectangle rectangle;
	private Circle circle;
	//private Figure thisFigures;
	private ArrayList<String> figuresStringRepresentation = new ArrayList<String>();

/**
	* invokes super class's constructor
	* creates and initializes buttons and panels.
	* adds components to the frame.
	* creates and registers button listeners.
	* @param none
*/
	public FigureGUI() {
		super("Figures GUI");
		
		//Default font size and attribute is set for all buttons
		Font defaultFont = new Font("Arial", Font.BOLD, 18);
		redButton.setFont(defaultFont);
		greenButton.setFont(defaultFont);
		blueButton.setFont(defaultFont);
		rectangleButton.setFont(defaultFont);
		circleButton.setFont(defaultFont);
		exitButton.setFont(defaultFont);
		
		//current date is formatted and will be added to figurePanel
		currentDate = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = dateFormat.format(currentDate.getTime());
		
		JPanel allButtonsPanel = new JPanel(new GridLayout(2, 3));
		JPanel componentsPanel = new JPanel(new GridLayout(0, 3));
		
		//mouse listening event is added to figurePanel
		figuresPanel = new FiguresPanel(formattedDate);
		figuresPanel.addMouseListener(figuresPanel);
		componentsPanel.add(figuresPanel);
		
		//buttons are added to allButtonsPanel
		allButtonsPanel.add(redButton);
		allButtonsPanel.add(greenButton);
		allButtonsPanel.add(blueButton);	
		allButtonsPanel.add(rectangleButton);
		allButtonsPanel.add(circleButton);
		allButtonsPanel.add(exitButton);
		
		componentsPanel.add(allButtonsPanel);
		componentsPanel.add(listArea);
		componentsPanel.setBackground(Color.gray);
		add(componentsPanel);
		
		//create listener objects of type PressedButtonListener to use it for buttons.
		PressedButtonListener redButtonListener = new PressedButtonListener();
		PressedButtonListener greenButtonListener = new PressedButtonListener();
		PressedButtonListener blueButtonListener = new PressedButtonListener();
		PressedButtonListener rectangleButtonListener = new PressedButtonListener();
		PressedButtonListener circleButtonListener = new PressedButtonListener();
		PressedButtonListener exitButtonListener = new PressedButtonListener();
	
		//register listeners onto all buttons
		redButton.addActionListener(redButtonListener);
		greenButton.addActionListener(greenButtonListener);
		blueButton.addActionListener(blueButtonListener);
		rectangleButton.addActionListener(rectangleButtonListener);
		circleButton.addActionListener(circleButtonListener);
		exitButton.addActionListener(exitButtonListener);

	}
/**
	 * it split up stringObject by delimiter space and uses for color code
	 * it uses compareTo() method to compare equality of two strings.
	 * it finds the right color code and creates rectangle and circle objects
	 * of particular color. it calls draw() method of rectangle and circle methods.
	 * @param g
	 * @param stringObject
	 * @returns nothing 
*/		
	public void drawFigure(String stringObject, Graphics g){
		//x and y are coordinates, w and h are width and height
		int x, y, w, h;
		//default color is black
		Color color = Color.BLACK;
		//stringArray stores string elements of stringObject splited by space
		String[] stringArray = stringObject.split(" ");
		if(stringArray[0].compareTo("1") == 0) {
			x = Integer.parseInt(stringArray[1]);
			y = Integer.parseInt(stringArray[2]);
			w = Integer.parseInt(stringArray[3]);
			h = Integer.parseInt(stringArray[4]);
			
			if(Integer.parseInt(stringArray[5]) == 0) {
				color = Color.RED;
			}else if(Integer.parseInt(stringArray[5]) == 1) {
				color = Color.GREEN;
			}else if(Integer.parseInt(stringArray[5]) == 2) {
				color = Color.BLUE;
			}
			
			this.rectangle= new Rectangle(color);
			this.rectangle.draw(g, x, y, w, h);
			
//			String figureInformation = "Rectangle [x=" + x + ", y=" + y 
//					+ ", Width=" + (w) 
//					+ ", Height=" + (h) 
//					+ ", Color=" + color + " ]\n"; 
			//listArea.append(figureInformation);
			listArea.append(this.rectangle.toString());
		}
		else if(stringObject.split(" ")[0].compareTo("2") == 0){
			x = Integer.parseInt(stringArray[1]);
			y = Integer.parseInt(stringArray[2]);
			w = Integer.parseInt(stringArray[3]);
			h = Integer.parseInt(stringArray[4]);
			if(Integer.parseInt(stringArray[5]) == 0) {
				color = Color.RED;
			}else if(Integer.parseInt(stringArray[5]) == 1) {
				color = Color.GREEN;
			}else if(Integer.parseInt(stringArray[5]) == 2) {
				color = Color.BLUE;
			}
			this.circle= new Circle(color);
			this.circle.draw(g, x, y, w,h);
//			String figureInformation = "Circle [x=" + x + ", y=" + y 
//					+ ", radius=" + (w / 2) 
//					+ ", Color=" + color + " ]\n"; 
			//figure information is displayed to the text area of GUI
//			listArea.append(figureInformation);
			listArea.append(this.circle.toString());
		}
		
	}
	
/**
	 * 
	 * creates inputFile input file stream.
	 * Retrieve objects and stores in to figurObject array list.
	 * Loops through figureObject and draw its previous objects and the new one.
	 * @param g
	 * @returns nothing 
*/				
	public void initialDrawings(Graphics g){
		FileInputStream inputFile = null;
		ObjectInputStream input = null;
		try {
			inputFile = new FileInputStream(fileName);
			input = new ObjectInputStream(inputFile);
			@SuppressWarnings("unchecked")
			ArrayList<String> figureObject = (ArrayList<String>) input.readObject();
			//loop through figureObjects and retrieve them
			for(int i = 0; i < figureObject.size(); i++) {
				//each new figure object is appended to previously saved objects
				this.figuresStringRepresentation.add(figureObject.get(i));
				drawFigure(figureObject.get(i), g);
			}
			inputFile.close();
				
		} catch ( IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
		}
		    
	}
/**
	* This class FiguresPanel extends JPanel and implements MouseListener.
	* This class implements listeners for all buttons.
	* The following are main purpose:
	*     method actionPerformed() is overridden and implemented.
	*     source of event will be evaluated and action will be assigned a value.
	*	  If event source is an exit button, the figures and the content of the
	*     text area will be serialized to disk in a file named fileName, 
	*     and it terminates the program. 
*/	
	private class FiguresPanel extends JPanel implements MouseListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/*
	* values of mouse click, x and y coordinates are stored in x and y variables.
	* current date is stored in date variable
	* count is a counter
	* 2D mousPosition array is to store the first and second mouse click values.
*/
		private int x;
		private int y;
		private String date;
		private int count;
		private int[][] mousePosition ;
/**
	* initializes x, y, count, and date.
	* creates mousePosition to be 2 by 2 array of integer
	* @param formatted current date
*/
		public FiguresPanel(String formattedDate) {
			x = 0;
			y = 0;
			count = 0;
			mousePosition= new int[2][2];
			date = formattedDate;
		}
/**
	 * Overrides paintComponent() method
	 * invokes super class's paintComponent() method.
	 * it calls initialDrawing() method.
	 * prints current date on the panel.
	 * it collects string representation of an object and it displays to listArea.
	 * @param g
	 * @returns nothing 
*/		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString(date, 10, this.getHeight() - 20);
			initialDrawings(g);
		}
/**
	 * Overrides mousClicked() method
	 * Waits for the mouse click and creates the appropriate figures.
	 * it selects cases accordingly, it creates objects rectangle and circle.
	 * it collects string representation of an object and it displays to listArea.
	 * @param event
	 * @returns nothing 
*/
		@Override
		public void mouseClicked(MouseEvent event) {
			String figureInformation ="";
			//x and y coordinates are assigned to variable x and y
			x = event.getX();
			y = event.getY();
			mousePosition[count][0] = x;
			mousePosition[count++][1] = y;
			if(count == 2)
				switch(action) {
					case RECTANGLE:
						// process the mouse click for rectangle object
						rectangle = new Rectangle(color);
						graphics = figuresPanel.getGraphics();
						rectangle.draw(graphics, mousePosition[0][0], 
								mousePosition[0][1], 
								mousePosition[1][0] - mousePosition[0][0],
								mousePosition[1][1] - mousePosition[0][1]);
						//rectangle's string representation is collected
						figureInformation += "Rectangle [x=" + mousePosition[0][0] 
								+ ", y=" + mousePosition[0][1] 
								+ ", Width=" + (mousePosition[1][0]-mousePosition[0][0]) 
								+ ", Height=" + (mousePosition[1][1]-mousePosition[0][1]) 
								+ ", Color=" + color + " ]\n"; 
						listArea.append(figureInformation);
						//rectangle object representation is stored in array list
						figuresStringRepresentation.add("1 " + mousePosition[0][0] 
								+ " " + mousePosition[0][1] + " "
								+ (mousePosition[1][0] - mousePosition[0][0]) + " " 
								+ (mousePosition[1][1] - mousePosition[0][1] + " " 
								+ this.getColorCode(color))); 
						count = 0;
						break;
					case CIRCLE:
						// process the mouse click for circle object
						circle = new Circle(color);
						graphics = figuresPanel.getGraphics();
						circle.draw(graphics, mousePosition[0][0], 
								mousePosition[0][1], 
								mousePosition[1][0]-mousePosition[0][0],
								mousePosition[1][1] - mousePosition[0][1]);
						//circle's string representation is collected
						figureInformation += "Circle [x=" + mousePosition[0][0] 
								+ ", y=" + mousePosition[0][1] 
								+ ", radius=" + (mousePosition[1][0]-mousePosition[0][0])/2 
								+ ", Color=" + color + " ]\n"; 
						listArea.append(figureInformation);
						//circle object representation is stored in array list
						figuresStringRepresentation.add("2 "+ mousePosition[0][0] 
								+ " " + mousePosition[0][1] + " " 
								+ (mousePosition[1][0]-mousePosition[0][0])+ " "
								+ (mousePosition[1][1] - mousePosition[0][1]) + " "
								+ this.getColorCode(color)); 
						count = 0;
						break;
											
					case NOTHING:
						count = 0;
						break;
						
				}
			
		}
/**
	* identifies the kind of colors passed.
	* it returns color code
	* @param color.
	* @returns integer value
*/
		public int getColorCode(Color color){
			if(color == Color.RED){
				return 0;
			}else if(color == Color.GREEN){
				return 1;
			}else if(color == Color.BLUE){
				return 2;
			}
			return -1;
		}
		/**
		 * Not used
		 */
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		/**
		 * Not used
		 */
		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		/**
		 * Not used
		 */
		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		/**
		 * Not used
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	} //FiguresPanel
		
/**
	* This class PressedButtonListener implements ActionListener.
	* This class implements listeners for all buttons.
	* The following are main purpose:
	*     method actionPerformed() is overridden and implemented.
	*     source of event will be evaluated and action will be assigned a value.
	*	  If event source is an exit button, the figures and the content of the
	*     text area will be serialized to disk in a file named fileName, 
	*     and it terminates the program. 
*/
	private class PressedButtonListener implements ActionListener {
/**
	* Overrides method actionPerformed().
	* it compares events source and perform accordingly.
	* it writes objects to output stream suspends the program.
	* @param button click event.
	* @returns nothing
*/
		@Override
		public void actionPerformed(ActionEvent event) {
		
			if (event.getSource() == rectangleButton) {
				action = Action.RECTANGLE;
			}
			if (event.getSource() == circleButton) {
				action = Action.CIRCLE;
			}
			if (event.getSource() == redButton) {
				color = Color.RED;
			}
			if (event.getSource() == greenButton) {
				color = Color.GREEN;
			}
			if (event.getSource() == blueButton) {
				color = Color.BLUE;
			}
			if (event.getSource() == exitButton) {
				//writing figures and its string representation into file.						
				FileOutputStream outputfile = null;
				try {
					outputfile = new FileOutputStream(fileName);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ObjectOutputStream output = null;
				try {
					output = new ObjectOutputStream(outputfile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					//output.writeObject(figuresPanel);
					output.writeObject(figuresStringRepresentation);
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(ABORT);
			}
			
		}
	
	}
/**
	 * Program execution starts here.
	 * Object of FigureGUI will be created here.
	 * frame's object properties are set
	 * @param args not used
*/
	public static void main(String[] args) {
		FigureGUI frame = new FigureGUI();
		frame.setSize(1600, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}

