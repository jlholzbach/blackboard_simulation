

import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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

public class ModifyScaleInterface  extends JFrame
{
	private JLabel scaleNameJLabel;
	private JTextField scaleNameJTextField;
	
	private JLabel scaleTitleLabel;
	

	private JButton saveEditJButton;
	private JButton resetJButton;
	private JButton addRowJButton;
	private JButton cancelJButton;
	private JButton removeJButton;
	private JTable divJTable;

	private JLabel errorJLabel;
	private JTextArea errorTextArea;//Used to output error messages
	private GradeTableModel DTM;
	private Container contentPane;

	private String next;
	private GradingScale GS;
	private Courses from;
	private Person user;
	private Boolean isViewOnly;
	
	private static String type;
	private ArrayList<Grade> divs;
	private scaleInterface parent;
	public ModifyScaleInterface(scaleInterface par,GradingScale GS,Courses course, Person user,Boolean isViewOnly)//Creates login interface
	{		
		this.parent=par;
		this.from = course;
		this.user = user;
		this.GS=GS;
		this.isViewOnly=isViewOnly;
		divs=GS.getGradingScale();
		contentPane = getContentPane();
		contentPane.setLayout( null );

		scaleTitleLabel = new JLabel();
		scaleTitleLabel.setText("Grading Scale for Course: "+course.getCourseName()+" Created by: "+course.getInstructor().getFullName());
		scaleTitleLabel.setBounds(10,0,400,20);
		scaleNameJLabel = new JLabel();
		scaleNameJLabel.setText("Scale Name: ");
		scaleNameJLabel.setBounds(10,30,90,20);
		scaleNameJTextField = new JTextField();
		scaleNameJTextField.setBounds(100,30,250,20);
		scaleNameJTextField.setText(GS.getScaleName());
			
		saveEditJButton = new JButton();
		saveEditJButton.setBounds( 670, 325, 120, 30 );
		saveEditJButton.setText( "Save Changes" );

		cancelJButton = new JButton();
		cancelJButton.setBounds( 565, 325, 100, 30 );
		cancelJButton.setText( "Cancel" );
		
		addRowJButton = new JButton();
		addRowJButton.setBounds( 10, 325, 160, 30 );
		addRowJButton.setText( "Add Scale Division" );
		
		removeJButton = new JButton();
		removeJButton.setBounds( 180, 325, 200, 30 );
		removeJButton.setText( "Remove Selected Scale" );
		
		errorJLabel = new JLabel();
		errorJLabel.setBounds( 10, 260, 50, 20 );
		errorJLabel.setText("Error:" );			
		
		errorTextArea = new JTextArea();  	
		//errorTextArea.setBounds( 70, 260, 400, 60 );
		JScrollPane errorScrollPane = new JScrollPane(errorTextArea);
		errorScrollPane.setBounds(70,260,400,60);

		DTM = new GradeTableModel(divs,isViewOnly);
		divJTable=new JTable(DTM);
		divJTable.setPreferredScrollableViewportSize(new Dimension(350, 300));
		divJTable.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(divJTable);
		scrollPane.setBounds(10,60,780,200);
	//	System.out.println("Scale1.3");

		if(isViewOnly)
		{
			scaleNameJTextField.setEditable(false);
			saveEditJButton.setText( "Return" );
			saveEditJButton.addActionListener(new ActionListener() 
			{public void actionPerformed( ActionEvent event )
				{
					cancelJButtonActionPerformed( event );
				}} );
			setTitle( "View Scale" );
		}
		else
		{
			contentPane.add(addRowJButton);
			contentPane.add(cancelJButton );
			contentPane.add(removeJButton);
			saveEditJButton.addActionListener(new ActionListener() 
			{public void actionPerformed( ActionEvent event )
				{
					saveEditJButtonActionPerformed( event );
				}} );
			setTitle( "Modify Scale" );
			removeJButton.addActionListener(new ActionListener() 
			{public void actionPerformed( ActionEvent event )
				{
					removeJButtonActionPerformed();
				}});
			
		}
		
		contentPane.add(scaleTitleLabel);
		contentPane.add(scaleNameJLabel);
		contentPane.add(scaleNameJTextField);
		contentPane.add(saveEditJButton);		
		contentPane.add(errorJLabel );
		contentPane.add(errorScrollPane );
		contentPane.add(scrollPane);
	 
		setSize( 800, 400 );     
		setVisible(true);		

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
	}

	private void saveEditJButtonActionPerformed(
			ActionEvent event) {
		ArrayList<Grade> gradeTable = DTM.getDivision();
		boolean error = false;
		errorTextArea.setText("");
		for(Grade g : gradeTable)
		{
			if(g.getletterGrade().equals(""))
			{
				error=true;
				errorTextArea.append("Letter Grade must be a Valid Name \n");
			}
			if(!(g.getCutoffscore()>=0))
			{
				error=true;
				errorTextArea.append("Grade Cutoff must be above 0 \n");
			}
		}
		if(gradeTable.size()==0)
		{
			error=true;
			errorTextArea.append("Scale Must have at least 1 valid Division \n");
		}
		if(scaleNameJTextField.getText().equals(""))
		{
			error=true;
			errorTextArea.append("Scale Must have a name \n");
		}
		
		if(!error)
		{
			GS.setName(scaleNameJTextField.getText());
			GS.setGradeArray(gradeTable);
			try {
				if(GS.getScaleID()==-1)
				{
					GradingScale nScale =ExClient.addScale(GS);
					from.setScaleID(nScale);
					ExClient.updateCourse(from);
				}
				else
				{
					ExClient.updateScale(GS);
				}
				setVisible(false);
				scaleInterface view = new scaleInterface(user, from);  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void removeJButtonActionPerformed() 
	{
		try {
			DTM.removeSelectedRows();
			
		
		} catch (Exception e) {
			e.printStackTrace();
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
		//(String name, String strategy, int weight,boolean hasPrefix, String prefixName, boolean hasMaxScore, int maxScore, int numOfDrops)
		Grade tCat = new Grade("",0);
		DTM.addRow(tCat);
		
	}
	private void cancelJButtonActionPerformed(
			ActionEvent event) {
			setVisible(false);
			scaleInterface view = new scaleInterface(user, from);  
		// TODO Auto-generated method stub
		
	}

	
	public void setUpStratColumn(JTable table,TableColumn stratColumn) 
	{
		//Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("standard");
		comboBox.addItem("drops allowed");
		comboBox.addItem("excuses allowed");
		stratColumn.setCellEditor(new DefaultCellEditor(comboBox));
		
		//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer =
		new DefaultTableCellRenderer();
		stratColumn.setCellRenderer(renderer);
	}

	
	class GradeTableModel extends AbstractTableModel {
        private String[] columnNames; 
        private ArrayList<Grade> div;
        private Boolean isReadOnly;
        private Boolean[] isSelected;
        
        GradeTableModel(ArrayList<Grade> div,Boolean isReadOnly)
        {
        	this.div=div;
        	this.isReadOnly=isReadOnly;
        	if(isReadOnly)
        	{
        		columnNames = new String[]{
					"Grade",
					"Cutoff"};
        	}
        	else
        	{
        		columnNames = new String[]{
    				"Grade",
    				"Cutoff",
    				"Remove"};
        	}
        	isSelected =new Boolean[div.size()];
        	for(int i=0;i<div.size();i++)
        	{
        		isSelected[i]=false;
        	}
        }
        
        public ArrayList<Grade> getDivision()
        {
        	return div;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return div.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }
        
        public void addRow(Grade newDiv)
        {
        	this.div.add(newDiv);
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
        			div.remove(i);
        		}
        	}
        	isSelected =new Boolean[div.size()];
        	for(int i=0;i<div.size();i++)
        	{
        		isSelected[i]=false;
        	}
        	fireTableRowsDeleted(0,getRowCount());
        }


        
        public Object getValueAt(int row, int col) {
            Grade c = div.get(row);
            switch(col)
            {
            	case 0: return c.getletterGrade();
            	case 1: return c.getCutoffscore();
            	case 2: return isSelected[row];
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
        	Grade c = div.get(row);
        	switch(col)
            {
            	case 0: c.setLetterGrade((String) value); break;
            	case 1: c.setCutoff( (Double) value); break;
            	case 2: isSelected[row]=(Boolean) value;
            }
            fireTableCellUpdated(row, col);
            
        }
        
        public ArrayList<Grade> getDivsToBeRemoved()
        {
        	ArrayList<Grade> rList = new ArrayList<Grade>();
        	for(int i=0;i<isSelected.length;i++)
        	{
        		if(isSelected[i])
        		{
        			rList.add(div.get(i));
        		}
        	}
        	return rList;
        }



    }


}