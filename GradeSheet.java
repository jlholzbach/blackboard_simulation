

import java.util.ArrayList;
public class GradeSheet 
{		 
	ArrayList<AssignmentGrade> grades;
	Person student;
	
	public GradeSheet(Person student)
	{ 
		this.student = student;
		this.grades = new ArrayList<AssignmentGrade>();
	}
	
	public ArrayList<AssignmentGrade> getAssignments()
	{
		return this.grades;
	}
	
	public Person getStudent()
	{
		return this.student;
	}
	
	public void setGrade(AssignmentGrade grade)
	{ 
		this.grades.add(grade);
	}
	public AssignmentGrade getGrade(int index)
	{ 
		return grades.get(index);
	}
		
}