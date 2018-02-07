import java.io.Serializable;
import java.util.*;


public class Courses implements Serializable{
	
	private String courseName; // name of course 
	private int courseID;
	private int schemeID;
	private int scaleID;
	private String instructorName;
	private String instructorUserName;
	private Person instructor;
	private boolean isArchived;
	public Courses(){
		
	}
	
	public Courses(String courseName,int courseID, int schemeID, int scaleID, String instructorName, String instructorUserName, boolean isArchived)
	{
		this.courseName=courseName;
		this.courseID = courseID;
		this.schemeID = schemeID;
		this.scaleID = scaleID;
		this.instructorName = instructorName;
		this.instructorUserName = instructorUserName;
		this.instructor = new Person(instructorUserName);
		this.isArchived=isArchived;
	}
	
	public Courses(String courseName,int courseID, int schemeID, int scaleID, Person instructor, boolean isArchived)
	{
		this.courseName=courseName;
		this.courseID = courseID;
		this.schemeID = schemeID;
		this.scaleID = scaleID;
		this.instructor = instructor;
		this.instructorName = instructor.getFullName();
		this.instructorUserName = instructor.getUserName();
		this.isArchived=isArchived;
	}
		
	public Courses(String courseName,Person instructor)
	{
		this.courseName=courseName;
		this.courseID = -1;
		this.schemeID = -1;
		this.scaleID = -1;
		this.instructor = instructor;
		this.instructorName = instructor.getFullName();
		this.instructorUserName = instructor.getUserName();
		this.isArchived=false;
	}
	
	public void setArchived(boolean status)
	{
		this.isArchived = status;
	}
	
	public void setSchemeID(int schemeID) {
		this.schemeID = schemeID;
	}
	public void setSchemeID(GradingScheme scheme) {
		this.schemeID = scheme.getSchemeID();
	}

	public void setScaleID(int scaleID) {
		this.scaleID = scaleID;
	}
	public void setScaleID(GradingScale scale) {
		this.scaleID = scale.getScaleID();
	}

	public String getcourse()
	{
		return courseName;
	}
	
	public String getCourseName()
	{
		return courseName;
	}
	
	public int getCourseID() {
		return courseID;
	}

	public int getSchemeID() {
		return schemeID;
	}

	public int getScaleID() {
		return scaleID;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public String getInstructorUserName() {
		return instructorUserName;
	}
	
	public Person getInstructor()
	{
		return this.instructor;
	}
	
	public boolean isArchived()
	{
		return isArchived;
	}

	
	 public String toString()
	   {
		   String result = "";
		   	  result = result + "course name: " + courseName; 
		   	  
		   	  return result;
	   }
	
	public String toStringVerbose()
		{
				String result = "";
		   	  result = result + "course name: " + courseName + "instructor: " + instructorName+ "user name:" + instructorUserName + "course id:" + courseID;
		   	  if(isArchived){result+= "Archived";}
		   	  
		   	  return result;
		}
		
	public void setID(int ID) {
		// TODO Auto-generated method stub
		this.courseID=ID;
	}
	
	public void setCourseName(String cName)
	{
		this.courseName=cName;
	}

}
