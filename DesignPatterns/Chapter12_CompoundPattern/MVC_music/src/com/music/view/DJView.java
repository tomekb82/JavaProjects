package com.music.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.music.controller.ControllerInterface;
import com.music.model.BeatModelInterface;
import com.music.observers.*;
import com.music.defaults.*;

/*
 * DJView is an observer for both beats and BPM changes
 * 
 *  - VIEW of the model, displayinf current BPM and beat bar
 */
public class DJView implements ActionListener, BeatObserver, BPMObserver{

	/* reference to MODEL*/
	BeatModelInterface model;
	/* reference to CONTROLLER */
	ControllerInterface controller;
	
	/* Creating components for the display(view)
	 *
	 */
	JFrame viewFrame;  		// frame
	JPanel viewPanel;  		// panel
	BeatBar beatBar;   		// bar
	JLabel bpmOutputLabel; 	// label
	
	/* Creating components for the controlls 
	 *
	 */
	JFrame controlFrame;		// frame
	JPanel controlPanel;		// panel
	JLabel bpmLabel;			// label
	JTextField bpmTextField;	// text
	JButton setBPMButton;		// button
	JButton increaseBPMButton;	// button
	JButton decreaseBPMButton;	// butoon
	JMenu menu;					// menu
	JMenuBar menuBar;			// menu bar
	JMenuItem startMenuItem;	// menu item
	JMenuItem stopMenuItem;		// menu item

	/* Constructor:
	 *  - gets a reference to the CONTROLLER and the MODEL
	 *  - register as a BeatObserver and BPMObserver of the MODEL
	 */
	public DJView(ControllerInterface controller, BeatModelInterface model){
		this.controller = controller;
		this.model = model;
		model.registerObserver((BeatObserver)this);
		model.registerObserver((BPMObserver)this);
	}
	
	/*
	 * Swing components here..
	 */
	public void createView(){
		/* VIEW Panel */
		viewPanel = new JPanel(new GridLayout(1,2));
		/* Frame */
		viewFrame = new JFrame("View");
		viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewFrame.setSize(new Dimension(100, 80));
		
		bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
		
		/* Bar */
		beatBar = new BeatBar();
		beatBar.setValue(0);
		
		/* BPM Panel */
		JPanel bpmPanel = new JPanel(new GridLayout(2,1));
		bpmPanel.add(beatBar);
		bpmPanel.add(bpmOutputLabel);
		
		viewPanel.add(bpmPanel);
		
		viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
		viewFrame.pack();
		viewFrame.setVisible(true);
		
	}
	
	/*
	 * Creating Swing components 
	 * and placing them in the interface ...
	 */
	public void createControls(){
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		controlFrame = new JFrame("Control");
		controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controlFrame.setSize(new Dimension(100, 80));
		
		/* Cotrol Panel */
		controlPanel = new JPanel(new GridLayout(1,2));
		
		menuBar = new JMenuBar();
		menu = new JMenu("DJ Control");
		
		/* 'Start' Menu Item */
		startMenuItem = new JMenuItem("Start");
		menu.add(startMenuItem);
		startMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				controller.start();
			}
		});
		
		/* 'Stop' Menu Item */
		stopMenuItem = new JMenuItem("Stop");
		menu.add(stopMenuItem);
		stopMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				controller.stop();
			}
		});
		
		/* 'Quit' Menu Item */
		JMenuItem exit = new JMenuItem("Quit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		menu.add(exit);
		
		menuBar.add(menu);
		controlFrame.setJMenuBar(menuBar);
		
		/* Text filed */
		bpmTextField = new JTextField(2);
		bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
		
		/* Buttons */
		setBPMButton = new JButton("Set");
		setBPMButton.setSize(new Dimension(10,40));
		increaseBPMButton = new JButton(">>");
		decreaseBPMButton = new JButton("<<");
		setBPMButton.addActionListener(this);
		increaseBPMButton.addActionListener(this);
		decreaseBPMButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		buttonPanel.add(decreaseBPMButton);
		buttonPanel.add(increaseBPMButton);
		
		JPanel enterPanel = new JPanel(new GridLayout(1,2));
		enterPanel.add(bpmLabel);
		enterPanel.add(bpmTextField);
		
		JPanel insideControlPanel = new JPanel(new GridLayout(3,1));
		insideControlPanel.add(enterPanel);
		insideControlPanel.add(setBPMButton);
		insideControlPanel.add(buttonPanel);
		controlPanel.add(insideControlPanel);
		
		bpmLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		controlFrame.getRootPane().setDefaultButton(setBPMButton);
		controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);
		
		controlFrame.pack();
		controlFrame.setVisible(true);
	}
	
	public void enableStopMenuItem(){
		stopMenuItem.setEnabled(true);
	}
	
	public void disableStopMenuItem(){
		stopMenuItem.setEnabled(false);
	}
	
	public void enableStartMenuItem(){
		startMenuItem.setEnabled(true);
	}
	
	public void disableStartMenuItem(){
		startMenuItem.setEnabled(false);
	}
	
	/*
	 * Called when a button is clicked
	 */
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource() == setBPMButton){
			try{
				int bpm = Integer.parseInt(bpmTextField.getText());
				controller.setBPM(bpm);
			} catch (Exception e){
				e.printStackTrace();
			}
		} else if(event.getSource() == increaseBPMButton){
			controller.increaseBPM();
		} else if(event.getSource() == decreaseBPMButton){
			controller.decreaseBPM();
		}
	}
		
	/*
	 * Called when a state change occurs in the model
	 */
	public void updateBPM(){
		int bpm = model.getBPM();
		if(bpm == 0){
			bpmOutputLabel.setText("offline");
		}else{
			bpmOutputLabel.setText("Current BPM: " + model.getBPM());
		}
	}
	
	/* 
	 * Called when the model starts a new beat.
	 */
	public void updateBeat(){
		int bpm = model.getBPM();
		beatBar.setValue(bpm);
		//beatBar.setValue(100);
	}
	
		
}

