//CMSC 420
//Josh Holzbach

import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class loginInterface extends JFrame
	{
		private JLabel usernameJLabel;
		private JTextField usernameJTextField;
		
		private JLabel passwordJLabel;
		private JTextField passwordJTextField;
		
		private JButton loginJButton;//Used to login
		private JButton exitJButton;//Used to exit program
		
		private JLabel errorJLabel;
		private TextArea errorTextArea;//Used to output error messages
				
	 	String next;
	
		static String type;
		
		public loginInterface()//Creates login interface
			{
				Container contentPane = getContentPane();
      		contentPane.setLayout( null );
      	    this.addWindowListener(new exitListener());

			
				usernameJLabel = new JLabel();
      		usernameJLabel.setBounds( 20, 65, 90, 20 );
      		usernameJLabel.setText("username" );			
				contentPane.add(usernameJLabel );
				usernameJTextField = new JTextField();
      		usernameJTextField.setBounds( 100, 65, 115, 20 );
      		//usernameJTextField.setN
      		contentPane.add(usernameJTextField );
				
				passwordJLabel = new JLabel();
      		passwordJLabel.setBounds( 20, 100, 90, 20 );
      		passwordJLabel.setText("password" );			
				contentPane.add(passwordJLabel );
				passwordJTextField = new JTextField();
      		passwordJTextField.setBounds( 100, 100, 115, 20 );
      		contentPane.add(passwordJTextField );
				
				errorJLabel = new JLabel();
      		errorJLabel.setBounds( 20, 150, 50, 20 );
      		errorJLabel.setText("error" );			
				contentPane.add(errorJLabel );
				errorTextArea = new TextArea();  	
				errorTextArea.setBounds( 100, 150, 150, 150 );
      		contentPane.add(errorTextArea );
				
				loginJButton = new JButton();
      		loginJButton.setBounds( 225, 65, 95, 25 );
     			loginJButton.setText( "login" );
      		contentPane.add( loginJButton );
      		loginJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								loginJButtonActionPerformed( event );
							}

					} 

				); 
				
				exitJButton = new JButton();
      		exitJButton.setBounds( 225, 105, 95, 25 );
     			exitJButton.setText( "exit" );
      		contentPane.add( exitJButton );
      		exitJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								exitJButtonActionPerformed( event );
							}

					} 

				); 	
				
						
				setTitle( "Login" ); 
      		setSize( 400, 400 );     
      		setVisible(true);    
			
			}
		
		public void errorInfo()
			{
				this.errorTextArea.setText("Error invalid username or password");
			}
	
		private void loginJButtonActionPerformed( ActionEvent event )
   		{
				String username = usernameJTextField.getText();
				String password = passwordJTextField.getText();
				
				if(username.equals("") == false)
					{
						if(password.equals("") == false)
							{
								String send = username + " " + password;  
								send = send.trim();	    
    							Person p = null;			
								
								try
									{			
										p = ExClient.sendLogin(send);


									}
									
								catch(Exception e)
									{
									
									}		
										if(p.isNullPerson()==true)
											{
												errorTextArea.setText("Error invalid information");
											}
										
										if(p.isNullPerson()==false)
											{
												if((p.isInstructor() == true) && (p.isAdmin() == false))
													{
														setVisible(false);
														selectOrCreateCourseInterface view = new selectOrCreateCourseInterface(true,p);		
													}
											
												if((p.isInstructor() == false) && (p.isAdmin() == true))
													{
														setVisible(false);
														adminInterface view = new adminInterface(p);		
													}
												
												if((p.isInstructor() == true) && (p.isAdmin() == true))
													{
														setVisible(false);
														adminInterface view = new adminInterface(p);		
													}
											
												if((p.isInstructor() == false) && (p.isAdmin() == false))
													{
						
														setVisible(false);
														selectOrCreateCourseInterface sample = new selectOrCreateCourseInterface(false,p);									
													}
											}
									}
									
								//catch(Exception e)
									//{
									
									//}	
							}
					//}
				
			}
	
		private void exitJButtonActionPerformed( ActionEvent event)
			{				
				System.exit(1);
			}
				
	}