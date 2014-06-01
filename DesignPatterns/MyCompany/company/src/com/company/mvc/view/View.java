package com.company.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import com.company.bugs.*;
import com.company.devices.*;
import com.company.devices.factory.AbstractDeviceFactory;
import com.company.devices.factory.DeviceFactory;
import com.company.documentation.*;
import com.company.documentation.decorator.*;
import com.company.documentation.factory.AbstractDocumentationFactory;
import com.company.documentation.factory.DocumentationFactory;
import com.company.mvc.observer.*;
import com.company.mvc.controller.*;
import com.company.mvc.model.*;
import com.company.reports.*;
import com.company.reports.decorator.ReportDecorator;
import com.company.reports.factory.AbstractReportFactory;
import com.company.reports.factory.ReportFactory;

public class View extends JFrame implements FocusListener, ActionListener, Observer{

	/* reference to MODEL*/
	ModelInterface model;
	/* reference to CONTROLLER */
	ControllerInterface controller;
	
	/* Factories: device, documentation, report */
	AbstractDeviceFactory deviceFactory;
	AbstractDocumentationFactory documentFactory;
	AbstractReportFactory reportFactory;
	
	/* Creating components for the display(view)
	 *
	 */
	
	/* Device */
	JFrame frameDevice;  		
	JPanel panelDevice;  	
	JTextField textDeviceSystem = new JTextField(2);
	JTextField textDeviceModule = new JTextField(2);
	JTextField textDeviceAlgorithms = new JTextField(2);
	JTextField textDeviceVersion = new JTextField(2);
	Choice listDevice = new Choice();
	JButton buttonOK = new JButton("OK");
	JButton buttonExit = new JButton("Exit");
	JButton buttonAddDoc = new JButton("Add Doc");
	JButton buttonAddBug = new JButton("Add Bug");
	/* Document */
	JFrame frameDocument;
	JPanel panelDocument;
	JTextField textDocumentName = new JTextField(2);
	JButton buttonDocOK = new JButton("OK");
	JButton buttonDocExit = new JButton("Exit");
	JButton buttonDocAddReport = new JButton("Add report");
	static boolean add_report = false;
	JRadioButton[] wybor = new JRadioButton[2];
	/* Report */
	JFrame frameReport;
	JPanel panelReport;
	JTextField textDocumentNameReport = new JTextField(2);
	JTextField textReportName = new JTextField(2);
	JTextField textReportAuthor = new JTextField(2);
	JTextField textReportDate = new JTextField(2);
	JTextField textReportDevice = new JTextField(2);
	JTextField textReportVersion = new JTextField(2);
	JButton buttonReportOK = new JButton("OK");
	JButton buttonReportExit = new JButton("Exit");
	Checkbox actualityReport= new Checkbox("Actuality:",null,true);
	/* Bug */
	JFrame frameBug;
	JPanel panelBug;
	JTextField textBugTitle = new JTextField(2);
	JTextArea textBugDescription = new JTextArea (2,18);
	JButton buttonBugOK = new JButton("OK");
	JButton buttonBugExit = new JButton("Exit");
	
	/* Constructor:
	 *  - gets a reference to the CONTROLLER and the MODEL
	 *  - register as an Observer of the MODEL
	 */
	public View(ControllerInterface controller, ModelInterface model){
		deviceFactory = new DeviceFactory();
		documentFactory = new DocumentationFactory();
		reportFactory = new ReportFactory();
		this.controller = controller;
		this.model = model;
		model.registerObserver((Observer)this);
	}
	
	/*
	 * Swing components here..
	 */
	public void createViewDevice(){
		
		// VIEW Panel 
		panelDevice = new JPanel(new GridLayout(7,1));
		
		// Frame 
		frameDevice = new JFrame("DEVICE");
		frameDevice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDevice.setSize(new Dimension(100, 80));
		
		// Panel: Device
		JLabel labelDevice = new JLabel("Device:", SwingConstants.RIGHT);
		listDevice.addItem("A");
		listDevice.addItem("B");
		listDevice.addItem("C");
		listDevice.addItem("D");
		JPanel panelDeviceType = new JPanel(new GridLayout(1,2));
		panelDeviceType.add(labelDevice);
		panelDeviceType.add(listDevice);

		// Panel: DeviceSystem
		JLabel labelDeviceSystem = new JLabel("Device system:", SwingConstants.RIGHT);
		JPanel panelDeviceSystem = new JPanel(new GridLayout(1,2));
		panelDeviceSystem.add(labelDeviceSystem);
		panelDeviceSystem.add(textDeviceSystem);
		
		// Panel: DeviceModule
		JLabel labelDeviceModule = new JLabel("Device module:", SwingConstants.RIGHT);
		JPanel panelDeviceModule = new JPanel(new GridLayout(1,2));
		panelDeviceModule.add(labelDeviceModule);
		panelDeviceModule.add(textDeviceModule);
		
		// Panel: DeviceAlgorithms
		JLabel labelDeviceAlgorithms = new JLabel("Device algorithms:", SwingConstants.RIGHT);
		JPanel panelDeviceAlgorithms = new JPanel(new GridLayout(1,2));
		panelDeviceAlgorithms.add(labelDeviceAlgorithms);
		panelDeviceAlgorithms.add(textDeviceAlgorithms);
		
		// Panel: DeviceVersion
		JLabel labelDeviceVersion = new JLabel("Device versions:", SwingConstants.RIGHT);
		JPanel panelDeviceVersion = new JPanel(new GridLayout(1,2));
		panelDeviceVersion.add(labelDeviceVersion);
		panelDeviceVersion.add(textDeviceVersion);
		
		// Button
		buttonOK.addActionListener(this);
		buttonExit.addActionListener(this);
		buttonAddDoc.addActionListener(this);
		buttonAddBug.addActionListener(this);
		JPanel buttonPanel1 = new JPanel(new GridLayout(1,2));
		JPanel buttonPanel2 = new JPanel(new GridLayout(1,2));
		buttonPanel1.add(buttonAddDoc);
		buttonPanel1.add(buttonAddBug);
		buttonPanel2.add(buttonOK);
		buttonPanel2.add(buttonExit);
		
		panelDevice.add(panelDeviceType);
		panelDevice.add(panelDeviceSystem);
		panelDevice.add(panelDeviceModule);
		panelDevice.add(panelDeviceAlgorithms);
		panelDevice.add(panelDeviceVersion);
		panelDevice.add(buttonPanel1);
		panelDevice.add(buttonPanel2);
		
		frameDevice.getContentPane().add(panelDevice, BorderLayout.CENTER);
		frameDevice.pack();
		frameDevice.setVisible(true);
	}
	
	public void createViewDocument(){
		// VIEW Panel 
		panelDocument = new JPanel(new GridLayout(4,1));
		
		// Frame 
		frameDocument = new JFrame("Document");
		frameDocument.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDocument.setSize(new Dimension(100, 80));
		
		/* RadioButtons */
		wybor[0]=new JRadioButton("DK",true);
		wybor[1]=new JRadioButton("OFU");
		
		ButtonGroup wybory = new ButtonGroup();
		for (int i=0; i<wybor.length; i++){ 
			wybor[i].addActionListener(this); //oraz od działań uŜytkownika (np. klikanie myszy)
			wybory.add(wybor[i]); //wstawianie przycisków do kontenera
			panelDocument.add(wybor[i]); 
		}
		
		// Panel: DocumentName
		JLabel labelDocumentName = new JLabel("Document name:", SwingConstants.RIGHT);
		JPanel panelDocumentName= new JPanel(new GridLayout(1,2));
		panelDocumentName.add(labelDocumentName);
		panelDocumentName.add(textDocumentName);
		// Button
		buttonDocOK.addActionListener(this);
		JPanel buttonPanel = new JPanel(new GridLayout(1,3));
		buttonPanel.add(buttonDocOK);
		buttonDocAddReport.addActionListener(this);
		buttonPanel.add(buttonDocAddReport);
		buttonDocExit.addActionListener(this);
		buttonPanel.add(buttonDocExit);

		panelDocument.add(panelDocumentName);
		panelDocument.add(buttonPanel);
		frameDocument.getContentPane().add(panelDocument, BorderLayout.CENTER);
		frameDocument.pack();
		frameDocument.setVisible(true);
	}
	
	public void createViewBug(){
		// VIEW Panel 
		panelBug = new JPanel(new GridLayout(5,1));
		// Frame 
		frameBug = new JFrame("Bug");
		frameBug.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameBug.setSize(new Dimension(100, 80));
		// Panel: BugTitle
		JLabel labelBugTitle = new JLabel("Title:", SwingConstants.LEFT);
		JPanel panelBugTitle= new JPanel(new GridLayout(1,2));
		panelBugTitle.add(labelBugTitle);
		panelBugTitle.add(textBugTitle);
		// Panel: BugDescription
		JLabel labelBugDescription = new JLabel("Description:", SwingConstants.LEFT);
		JPanel panelBugDescription = new JPanel(new GridLayout(1,2));
		
		textBugDescription.addFocusListener(this);
		textBugDescription.setLineWrap(true);
		textBugDescription.setWrapStyleWord(true);
		
		//JScrollPane scrollBugDescription = new JScrollPane
		//				(textBugDescription,
		//				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		//				HORIZONTAL_SCROLLBAR_ NEVER);
		panelBugDescription.add(labelBugDescription);
		panelBugDescription.add(textBugDescription);
		//panelBugDescription.add(scrollBugDescription);
		// Button
		buttonBugOK.addActionListener(this);
		buttonBugExit.addActionListener(this);
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		buttonPanel.add(buttonBugOK);
		buttonPanel.add(buttonBugExit);

		panelBug.add(panelBugTitle);
		panelBug.add(panelBugDescription);
		panelBug.add(buttonPanel);
		frameBug.getContentPane().add(panelBug, BorderLayout.CENTER);
		frameBug.pack();
		frameBug.setVisible(true);
	}
	
	public void createViewReport(){
		// VIEW Panel 
		panelReport = new JPanel(new GridLayout(10,1));
		// Frame 
		frameReport = new JFrame("Report");
		frameReport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameReport.setSize(new Dimension(100, 180));
		
		// Panel: DocumentName
		JLabel labelDocumentName = new JLabel("Document name:", SwingConstants.RIGHT);
		JPanel panelDocumentName= new JPanel(new GridLayout(1,2));
		panelDocumentName.add(labelDocumentName);
		panelDocumentName.add(textDocumentNameReport);
		
		// Panel: ReportName
		JLabel labelReportName = new JLabel("Name:", SwingConstants.RIGHT);
		JPanel panelReportName = new JPanel(new GridLayout(1,2));
		panelReportName.add(labelReportName);
		panelReportName.add(textReportName);
		// Panel: ReportAuthor
		JLabel labelReportAuthor = new JLabel("Author:", SwingConstants.RIGHT);
		JPanel panelReportAuthor = new JPanel(new GridLayout(1,2));
		panelReportAuthor.add(labelReportAuthor);
		panelReportAuthor.add(textReportAuthor);
		// Panel: ReportDate
		JLabel labelReportDate = new JLabel("Date:", SwingConstants.RIGHT);
		JPanel panelReportDate = new JPanel(new GridLayout(1,2));
		panelReportDate.add(labelReportDate);
		panelReportDate.add(textReportDate);
		// Panel: ReportDevice
		JLabel labelReportDevice = new JLabel("Device:", SwingConstants.RIGHT);
		JPanel panelReportDevice = new JPanel(new GridLayout(1,2));
		panelReportDevice.add(labelReportDevice);
		panelReportDevice.add(textReportDevice);
		// Panel: ReportVersion
		JLabel labelReportVersion = new JLabel("Version:", SwingConstants.RIGHT);
		JPanel panelReportVersion = new JPanel(new GridLayout(1,2));
		panelReportVersion.add(labelReportVersion);
		panelReportVersion.add(textReportVersion);
	
		// Button
		buttonReportOK.addActionListener(this);
		buttonReportExit.addActionListener(this);
		JPanel buttonPanel = new JPanel(new GridLayout(1,3));
		buttonPanel.add(buttonReportOK);
		buttonPanel.add(buttonReportExit);

		panelReport.add(panelDocumentName);
		panelReport.add(panelReportName);
		panelReport.add(panelReportAuthor);
		panelReport.add(panelReportDate);
		panelReport.add(panelReportDevice);
		panelReport.add(panelReportVersion);
		panelReport.add(actualityReport);
		panelReport.add(buttonPanel);
		frameReport.getContentPane().add(panelReport, BorderLayout.CENTER);
		frameReport.pack();
		frameReport.setVisible(true);
	}
	
	
	/*
	 * Called when a button is clicked
	 */
	public void actionPerformed(ActionEvent event){
		
		/* Devices */
		if(event.getSource() == buttonOK){
			DeviceInterface device = null;
			if(listDevice.getSelectedItem().equals("A")){
				device = deviceFactory.createDeviceA();
			} else if(listDevice.getSelectedItem().equals("B")){
				device = deviceFactory.createDeviceB();
			} else if(listDevice.getSelectedItem().equals("C")){
				device = deviceFactory.createDeviceC();
			} else if(listDevice.getSelectedItem().equals("D")){
				device = deviceFactory.createDeviceD();
			}
			device.setSystem(textDeviceSystem.getText());
			device.setModule(textDeviceModule.getText());
			device.setAlgorithm(textDeviceAlgorithms.getText());
			device.setVersion(textDeviceVersion.getText());
			controller.addDevice(device);	
		} else if(event.getSource() == buttonExit){
			System.exit(0);	
		/* Document */	
		} else if(event.getSource() == buttonAddDoc){
			createViewDocument();
		} else if(event.getSource() == buttonDocOK){
			DocumentationInterface document = null;
			if(wybor[0].isSelected()){
				document = documentFactory.createDocDK(textDocumentName.getText());
			}else if(wybor[1].isSelected()){
				document = documentFactory.createDocOFU(textDocumentName.getText());
			}
			controller.addDocument(document);
			add_report = true;
		} else if(event.getSource() == buttonDocExit){
			frameDocument.setVisible(false);
		} else if(event.getSource() == buttonDocAddReport){
			createViewReport();
		/* Report */
		} else if(event.getSource() == buttonReportOK){
			System.out.println("dok: " + textDocumentNameReport.getText());
			
			if(add_report){
				ReportInterface report = reportFactory.createReport();
				report.createReport(textReportName.getText(),
						textReportAuthor.getText(),
						textReportDate.getText(),
						textReportDevice.getText(),
						textReportVersion.getText());
				((ReportDecorator)report).setActuality(actualityReport.getState());
				controller.addReport(report, textDocumentNameReport.getText());	
			}
		} else if(event.getSource() == buttonReportExit){
			frameReport.setVisible(false);
		/*  Bug */	
		} else if(event.getSource() == buttonAddBug){
			createViewBug();
		} else if(event.getSource() == buttonBugOK){
			BugInterface bug = new Bug(textBugTitle.getText(), 
									textBugDescription.getText());
			controller.addBug(bug);
		} else if(event.getSource() == buttonBugExit){
			frameBug.setVisible(false);
		}
		
	}
	
	/* 
	 * Called when the model starts a new beat.
	 */
	public void update(){
		//int bpm = model.getBPM();
	}
	
}
