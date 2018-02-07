import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.util.*;

public class selectOrCreateCourseInterface extends JFrame
	{	
		
	
		private JButton selectJButton;
		private JButton createJButton;
		private JButton passwordChangeJButton;
		private JButton logoutJButton;
		
		private JLabel passwordJLabel;
		private JTextField passwordJTextField;
		private JLabel courseNameJLabel;
		private JTextField courseNameJTextField;
		
		private JLabel currentCoursesJLabel;
		private TextArea currentCoursesTextArea;
		private JLabel archivedCoursesJLabel;
		private TextArea archivedCoursesTextArea;
		private JLabel errorJLabel;
		private TextArea errorTextArea;
		private JList currentCoursesJList;
		private JList archivedCoursesJList;

		Person transition;
							
		public selectOrCreateCourseInterface(boolean create, Person user)
			{		
				
				transition = user;		
				Container contentPane = getContentPane();
      		contentPane.setLayout( null );
      	    this.addWindowListener(new exitListener());

				currentCoursesJList = new JList();  
				currentCoursesJList.setSelectedIndex(0);
				currentCoursesJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
				currentCoursesJList.setVisibleRowCount(5);  
				JScrollPane listScroller = new JScrollPane(currentCoursesJList); 
				listScroller.setBounds(160,50,150,150); 
				contentPane.add(listScroller); 
				
				currentCoursesJLabel = new JLabel();
      		currentCoursesJLabel.setBounds( 5, 50, 150, 20 );
      		currentCoursesJLabel.setText("Current courses" );			
				contentPane.add(currentCoursesJLabel );
				/*currentCoursesTextArea = new TextArea();
				currentCoursesTextArea.setBounds( 160, 50, 150, 150 );
      		//contentPane.add(currentCoursesTextArea );*/
				
			
				archivedCoursesJList = new JList();  
				archivedCoursesJList.setSelectedIndex(0);
				archivedCoursesJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
				archivedCoursesJList.setVisibleRowCount(5);  
				JScrollPane archivedListScroller = new JScrollPane(archivedCoursesJList); 
				archivedListScroller.setBounds(160,200,150,150); 
				contentPane.add(archivedListScroller); 
				
				archivedCoursesJLabel = new JLabel();
      		archivedCoursesJLabel.setBounds( 5, 200, 150, 20 );
      		archivedCoursesJLabel.setText("Archived courses" );			
				contentPane.add(archivedCoursesJLabel );
				//archivedCoursesTextArea = new TextArea();  	
				//archivedCoursesTextArea.setBounds( 160, 200, 150, 150 );
      		//contentPane.add(archivedCoursesTextArea );
				currentCoursesJList.addListSelectionListener(new ListSelectionListener(){
					@Override
					public void valueChanged(ListSelectionEvent e) {
						archivedCoursesJList.clearSelection();	
					}
				});
				archivedCoursesJList.addListSelectionListener(new ListSelectionListener(){
					@Override
					public void valueChanged(ListSelectionEvent e) {
						currentCoursesJList.clearSelection();	
					}
				});			
				try
					{
						Person now = getUser();	
						ArrayList<Courses> currentCourses = ExClient.getPersonCoursesCurrent(now);
						currentCoursesJList.setListData(currentCourses.toArray());
					}
				
				catch (Exception e)
					{
					
					}	
				
				try
					{
						Person now = getUser();
						
						ArrayList<Courses> archivedCourses = ExClient.getPersonCoursesArchived(now);
 						archivedCoursesJList.setListData(archivedCourses.toArray());
					}
				
				catch (Exception e)
					{
					
					}	
					
				errorJLabel = new JLabel();
      		errorJLabel.setBounds( 350, 250, 100, 20 );
      		errorJLabel.setText("Error" );			
				contentPane.add(errorJLabel );
				errorTextArea = new TextArea();  	
				errorTextArea.setBounds( 460, 250, 200, 150 );
      		contentPane.add(errorTextArea );
				
				if(create == true)
					{
						courseNameJLabel = new JLabel();
      				courseNameJLabel.setBounds( 350, 65, 90, 20 );
      				courseNameJLabel.setText( "Course name" );			
						contentPane.add( courseNameJLabel );
						courseNameJTextField = new JTextField();
      				courseNameJTextField.setBounds( 450, 65, 115, 20 );
      				contentPane.add(courseNameJTextField );
						
						
						createJButton = new JButton();
      				createJButton.setBounds( 600, 65, 90, 20 );
     					createJButton.setText( "Create" );
      				contentPane.add( createJButton );
      				createJButton.addActionListener(

        				new ActionListener() 
         				{
            	
            				public void actionPerformed( ActionEvent event )
            					{
               					createJButtonActionPerformed( event );
            					}

         				} 

      				); 
					}
					
				selectJButton = new JButton();
      		selectJButton.setBounds( 350, 95, 90, 20 );
     			selectJButton.setText( "Select" );
      		contentPane.add( selectJButton );
      		selectJButton.addActionListener(

        		new ActionListener() 
         		{
            	
            		public void actionPerformed( ActionEvent event )
            			{
               			selectJButtonActionPerformed( event );
            			}

         		} 

      		); 
				
				passwordJLabel = new JLabel();
      		passwordJLabel.setBounds( 350, 130, 90, 20 );
      		passwordJLabel.setText( "Password" );			
				contentPane.add( passwordJLabel );
				passwordJTextField = new JTextField();
      		passwordJTextField.setBounds( 450, 130, 115, 20 );
      		contentPane.add(passwordJTextField );
				
				passwordChangeJButton = new JButton();
      		passwordChangeJButton.setBounds( 350, 160, 140, 20 );
     			passwordChangeJButton.setText( "Change password" );
      		contentPane.add( passwordChangeJButton );
      		passwordChangeJButton.addActionListener(

        		new ActionListener() 
         		{
            	
            		public void actionPerformed( ActionEvent event )
            			{
               			passwordChangeJButtonActionPerformed( event );
            			}

         		} 

      		);
				
				logoutJButton = new JButton();
      		logoutJButton.setBounds(0, 0, 90, 20 );
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
				setTitle( "Course creation or selection" ); 
      		setSize( 750, 500 );     
      		setVisible(true);    
			
			}
		
		@SuppressWarnings("static-access")
		private void createJButtonActionPerformed( ActionEvent event )
   		{
				String name = courseNameJTextField.getText();
				
				if(name.equals("") == true)
					{
						errorTextArea.setText("No course name entered");	
					}
			
				if(name.equals("") == false)
					{
						boolean createCourse = false;
						
						try
							{
								createCourse = ExClient.uniqueCourseName(name);
								
							}
							
						catch (Exception e)
							{
							
							}
									
						if(createCourse == true)
							{
								setVisible(false);
								Person user = getUser();
								//Roll list = null;
								//scaleInterface create = new scaleInterface(user,list,name);
								
								try
								{
									NewCourse create = new NewCourse();
									GradingScheme scheme = new GradingScheme(name + "Scheme", user);
									scheme.setgradingSchemeCategory("Project","Standard",50,false,"",false,0,0);
									scheme.setgradingSchemeCategory("Test","Standard",50,false,"",false,0,0);
									GradingScale scale = new GradingScale(name + "Scale", user);
									scale.setGradingScale("Pass",50);
									scale.setGradingScale("Fail",0);
									Courses course = new Courses(name,user);
									create.setScheme(scheme);
									create.setScale(scale);
									create.setCourse(course);
									ExClient.addCourse(create);
									setVisible(false);
									new selectOrCreateCourseInterface(true,user);
								}
							
								catch(Exception e)
								{
									
								}
							}
						
						if(createCourse == false)
							{
								errorTextArea.setText("Course name entered is not unique"); 
							}
					}
			}
	
		public void selectJButtonActionPerformed( ActionEvent event)
			{
				Person user = getUser();
				select(user);	 
			}
	
		public void passwordChangeJButtonActionPerformed( ActionEvent event)
			{
				String password = passwordJTextField.getText();
			
				if(password.equals("") == true)
					{
						errorTextArea.setText("No password entered");
					}
			
				if(password.equals("") == false)
					{
						Person user = getUser();
						user.setPassword(password);
						
						try
							{
								ExClient.updatePerson(user);
							}
						
						catch (Exception e)
							{
							
							}
					}
			}
			
		public void logoutJButtonActionPerformed( ActionEvent event)
			{
				setVisible(false);
				logout();
			}	
		
		
		public Person getUser()
			{
				return transition;
			}
		
		public void logout()
			{
				setVisible(false);
				loginInterface logout = new loginInterface();
			}
					
		public void select (Person user)
			{	
				Object[] current = currentCoursesJList.getSelectedValues();
				Object[] archive = archivedCoursesJList.getSelectedValues();  
				
				//String possibleTitle = currentCoursesTextArea.getSelectedText();
				//String possibleTitle2 = archivedCoursesTextArea.getSelectedText();
									
				if(user.isInstructor() == false)
					{
						
						if((current.length == 1) && (archive.length == 0))
							{
								Courses next = (Courses)current[0];
								Person student = getUser();
								ArrayList<AssignmentGrade> grades = null;
								setVisible(false);
								reportInterface view = new reportInterface(next,student,grades);
							}
						
						if((current.length == 0) && (archive.length == 1))
							{						
								Courses next = (Courses)archive[0];
								Person student = getUser();
								ArrayList<AssignmentGrade> grades = null;
								setVisible(false);
								reportInterface view = new reportInterface(next,student,grades);
							}
							
						
						else
							{
								errorTextArea.setText("No course selected or multiple courses selected");
							}
								
					}
				
				if(user.isInstructor() == true)
					{
						if((current.length == 1) && (archive.length == 0))
							{
								Courses next = (Courses)current[0];
								Person instructor = getUser();
								setVisible(false);
								courseOptionsInterface view = new courseOptionsInterface(next,instructor);
							}
						
						if((current.length == 0) && (archive.length == 1))
							{
								Courses next = (Courses)archive[0];
								Person instructor = getUser();
								setVisible(false);
								courseOptionsInterface view = new courseOptionsInterface(next,instructor);
							}
						
						else
							{
								errorTextArea.setText("No course selected or multiple courses selected");
							}
						
					}
				}
			}