
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class ModifySchemeInterface  extends JFrame
{
	private JLabel schemeNameJLabel;
	private JTextField schemeNameJTextField;
	
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

	private GradingScheme GS;
	private Courses currentCourse;
	private Person user;
	
	static String type;
	private ArrayList<Category> cats;
	private schemeInterface parentFrame;
	private Boolean isViewOnly;

	public ModifySchemeInterface(schemeInterface par,GradingScheme GS,Courses course, Person user,Boolean isViewOnly)//Creates login interface
	{	
		if(course.isArchived() || isViewOnly)
		{
			this.isViewOnly=true;
		}
		else
		{
			this.isViewOnly=false;
		}
		
		setVisible(true);
		parentFrame=par;
		this.currentCourse = course;
		this.user = user;
		
		this.GS=GS;
		cats=GS.getGradingscheme();
		contentPane = getContentPane();
		contentPane.setLayout( null );

		schemeTitleLabel = new JLabel();
		schemeTitleLabel.setText("Grading Scheme for Course: "+course.getCourseName()+" Created by: "+course.getInstructor().getFullName());
		schemeTitleLabel.setBounds(10,0,400,20);
		schemeNameJLabel = new JLabel();
		schemeNameJLabel.setText("Scheme Name: ");
		schemeNameJLabel.setBounds(10,30,90,20);
		schemeNameJTextField = new JTextField();
		schemeNameJTextField.setBounds(100,30,250,20);
		schemeNameJTextField.setText(GS.getSchemeName());
		
		saveEditJButton = new JButton();
		saveEditJButton.setBounds( 870, 325, 120, 30 );
		saveEditJButton.setText( "Save Changes" );
	
		cancelJButton = new JButton();
		cancelJButton.setBounds( 765, 325, 100, 30 );
		cancelJButton.setText( "Cancel" );
		
		addRowJButton = new JButton();
		addRowJButton.setBounds( 10, 325, 160, 30 );
		addRowJButton.setText( "Add Scheme Category" );
		
		removeJButton = new JButton();
		removeJButton.setBounds( 180, 325, 200, 30 );
		removeJButton.setText( "Remove Selected Categories" );
		
		errorJLabel = new JLabel();
		errorJLabel.setBounds( 10, 260, 50, 20 );
		errorJLabel.setText("Error:" );			
		
		errorTextArea = new JTextArea();  	
		//errorTextArea.setBounds( 70, 260, 400, 60 );
		JScrollPane errorScrollPane = new JScrollPane(errorTextArea);
		errorScrollPane.setBounds(70,260,400,60);
		
		CTM = new CategoryTableModel(cats,this.isViewOnly);
		catJTable=new JTable(CTM);
		catJTable.setPreferredScrollableViewportSize(new Dimension(350, 300));
		catJTable.setFillsViewportHeight(true);
				
		JScrollPane scrollPane = new JScrollPane(catJTable);
		scrollPane.setBounds(10,60,980,200);
		
	//	System.out.println(this.isViewOnly);
		if(this.isViewOnly)
		{
			
			schemeNameJTextField.setEditable(false);
			setTitle( "View Scheme" ); 
			saveEditJButton.setText("Return");
			saveEditJButton.addActionListener(new ActionListener() 
			{public void actionPerformed( ActionEvent event )
				{
					cancelJButtonActionPerformed( event );
				}} );	
		}
	
		
		if(this.isViewOnly == false)
		//else
		{
			contentPane.add(cancelJButton );
			contentPane.add(addRowJButton);
			contentPane.add(removeJButton);
			setTitle( "Modify Scheme" ); 
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
					cancelJButtonActionPerformed( event );
				}} );
			setUpStratColumn(catJTable, catJTable.getColumnModel().getColumn(4));
			removeJButton.addActionListener(new ActionListener() 
			{public void actionPerformed( ActionEvent event )
				{
					removeJButtonActionPerformed();
				}});
			
		}
		
		contentPane.add(schemeTitleLabel);
		contentPane.add(schemeNameJLabel);
		contentPane.add(schemeNameJTextField);
		contentPane.add(saveEditJButton);
		contentPane.add(errorJLabel );
		contentPane.add(errorScrollPane );
		contentPane.add(scrollPane);

		setSize( 1000, 400 );     
		setVisible(true);		
  	    this.addWindowListener(new exitListener());

	}

	private void saveEditJButtonActionPerformed(
			ActionEvent event) {
		ArrayList<Category> catTable = CTM.getCategories();
		int tempWeight=0;
		boolean error = false;
		errorTextArea.setText("");
		for(Category c : catTable)
		{
			tempWeight += c.getCategoryWeight();
		//	System.out.println("Weight :"+c.getCategoryWeight());
			if(c.getCategoryName().equals(""))
			{
				errorTextArea.append("Category name can not be blank \n");
				error=true;
			}
			if(c.getPrefixStatus())
			{
				if(c.getPrefix().equals(""))
				{
					errorTextArea.append("Category Prefix can not be blank for Category "+c.getCategoryName()+" \n");
					error=true;
				}
			}
			if(c.getHasMaxScore())
			{
				if(c.getMaxScore()<=0)
				{
					errorTextArea.append("Max Score must be greater than 0 for Category "+c.getCategoryName()+" \n");
					error=true;
				}
			}
		}
		if(tempWeight!=100)
		{
			errorTextArea.append("Total category weights must sum to 100 \n");
			error=true;
		}
		if(catTable.size()==0)
		{
			errorTextArea.append("Total Number of Cateories Must be Greater then 0 \n");
			error=true;
			
		}
		
		if(!error)
		{
			GS.setGradingscheme(catTable);
			try {
				if(GS.getSchemeID()==-1)
				{
					ExClient.addScheme(GS);	
				}
				else
				{
					ExClient.updateScheme(GS);
				}
				setVisible(false);
			//	System.out.println("exiting modifyscheme");
				schemeInterface view = new schemeInterface(user, currentCourse);
			//	parent.setVisible(true);
			} catch (IOException e) {
			//	System.out.println("eE");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public Courses getCourse()
	{
		return currentCourse;
	}	
	
	public Person getUser()
	{
		
		return user;
	}
	
	private void addRowJButtonActionPerformed(
			ActionEvent event) {
		//(String name, String strategy, int weight,boolean hasPrefix, String prefixName, boolean hasMaxScore, int maxScore, int numOfDrops)
		Category tCat = new Category("","standard",0,false,"",false,0,0);
		CTM.addRow(tCat);
		
	}
	private void cancelJButtonActionPerformed(
			ActionEvent event) {
			setVisible(false);
			schemeInterface view = new schemeInterface(user, currentCourse);  
		// TODO Auto-generated method stub
		
	}
	
	private void removeJButtonActionPerformed() 
	{
		try {
			ArrayList<Category> rList =CTM.getCategoriesToBeRemoved();
			ArrayList<Assignment> aList = ExClient.getAssignment(currentCourse);
			Boolean doRemove=true;
			for(Category c:rList)
			{
				Boolean prompt = false;
				for(Assignment a :aList)
				{
					if(a.getCatID()==c.getID())
					{
						prompt=true;
					}
				}
				if(prompt)
				{
					int resp =JOptionPane.showConfirmDialog(
	        			    null,
	        			    "The Category: "+c.getCategoryName()+" contains Assignments.\n"+
	        			    "Are you sure you want to delete this category and all of its Assignments?",
	        			    "promptbox",
	        			    JOptionPane.YES_NO_OPTION);
					if(resp==1)
					{
						doRemove=false;
					}
				}	
			}
			if(doRemove)
			{
				CTM.removeSelectedRows();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	
	public void setUpStratColumn(JTable table,TableColumn stratColumn) 
	{
		//Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Standard");
		comboBox.addItem("Drops Allowed");
		comboBox.addItem("Excuses Allowed");
		stratColumn.setCellEditor(new DefaultCellEditor(comboBox));
		
		//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer =
		new DefaultTableCellRenderer();
		stratColumn.setCellRenderer(renderer);
	}
	
	class CategoryTableModel extends AbstractTableModel {
        private String[] columnNames;
        private ArrayList<Category> cats;
        private Boolean[] isSelected;
        Boolean isReadOnly;
        
        CategoryTableModel(ArrayList<Category> cat,Boolean isReadOnly)
        {
        	if(isReadOnly)
        	{
        	columnNames  =new String[]{
    				"Name",
    				"Has Prefix",
    				"Name Prefix",
    				"Weight",
    				"Strategy",
    				"Has Max Score",
    				"Max Score",
    				"Drops Allowed"};
        	}
        	else
        	{
        		columnNames  =new String[]{
        				"Name",
        				"Has Prefix",
        				"Name Prefix",
        				"Weight",
        				"Strategy",
        				"Has Max Score",
        				"Max Score",
        				"Drops Allowed",
        				"Remove Category"};
        	}
        	this.isReadOnly=isReadOnly;
        	this.cats=cat;
        	isSelected =new Boolean[cat.size()];
        	for(int i=0;i<cat.size();i++)
        	{
        		isSelected[i]=false;
        	}
        	
        }
        
        public ArrayList<Category> getCategories()
        {
        	return cats;
        }
        public ArrayList<Category> getCategoriesToBeRemoved()
        {
        	ArrayList<Category> rList = new ArrayList<Category>();
        	for(int i=0;i<isSelected.length;i++)
        	{
        		if(isSelected[i])
        		{
        			rList.add(cats.get(i));
        		}
        	}
        	return rList;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return cats.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }
        
        public void addRow(Category newCat)
        {
        	this.cats.add(newCat);
        	Boolean[] nIsSelected = new Boolean[isSelected.length+1];
        	for(int i=0;i<isSelected.length;i++)
        	{
        		nIsSelected[i]=isSelected[i];
        	}
        	nIsSelected[isSelected.length]=false;
        	isSelected=nIsSelected;
        	fireTableRowsInserted(getRowCount()-1,getRowCount()-1);
        }

        public void removeSelectedRows()
        {
        	for(int i=getRowCount()-1;i>=0;i--)
        	{
        		if(isSelected[i])
        		{
        			Category c =cats.remove(i);
        			try {
						ExClient.removeCategory(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
        		}
        	}
        	isSelected =new Boolean[cats.size()];
        	for(int i=0;i<cats.size();i++)
        	{
        		isSelected[i]=false;
        	}
        	fireTableRowsDeleted(0,getRowCount());
        }

        
        public Object getValueAt(int row, int col) {
            Category c = cats.get(row);
            switch(col)
            {
            	case 0: return c.getCategoryName();
            	case 1: return c.getPrefixStatus();
            	case 2: return c.getPrefix();
            	case 3: return c.getCategoryWeight();
            	case 4: return c.getCategoryStrategy();
            	case 5: return c.getHasMaxScore();
            	case 6: return c.getMaxScore();
            	case 7: return c.getNumDrops();
            	case 8: return isSelected[row];
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

                return !isReadOnly;
      
        }
        
        public void setValueAt(Object value, int row, int col) {
        	Category c = cats.get(row);
        	switch(col)
            {
            	case 0: c.setCategoryName((String) value); break;
            	case 1: c.setPrefixStatus((Boolean) value); break;
            	case 2: c.setPrefixName((String) value); break;
            	case 3: c.setCategoryWeight((Integer) value); break;
            	case 4: c.setCategoryStrategy((String) value); break;
            	case 5: c.setHasMaxScore((Boolean) value); break;
            	case 6: c.setScore((Integer) value); break;
            	case 7: c.setNumofDrops((Integer) value); break;
            	case 8: isSelected[row]=(Boolean) value;
            }
            fireTableCellUpdated(row, col);
            
        }



    }


}