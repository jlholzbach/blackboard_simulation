import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.util.*;

public class adminInterface extends JFrame
{	
	private JButton changePasswordJButton;
	private JButton createJButton;
	private JButton logoutJButton;	
	private JButton removeJButton;
	private JButton selectJButton;
	private JButton usersJButton;

	private JLabel passwordJLabel;
	private JTextField passwordJTextField;
	private JLabel courseNameJLabel;
	private JTextField courseNameJTextField;
	private JLabel rollJLabel;
	

	
	private JLabel errorJLabel;
	private TextArea errorTextArea;

	
	private JList rollJList;

	Person transition;

	public adminInterface(Person user)
	{
  	    this.addWindowListener(new exitListener());

		transition = user;

		Container contentPane = getContentPane();
		contentPane.setLayout( null );

		logoutJButton = new JButton();
		logoutJButton.setBounds(0, 0 , 95, 25 );
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

		if(user.isInstructor() == false)
		{
			setSize(800,600);
		}

	

		passwordJLabel = new JLabel();
		passwordJLabel.setBounds( 80, 75, 90, 20 );
		passwordJLabel.setText("Password" );			
		contentPane.add(passwordJLabel );
		passwordJTextField = new JTextField();
		passwordJTextField.setBounds(150, 75, 115, 20 );
		contentPane.add(passwordJTextField );

		changePasswordJButton = new JButton();
		changePasswordJButton.setBounds(300, 75 , 200, 25 );
		changePasswordJButton.setText( "Change password" );
		contentPane.add( changePasswordJButton );
		changePasswordJButton.addActionListener(

				new ActionListener() 
				{

					public void actionPerformed( ActionEvent event )
					{
						changePasswordJButtonActionPerformed( event );
					}

				} 
		);

		selectJButton = new JButton();
		selectJButton.setBounds(300, 100 , 200, 25 );
		selectJButton.setText( "Select course" );
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

		removeJButton = new JButton();
		removeJButton.setBounds(300, 125 , 200, 25 );
		removeJButton.setText( "Remove course" );
		contentPane.add( removeJButton );
		removeJButton.addActionListener(

				new ActionListener() 
				{

					public void actionPerformed( ActionEvent event )
					{
						removeJButtonActionPerformed( event );
					}

				} 
		);
		rollJLabel = new JLabel();
		rollJLabel.setBounds(560, 50, 120, 20);
		rollJLabel.setText("List of Courses");
		contentPane.add(rollJLabel);
		// rollTextArea = new TextArea();
		// rollTextArea.setBounds( 975, 100, 150, 350 );
		// contentPane.add(rollTextArea );
		ArrayList<Courses> p = null;
		try {
			p = ExClient.getAllCourses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rollJList = new JList(p.toArray());
		rollJList.setSelectedIndex(0);
		rollJList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		rollJList.setVisibleRowCount(5);
		JScrollPane rollListScroller = new JScrollPane(rollJList);
		rollListScroller.setBounds(560, 100, 150, 350);
		contentPane.add(rollListScroller);

		usersJButton = new JButton();
		usersJButton.setBounds(300, 150 , 200, 25 );
		usersJButton.setText( "Users information" );
		contentPane.add( usersJButton );
		usersJButton.addActionListener(

				new ActionListener() 
				{

					public void actionPerformed( ActionEvent event )
					{
						usersJButtonActionPerformed( event );
					}

				} 
		);


		errorJLabel = new JLabel();
		errorJLabel.setBounds( 100, 240, 100, 20 );
		errorJLabel.setText(" Error " );			
		contentPane.add(errorJLabel);
		errorTextArea = new TextArea();  	
		errorTextArea.setBounds( 200, 250, 250, 250 );
		contentPane.add(errorTextArea);


		setTitle("Admin");      
		setVisible(true);    


	}

	private void logoutJButtonActionPerformed( ActionEvent event )
	{
		logout();
	}

	private void createJButtonActionPerformed( ActionEvent event )
	{
		String name = courseNameJTextField.getText();

		if(name.equals("") == true)
		{
			errorTextArea.setText("No course name found");
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
				
				//Roll list = new Roll();
				//scaleInterface create = new scaleInterface(user,list,name);
			}

			else
			{
				errorTextArea.setText("Course name is not unique");
			}
		}
	}


	private void changePasswordJButtonActionPerformed( ActionEvent event )
	{
		String password = passwordJTextField.getText();

		if(password.equals("") == true)
		{
			errorTextArea.setText("No password found");
		}

		if(password.equals("") == false)
		{
			Person user = getUser();
			user.setPassword(password);

			try
			{
				ExClient.updatePerson(user);
				passwordJTextField.setText("");
			}

			catch (Exception e)
			{

			}
		}
	}

	private void selectJButtonActionPerformed( ActionEvent event )
	{
		Person user = getUser();
		select(user);	 
	}

	private void removeJButtonActionPerformed( ActionEvent event )
	{
		String course="",instructor="";
		boolean selected=false;
		if(rollJList.getSelectedValue() != null){
			course=((Courses)rollJList.getSelectedValue()).getCourseName();
			instructor=((Courses)rollJList.getSelectedValue()).getInstructorUserName();
			
			selected=true;
		}
		if(course.equals("") == true)
			if((course.equals("") == true) && (instructor.equals("") == false))
			{
				errorTextArea.setText("No course name found");	
			}

		if((course.equals("") == false) && (instructor.equals("") == true))
		{
			errorTextArea.setText("No instructor found");	
		}


		if((course.equals("") == false) && (instructor.equals("") == false))
		{
			Person instruct = null;
			Courses remove = null;

			try
			{
				instruct = ExClient.getPerson(instructor);
			}

			catch(Exception e)
			{

			}

			if(instruct != null)
			{		
				try
				{
					if(selected){
						remove=(Courses) rollJList.getSelectedValue();
					}else{
					remove = ExClient.checkAndGetCourse(course,instruct);
					}
					
				}

				catch (Exception e)
				{

				}

				if(remove != null)
				{
					try
					{
						
						ExClient.removeCourse(remove);		
						
							rollJList.setListData(ExClient.getAllCourses().toArray());
						
					}

					catch (Exception e)
					{
						System.err.println(e.getMessage());
					}
				}


				if(remove == null)
				{

					errorTextArea.setText("Cannot not remove not a valid course");		
				
				}
			}

			if(instruct == null)
			{
				errorTextArea.setText("Instructor " + instructor + " is not in the database");
				
			}
		}

		if((course.equals("") == true) && (instructor.equals("") == true))
		{
			errorTextArea.setText("No instructor name found and no course name found");	
			
		}
		

	}

	private void usersJButtonActionPerformed( ActionEvent event )
	{
		Person user = getUser();
		info(user);
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

	public void info(Person user)
	{
		setVisible(false);
		try
		{
				infoInterface info = new infoInterface(user);
				
		}
	
		catch(Exception e)
		{
			
			
		}
	}
	
	public void select (Person user)
	{						
		String course="",instructor="";
		if(rollJList.getSelectedValue() != null){
			course=((Courses)rollJList.getSelectedValue()).getCourseName();
			instructor=((Courses)rollJList.getSelectedValue()).getInstructorUserName();
			
		}
		

		
			Person instruct = null;
			Courses select = null;

			try
			{
				instruct = ExClient.getPerson(instructor);
			}

			catch(Exception e)
			{

			}

			if(instruct != null)
			{		
				try
				{
					select = ExClient.checkAndGetCourse(course,instruct);						
				}

				catch (Exception e)
				{

				}

				if(select != null)
				{
					try
					{
						setVisible(false);
						courseOptionsInterface display = new courseOptionsInterface(select,user);		
					}

					catch (Exception e)
					{

					}
				}


				if(select == null)
				{

					errorTextArea.setText("Cannot not select " + course + " is not a valid course");		
					
				}
			}

			if(instruct == null)
			{
				errorTextArea.setText("Instructor " + instructor + " is not in the database");
				
			}

		

		if((course.equals("") == true) && (instructor.equals("") == true)  && (user.isInstructor() == false)) 
		{

			errorTextArea.setText("No course name and instructor username found");
		
		}

		if((course.equals("") == true) && (instructor.equals("") == false)  && (user.isInstructor() == false)) 
		{
			errorTextArea.setText("No course name found");
			
		}

		if((course.equals("") == false) && (instructor.equals("") == true)  && (user.isInstructor() == false)) 
		{
			errorTextArea.setText("No instructor username found");
		
		}

		
		

	}
}