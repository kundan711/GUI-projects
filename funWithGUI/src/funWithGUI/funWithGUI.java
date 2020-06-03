package funWithGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.*;
import javax.sound.sampled.*;


public class funWithGUI extends JFrame implements ActionListener, ItemListener {
	
	//Define all variables used in the program
	
	JLabel subject = new JLabel("Schnelliwich Company");
	JLabel sandwich = new JLabel("Sandwiches");
	JLabel Drinks= new JLabel("Drinks");
	JLabel Sideorders = new JLabel("Side Orders");
	
	JPanel topPanel, bottomPanel, centerPanel;
	
	Color Color1, Color2;
	
	ImageIcon icon;
	JButton iconLabel;
	
	DefaultListModel<String> main;
	JList<String> list;

	DefaultListModel<String> drink;
	JList<String> list2;

	DefaultListModel<String> sides;
	JList<String> list3;	
	
	JComboBox<String> size;
	
	JCheckBox sound1, sound2;
	ButtonGroup group;

	JRadioButton rb1, rb2;
	ButtonGroup grp;
	
	JTextArea Prices;
	
	JButton order;
	JButton cancel;
	JButton done;
	
	File soundFile;
	AudioInputStream audioIn;
	Clip song;
	
	String sm = "$5.50"; //define price of small, medium, large, and xlarge as doubles 
	String med = "$7.00";
	String lar = "$8.50";
	String xlar = "$9.50";
	
	JFrame popupFrame;
	
	int count = 0;
	
	String S1, S2, S3, S4, S5, S6;
		
	JLabel P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14;
	
	public funWithGUI() {
		//create window with the company title
		setSize(900,450);
		//Set a Border Layout
		setLayout(new BorderLayout());
		setTitle("Schnellwich Company");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Define two different background colors
		Color1 = new Color(52, 142, 200);
		Color2 = new Color(77, 120, 123);
		
		
		//show the prices for different size
		Prices = new JTextArea("Prices \n Small     $5.50 \n Medium $7.00 \n Large     $8.50 \n XLarge   $9.50");
		
		//Finalize order
		order = new JButton("Place Order");
		cancel = new JButton("Cancel");
		done = new JButton("Done");
		
		//Choose what type of food
		rb1 =  new JRadioButton("fried");
		rb2 =  new JRadioButton("grilled");
		grp = new ButtonGroup();
		grp.add(rb1);
		grp.add(rb2);
		
		//choose sound effect
		sound1 = new JCheckBox("Enable Sound");
		sound2 = new JCheckBox("Disable Sound");
		group = new ButtonGroup();
		group.add(sound1);
		group.add(sound2);
		
		//add combo box for sizes
		size= new JComboBox<String>();
		putInOptions();
		
		//add three different lists for main items, drinks, and side orders
		main = new DefaultListModel<String>();
		list = new JList<String>(main);
		addsomething();

		drink = new DefaultListModel<String>();
		list2 = new JList<String>(drink);
		addsomething2();


		sides = new DefaultListModel<String>();
		list3 = new JList<String>(sides);
		addsomething3();	
		
		//Set top Panel with the company name and list
		topPanel = new JPanel();
		topPanel.setBackground(Color1);
		icon = new ImageIcon("icon.jpg");
		iconLabel = new JButton(icon);
		topPanel.add(iconLabel);
		topPanel.add(subject);
		add(topPanel, BorderLayout.NORTH);
		
		//Set the center panel for the food drink and prices
		centerPanel = new JPanel();
		centerPanel.setBackground(Color2);
		centerPanel.add(sandwich);
		centerPanel.add(list);
		centerPanel.add(Drinks);
		centerPanel.add(list2);
		centerPanel.add(Sideorders);
		centerPanel.add(list3);
		centerPanel.add(Prices);
		centerPanel.add(size);
		add(centerPanel, BorderLayout.CENTER);
		
		//Set the bottom panel for sound and to finalize order
		//Set the bottom panel with a flow layout
		bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(Color1);
		bottomPanel.add(rb1);
		bottomPanel.add(rb2);
		bottomPanel.add(sound1);
		bottomPanel.add(sound2);
		bottomPanel.add(order);
		bottomPanel.add(cancel);
		add(bottomPanel, BorderLayout.SOUTH);
		
		//Cancel button is disabled initially
		order.setEnabled(true);
		cancel.setEnabled(false);
		
		//Specifies the buttons to implement action listener
		order.addActionListener(this);
		cancel.addActionListener(this);	
		done.addActionListener(this);
		
		//Specifies the radio buttons to implement action listener
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		
		//Specifies the check box to implement item listener
		sound1.addItemListener(this);
		sound2.addItemListener(this);
		
		setVisible(true);
		
		
	}
	//Item Listener method for sound combo box
	public void itemStateChanged(ItemEvent ie) {
		Object o = ie.getSource();
		if(ie.getStateChange() == ItemEvent.SELECTED) {
			//to know if the sound is enabled by the customer
			if(o == sound1) {
			P9 = new JLabel("Enabled");
			count = 1;
			} else if(o == sound2){
				P9 = new JLabel("Disabled");
				count = 0;
			}
		}
		}
	
	//Action Listener method
	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
	
		//To know which radio button is selected by the customer
		if(o == rb1) {
			P7 = new JLabel("Fried");
		}
		else if (o == rb2) {
			P7 = new JLabel("Grilled");
		}
		
		//To know if the customer places or cancels the order
		if(o == order) {
			// Handle pop-up window
			popupFrame = new JFrame("Pop-Up Window");
			popupFrame.setSize(400,200);
			popupFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			popupFrame.setLayout(new GridLayout(9,2));
			
			//checks if the sound is enabled
			if (count == 1) {
				//plays the first sound if order is placed
			try {
				soundFile = new File("S1.wav");
				audioIn = AudioSystem.getAudioInputStream(soundFile);
				song = AudioSystem.getClip();
				song.open(audioIn);
			}
			//Displays error if occured
			catch (Exception e) {
				System.out.println("An error has occurred.");
			}
			//Starts the sound from the beginning
			song.setFramePosition(0);
			song.start();
			}
			
			//Retrives value from the sandwich, drinks, and side orders list
			S1 = (String) list.getSelectedValue();
			S2 = (String) list2.getSelectedValue();
			S3 = (String) list3.getSelectedValue();
			S4 = (String) size.getSelectedItem();
			
			//Stores the price in a JLabel according to the size selected
			if (S4 == "Select Size") {
				S4 = null;
				S5 =  null;
			}
			else if (S4 == "Small") {
				S5 = sm;
				}
				else if (S4 == "Medium") {
				S5 = med;
				}
				else if (S4 =="Large") {
				S5 = lar;
				}
				else if (S4 == "Extra Large") {
				S5 = xlar;
				}
			
			//Stores every other strings into JLabel
			P1 = new JLabel(S1);
			P2 = new JLabel(S2);
			P3 = new JLabel(S3);
			P4 = new JLabel(S4);
			P5 = new JLabel("Size :");
			P6 = new JLabel("Type :");
			P8 = new JLabel("Sound :");
			P10 = new JLabel("Sandwich :");
			P11 = new JLabel("Drinks :");
			P12 = new JLabel("Side-orders :");
			P13 = new JLabel("Total :");
			P14 = new JLabel(S5);
			

		
			
			//Adds everything to the pop-up window		
			popupFrame.add(P10);
			popupFrame.add(P1);
			popupFrame.add(P11);
			popupFrame.add(P2);
			popupFrame.add(P12);
			popupFrame.add(P3);
			popupFrame.add(P5);
			popupFrame.add(P4);
			popupFrame.add(P6);
			popupFrame.add(P7);
			popupFrame.add(P8);
			popupFrame.add(P9);
			popupFrame.add(P13);
			popupFrame.add(P14);
			popupFrame.add(done);
			
			//Disables order buttona nd enables cancel button when pop up window appears
			order.setEnabled(false);
			cancel.setEnabled(true);

			//Sets the pop-up window visible
			popupFrame.setVisible(true);
		}
		else if (o == cancel) {
			//stops the first song if being played
			song.stop();

			if (count == 1) {
				//plays second sound if the order is cancelled
			try {
				soundFile = new File("S2.wav");
				audioIn = AudioSystem.getAudioInputStream(soundFile);
				song = AudioSystem.getClip();
				song.open(audioIn);
			}
			// Displays error if occurred
			catch (Exception e) {
				System.out.println("An error has occurred.");
			}
			
			song.setFramePosition(0);
			song.start();
			}
			//Disposes popup window when order is cancelled
			popupFrame.dispose();
			
			//enables order button and disables cancel button
			order.setEnabled(true);
			cancel.setEnabled(false);
			Reset();
			
		}
			
		
		if (o == done) {
			//disposes pop up window when order is finalized
			popupFrame.dispose();
		} 
		
	}
	

	//Reset Method
	public void Reset() {
		//Clears everything
		list.setSelectedIndex(0);
		list2.setSelectedIndex(0);
		list3.setSelectedIndex(0);
		size.setSelectedIndex(0);
		grp.clearSelection();
		group.clearSelection();
	//	repaint();
	}
	
	//list options for size
	public void putInOptions(){
		size.addItem("Select Size");
		size.addItem("Small");
		size.addItem("Medium");
		size.addItem("Large");
		size.addItem("Extra Large");
	}
	
	//list main items
	public void addsomething() {
		main.addElement("");
		main.addElement("Chicken Sandwich");
		main.addElement("Cheese Burger");
		main.addElement("Ham Burger");
		main.addElement("Italian Sandwich");
	}
	
	//list drinks
	public void addsomething2() {
		drink.addElement("");
		drink.addElement("Pepsi");
		drink.addElement("Coke");
		drink.addElement("Sprite");
		drink.addElement("Dr Pepper");
	}
	
	
	//list side orders
	public void addsomething3() {
		sides.addElement("");
		sides.addElement("Fries");
		sides.addElement("Egg Roll");
		sides.addElement("Avocado");
		sides.addElement("Pickles");
	}



	
		//execute the program
		public static void main(String args[]) {
			new funWithGUI();
		}
		


}