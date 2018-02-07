import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.util.*;

public class scaleInterface extends JFrame
{
	private JButton homeJButton;
	private JButton logoutJButton;
	private JButton courseHomeJButton;
	private JButton createJButton;
	private JButton viewJButton;
	private JButton selectJButton;
	private JButton selectCreateJButton;
	private JButton modifyJButton;
	private JButton viewScaleJButton;

	private JLabel currentScaleJLabel;
	private JTextArea currentScaleJText;
	
	private JTextArea viewTextArea;
	private JLabel viewJLabel;

	private JTextArea noticeTextArea;
	private JLabel noticeJLabel;

	private JLabel listJLabel;

	private JList scaleJList;

	private Person instructor;
	private Courses currentCourse;
	private Roll roster;
	private String name;
	private GradingScale[] scales;

	private ArrayList<GradingScale> scaleList = new ArrayList<GradingScale>();
	private GradingScale currentScale =new GradingScale("",null);

	//for use when creating a new course
	public scaleInterface(Person user, Roll list, String courseName)
	{
		instructor = user;
		roster = list;
		name = courseName;
		init();
		Container contentPane = getContentPane();
		contentPane.setLayout( null );
				
		selectCreateJButton = new JButton();
		selectCreateJButton.setBounds(50,125,155,25);
		selectCreateJButton.setText("Select scale"); 
		contentPane.add(selectCreateJButton);
		selectCreateJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						selectCreateJButtonActionPerformed( event );
					}
				} 
		);		

		setTitle( "Grading scale " );   		
  
	}

	public scaleInterface(Person user, boolean archive, Courses home)
	{
		scaleInterfaceArchive(user,true,home);
	}
	//for use when viewing an archived course
	public void scaleInterfaceArchive(Person user, boolean archive, Courses home)
	{
		instructor = user;
		currentCourse = home;
		try
		{
			this.scaleList = ExClient.getInstructorsScale(getUser());
			for(GradingScale GS :scaleList)
			{
				if(home.getScaleID()==GS.getScaleID())
				{
					this.currentScale=GS;
				}
			}
		}
		catch(Exception e)
		{
		}
		init();
		
		selectJButton.setEnabled(false);
		createJButton.setEnabled(false);
		modifyJButton.setEnabled(false);
		setTitle( "Archived Grading Scale" );   		 
	}

	public scaleInterface(Person user,Courses home)
	{
		if(home.isArchived())
		{
			scaleInterfaceArchive(user,true,home);
		}
		else
		{
			scaleInterfaceStandard(user, home);
		}
		
	}
	//for viewing a current course
	public void scaleInterfaceStandard(Person user,Courses home)
	{
		instructor = user;
		currentCourse = home;
		try
		{
			this.scaleList = ExClient.getInstructorsScale(getUser());
			for(GradingScale GS :scaleList)
			{
				if(home.getScaleID()==GS.getScaleID())
				{
					this.currentScale=GS;
				}
			}
			if(scaleList.size()==0)
			{
				currentScale=ExClient.getScale(currentCourse);
				scaleList.add(currentScale);
				
			}
		}
		catch(Exception e)
		{
		}
		init();	
		setTitle( "Grading Scale " ); 
	}
	
	private void init()
	{
		Container contentPane = getContentPane();
		contentPane.setLayout( null );
		
		listJLabel = new JLabel();
		listJLabel.setBounds(350, 10, 90, 20 );
		listJLabel.setText("Scale list" );			
		
		viewJLabel = new JLabel();
		viewJLabel.setBounds( 50, 230, 150, 20 );
		viewJLabel.setText("Currently Selected Scale" );			
		
		noticeJLabel = new JLabel();
		noticeJLabel.setBounds(350, 230, 90, 20 );
		noticeJLabel.setText("Notice" );
					
		homeJButton = new JButton();
		homeJButton.setBounds(80, 0 , 95, 25 );
		homeJButton.setText( "Home" );
  	    this.addWindowListener(new exitListener());

		logoutJButton = new JButton();
		logoutJButton.setBounds(0, 0 , 95, 25 );
		logoutJButton.setText( "Logout" );
			
		courseHomeJButton = new JButton();
		courseHomeJButton.setBounds(160, 0 , 125, 25 );
		courseHomeJButton.setText( "Course home" );
			
		viewJButton = new JButton();
		viewJButton.setBounds(50,190,250,25);
		viewJButton.setText("View Selected Scale Details");
		
		selectJButton = new JButton();
		selectJButton.setBounds(50,125,250,25);
		selectJButton.setText("Set Selected To Scale For Course"); 
		
		createJButton = new JButton();
		createJButton.setBounds(50,155,250,25);
		createJButton.setText("Create New Scale");
		
		modifyJButton = new JButton();
		modifyJButton.setBounds(50,90,250,25);
		modifyJButton.setText("Modify Selected Scale");			
				
		viewTextArea = new JTextArea();  	
		viewTextArea.setBounds( 50, 250, 250, 200 );					
		
		noticeTextArea = new JTextArea();  	
		noticeTextArea.setBounds( 350, 250, 250, 200 );
		
		currentScaleJLabel = new JLabel();
		currentScaleJLabel.setText("Current Course Scale: ");
		currentScaleJLabel.setBounds(50,50,150,25);
		
		currentScaleJText = new JTextArea();
		currentScaleJText.setText(currentScale.getScaleName());
		currentScaleJText.setBounds(200, 50, 100, 25);
		currentScaleJText.setEditable(false);		
		
		scaleJList = new JList();  
		scaleJList.setSelectedIndex(0);
		scaleJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
		scaleJList.setVisibleRowCount(5);  
		JScrollPane scaleListScroller = new JScrollPane(scaleJList); 
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
		contentPane.add(currentScaleJLabel);
		contentPane.add(currentScaleJText);
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
		viewJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						viewJButtonActionPerformed();
					}
				} 
		);
		
		scaleJList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				viewSelectedActionPerformed();	
			}
		});
		createJButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent event )
					{
						createJButtonActionPerformed( event );
					}
				} 
		);
		
		if(scaleList.size() != 0)
		{
			if(scaleList != null)
			{
				//scaleList.toArray(scales);
				scaleJList.setListData(scaleList.toArray());
			}
		}
		if(scaleList.size() == 0)
		{
			noticeTextArea.setText("Instructor has no scales to select");
		}
	}

	/*
	 * displayes info about the selected scale in the text pane
	 */
	private void viewSelectedActionPerformed()
	{				
		viewTextArea.setText("");
		GradingScale current = (GradingScale) scaleJList.getSelectedValue();
		
		for(int i = 0; i < current.getGradingScale().size(); i++)
		{	
			Grade display = (Grade)current.getGradingScale().get(i);
			viewTextArea.append(display.toString() + '\n');	
		}	
	}

	private void createJButtonActionPerformed(ActionEvent event)
	{
		setVisible(false);
		GradingScale newScale = new GradingScale(currentCourse.getCourseName()+" Scale",instructor);
		ModifyScaleInterface modify = new ModifyScaleInterface(this,newScale,currentCourse,instructor,false);
	}

	private void homeJButtonActionPerformed( ActionEvent event )
	{
		setVisible(false);
		Person user = getUser();
		home(user);
	}

	private void logoutJButtonActionPerformed( ActionEvent event)
	{
		logout();
	}

	/*
	 * displayes the modify scale interface
	 */
	private void modifyJButtonActionPerformed( ActionEvent event)
	{
		try
		{
			if(!scaleJList.getSelectedValue().equals(null))
			{
				setVisible(false);
				GradingScale unmodified = (GradingScale) scaleJList.getSelectedValue();
				ModifyScaleInterface modify = new ModifyScaleInterface(this,unmodified,this.currentCourse,this.getUser(),false);
			}
			else
			{
				noticeTextArea.setText("Instructor has no scale to select");
			}
		}
		catch(Exception e)
		{

		}				
	}

	private void courseHomeJButtonActionPerformed( ActionEvent event)
	{
		Person user = getUser();
		Courses current = getCourse();
		setVisible(false);
		new courseOptionsInterface(current,user);
	}

	private void selectJButtonActionPerformed( ActionEvent event )
	{
		GradingScale selScale = (GradingScale) scaleJList.getSelectedValue();
		if(selScale.equals(null))
		{
			//TODO print error
		}
		else
		{
			try
			{
				int resp =JOptionPane.showConfirmDialog(
        			    null,
        			    "Selecting a new Scale for the Course will delete the old scale.\n"+
        			    "Are you sure you want to continue?",
        			    "promptbox",
        			    JOptionPane.YES_NO_OPTION);
				if(resp==0)
				{
					currentCourse = ExClient.setScale(selScale, currentCourse);
					currentScaleJText.setText(selScale.getScaleName());
					scaleList = ExClient.getInstructorsScale(getUser());
					currentScale =selScale;
					scaleJList.setListData(scaleList.toArray());
				}
			}
			catch(Exception e)
			{
			}		
		}
	}

	private void selectCreateJButtonActionPerformed( ActionEvent event)
	{
		Object [] view = scaleJList.getSelectedValues();  
		if(view.length == 1)
		{
			setVisible(false);
			GradingScale current = (GradingScale)view[0];		
			Person user = getUser();
			Roll list = getRoll(); 
			String course = getName();
			new schemeInterface(user,current,list,course);
		}
	}

	/*
	 * displayes the View Scale Interface
	 */
	private void viewJButtonActionPerformed()
	{
		try
		{
			if(!scaleJList.getSelectedValue().equals(null))
			{
				setVisible(false);
				GradingScale unmodified = (GradingScale) scaleJList.getSelectedValue();
				ModifyScaleInterface modify = new ModifyScaleInterface(this,unmodified,this.currentCourse,this.getUser(),true);
			}
			else
			{
				noticeTextArea.setText("Instructor has no scale to select");
			}
		}
		catch(Exception e)
		{

		}
	}

	public Person getUser()
	{
		return instructor;
	}

	public Courses getCourse()
	{
		return currentCourse;
	}

	public Roll getRoll()
	{
		return roster;
	}

	public String courseName()
	{
		return name;	
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