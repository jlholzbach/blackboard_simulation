
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class assignmentInterface  extends JFrame
{
	
	private JLabel schemeTitleLabel;	
	private JButton saveEditJButton;
	private JButton addRowJButton;
	private JButton cancelJButton;
	private JButton removeJButton;

	private JTable catJTable;

	private JLabel errorJLabel;
	private JTextArea errorTextArea;//Used to output error messages
	private CategoryTableModel CTM;
	private Container contentPane;

	String next;
	Courses from;
	Person user;
	
	static String type;
	ArrayList<Assignment> assignments;
	ArrayList<Person> Students;
	public assignmentInterface(Person user, Courses from)
	{		
		
		this.from = from;
		this.user = user;
		try {
			assignments=ExClient.getAssignment(from);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Students=(ExClient.getRoll(from)).roll;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
  	    
		this.addWindowListener(new exitListener());
		contentPane = getContentPane();
		contentPane.setLayout( null );

		schemeTitleLabel = new JLabel();
		schemeTitleLabel.setText("Assignments for Course: "+from.getCourseName());
		schemeTitleLabel.setBounds(10,0,400,20);

		saveEditJButton = new JButton();
		saveEditJButton.setBounds( 670, 325, 120, 30 );
		saveEditJButton.setText( "Save Changes" );
		removeJButton = new JButton();
		removeJButton.setBounds( 180, 325, 200, 30 );
		removeJButton.setText( "Remove Selected Assignments" );
		removeJButton.addActionListener(new ActionListener() 
		{public void actionPerformed( ActionEvent event )
			{
				removeJButtonActionPerformed();
			}});
		
		contentPane.add(removeJButton);


		
		cancelJButton = new JButton();
		cancelJButton.setBounds( 565, 325, 100, 30 );
		cancelJButton.setText( "Cancel" );
		
		addRowJButton = new JButton();
		addRowJButton.setBounds( 10, 325, 200, 30 );
		addRowJButton.setText( "Add Assignment" );
		
		errorJLabel = new JLabel();
		errorJLabel.setBounds( 10, 260, 50, 20 );
		errorJLabel.setText("Error:" );			
		
		errorTextArea = new JTextArea();  	
		//errorTextArea.setBounds( 70, 260, 400, 60 );
		JScrollPane errorScrollPane = new JScrollPane(errorTextArea);
		errorScrollPane.setBounds(70,260,400,60);
		
		CTM = new CategoryTableModel(Students,assignments);
		catJTable=new JTable(CTM);
		catJTable.setPreferredScrollableViewportSize(new Dimension(350, 300));
		catJTable.setFillsViewportHeight(true);
		//setUpStratColumn(catJTable, catJTable.getColumnModel().getColumn(4));		
		JScrollPane scrollPane = new JScrollPane(catJTable);
		scrollPane.setBounds(10,60,780,200);

		contentPane.add(schemeTitleLabel);
		if(from.isArchived()){
		saveEditJButton.setEnabled(false);
		addRowJButton.setEnabled(false);
		}
		contentPane.add(saveEditJButton);
		contentPane.add(addRowJButton);
		
		contentPane.add(errorJLabel );
		contentPane.add(errorScrollPane );
		contentPane.add(cancelJButton );
		contentPane.add(scrollPane);

		setTitle( "Modify Assignments" ); 
		setSize( 800, 400 );     
		setVisible(true);
		
		saveEditJButton.addActionListener(new ActionListener() 
				{public void actionPerformed( ActionEvent event )
					{
						saveEditJButtonActionPerformed( event );
					}} );

		addRowJButton.addActionListener(new ActionListener() 
			{public void actionPerformed( ActionEvent event )
				{
					addRowJButtonActionPerformed( event );
				}} );
		cancelJButton.addActionListener(new ActionListener() 
		{public void actionPerformed( ActionEvent event )
			{
				cancelJButtonActionPerformed(  );
			}} );
		
		
		    

	}

	private void removeJButtonActionPerformed() 
	{
		
			ArrayList<Assignment> rList =CTM.getCategoriesToBeRemoved();
			
		for(int i=0;i<rList.size();i++){
			Assignment a=rList.get(i);
					int resp =JOptionPane.showConfirmDialog(
	        			    null,
	        			    "The Assignment: "+a.getAssignName()+" may contain Grades.\n"+
	        			    "Are you sure you want to delete this Assigment and all of its Grades?",
	        			    "promptbox",
	        			    JOptionPane.YES_NO_OPTION);
					if(resp==0)
					{
						try {
							ExClient.removeAssignment(a);
							CTM.removeAssignment(a);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}
		}
		
	}
	

	private void saveEditJButtonActionPerformed(
			ActionEvent event) {
		ArrayList<Assignment> assign = CTM.getAssignments();
		ArrayList<AssignmentGrade> AAG=CTM.getAssignmentGrade();
		boolean error = false;
		errorTextArea.setText("");
	//	System.out.println("assign: "+assign.size());
		//System.out.println("AAG "+AAG.size());
		for(int i=0;i<assign.size();i++){
			if(assign.get(i).getAssignID()==-1){
				try {
					ExClient.addAssignment(assign.get(i));
				} catch (IOException e) {
					error=true;
					errorTextArea.setText("ERROR UPDATING INFORMATION");

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					ExClient.updateAssignment(assign.get(i));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					error=true;
					errorTextArea.setText("ERROR UPDATING INFORMATION");

				}
			}
		}
		
		for(int i=0;i<AAG.size();i++){
			
				if(AAG.get(i).getGradeID()==-1){
					try {
						ExClient.addAssignmentGrade(AAG.get(i));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						error=true;
						errorTextArea.setText("ERROR UPDATING INFORMATION");

					}
				}else{
					try {
						ExClient.updateAssignmentGrade(AAG.get(i));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						error=true;
						errorTextArea.setText("ERROR UPDATING INFORMATION");

					}
				}
			
		}
	
		if(!error)
		{
			setVisible(false);
			courseOptionsInterface view = new courseOptionsInterface(from, user);
		}
		
		
	}
	
	
	public Courses getCourse()
	{
		return from;
	}	
	
	public Person getUser()
	{
		
		return user;
	}
	
	private void addRowJButtonActionPerformed(
			ActionEvent event) {
		ArrayList<Category> categ = null;
		String prefixName = "";
		int assignmentNumber = 0;
		Category c=null;
		
		try {
			GradingScheme sc=ExClient.getScheme(from);
			categ=sc.getGradingscheme();
			Object[] ob=new Object[categ.size()];
			
			for(int i=0;i<categ.size();i++){
				ob[i]=categ.get(i).getCategoryName();
			}
			String s ="";
			while(s.equals("")){
			s= (String)JOptionPane.showInputDialog(
                    null,
                    "Select your category","Select category",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    ob,
                    "");
			}
			for(int i=0;i<categ.size();i++){
				if(s.equals(categ.get(i).getCategoryName())){
					c=categ.get(i);
					prefixName=c.getPrefix();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean go=true;
		while(go){
			go=false;
			
		String temp =(String) JOptionPane.showInputDialog(
 			    null,
 			    "Enter assignment Number",
 			    "promptbox",JOptionPane.PLAIN_MESSAGE,null,
 			    null,"0");
		try{
			assignmentNumber=Integer.valueOf(temp);

			}
			catch(NumberFormatException nfe)  {
	       		  go=true;
	       	 }
		}
		//TODO
		//(String name, String strategy, int weight,boolean hasPrefix, String prefixName, boolean hasMaxScore, int maxScore, int numOfDrops)
		Assignment tCat = new Assignment("",c,prefixName,assignmentNumber,from);
		try {
			tCat.setAssignID(ExClient.addAssignment(tCat));
			CTM.addRow(tCat);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private int cancelJButtonActionPerformed() {
			setVisible(false);
			courseOptionsInterface view = new courseOptionsInterface(from, user);
			return 0;  
		
	}

	

	
	class CategoryTableModel extends AbstractTableModel {
        private String[] columnNames;     //  private ArrayList<Category> cats;
        private ArrayList<Person> persons;
        private ArrayList<Assignment> assignments=new ArrayList<Assignment>();
        private ArrayList<Assignment> assignmentsprior=new ArrayList<Assignment>();
        private Boolean[] isSelected;
        
        private ArrayList<ArrayList<AssignmentGrade>> AGG=new ArrayList<ArrayList<AssignmentGrade>>();
        private ArrayList<ArrayList<AssignmentGrade>> AGGprior=new ArrayList<ArrayList<AssignmentGrade>>();

        CategoryTableModel(ArrayList<Person> per,ArrayList<Assignment> as){
        	this.persons=per;
        	assignments=as;
        	for(int i=0;i<as.size();i++){
        		assignmentsprior.add(as.get(i).clonit());
        	}
        	
        	columnNames=new String[(per.size()+2)];
        	columnNames[0]="          ";
        	columnNames[1]="Remove";
        	for(int i=0;i<per.size();i++){
        		columnNames[i+2]=per.get(i).getFullName();
        		try {
        			ArrayList<AssignmentGrade> a=ExClient.getAssignmentGrade(per.get(i), from);
        			if(a!=null)
        				AGG.add(a);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
        	}
        	for(int i=0;i<AGG.size();i++){
        		ArrayList<AssignmentGrade> g=new ArrayList<AssignmentGrade>();
        		for(int j=0;j<AGG.get(i).size();j++){
        			g.add(AGG.get(i).get(j).clonit());
        		}
        		AGGprior.add(g);
        	}
        	isSelected =new Boolean[as.size()];
        	for(int i=0;i<as.size();i++)
        	{
        		isSelected[i]=false;
        	}
        	
        	
        	
        }
        public void removeAssignment(Assignment a){
    		assignments.remove(a);
    		int pos=0;
    		for(int i=0;i<assignmentsprior.size();i++){
    			if(assignmentsprior.get(i).getAssignID()==a.getAssignID()){
    				assignmentsprior.remove(i);
    				pos=i;
    			}
    		}
    		for(int i=0;i<AGG.size();i++){
    			
    			for(int j=0;j<AGG.get(i).size();j++){
    				if(AGG.get(i).get(j).getAssignID()==a.getAssignID()){
    					AGG.get(i).remove(j);
    					AGGprior.get(i).remove(j);
    				}
    			}
    		}
    		
    		Boolean[] b=new Boolean[isSelected.length-1];
    		for(int i=0;i<b.length;i++){
        		b[i]=false;
        	}
        	isSelected=b;
        	fireTableRowsDeleted(0,getRowCount());

    	}
        public ArrayList<Assignment> getCategoriesToBeRemoved()
        {
        	ArrayList<Assignment> rList = new ArrayList<Assignment>();
        	for(int i=0;i<isSelected.length;i++)
        	{
        		if(isSelected[i])
        		{
        			rList.add(assignments.get(i));
        		}
        	}
        	return rList;
        }
        
        public ArrayList<Assignment> getAssignments()
        {
        	ArrayList<Assignment> a=new ArrayList<Assignment>();
        	for(int i=0;i<assignments.size();i++){
        	

        		if(i<assignmentsprior.size()){
        			if(!assignmentsprior.get(i).getAssignName().equals(assignments.get(i).getAssignName())){
        				a.add(assignments.get(i));
        			}
        		}else{
        			a.add(assignments.get(i));
        		}
        	}
        	return a;
        }
        public ArrayList<AssignmentGrade> getAssignmentGrade(){
        	ArrayList<AssignmentGrade> a=new ArrayList<AssignmentGrade>();
        	for(int i=0;i<AGG.size();i++){
        		if(i<AGGprior.size()){
        			for(int j=0;j<AGG.get(i).size();j++){
        				if(j<AGGprior.get(i).size()){
        					if(AGG.get(i).get(j).getGrade()!=(AGGprior.get(i).get(j)).getGrade()){
        						a.add(AGG.get(i).get(j));
        						
        					}
        				}else{
        					a.add(AGG.get(i).get(j));
        				}
        			}
        		}else{
        			//loop through add;
        			for(int j=0;j<AGG.get(i).size();j++){
        				a.add(AGG.get(i).get(j));
        			}
        		}
        	}
        
        	return a;
        }

        public int getColumnCount() {
            return columnNames.length;
        }
        

        public int getRowCount() {
            return assignments.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }
        //TODO
        public void addRow(Assignment newCat)
        {
        	this.assignments.add(newCat);
        	Boolean[] b=new Boolean[isSelected.length+1];
        	for(int i=0;i<isSelected.length;i++){
        		b[i]=isSelected[i];
        	}
        	b[isSelected.length]=false;
        	isSelected=b;
        	fireTableRowsInserted(getRowCount()-1,getRowCount()-1);
        }


        
        public Object getValueAt(int row, int col) {
        	if(row<assignments.size()){
            Assignment c = assignments.get(row);
        	
            if(col==0){
            	return c.getAssignName();
            }else if(col==1){
            	return isSelected[row];
            }else{
            	
					ArrayList<AssignmentGrade> AG=AGG.get(col-2);
					if(AG.size()<=assignments.size()){
						for(int i=0;i<AG.size();i++){
						if(AG.get(i).getAssignID()==c.getAssignID()){
							return Double.toString((AG.get(i).getGrade()));
						}
						}
						return "N/A";
					}
				 
            }
        	}
            
            return "";
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.

                return true;
      
        }
        
        public void setValueAt(Object value, int row, int col) {
        if(col<=0){
        	//Creates new assignment, add it to the list;
        	Assignment a=assignments.get(row);
        	assignments.get(row).setAssignName((String)value);
        	
        }else if(col==1){
        	isSelected[row]=!isSelected[row];
        }
        else{
        	try{
        	Double.valueOf((String)value);
        	
        	ArrayList<AssignmentGrade> a=AGG.get(col-2);
        	if(row<assignments.size()){
        	Assignment as=assignments.get(row);
        	boolean found=false;
        	for(int i=0;i<a.size();i++){
        		if(as.getAssignID()==a.get(i).getAssignID()){
        			found=true;        			
        			a.get(i).setGrade(Double.valueOf(((String)value)));
        		}
        	}
        	if(!found){
        		boolean excuse=false;
        		boolean drop=false;
        		int max=0;
        		int n = JOptionPane.showConfirmDialog(
        			    null,
        			    "Would you like excuses?",
        			    "promptbox",
        			    JOptionPane.YES_NO_OPTION);
        		if(n==0)
        			excuse=true;
        		 n = JOptionPane.showConfirmDialog(
        			    null,
        			    "Would you like to allow drops?",
        			    "promptbox",
        			    JOptionPane.YES_NO_OPTION);
        		 if(n==0)
        			 drop=true;
        		boolean validInt=false;
        		while(validInt==false){
        			validInt=true;
        			String s =(String) JOptionPane.showInputDialog(
         			    null,
         			    "What is the max score of this assignment",
         			    "promptbox",JOptionPane.PLAIN_MESSAGE,null,
         			    null,"100");
        			try{
               		 max=Integer.valueOf(s);
	
        			}
        			catch(NumberFormatException nfe)  {
        	       		  validInt=false;
        	       	 }
        		 
        		}
        		Person pers=persons.get(col-2);
        		AssignmentGrade asd=new AssignmentGrade(as,pers,Double.valueOf((String)value),excuse,drop,max);
        		a.add(asd);
        	}
        }
        	}
       	 catch(NumberFormatException nfe)  {
       		  value="0.0";
       	 }
        	}
        	
        	
            fireTableCellUpdated(row, col);
           
        }



    }
}


