import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import java.io.*;

public class courseOptionsInterface extends JFrame
	{
		private JButton assignmentsJButton;
		private JButton studentsJButton;
		private JButton gradeReportsJButton;	
		private JButton modifySchemeJButton;
		private JButton modifyScaleJButton;
		private JButton statusJButton;
		private JButton logoutJButton;
		private JButton homeJButton;
		
		Person transition;
		Courses from;
		
		
		public courseOptionsInterface(Courses view, Person user)
			{
      	    this.addWindowListener(new exitListener());

				transition = user;
				from = view;
				
				Container contentPane = getContentPane();
      		contentPane.setLayout( null );
				
				homeJButton = new JButton();
      		homeJButton.setBounds(0, 0 , 95, 25 );
     			homeJButton.setText( "Home" );
				contentPane.add( homeJButton );
      		homeJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								homeJButtonActionPerformed( event );
							}

					} 
				);
				
				
				
				logoutJButton = new JButton();
      		logoutJButton.setBounds(80, 0 , 95, 25 );
     			logoutJButton.setText( "Logout" );
      		contentPane.add( logoutJButton );
      		logoutJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								logoutJButtonActionPerformed( event );
							}

					} 
				);
								
				assignmentsJButton = new JButton();
			   assignmentsJButton.setBounds(130, 50 , 130, 25 );
			   assignmentsJButton.setText( "Assigments" );
			   assignmentsJButton.addActionListener(
						
				new ActionListener() 
					{
			            	
						public void actionPerformed( ActionEvent event )
							{
								assignmentsJButtonActionPerformed( event );
								setVisible(false);

							}
			
					} 
				);
				
				studentsJButton = new JButton();
			   studentsJButton.setBounds(250, 50 , 130, 25 );
			   studentsJButton.setText( "Students" );
			   studentsJButton.addActionListener(
							
				new ActionListener() 
					{
			           public void actionPerformed( ActionEvent event )
								{
									studentsJButtonActionPerformed( event );
								}
			
					} 
				);
							
				modifySchemeJButton = new JButton();
			   modifySchemeJButton.setBounds(0, 100 , 130, 25 );
			   modifySchemeJButton.setText( "Modify scheme" );
			   modifySchemeJButton.addActionListener(
							
				new ActionListener() 
					{
			      	public void actionPerformed( ActionEvent event )
							{
								modifySchemeJButtonActionPerformed( event );
							}
			
					} 
				);
							
				modifyScaleJButton = new JButton();
			   modifyScaleJButton.setBounds(130, 100 , 130, 25 );
			   modifyScaleJButton.setText( "Modify scale" );
			   modifyScaleJButton.addActionListener(
							
				new ActionListener() 
					{
			          public void actionPerformed( ActionEvent event )
								{
									modifyScaleJButtonActionPerformed( event );
								}
			
					} 
				);
											
						
				gradeReportsJButton = new JButton();
      		gradeReportsJButton.setBounds(0, 50 , 130, 25 );
     			gradeReportsJButton.setText( "Grade report" );
      		contentPane.add( gradeReportsJButton );
      		gradeReportsJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								gradeReportsJButtonActionPerformed( event );
							}

					} 
				);
							
				contentPane.add( assignmentsJButton );
				
				if(view.isArchived() == false)
					{
						contentPane.add( studentsJButton );
					}
					
				contentPane.add(modifySchemeJButton );
				contentPane.add( modifyScaleJButton );
					
				statusJButton = new JButton();
			   statusJButton.setBounds(260, 100 , 130, 25 );
			   if(getCourse().isArchived())
			   {
				   statusJButton.setText( "Set Active" );
			   }
			   else
			   {
				   statusJButton.setText( "Set Archived" );
			   }
			   contentPane.add( statusJButton );
			   statusJButton.addActionListener(
							
				new ActionListener() 
					{
			         public void actionPerformed( ActionEvent event )
							{
								statusJButtonActionPerformed( event );
							}
			
					} 
				);
							
							
				setSize(550, 300 );   
				setTitle(view.getCourseName());   
      		setVisible(true);    
			
				
			}
		
		private void logoutJButtonActionPerformed( ActionEvent event )
   		{
				logout();
			}
		
		private void homeJButtonActionPerformed( ActionEvent event )
   		{
				setVisible(false);
				Person user = getUser();
				home(user);
			}
				
		private void gradeReportsJButtonActionPerformed( ActionEvent event )
   		{
				Courses current = getCourse();
				Person user = getUser();
				
				setVisible(false);
				reportInterface reports = new reportInterface(current,user);
				//System.exit(1);
			}
	
		private void assignmentsJButtonActionPerformed( ActionEvent event )
   		{
				setVisible(false);
				Courses from = getCourse();
				Person user = getUser();
				assignmentInterface next = new assignmentInterface(user,from);

				
				
				
			}
		
		private void studentsJButtonActionPerformed( ActionEvent event )
   		{
				setVisible(false);
				Courses from = getCourse();
				Person user = getUser();
				infoInterface view = new infoInterface(user,from);
			}
	
		private void modifySchemeJButtonActionPerformed( ActionEvent event )
   		{
				Courses from = getCourse();
				Person user = getUser();
				
				if(from.isArchived() == true)
					{
						setVisible(false);
						schemeInterface next = new schemeInterface(user,true,from);
					}
				
				if(from.isArchived() == false)
					{
						setVisible(false);
						schemeInterface next = new schemeInterface(user,from);
					}
				
			}
	
		private void modifyScaleJButtonActionPerformed( ActionEvent event )
   		{
				Courses from = getCourse();
				Person user = getUser();
				
				if(from.isArchived() == true)
					{
						setVisible(false);
						scaleInterface next = new scaleInterface(user,true,from);
					}
				
				if(from.isArchived() == false)
					{
						setVisible(false);
						scaleInterface next = new scaleInterface(user,from);
					}
			}
	
		private void statusJButtonActionPerformed( ActionEvent event )
   		{
				Courses from = getCourse();
				
				//change status then update in database;
				boolean changed = false;
				
				if(from.isArchived() == true)
					{
						from.setArchived(false);
						changed = true;
						
						try
							{
								ExClient.updateCourse(from);
							}
					
						catch(Exception e)
							{
							
							}
					}
				
				if((from.isArchived() == false)  && (changed == false))
					{
						from.setArchived(true);
						changed = true;
					
						try
							{
								ExClient.updateCourse(from);
							}
					
						catch(Exception e)
							{
							
							}
					}
				
				Person user = getUser();
				setVisible(false);
				
				courseOptionsInterface view = new courseOptionsInterface(from,user);
			}
	
		public void home(Person user)
			{
				setVisible(false);
				if(user.isAdmin() == true)
					{
						adminInterface admin = new adminInterface(user);
					}
			
				if((user.isInstructor() == true)  && (user.isAdmin() == false))
					{
						selectOrCreateCourseInterface create = new selectOrCreateCourseInterface(true,user);
					}
			
				if((user.isInstructor() == false) && (user.isAdmin() == false))
					{
					
						selectOrCreateCourseInterface create = new selectOrCreateCourseInterface(false,user);
					}
			}
		
		
		public Person getUser()
			{
				return transition;
			}
		
		public Courses getCourse()
			{
				return from;
			}
		
		
		public void logout()
			{
				setVisible(false);
				loginInterface logout = new loginInterface();
			}	
		
	}