package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Controller extends JFrame  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel buttonPanel = new JPanel();
    JButton decreaseInflowBtn = new JButton();
    JButton increaseInflowBtn = new JButton();

    public Controller() {
    	setSize(1280, 720);  
        init();
        setVisible(true);  
    }

    public void init() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      layOutComponents();
      // Connect model and view
   }
    
    private void layOutComponents() {
        setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
       // this.pack();
        buttonPanel.add(increaseInflowBtn);
        buttonPanel.add(decreaseInflowBtn);
    	//this.add(BorderLayout.CENTER, buttonPanel);
    	//DisableButtons();
        this.setVisible(true);  

    }
}
