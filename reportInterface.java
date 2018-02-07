import java.awt.*;
import java.awt.event.*;
import java.text.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class reportInterface extends JFrame
	{
	
		Person transition;
		Courses view;
		
		private JLabel reportJLabel;private TextArea reportTextArea;
		private JLabel studentsJLabel;
		//private TextArea studentsTextArea;
		
		private JLabel filenameJLabel;private JTextField filenameJTextField;		
		private JButton viewStudentJButton,printStudentJButton, viewGradeJButton,printGradeJButton,logoutJButton,homeJButton,courseHomeJButton;	
		private JList rollJList;									
		courseOptionsInterface next;
		//TODO interface 1
		public reportInterface(Courses previous, Person user, ArrayList<AssignmentGrade> grades)
			{
      	    this.addWindowListener(new exitListener());

			transition = user;
			view = previous;
			try {
				theRoll=(ExClient.getRoll(previous));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
			Container contentPane = getContentPane();
			contentPane.setLayout( null );
			
			reportJLabel = new JLabel();
			reportJLabel.setBounds(	170, 25, 100, 20 );
			reportJLabel.setText("Grade reports");			
			contentPane.add(reportJLabel );
			reportTextArea = new TextArea();  	
			reportTextArea.setBounds( 50, 50, 500, 500 );
			contentPane.add(reportTextArea );
				
			try
			{
				ArrayList<Person> student = new ArrayList<Person>();
				student.add(user);
				String display=ExClient.getStudentReport(view, student);
				//String display = ExClient.getStudentReport(previous,student);
				reportTextArea.setText(display);
			}	
			catch(Exception e)
			{}
				
			logoutJButton = new JButton();
      		logoutJButton.setBounds(0, 0 , 95, 25 );
      		logoutJButton.setText("Logout");
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
      		filenameJLabel = new JLabel();
      		filenameJLabel.setBounds(600, 430, 90, 20 );
      		filenameJLabel.setText("File name" );			
      		contentPane.add(filenameJLabel );
      		filenameJTextField = new JTextField();
      		filenameJTextField.setBounds(700, 430, 115, 20 );
      		contentPane.add(filenameJTextField );
				
      		homeJButton = new JButton();
      		homeJButton.setBounds(80, 0 , 95, 25 );
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
			
      		printStudentJButton = new JButton();
      		printStudentJButton.setBounds(820, 430 ,150, 25 );
      		printStudentJButton.setText( "Print student report" );
      		contentPane.add( printStudentJButton );
      		printStudentJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								printStudentJButtonActionPerformed2( event );
							}

					} 
				);
      		setTitle(" Grade reports "); 
      		setSize( 1000, 600 );     
      		setVisible(true);  
			
			}
		//TODO interface 2
		Roll theRoll=null;
		public reportInterface(Courses previous, Person user)
		{
      	    this.addWindowListener(new exitListener());

			
			transition = user;
			view = previous;
			try {
				theRoll=(ExClient.getRoll(previous));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Container contentPane = getContentPane();
			contentPane.setLayout( null );
			
			reportJLabel = new JLabel();
      		reportJLabel.setBounds(	170, 25, 100, 20 );
      		reportJLabel.setText("Grade reports");			
      		contentPane.add(reportJLabel );
      		reportTextArea = new TextArea();  	
      		reportTextArea.setBounds( 50, 50, 500, 600 );
      		contentPane.add(reportTextArea );
				
      		studentsJLabel = new JLabel();
      		studentsJLabel.setBounds( 700, 75, 100, 20 );
      		studentsJLabel.setText("List of students");			
      		contentPane.add(studentsJLabel );
      		//studentsTextArea = new TextArea();  	
      		//studentsTextArea.setBounds( 700, 100, 200, 300 );
      		//contentPane.add(studentsTextArea );
				
      		rollJList = new JList();  
      		rollJList.setSelectedIndex(0);
      		rollJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
      		rollJList.setVisibleRowCount(5);  
      		JScrollPane rollListScroller = new JScrollPane(rollJList); 
      		rollListScroller.setBounds(700,100,200,300); 
      		contentPane.add(rollListScroller); 
      		try{
      			rollJList.setListData((ExClient.getRoll(previous)).roll.toArray());
      		}catch(Exception e){
      			
      		}
				//
      		filenameJLabel = new JLabel();
      		filenameJLabel.setBounds(600, 430, 90, 20 );
      		filenameJLabel.setText("File name" );			
      		contentPane.add(filenameJLabel );
      		filenameJTextField = new JTextField();
      		filenameJTextField.setBounds(700, 430, 115, 20 );
      		contentPane.add(filenameJTextField );
				
      		logoutJButton = new JButton();
      		logoutJButton.setBounds(0, 0 , 95, 25 );
      		logoutJButton.setText("Logout");
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
				
      		homeJButton = new JButton();
      		homeJButton.setBounds(80, 0 , 95, 25 );
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
				
      		courseHomeJButton = new JButton();
      		courseHomeJButton.setBounds(160, 0 ,115, 25 );
      		courseHomeJButton.setText( "Course home" );
      		contentPane.add( courseHomeJButton );
      		courseHomeJButton.addActionListener(
      				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
						{
							courseHomeJButtonActionPerformed( event );
						}
					} 
      		);
				
      		printStudentJButton = new JButton();
      		printStudentJButton.setBounds(820, 430 ,150, 25 );
      		printStudentJButton.setText( "Print student report" );
      		contentPane.add( printStudentJButton );
      		printStudentJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								printStudentJButtonActionPerformed( event );
							}

					} 
				);
				
      		viewStudentJButton = new JButton();
      		viewStudentJButton.setBounds(700, 400 ,150, 25 );
      		viewStudentJButton.setText( "View student report" );
      		contentPane.add( viewStudentJButton );
      		viewStudentJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								viewStudentJButtonActionPerformed( event );
							}

					} 
				);
				
      		printGradeJButton = new JButton();
      		printGradeJButton.setBounds(1000, 430 ,150, 25 );
      		printGradeJButton.setText( "Print grade report" );
      		contentPane.add( printGradeJButton );
      		printGradeJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								printGradeJButtonActionPerformed( event );
							}

					} 
				);
				
      		viewGradeJButton = new JButton();
      		viewGradeJButton.setBounds(900, 400 ,150, 25 );
      		viewGradeJButton.setText( "View grade report" );
      		contentPane.add( viewGradeJButton );
      		viewGradeJButton.addActionListener(
				
				new ActionListener() 
					{
            	
						public void actionPerformed( ActionEvent event )
							{
								viewGradeJButtonActionPerformed( event );
							}

					} 
				);
				
				setTitle(" Grade reports "); 
      		setSize( 1150, 800 );     
      		setVisible(true);  
			
			}
	
		private void courseHomeJButtonActionPerformed( ActionEvent event)
			{
				setVisible(false);
				Person user = getUser();
				Courses prev = getCourse();
				next = new courseOptionsInterface(prev,user);		
			}
	
		private void homeJButtonActionPerformed( ActionEvent event )
   		{
				setVisible(false);
				Person user = getUser();
				home(user);
			}
		
		private void printStudentJButtonActionPerformed( ActionEvent event)
			{
			String fileName = filenameJTextField.getText();
			
			if(fileName.equals("") == false)
				{
					try
						{
							FileWriter reportFile  = new FileWriter(fileName);
							BufferedWriter report = new BufferedWriter(reportFile);
					
							int[] students = rollJList.getSelectedIndices();  
							String display="";
							if(students.length > 0)
								{
									ArrayList<Person> student1 = new ArrayList<Person>();
									for(int i = 0; i < students.length; i++)
										{
											Person select = theRoll.roll.get(students[i]);
											student1.add(select);
											
										}
									
									Collator sorter = Collator.getInstance();
									Person temp;
									if(student1.size() > 1)
									{
										for(int j = 0; j < student1.size(); j++)
										{
											for(int i = j+1; i < student1.size(); i++)
											{
												if(sorter.compare(student1.get(j).getLastName(), student1.get(i).getLastName()) > 0)
												{
													temp = student1.get(j);
													student1.set(j,  student1.get(i));
													student1.set(i, temp);
												}
											}
										}
									}
									//Person user = getUser();
									Courses current = getCourse();
									try
									{
										display = ExClient.getStudentReport(current,student1);
										reportTextArea.setText(display);
									}
									catch(Exception e)
									{
									
									}
								}
								
							
							Scanner scan = new Scanner(display);
							
							while(scan.hasNext())
							{
								String displaying = scan.nextLine();

								report.write(displaying);
								report.newLine();
							}
							
							//report.write(display);
				
							report.close();
						}
				
						catch (Exception e)
							{
								reportTextArea.setText("Invalid file name");
							}
				}
				
				
				if(fileName.equals("") == true)
					{
						reportTextArea.setText("No file found to write to");
					}
		}
		//TODO 
		private void printStudentJButtonActionPerformed2( ActionEvent event)
		{
		String fileName = filenameJTextField.getText();
		
		if(fileName.equals("") == false)
			{
				try
					{
						FileWriter reportFile  = new FileWriter(fileName);
						BufferedWriter report = new BufferedWriter(reportFile);
						String display="";
						ArrayList<Person> student = new ArrayList<Person>();
						student.add(transition);
								//Person user = getUser();
								Courses current = getCourse();
								try
								{
									display = ExClient.getStudentReport(current,student);
									reportTextArea.setText(display);
								}
								catch(Exception e)
								{
								
								}
							
							
						
						report.write(display);
			
						report.close();
					}
			
					catch (Exception e)
						{
							reportTextArea.setText("Invalid file name");
						}
			}
			
			
			if(fileName.equals("") == true)
				{
					reportTextArea.setText("No file found to write to");
				}
	}
		private void viewStudentJButtonActionPerformed( ActionEvent event)
			{

				reportTextArea.setText("");
				Object[] students1 = rollJList.getSelectedValues();  
			
				if(students1.length > 0)
					{
						
						ArrayList<Person> student = new ArrayList<Person>();
						for(int i = 0; i < students1.length; i++)
							{
								Person select = (Person)students1[i];
								student.add(select);
							}	
						
						Collator sorter = Collator.getInstance();
						Person temp;
						if(student.size() > 1)
						{
							for(int j = 0; j < student.size(); j++)
							{
								for(int i = j+1; i < student.size(); i++)
								{
									if(sorter.compare(student.get(j).getLastName(), student.get(i).getLastName()) > 0)
									{
										temp = student.get(j);
										student.set(j,  student.get(i));
										student.set(i, temp);
									}
								}
							}
						}
						
						//Person user = getUser();
						Courses current = getCourse();
						
						try
						{
						String display = ExClient.getStudentReport(current,student);
						reportTextArea.setText(display);
						}	
						
						catch(Exception e)
						{
						
						}
					}
			}
		
		private void printGradeJButtonActionPerformed( ActionEvent event)
			{
				String fileName = filenameJTextField.getText();
				
				if(fileName.equals("") == false)
					{
						try
							{
								FileWriter reportFile  = new FileWriter(fileName);
								BufferedWriter report = new BufferedWriter(reportFile);
						
								int[] students = rollJList.getSelectedIndices();  
								String display="";
								if(students.length > 0)
									{
										ArrayList<Person> student1 = new ArrayList<Person>();
										for(int i = 0; i < students.length; i++)
											{
												Person select = theRoll.roll.get(students[i]);
												student1.add(select);
												
											}
										//Person user = getUser();
										Courses current = getCourse();
										try
										{
											display = ExClient.getGradeReport(current,student1);
											reportTextArea.setText(display);
										}
										catch(Exception e)
										{
										
										}
									}
									
								
								Scanner scan = new Scanner(display);
								while (scan.hasNext())
								{
									report.write(scan.nextLine());
									report.newLine();
								}
								//report.write(display);
					
								report.close();
							}
					
							catch (Exception e)
								{
									reportTextArea.setText("Invalid file name");
								}
					}
					
					
					if(fileName.equals("") == true)
						{
							reportTextArea.setText("No file found to write to");
						}
			}
		//TODO
		private void viewGradeJButtonActionPerformed( ActionEvent event)
			{
				int[] students = rollJList.getSelectedIndices();  
				
				if(students.length > 0)
					{
						ArrayList<Person> student1 = new ArrayList<Person>();
						for(int i = 0; i < students.length; i++)
							{
								Person select = theRoll.roll.get(students[i]);
								student1.add(select);
								
							}
						//Person user = getUser();
						Courses current = getCourse();
						try
						{
							String display = ExClient.getGradeReport(current,student1);
							reportTextArea.setText(display);
						}
					
						catch(Exception e)
						{
						
						}
					}
			
				//System.exit(1);
			}	
				
		private void logoutJButtonActionPerformed( ActionEvent event )
   		{
				logout();
			}
		
		public Person getUser()
			{
				return transition;
			}
		
		public Courses getCourse()
			{
				return view;
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
		
		public void logout()
			{
				setVisible(false);
				loginInterface logout = new loginInterface();
			}
	}