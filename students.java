import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class students extends JFrame
	{
		private JLabel listJLabel;
		private TextArea listTextArea;
		
		private JButton exitJButton;
		
		public students(Roll students)
			{
				Container contentPane = getContentPane();
      		contentPane.setLayout( null );
      	    this.addWindowListener(new exitListener());
				listJLabel = new JLabel();
      		listJLabel.setBounds( 70, 25, 80, 20 );
      		listJLabel.setText("Students");			
				contentPane.add(listJLabel );
				listTextArea = new TextArea();  	
				listTextArea.setBounds( 50, 45, 200, 200 );
      		contentPane.add(listTextArea );
				
				exitJButton = new JButton();
      		exitJButton.setBounds( 0, 0, 150, 25 );
     			exitJButton.setText("Exit window");
      		contentPane.add( exitJButton );
      		exitJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								exitJButtonActionPerformed(event);
							}

					} 
				);				
				
				setTitle(" List of Students "); 
      		setSize( 300, 300 );     
      		setVisible(true);  
			}
		
		private void exitJButtonActionPerformed( ActionEvent event)
			{	
				setVisible(false);
			}
	}