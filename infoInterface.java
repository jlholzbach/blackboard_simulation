import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class infoInterface extends JFrame {
	private JLabel nameJLabel;
	private JLabel usernameJLabel;
	private JLabel passwordJLabel;
	private JLabel typeJLabel;
	private JLabel typeInfoJLabel;
	private JLabel unmodifiedInfoJLabel;
	private JLabel unmodifiedNameJLabel;
	private JLabel unmodifiedTypeJLabel;
	private JLabel addRemoveJLabel;
	private JLabel modifiedInfoJLabel;
	private JLabel modifiedNameJLabel;
	private JLabel modifiedTypeJLabel;
	private JLabel searchByUsernameJLabel;
	private JLabel fileNameJLabel;
	private JLabel errorJLabel;
	private JLabel rollJLabel;
	

	private JTextField nameJTextField;
	private JTextField usernameJTextField;
	private JTextField passwordJTextField;
	//private JTextField typeJTextField;
	private JTextField unmodifiedNameJTextField;
	private JTextField unmodifiedTypeJTextField;
	private JTextField modifiedNameJTextField;
	private JTextField modifiedTypeJTextField;
	private JTextField searchJTextField;
	private JTextField fileNameJTextField;

	private JButton logoutJButton;
	private JButton homeJButton;
	private JButton courseHomeJButton;
	private JButton searchJButton;
	private JButton addDatabaseJButton;
	// private JButton addCourseJButton;
	private JButton addRollJButton;
	private JButton modifyJButton;
	private JButton modifyRollJButton;
	private JButton removeDatabaseJButton;
	private JButton removeCourseJButton;
	private JButton scanFileJButton;
	// private JButton scanRollJButton;
	private JButton updateRollJButton;
	private JButton selectJButton;

	private TextArea typeInfoTextArea;
	private TextArea errorTextArea;
	// private TextArea rollTextArea;

	private JList rollJList;
	private JList typeJList;
	private JList studentJList;

	Person transition;
	Courses add;
	Roll list;

	// String prev;

	public infoInterface(Person user) throws IOException, ClassNotFoundException {
		transition = user;

		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		homeJButton = new JButton();
		homeJButton.setBounds(0, 0, 95, 25);
		homeJButton.setText("Home");
		contentPane.add(homeJButton);
		homeJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				homeJButtonActionPerformed(event);
			}

		});

		logoutJButton = new JButton();
		logoutJButton.setBounds(80, 0, 95, 25);
		logoutJButton.setText("Logout");
		contentPane.add(logoutJButton);
		logoutJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				logoutJButtonActionPerformed(event);
			}

		});

		addRemoveJLabel = new JLabel();
		addRemoveJLabel.setBounds(20, 40, 150, 20);
		addRemoveJLabel.setText(" User Info");
		contentPane.add(addRemoveJLabel);

		nameJLabel = new JLabel();
		nameJLabel.setBounds(20, 65, 90, 20);
		nameJLabel.setText(" Name");
		contentPane.add(nameJLabel);
		nameJTextField = new JTextField();
		nameJTextField.setBounds(100, 65, 150, 20);
		contentPane.add(nameJTextField);

		usernameJLabel = new JLabel();
		usernameJLabel.setBounds(20, 125, 90, 20);
		usernameJLabel.setText(" Username");
		contentPane.add(usernameJLabel);
		usernameJTextField = new JTextField();
		usernameJTextField.setBounds(100, 125, 150, 20);
		contentPane.add(usernameJTextField);

		passwordJLabel = new JLabel();
		passwordJLabel.setBounds(20, 175, 90, 20);
		passwordJLabel.setText(" Password");
		contentPane.add(passwordJLabel);
		passwordJTextField = new JTextField();
		passwordJTextField.setBounds(100, 175, 150, 20);
		contentPane.add(passwordJTextField);

		typeJLabel = new JLabel();
		typeJLabel.setBounds(20, 225, 90, 20);
		typeJLabel.setText(" Type");
		contentPane.add(typeJLabel);
		String[] slis={"ADMIN","STUDENT","PROFESSOR"};
		typeJList = new JList(slis);
		typeJList.setSelectedIndex(0);
		typeJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		typeJList.setVisibleRowCount(3);
		JScrollPane typeListScroller = new JScrollPane(typeJList);
		typeListScroller.setBounds(100, 225, 150, 50);
		contentPane.add(typeListScroller);
		///contentPane.add(typeJLabel);
		
		studentJList = new JList(ExClient.getAll().toArray());
		studentJList.setSelectedIndex(0);
		studentJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		studentJList.setVisibleRowCount(7);
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		            int index = studentJList.locationToIndex(e.getPoint());
		            Person p=(Person)studentJList.getSelectedValue();
		            nameJTextField.setText(p.getFullName());
		    		usernameJTextField.setText(p.getUserName());
		    		passwordJTextField.setText(p.getPassword());
		    		Object h="";
		    		if(p.isAdmin()){
		    			h="ADMIN";
		    		}
		    		else if(p.isInstructor()){
		    			h="PROFESSOR";
		    		}
		    		else{
		    			h="STUDENT";
		    		}
		    		
		    		typeJList.setSelectedValue(h, true);
		         }
		    }
		};
		studentJList.addMouseListener(mouseListener);
		
		JScrollPane ListScroller = new JScrollPane(studentJList);
		ListScroller.setBounds(260, 100, 100, 350);
		contentPane.add(ListScroller);

		searchByUsernameJLabel = new JLabel();
		searchByUsernameJLabel.setBounds(300, 20, 150, 20);
		searchByUsernameJLabel.setText(" Search by username");
		searchJTextField = new JTextField();
		searchJTextField.setBounds(450, 20, 150, 20);

		searchJButton = new JButton();
		searchJButton.setBounds(620, 10, 95, 25);
		searchJButton.setText("Search");
		searchJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				searchJButtonActionPerformed(event);
			}

		});

		unmodifiedInfoJLabel = new JLabel();
		unmodifiedInfoJLabel.setBounds(300, 40, 150, 20);
		unmodifiedInfoJLabel.setText(" Unmodified info");

		unmodifiedNameJLabel = new JLabel();
		unmodifiedNameJLabel.setBounds(300, 65, 125, 20);
		unmodifiedNameJLabel.setText(" Unmodified name");
		unmodifiedNameJTextField = new JTextField();
		unmodifiedNameJTextField.setBounds(450, 65, 150, 20);

		unmodifiedTypeJLabel = new JLabel();
		unmodifiedTypeJLabel.setBounds(300, 125, 150, 20);
		unmodifiedTypeJLabel.setText(" Unmodified type");
		unmodifiedTypeJTextField = new JTextField();
		unmodifiedTypeJTextField.setBounds(450, 125, 150, 20);

		modifiedInfoJLabel = new JLabel();
		modifiedInfoJLabel.setBounds(620, 40, 150, 20);
		modifiedInfoJLabel.setText(" Modified info");

		modifiedNameJLabel = new JLabel();
		modifiedNameJLabel.setBounds(620, 65, 125, 20);
		modifiedNameJLabel.setText(" Modified name");
		modifiedNameJTextField = new JTextField();
		modifiedNameJTextField.setBounds(720, 65, 150, 20);

		modifiedTypeJLabel = new JLabel();
		modifiedTypeJLabel.setBounds(620, 125, 150, 20);
		modifiedTypeJLabel.setText(" Modified type");
		modifiedTypeJTextField = new JTextField();
		modifiedTypeJTextField.setBounds(720, 125, 150, 20);

		contentPane.add(unmodifiedTypeJLabel);
		contentPane.add(unmodifiedTypeJTextField);
		//contentPane.add(modifiedTypeJLabel);
		//contentPane.add(modifiedTypeJTextField);

		addDatabaseJButton = new JButton();
		addDatabaseJButton.setBounds(80, 300, 125, 25);
		addDatabaseJButton.setText("Add Person");
		contentPane.add(addDatabaseJButton);
		addDatabaseJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				try {
					addDatabaseJButtonActionPerformed(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		modifyJButton = new JButton();
		modifyJButton.setBounds(720, 10, 125, 25);
		modifyJButton.setText("Modify Person");
		modifyJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				modifyJButtonActionPerformed(event);
			}

		});
		rollJList = new JList(ExClient.getAll().toArray());
		rollJList.setSelectedIndex(0);
		rollJList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		rollJList.setVisibleRowCount(5);
		JScrollPane rollListScroller = new JScrollPane(rollJList);
		rollListScroller.setBounds(975, 100, 150, 350);
		contentPane.add(rollListScroller);

		removeDatabaseJButton = new JButton();
		removeDatabaseJButton.setBounds(845, 10, 125, 25);
		removeDatabaseJButton.setText("Remove Person");
		removeDatabaseJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				removeDatabaseJButtonActionPerformed(event);
			}

		});

		contentPane.add(removeDatabaseJButton);
		contentPane.add(modifyJButton);
		contentPane.add(modifiedNameJTextField);
		contentPane.add(modifiedNameJLabel);
		contentPane.add(modifiedInfoJLabel);
		contentPane.add(unmodifiedNameJTextField);
		contentPane.add(unmodifiedNameJLabel);
		contentPane.add(unmodifiedInfoJLabel);
		contentPane.add(searchJButton);
		contentPane.add(searchJTextField);
		contentPane.add(searchByUsernameJLabel);

		/*
		 * typeInfoTextArea = new TextArea(); typeInfoTextArea.setBounds( 50,
		 * 350, 400, 100 ); contentPane.add(typeInfoTextArea );
		 * typeInfoTextArea.
		 * append("User types: Admin, Admin-Professor, Professor, Student.");
		 * typeInfoTextArea.append('\n' +
		 * "When coming from a course or creating one no matter the type of user "
		 * ); typeInfoTextArea.append('\n' +
		 * "only student type can be selected");
		 */

		errorJLabel = new JLabel();
		errorJLabel.setBounds(475, 250, 70, 20);
		errorJLabel.setText("Error");
		contentPane.add(errorJLabel);
		errorTextArea = new TextArea();
		errorTextArea.setBounds(550, 250, 400, 100);
		contentPane.add(errorTextArea);

		setTitle("User Information");
		setSize(1150, 500);
		setVisible(true);
	}

	public infoInterface(Person user, Courses course) {
		transition = user;
		add = course;

		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		homeJButton = new JButton();
		homeJButton.setBounds(0, 0, 95, 25);
		homeJButton.setText("Home");
		contentPane.add(homeJButton);
		homeJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				homeJButtonActionPerformed(event);
			}

		});
		try {
			studentJList = new JList(ExClient.getAll().toArray());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		studentJList.setSelectedIndex(0);
		studentJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		studentJList.setVisibleRowCount(7);
		JScrollPane ListScroller = new JScrollPane(studentJList);
		ListScroller.setBounds(260, 100, 100, 350);
		contentPane.add(ListScroller);
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		            int index = studentJList.locationToIndex(e.getPoint());
		            Person p=(Person)studentJList.getSelectedValue();
		            nameJTextField.setText(p.getFullName());
		    		usernameJTextField.setText(p.getUserName());
		    		passwordJTextField.setText(p.getPassword());
		    		Object h="";
		    		if(p.isAdmin()){
		    			h="ADMIN";
		    		}
		    		else if(p.isInstructor()){
		    			h="PROFESSOR";
		    		}
		    		else{
		    			h="STUDENT";
		    		}
		    		
		    		typeJList.setSelectedValue(h, true);
		         }
		    }
		};
		studentJList.addMouseListener(mouseListener);

		logoutJButton = new JButton();
		logoutJButton.setBounds(80, 0, 95, 25);
		logoutJButton.setText("Logout");
		contentPane.add(logoutJButton);
		logoutJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				logoutJButtonActionPerformed(event);
			}

		});

		courseHomeJButton = new JButton();
		courseHomeJButton.setBounds(160, 0, 115, 25);
		courseHomeJButton.setText("Course home");
		contentPane.add(courseHomeJButton);
		courseHomeJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				courseHomeJButtonActionPerformed(event);
			}

		});

		addRemoveJLabel = new JLabel();
		addRemoveJLabel.setBounds(20, 40, 150, 20);
		addRemoveJLabel.setText(" User Info");
		contentPane.add(addRemoveJLabel);

		nameJLabel = new JLabel();
		nameJLabel.setBounds(20, 65, 90, 20);
		nameJLabel.setText(" Name");
		contentPane.add(nameJLabel);
		nameJTextField = new JTextField();
		nameJTextField.setBounds(100, 65, 150, 20);
		contentPane.add(nameJTextField);

		usernameJLabel = new JLabel();
		usernameJLabel.setBounds(20, 125, 90, 20);
		usernameJLabel.setText(" Username");
		contentPane.add(usernameJLabel);
		usernameJTextField = new JTextField();
		usernameJTextField.setBounds(100, 125, 150, 20);
		contentPane.add(usernameJTextField);

		passwordJLabel = new JLabel();
		passwordJLabel.setBounds(20, 175, 90, 20);
		passwordJLabel.setText(" Password");
		contentPane.add(passwordJLabel);
		passwordJTextField = new JTextField();
		passwordJTextField.setBounds(100, 175, 150, 20);
		contentPane.add(passwordJTextField);

		typeJLabel = new JLabel();
		typeJLabel.setBounds(20, 225, 90, 20);
		typeJLabel.setText(" Type");
		contentPane.add(typeJLabel);
		String[] slis={"ADMIN","STUDENT","PROFESSOR"};
		typeJList = new JList(slis);
		typeJList.setSelectedIndex(0);
		typeJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		typeJList.setVisibleRowCount(3);
		JScrollPane typeListScroller = new JScrollPane(typeJList);
		typeListScroller.setBounds(100, 225, 150, 50);
		contentPane.add(typeListScroller);
		typeJList.setListData(new String[]{"STUDENT"});


		selectJButton = new JButton();
		selectJButton.setBounds(400, 10, 325, 25);
		selectJButton
				.setText("Select student whose info to view for modification");
		selectJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				selectJButtonActionPerformed(event);
			}

		});

		unmodifiedInfoJLabel = new JLabel();
		unmodifiedInfoJLabel.setBounds(300, 40, 150, 20);
		unmodifiedInfoJLabel.setText(" Unmodified info");

		unmodifiedNameJLabel = new JLabel();
		unmodifiedNameJLabel.setBounds(300, 65, 125, 20);
		unmodifiedNameJLabel.setText(" Unmodified name");
		unmodifiedNameJTextField = new JTextField();
		unmodifiedNameJTextField.setBounds(450, 65, 150, 20);

		/*
		 * unmodifiedTypeJLabel = new JLabel(); unmodifiedTypeJLabel.setBounds(
		 * 300, 125, 150, 20 ); unmodifiedTypeJLabel.setText(" Unmodified type"
		 * ); unmodifiedTypeJTextField = new JTextField();
		 * unmodifiedTypeJTextField.setBounds( 450, 125, 150, 20 );
		 */

		modifiedInfoJLabel = new JLabel();
		modifiedInfoJLabel.setBounds(620, 40, 150, 20);
		modifiedInfoJLabel.setText(" Modified info");

		modifiedNameJLabel = new JLabel();
		modifiedNameJLabel.setBounds(620, 65, 125, 20);
		modifiedNameJLabel.setText(" Modified name");
		modifiedNameJTextField = new JTextField();
		modifiedNameJTextField.setBounds(720, 65, 150, 20);

		/*
		 * modifiedTypeJLabel = new JLabel(); modifiedTypeJLabel.setBounds( 620,
		 * 125, 150, 20 ); modifiedTypeJLabel.setText(" Modified type" );
		 * modifiedTypeJTextField = new JTextField();
		 * modifiedTypeJTextField.setBounds( 720, 125, 150, 20 );
		 */

		addRollJButton = new JButton();
		addRollJButton.setBounds(80, 300, 125, 25);
		addRollJButton.setText("Add Person");
		contentPane.add(addRollJButton);
		addRollJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				addRollJButtonActionPerformed(event);
			}

		});

		modifyRollJButton = new JButton();
		modifyRollJButton.setBounds(720, 10, 125, 25);
		modifyRollJButton.setText("Modify Name");
		modifyRollJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				modifyRollJButtonActionPerformed(event);
			}

		});

		removeCourseJButton = new JButton();
		removeCourseJButton.setBounds(845, 10, 150, 25);
		removeCourseJButton.setText("Remove Student");
		removeCourseJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				try {
					removeCourseJButtonActionPerformed(event);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

		});

		rollJLabel = new JLabel();
		rollJLabel.setBounds(975, 50, 120, 20);
		rollJLabel.setText("List of students");
		contentPane.add(rollJLabel);
		// rollTextArea = new TextArea();
		// rollTextArea.setBounds( 975, 100, 150, 350 );
		// contentPane.add(rollTextArea );
		ArrayList<Person> p = null;
		try {
			p = (ExClient.getRoll(getCourse())).roll;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		rollJList = new JList(p.toArray());
		rollJList.setSelectedIndex(0);
		rollJList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		rollJList.setVisibleRowCount(5);
		JScrollPane rollListScroller = new JScrollPane(rollJList);
		rollListScroller.setBounds(975, 100, 150, 350);
		contentPane.add(rollListScroller);

		fileNameJLabel = new JLabel();
		fileNameJLabel.setBounds(525, 300, 150, 20);
		fileNameJLabel.setText("File name");
		fileNameJTextField = new JTextField();
		fileNameJTextField.setBounds(625, 300, 150, 20);

		scanFileJButton = new JButton();
		scanFileJButton.setBounds(800, 300, 125, 25);
		scanFileJButton.setText("Scan file");
		scanFileJButton.addActionListener(

		new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				try {
					scanFileJButtonActionPerformed(event);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

		});

		contentPane.add(fileNameJLabel);
		contentPane.add(fileNameJTextField);
		contentPane.add(scanFileJButton);
		contentPane.add(removeCourseJButton);
		contentPane.add(modifyRollJButton);
		contentPane.add(modifiedNameJTextField);
		contentPane.add(modifiedNameJLabel);
		contentPane.add(modifiedInfoJLabel);
		contentPane.add(unmodifiedNameJTextField);
		contentPane.add(unmodifiedNameJLabel);
		contentPane.add(unmodifiedInfoJLabel);
		contentPane.add(selectJButton);
		// contentPane.add(searchJTextField);
		// contentPane.add(searchByUsernameJLabel);

		errorJLabel = new JLabel();
		errorJLabel.setBounds(475, 150, 70, 20);
		errorJLabel.setText("Error");
		contentPane.add(errorJLabel);
		errorTextArea = new TextArea();
		errorTextArea.setBounds(550, 150, 400, 100);
		contentPane.add(errorTextArea);

		setTitle("User Information");
		setSize(1150, 500);
		setVisible(true);

	}

	/*
	 * private void createCourseJButtonActionPerformed( ActionEvent event) {
	 * System.exit(1); }
	 */

	private void selectJButtonActionPerformed(ActionEvent event) {
		Object[] student = rollJList.getSelectedValues();

		if (student.length == 0) {
			errorTextArea.setText("No student selected");
		}

		if (student.length > 1) {
			errorTextArea.setText("You selected too many students");
		}

		if (student.length == 1) {
			Person select = (Person) student[0];

			unmodifiedNameJTextField.setText(select.getFullName());
		}

	}

	private void courseHomeJButtonActionPerformed(ActionEvent event) {
		Person user = getUser();
		Courses current = getCourse();
		setVisible(false);

		courseOptionsInterface view = new courseOptionsInterface(current, user);

	}

	private void addDatabaseJButtonActionPerformed(ActionEvent event) throws IOException {
		String name = nameJTextField.getText();
		String username = usernameJTextField.getText();
		String password = passwordJTextField.getText();
		String type = (String)typeJList.getSelectedValue();

		if ((name.equals("") == true) || (username.equals("") == true)
				|| (password.equals("") == true) || (type.equals("") == true)) {
			errorTextArea.setText("Missing info");
		}

		if ((name.equals("") == false) && (username.equals("") == false)
				&& (password.equals("") == false) && (type.equals("") == false)) {
			String lastname="",firstname="";
			Scanner scan=new Scanner(name);
			firstname = scan.next();
			if(scan.hasNext())
			lastname = scan.next();
			boolean professor = false;
			boolean admin = false;

			if (type.equalsIgnoreCase("Admin") == true) {
				professor = false;
				admin = true;
			}

			if (type.equalsIgnoreCase("Professor") == true) {
				professor = true;
				admin = false;
			}

			if (type.equalsIgnoreCase("Admin-Professor") == true) {
				professor = true;
				admin = true;
			}

			if (type.equalsIgnoreCase("Student") == true) {
				professor = false;
				admin = false;
			}

			Person found = ExClient.getPerson(username);

			try {

			}

			catch (Exception e) {

			}

			if (found == null) {

				Person user = new Person(firstname, lastname, username,
						password, professor, admin);
				//TODO

				try {
					Person per=ExClient.addPerson(user);

					
					rollJList.setListData(ExClient.getAll().toArray());

				}

				catch (Exception e) {

				}

			}
		}
		
		nameJTextField.setText("");
		usernameJTextField.setText("");
		passwordJTextField.setText("");
		
		
	}

	private void addRollJButtonActionPerformed(ActionEvent event) {
		String name = nameJTextField.getText();
		String username = usernameJTextField.getText();
		String type = (String)typeJList.getSelectedValue();
		String password = passwordJTextField.getText();

		if ((name.equals("") == true) || (username.equals("") == true)
				|| (type.equals("") == true) || (password.equals("") == true))

		{
			errorTextArea.setText("Missing info");
		}

		if ((name.equals("") == false) && (username.equals("") == false)
				&& (type.equals("") == false) && (password.equals("") == false)) {
			Person found = null;

			try {
				found = ExClient.getPerson(username);
			}

			catch (Exception e) {

			}

			if (found != null) {
				Courses add = getCourse();
				try {
					Roll r=ExClient.getRoll(add);
					r.addPersonToBeAdded(found);
					ExClient.updateRoll(r);
					rollJList.setListData(ExClient.getRoll(add).roll.toArray());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (found == null) {

				Scanner scan = new Scanner(name);
				String firstname="";
				String lastname="";
				if(scan.hasNext())
				firstname = scan.next();
				if(scan.hasNext())
				lastname = scan.next();

				Person user = new Person(firstname, lastname, username,
						password, false, false);
				Courses add = getCourse();
				user.addCourses(add.getcourse());
				// add to roll
				// update roll

				try {
					Person per=ExClient.addPerson(user);

					if (ExClient.getRoll(getCourse()) == null) {
				//		System.out.println("problemm");
					} else {

					}

					Roll r = ExClient.getRoll(getCourse());

					r.addPersonToBeAdded(per);

					ExClient.updateRoll(r);
					//TODO
					rollJList.setListData(r.roll.toArray());
				}

				catch (Exception e) {
			//		System.out.println(e);

				}

			}

			// Check if this person is in database
			// If no add them to database then to roll
			// If yes add them to roll

		}

		nameJTextField.setText("");
		usernameJTextField.setText("");
		passwordJTextField.setText("");
		
	}

	private void homeJButtonActionPerformed(ActionEvent event) {
		setVisible(false);
		Person user = getUser();
		home(user);
	}

	@SuppressWarnings("unused")
	private void modifyJButtonActionPerformed(ActionEvent event) {
		// modify username?
		Person user = getUser();
		String username = searchJTextField.getText();
		String name = modifiedNameJTextField.getText();

		if (user.isAdmin()) {
			String type = "Student";

			if ((username.equals("") == false) && (name.equals("") == false)
					&& (type.equals("") == false)) {
				Person modify = null;

				try {
					modify=ExClient.getPerson(username);
				}

				catch (Exception e) {

				}

				if (modify != null) {
					Scanner scan=new Scanner(name);
					String lastname="",firstname="";
					if(scan.hasNext())
					firstname = scan.next();
					if(scan.hasNext())
					lastname = scan.next();

					modify.setFirstName(firstname);
					modify.setLastName(lastname);

					/*if (type.equalsIgnoreCase("Admin")) {
						modify.setAdmin(true);
						modify.setProfessor(false);
					}

					if (type.equalsIgnoreCase("Admin-Professor")) {
						modify.setAdmin(true);
						modify.setProfessor(true);
					}

					if (type.equalsIgnoreCase("Professor")) {
						modify.setAdmin(false);
						modify.setProfessor(true);
					}

					if (type.equalsIgnoreCase("Student")) {
						modify.setAdmin(false);
						modify.setAdmin(false);
					}*/

					try {
						ExClient.updatePerson(modify);
						//System.out.println(modify.getFullName());
						rollJList.setListData(ExClient.getAll().toArray());
					}

					catch (Exception e) {

					}
				}
			}

			if ((username.equals("") == false) && (name.equals("") == true))
					/*&& (type.equals("") == false)) {
					 */
			{
					errorTextArea.setText("No name found");
			}

			/*if ((username.equals("") == false) && (name.equals("") == false)
					&& (type.equals("") == true)) {
				errorTextArea.setText("No type found");
			}*/

			if ((username.equals("") == true) && (name.equals("") == false))
					/*&& (type.equals("") == false)) {*/
			{	
				errorTextArea.setText("No username found");
			}

			if ((username.equals("") == true) && (name.equals("") == true))
					/*&& (type.equals("") == true)) {*/
			{	
				errorTextArea
						.setText("Cannot complete modification missing info");
			}
		}

		if ((user.isInstructor() == true) && (user.isAdmin() == false)) {
			if ((name.equals("") == true) && (username.equals("") == true)) {
				errorTextArea
						.setText("Cannot complete modification missing info");
			}

			if ((name.equals("") == true) && (username.equals("") == false)) {
				errorTextArea.setText("No name found");
			}

			if ((name.equals("") == false) && (username.equals("") == true)) {
				errorTextArea.setText("No username found");
			}

			if ((name.equals("") == false) && (username.equals("") == false)) {
				String firstname = name.substring(0, name.indexOf('_'));
				String lastname = name.substring(name.indexOf('_') + 1,
						name.length());

				Person modify = null;

				try {
					ExClient.getPerson(username);
				}

				catch (Exception e) {

				}

				if (modify != null) {
					modify.setFirstName(firstname);
					modify.setLastName(lastname);

					try {
						ExClient.updatePerson(modify);
					}

					catch (Exception e) {

					}
				}
			}
		}
		
		searchJTextField.setText("");
		modifiedNameJTextField.setText("");
		unmodifiedNameJTextField.setText("");
	}

	private void modifyRollJButtonActionPerformed(ActionEvent event) {
		// Modify username?
		// Person user = getUser();

		Person student =(Person) rollJList.getSelectedValue();

		
		if (student!=null) {

			Person currentStudent = student;
			String username = currentStudent.getUserName();
			String name = modifiedNameJTextField.getText();

			if ((name.equals("") == true) && (username.equals("") == true)) {
				errorTextArea
						.setText("Cannot complete modification missing info");
			}

			if ((name.equals("") == false) && (username.equals("") == true)) {
				errorTextArea.setText("No username found");
			}

			if ((name.equals("") == true) && (username.equals("") == false)) {
				errorTextArea.setText("No student name found");
			}

			if ((name.equals("") == false) && (username.equals("") == false)) {
				Scanner scan=new Scanner(name);
				String firstname="";
				String lastname="";
				boolean error=false;
				if(scan.hasNext())
				firstname =scan.next();
				else{
				errorTextArea.setText("No student name found");
				error=true;
				}

				if(scan.hasNext())
				lastname = scan.next();
				else{
				errorTextArea.setText("No student name found");
				error=true;
				}

				if(error==false){
				Person modify = null;

				try {
					modify=ExClient.getPerson(username);
				}

				catch (Exception e) {

				}

				if (modify != null) {
					modify.setFirstName(firstname);
					modify.setLastName(lastname);
					// modify in roll

					try {
						ExClient.updatePerson(modify);
						rollJList.setListData(ExClient.getRoll(getCourse()).roll.toArray());

					}

					catch (Exception e) {

					}
				}
			}
		}
		}
		
		modifiedNameJTextField.setText("");
		unmodifiedNameJTextField.setText("");
	}

	private void logoutJButtonActionPerformed(ActionEvent event) {
		logout();
	}

	private void removeDatabaseJButtonActionPerformed(ActionEvent event) {
		// String name = nameJTextField.getText();
		if(rollJList.getSelectedIndex()!=-1){
		String username = ((Person)rollJList.getSelectedValue()).getUserName();
		// String password = passwordJTextField.getText();
		// String type = typeJTextField.getText();

		if (username.equals("")) {
			errorTextArea.setText("No username for user that wish to remove found");
		}

		if (username.equals("") == false) {
			Person remove = null;

			try {
				remove = ExClient.getPerson(username);
			}

			catch (Exception e) {

			}

			if (remove != null) {
				// remove person
			
				try
				{
					ExClient.removePersonFromDB(remove);
					rollJList.setListData(ExClient.getAll().toArray());
				}
				catch(Exception e)
				{
			
				}
			}
			
			if (remove == null) {
				errorTextArea.setText("That user does not exist");
			}
		}


		usernameJTextField.setText("");
		}
		else{
			errorTextArea.setText("Error no person selected");
		}
	}

	private void removeCourseJButtonActionPerformed(ActionEvent event) throws IOException, ClassNotFoundException {
		// String name = nameJTextField.getText();
		String username = ((Person)rollJList.getSelectedValue()).getUserName();

		if (username.equals(""))
		// if((name.equals("") == true) || (username.equals("") == true))
		{
			errorTextArea.setText("No username found");
		}

		if (username.equals("") == false)
		{
			Person person= ExClient.getPerson(username);
			Roll r=ExClient.getRoll(getCourse());
			r.addPersonToBeRemoved(person);
			ExClient.updateRoll(r);
			rollJList.setListData(ExClient.getRoll(getCourse()).roll.toArray());
			// remove from course

		}

	}

	private void scanFileJButtonActionPerformed(ActionEvent event) throws IOException, ClassNotFoundException {
		String name = fileNameJTextField.getText();
		File fileName = new File(name);

		try {
			Scanner scan = new Scanner(fileName);
			boolean noError=true;
			String studentName="";
			String password="";
			String username="";

			while (scan.hasNext()&&noError) {
				// File format?
				if(scan.hasNext()){
					studentName = scan.nextLine();
				}else{
					noError=false;
				}
				if(scan.hasNext()){
					username = scan.nextLine();
				}else{
					noError=false;
				}
				if(scan.hasNext()){
					password = scan.nextLine();
				}else{
					noError=false;
				}
				if(noError){
					String firstname="";
					String lastname="";
					Scanner scan2=new Scanner(studentName);
					if(scan2.hasNext()){
						firstname = scan2.next();
					}else{
						noError=false;
					}
					if(scan2.hasNext()){
						lastname = scan2.next();
					}else{
						noError=false;
					}
					if(noError){

						Person found = null;
		
						try {
							found = ExClient.getPerson(username);
						}
		
						catch (Exception e) {
		
						}
		
						if (found != null) {
							Courses add = getCourse();
							found.addCourses(add.getcourse());			

							Roll r = ExClient.getRoll(getCourse());

							r.addPersonToBeAdded(found);

							ExClient.updateRoll(r);
							rollJList.setListData(r.roll.toArray());

							// add to roll
							// update roll
						}
		
						if (found == null) {
							Person student = new Person(firstname, lastname, username,
									password, false, false);
							Person per=ExClient.addPerson(student);

							Courses add = getCourse();
							per.addCourses(add.getcourse());
							

							Roll r = ExClient.getRoll(getCourse());

							r.addPersonToBeAdded(per);

							ExClient.updateRoll(r);
							rollJList.setListData(r.roll.toArray());
							// TODO add to roll
							// update roll
						}

				// get person
				// update roll
				// update persons courses
					}
				}
			}
		}

		catch (FileNotFoundException e) {
			errorTextArea.setText("File does not exist");
		}
	}

	private void searchJButtonActionPerformed(ActionEvent event) {
		
		errorTextArea.setText("No username to search for");
		

		if (rollJList.getSelectedIndex()!=-1) {
			Person found = null;

		
				found = (Person) rollJList.getSelectedValue();
			if (found != null) {
				unmodifiedNameJTextField.setText(found.getFullName());
				searchJTextField.setText(found.getUserName());
				//}
			}
		}else{
			errorTextArea.setText("Error no one selected");
		}
	}

	public Person getUser() {
		return transition;
	}

	public Courses getCourse() {
		return add;
	}

	// public String getPrevious()
	// {
	// return prev;
	// }

	public void home(Person user) {
		if (user.isAdmin() == true) {
			adminInterface admin = new adminInterface(user);
		}

		if ((user.isInstructor() == true) && (user.isAdmin() == false)) {
			selectOrCreateCourseInterface create = new selectOrCreateCourseInterface(
					true, user);
		}

		if ((user.isInstructor() == false) && (user.isAdmin() == false)) {

			selectOrCreateCourseInterface create = new selectOrCreateCourseInterface(
					false, user);
		}
	}

	public void logout() {
		setVisible(false);
		loginInterface logout = new loginInterface();
	}
}