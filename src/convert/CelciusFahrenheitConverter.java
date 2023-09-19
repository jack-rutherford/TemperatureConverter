package convert;

import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.*;
/**
 * 
 * Convert temperatures like Kelvin, Celcius, and Fahrenheit, using Java GUI
 * 
 * @author jackrutherford
 * Date: 1/25/22
 * CSCI 235
 *
 */
public class CelciusFahrenheitConverter extends JFrame {

	private JPanel panel;
	private JPanel innerPanel;
	private JButton button;
	private JTextField textField;
	private JLabel label;
	private JComboBox<String> comboBox; //Combo box of type String
	private final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 150;


	/**
	 * Constructor for the CelciusFahrenheitConverter class
	 */
	public CelciusFahrenheitConverter() {
		setTitle("Temperature Converter");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildPanel();
		//uncomment line below for JFrame menu
		//createAndShowGUI(); //make the JFrame menu (not really necessary but oh well)
		add(panel);
		setVisible(true);
	}
	

	/**
	 * Build the panel, and make all of the objects necessary for the panel 
	 */
	private void buildPanel() {

		String[] tempStrings = {"Celcius to Fahrenheit", "Fahrenheit to Celcius", "Kelvin to Celcius",
		"Celcius to Kelvin"};
		
		label = new JLabel("Enter a temperature");
		textField = new JTextField(10);
		button = new JButton("Convert");
		button.setToolTipText("Convert the temperature");
		comboBox = new JComboBox<String>(tempStrings);
		button.addActionListener(new ConvertButtonListener());

		innerPanel = new JPanel();
		innerPanel.setLayout(new FlowLayout()); 		//inner panel with FlowLayout
		
		panel = new JPanel(new BorderLayout());
		panel.setLayout(new BorderLayout()); 			//the main panel with a BorderLayout
	
		//add panel components
		panel.add(innerPanel, BorderLayout.CENTER);
		button.setPreferredSize(new Dimension(200, 50)); //resize button to be bigger
		panel.add(button, BorderLayout.PAGE_END);

		//add innerPanel components
		innerPanel.add(label);
		innerPanel.add(new JSeparator(SwingConstants.VERTICAL));
		innerPanel.add(textField);
		innerPanel.add(new JSeparator(SwingConstants.VERTICAL));
		innerPanel.add(comboBox);
		
		//make a small border for the panel
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	}

	/**
	 * Creates the JFrame and creates the menu made in the inner class
	 */
	private void createAndShowGUI() {

        JFrame frame = new JFrame("A Simple JFrame Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SimpleMenu smenu = new SimpleMenu();
        frame.setJMenuBar(smenu.createMenuBar());

        /* show frame */
        frame.pack();
        frame.setSize(400,300);
        frame.setVisible(true);
    }
	
	/**
	 * 
	 * @author jackrutherford
	 * 
	 * It didn't click with me that I was writing all this in JFrame instead of JPanel
	 * until the end, so I'm leaving it here but not going to count it as a change :)
	 *
	 */
	private class SimpleMenu implements ActionListener, ItemListener  {

		public JMenuBar createMenuBar() {
			//create a menubar
			JMenuBar menuBar;
			JMenu filemenu, editmenu;
			JMenu editsubmenu;
			JMenuItem menuItem;

			//Create the menu bar.
			menuBar = new JMenuBar();

			//Build the file menu.
			filemenu = new JMenu("File");
			filemenu.setMnemonic(KeyEvent.VK_F);
			filemenu.getAccessibleContext().setAccessibleDescription("This is the File menu.");
			menuBar.add(filemenu);

			//menu items
			menuItem = new JMenuItem("New",KeyEvent.VK_N);
			menuItem.getAccessibleContext().setAccessibleDescription("");
			menuItem.addActionListener(this);
			filemenu.add(menuItem);
			menuItem = new JMenuItem("Open File...",KeyEvent.VK_O);
			menuItem.getAccessibleContext().setAccessibleDescription("");
			menuItem.addActionListener(this);
			filemenu.add(menuItem);
			menuItem = new JMenuItem("Close",KeyEvent.VK_C);
			menuItem.getAccessibleContext().setAccessibleDescription("");
			menuItem.addActionListener(this);
			filemenu.add(menuItem);     

			//Build the Edit menu.
			editmenu = new JMenu("Edit");
			editmenu.setMnemonic(KeyEvent.VK_E);
			editmenu.getAccessibleContext().setAccessibleDescription("This is the Edit menu.");
			menuBar.add(editmenu);

			//menu items
			menuItem = new JMenuItem("Undo Typing",KeyEvent.VK_U);
			menuItem.getAccessibleContext().setAccessibleDescription("");
			menuItem.addActionListener(this);
			editmenu.add(menuItem);
			menuItem = new JMenuItem("Redo",KeyEvent.VK_R);
			menuItem.getAccessibleContext().setAccessibleDescription("");
			menuItem.addActionListener(this);
			editmenu.add(menuItem);
			editsubmenu = new JMenu("Expand Selection To");
			editsubmenu.setMnemonic(KeyEvent.VK_X);
			editsubmenu.addActionListener(this);
			editmenu.add(editsubmenu);

			menuItem = new JMenuItem("Enclosing Element");
			menuItem.getAccessibleContext().setAccessibleDescription("");
			menuItem.addActionListener(this);
			editsubmenu.add(menuItem);

			menuItem = new JMenuItem("Next Element");
			menuItem.getAccessibleContext().setAccessibleDescription("");
			menuItem.addActionListener(this);
			editsubmenu.add(menuItem);

			menuItem = new JMenuItem("Previous Element");
			menuItem.getAccessibleContext().setAccessibleDescription("");
			menuItem.addActionListener(this);
			editsubmenu.add(menuItem);

			return menuBar;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	
	/**
	 * Event listener class (inner class)
	 */
	private class ConvertButtonListener implements ActionListener{

		/**
		 * Override method to record the action performed
		 * 
		 * @param ActionEvent e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			String comboChoice = (String) comboBox.getSelectedItem();
			String userInput = textField.getText();

			if(!userInput.matches("[0-9]+")) { //check if the input String is only numbers
				JOptionPane.showMessageDialog(panel, "Please enter a number");
				return;
			}

			if(comboChoice.equals("Celcius to Fahrenheit")) {
				
				double fTemp, cTemp;
				cTemp = Double.parseDouble(userInput);
				fTemp = ((cTemp * (9.0/5)) + 32); //convert Celcius to Fahrenheit
				String sf1 = String.format("%.2f", fTemp); //format cTemp to only show 2 decimal places
				JOptionPane.showMessageDialog(panel, sf1 + " degrees Fahrenheit");
			}
			else if(comboChoice.equals("Fahrenheit to Celcius")) {
				double fTemp, cTemp;
				fTemp = Double.parseDouble(userInput);
				cTemp = (5/9.0) * (fTemp - 32); //convert Fahrenheit to Celcius
				String sf1 = String.format("%.2f", cTemp); //format fTemp to only show 2 decimal places
				JOptionPane.showMessageDialog(panel, sf1 + " degrees Celcius");
			}
			else if(comboChoice.equals("Kelvin to Celcius")) {
				double kTemp, cTemp;
				kTemp = Double.parseDouble(userInput);
				cTemp = kTemp - 273.15; //convert Kelvin to Celcius
				String sf1 = String.format("%.2f", cTemp); //format fTemp to only show 2 decimal places
				JOptionPane.showMessageDialog(panel, sf1 + " degrees Celcius");
			}
			else if(comboChoice.equals("Celcius to Kelvin")) {
				double kTemp, cTemp;
				cTemp = Double.parseDouble(userInput);
				kTemp = cTemp + 273.15; //convert Celcius to Kelvin
				String sf1 = String.format("%.2f", kTemp); //format fTemp to only show 2 decimal places
				JOptionPane.showMessageDialog(panel, sf1 + " degrees Kelvin");
			}
		}
	}

	
	/**
	 * Main class method, runs all methods inside of it (Constructor in this case)
	 * @param args
	 */
	public static void main(String[] args) {
		new CelciusFahrenheitConverter();
	}

	//@Override for JMenuItem
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem jmi = (JMenuItem)e.getSource();
		System.out.println("menu item clicked: " + jmi.getText());
		if (jmi.getText().equalsIgnoreCase("close")) {
			System.exit(0);
		}
	}

	//@Override Ignore this, not used
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}



}
