import java.io.Serializable;

public class AssignmentGrade implements Serializable,Cloneable
{	
	private int gradeID;
	private Assignment assignment;
	private int assignID;
	private Person student;
	private int studentID;
	private double grade;
	private boolean excused;
	private boolean dropped;
	private int maxScore;
	
	public AssignmentGrade(Assignment assignment, Person student, double grade, boolean excuse, boolean drop, int max) 
	{
		super();
		this.assignment = assignment;
		this.student = student;
		this.grade = grade;
		this.assignID=assignment.getAssignID();
		this.studentID=student.getID();
		this.gradeID=-1;
		this.excused = excuse;
		this.dropped = drop;
		this.maxScore = max;
		
	}

	public AssignmentGrade(int gradeID, Assignment assignment, Person student,double grade, boolean excuse, boolean drop, int max) 
	{
		super();
		this.gradeID = gradeID;
		this.assignment = assignment;
		this.student = student;
		this.grade = grade;
		this.assignID=assignment.getAssignID();
		this.studentID=student.getID();
		this.excused = excuse;
		this.dropped = drop;
		this.maxScore = max;
	}

	public int getGradeID() {
		return gradeID;
	}

	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public int getAssignID() {
		return assignID;
	}

	public void setAssignID(int assignID) {
		this.assignID = assignID;
	}

	public Person getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = student;
	}
	public AssignmentGrade clonit(){
		try {
			return (AssignmentGrade)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public double getGrade() {
		return grade;
	}
	
	
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public boolean getExcused()
		{
			return this.excused;
		}
		
	public void setExcused(boolean excuse)
		{
			this.excused = excuse;
		}
	
	public boolean getDropped()
		{
			return this.dropped;
		}
		
	public void setDropped(boolean drop)
		{
			this.dropped = drop;
		}
	
	public void setMaxScore(int max)
		{
			this.maxScore = max;
		}
	
	public int getMaxScore()
		{
			return this.maxScore;
		}
		
	public String toString()
	{
		return "Student: "+student.getFullName()+" Received Grade: "+grade+" on Assignment: "+assignment.getAssignName();
	}
	

}