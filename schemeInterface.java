
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.util.*;

public class schemeInterface extends JFrame
{
	private JButton homeJButton;
	private JButton logoutJButton;
	private JButton courseHomeJButton;
	
	private JButton createJButton;
	private JButton modifyJButton;
	private JButton viewJButton;
	private JButton selectJButton;
	//private JButton selectCreateJButton;
	//private JButton viewSchemeJButton;
	
	private JTextArea viewTextArea;
	private JLabel viewJLabel;

	private JTextArea noticeTextArea;
	private JLabel noticeJLabel;

	private JTextArea listTextArea;
	private JLabel listJLabel;

	private JLabel currentSchemeJLabel;
	private JTextArea currentSchemeJText;

	private JList schemeJList;
	
	private Person instructor;
	private Courses currentCourse;
	private GradingScheme currentScheme;
	private ArrayList<GradingScheme> schemeList;
	private Roll roster;
	private String name;

	private GradingScheme[] schemes; 

	public schemeInterface(Person user, GradingScale create, Roll list, String courseName)
	{
		
	}

	public schemeInterface(Person user, Courses from)
	{
		instructor = user;
		currentCourse = from;			
		try
		{
			schemeList = ExClient.getInstructorsScheme(user);
			for(GradingScheme GS :schemeList)
			{
				if(currentCourse.getSchemeID()==GS.getSchemeID())
				{
					currentScheme=GS;
				}
			}
			if(schemeList.size()==0)
			{
				currentScheme=ExClient.getScheme(currentCourse);
				schemeList.add(currentScheme);
			}	
		}
		catch(Exception e)
		{

		}
		init();
		if(schemeList.size() != 0)
		{
			schemeJList.setListData(schemeList.toArray());
		}
		if(schemeList.size() == 0)
		{
			noticeTextArea.setText("Instructor has no schemes to select");
		}
		setTitle( "Grading scheme " );   		    
		setVisible(true);
	}

	public schemeInterface(Person user, boolean archive, Courses from)
	{
		instructor = user;
		currentCourse = from;			
		try
		{
			schemeList = ExClient.getInstructorsScheme(user);
			for(GradingScheme GS :schemeList)
			{
				if(currentCourse.getSchemeID()==GS.getSchemeID())
				{
					currentScheme=GS;
				}
			}
			if(schemeList.size()==0)
			{
				currentScheme=ExClient.getScheme(currentCourse);
				schemeList.add(currentScheme);
			}	
		}
		catch(Exception e)
		{

		}
		init();
		createJButton.setEnabled(false);
		modifyJButton.setEnabled(false);
		selectJButton.setEnabled(false);
		if(schemeList.size() != 0)
		{
			schemeJList.setListData(schemeList.toArray());
		}
		if(schemeList.size() == 0)
		{
			noticeTextArea.setText("Instructor has no schemes to select");
		}
		
		
		
		setTitle( "Grading scheme " );   		    
		setVisible(true);
		
		setTitle( "Grading scheme Archived" );   		
		     
		setVisible(true);   


	}

	
	private void init()
	{
		Container contentPane = getContentPane();
		contentPane.setLayout( null );
		
		listJLabel = new JLabel();
		listJLabel.setBounds(350, 10, 90, 20 );
		listJLabel.setText("Scheme list" );			
  	    this.addWindowListener(new exitListener());

		viewJLabel = new JLabel();
		viewJLabel.setBounds( 50, 230, 150, 20 );
		viewJLabel.setText("Currently Selected Scheme" );			
		
		noticeJLabel = new JLabel();
		noticeJLabel.setBounds(350, 230, 90, 20 );
		noticeJLabel.setText("Notice" );
					
		homeJButton = new JButton();
		homeJButton.setBounds(80, 0 , 95, 25 );
		homeJButton.setText( "Home" );
		  			
		logoutJButton = new JButton();
		logoutJButton.setBounds(0, 0 , 95, 25 );
		logoutJButton.setText( "Logout" );
			
		courseHomeJButton = new JButton();
		courseHomeJButton.setBounds(160, 0 , 125, 25 );
		courseHomeJButton.setText( "Course home" );
			
		viewJButton = new JButton();
		viewJButton.setBounds(50,190,250,25);
		viewJButton.setText("View Selected Scheme Details");
		
		selectJButton = new JButton();
		selectJButton.setBounds(50,125,250,25);
		selectJButton.setText("Set Selected To Scheme For Course"); 
		
		createJButton = new JButton();
		createJButton.setBounds(50,155,250,25);
		createJButton.setText("Create New Scheme");
		
		modifyJButton = new JButton();
		modifyJButton.setBounds(50,90,250,25);
		modifyJButton.setText("Modify Selected Scheme");			
				
		viewTextArea = new JTextArea();  	
		viewTextArea.setBounds( 50, 250, 250, 200 );					
		
		noticeTextArea = new JTextArea();  	
		noticeTextArea.setBounds( 350, 250, 250, 200 );
		
		currentSchemeJLabel = new JLabel();
		currentSchemeJLabel.setText("Current Course Scheme: ");
		currentSchemeJLabel.setBounds(50,50,150,25);
		
		currentSchemeJText = new JTextArea();
		currentSchemeJText.setText(currentScheme.getSchemeName());
		currentSchemeJText.setBounds(200, 50, 100, 25);
		currentSchemeJText.setEditable(false);		
		
		schemeJList = new JList();  
		schemeJList.setSelectedIndex(0);
		schemeJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
		schemeJList.setVisibleRowCount(5);  
		JScrollPane scaleListScroller = new JScrollPane(schemeJList); 
		scaleListScroller.setBounds(350,30,250,200); 
		
		contentPane.add(viewJLabel);
		contentPane.add(listJLabel);
		contentPane.add(scaleListScroller); 
		contentPane.add(noticeTextArea );
		contentPane.add(viewTextArea );
		contentPane.add(noticeJLabel);
		contentPane.add(selectJButton);
		//contentPane.add( viewScaleJButton );
		contentPane.add( courseHomeJButton );
		contentPane.add( logoutJButton );
		contentPane.add( homeJButton );
		contentPane.add(viewJButton);
		contentPane.add(currentSchemeJLabel);
		contentPane.add(currentSchemeJText);
		contentPane.add(createJButton);
		contentPane.add(modifyJButton);
		
		  		
		setSize( 700, 600 );     
		setVisible(true); 
		
		modifyJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						modifyJButtonActionPerformed( event );
					}
				} 
		);
		selectJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						selectJButtonActionPerformed( event );
					}
				} 
		);
		viewJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						viewJButtonActionPerformed();
					}
				} 
		);
		createJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						createJButtonActionPerformed( event );
					}
				} 
		);
		
		schemeJList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				viewSelectedActionPerformed();	
			}
		});
			
		courseHomeJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						courseHomeJButtonActionPerformed( event );
					}
				} 
		);
		logoutJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						logoutJButtonActionPerformed( event );
					}
				} 
		);
		homeJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						homeJButtonActionPerformed( event );
					}
				} 
		);	
	}

	private void createJButtonActionPerformed(ActionEvent event)
	{
		try
		{
			setVisible(false);
			GradingScheme unmodified = new GradingScheme(currentCourse.getCourseName()+"New Scheme",instructor);
			/*ModifySchemeInterface modify =*/ new ModifySchemeInterface(this,unmodified,this.currentCourse,instructor,false);
		}
		catch(Exception e)
		{
		}
	}

	private void modifyJButtonActionPerformed(ActionEvent event)
	{
		try
		{
			if(!schemeJList.getSelectedValue().equals(null))
			{
				setVisible(false);
				GradingScheme unmodified = (GradingScheme) schemeJList.getSelectedValue();
				/*ModifySchemeInterface modify =*/ new ModifySchemeInterface(this,unmodified,this.currentCourse,instructor,false);
			}
			else
			{
				noticeTextArea.setText("Instructor has no schemes to select");
			}
		}
		catch(Exception e)
		{
		}				
	}

	private void selectJButtonActionPerformed( ActionEvent event )
	{
		GradingScheme selScheme = (GradingScheme) schemeJList.getSelectedValue();
		if(selScheme.equals(null))
		{
			//TODO print error
		}
		else
		{
			try
			{
				int resp =JOptionPane.showConfirmDialog(
        			    null,
        			    "Selecting a new Scheme for the Course will remove all old assignments.\n"+
        			    "Are you sure you want to continue?",
        			    "promptbox",
        			    JOptionPane.YES_NO_OPTION);
				if(resp==0)
				{
					currentCourse.setSchemeID(selScheme);// = ExClient.setScale(selScale, currentCourse);
					ExClient.setScheme(selScheme, currentCourse);
					currentSchemeJText.setText(selScheme.getSchemeName());
					currentScheme =selScheme;
				}
			}
			catch(Exception e)
			{
			}		
		}
	}

	private void viewJButtonActionPerformed()
	{
		try
		{
			if(!schemeJList.getSelectedValue().equals(null))
			{
				setVisible(false);
				GradingScheme unmodified = (GradingScheme) schemeJList.getSelectedValue();
				ModifySchemeInterface modify = new ModifySchemeInterface(this,unmodified,this.currentCourse,instructor,true);
			}
			else
			{
				noticeTextArea.setText("Instructor has no schemes to select");
			}
		}
		catch(Exception e)
		{
		}
	}
	
	private void viewSelectedActionPerformed()
	{
		GradingScheme GS = (GradingScheme) schemeJList.getSelectedValue();
		viewTextArea.setText("");
		for(Category c :GS.getGradingscheme())
		{
			viewTextArea.append(c.getCategoryName()+"\n");
		}	
	}
	private void homeJButtonActionPerformed( ActionEvent event )
	{
		setVisible(false);
		home(instructor);
	}
	private void logoutJButtonActionPerformed( ActionEvent event)
	{
		logout();
	}
	private void courseHomeJButtonActionPerformed( ActionEvent event)
	{
		setVisible(false);
		courseOptionsInterface view = new courseOptionsInterface(currentCourse,instructor);
	}

	public void home(Person user)
	{
		setVisible(false);
		if(user.isAdmin() == true)
		{
			new adminInterface(user);
		}

		if((user.isInstructor() == true)  && (user.isAdmin() == false))
		{
			new selectOrCreateCourseInterface(true,user);
		}

		if((user.isInstructor() == false) && (user.isAdmin() == false))
		{

			new selectOrCreateCourseInterface(false,user);
		}
	}

	public void logout()
	{
		setVisible(false);
		new loginInterface();
	}	
}