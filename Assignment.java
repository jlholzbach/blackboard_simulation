import java.io.Serializable;


public class Assignment implements Serializable, Cloneable
{

	private String assignName;
	private int assignID;
	private Category categ;
	private int catID;
	private Courses course;
	private int courseID;
	private String prefix;
	private int assignNum;
	/*private int number;
	private int maxScore;
	private double value;
	private boolean excuse;
	private boolean dropped;*/
		
	public Assignment(String assignName,Category categ, String prefixName, int assignmentNumber, Courses course)
	{
		this.assignName=assignName;
		this.categ=categ;
		this.catID=categ.getID();
		this.prefix = prefixName;
		this.assignNum = assignmentNumber;
		this.course=course;
		this.courseID=course.getCourseID();
		this.assignID=-1;
	}
	public Assignment(){
		
	}
	
	public Assignment(int id,String assignName,Category categ,Courses course,int assignmentNumber)
	{
		this.assignName=assignName;
		this.categ=categ;
		this.catID=categ.getID();
		this.course=course;
		this.courseID=course.getCourseID();
		this.assignID=id;
		this.assignNum=assignmentNumber;
		this.prefix=categ.getPrefix();
	}
	
	public Assignment(int id,String assignName,Category categ,Courses course,int assignmentNumber, String prefix)
	{
		this.assignName=assignName;
		this.categ=categ;
		this.catID=categ.getID();
		this.course=course;
		this.courseID=course.getCourseID();
		this.assignID=id;
		this.assignNum=assignmentNumber;
		this.prefix=prefix;
	}
	
	
	
	public Assignment(String assignName, int assignID, int catID, int courseID) 
	{
		this.assignName = assignName;
		this.assignID = assignID;
		this.catID = catID;
		this.courseID = courseID;
		this.categ=null;
		this.course=null;
	}

	public String getAssignName() {
		return assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	public int getAssignID() {
		return assignID;
	}

	public void setAssignID(int assignID) {
		this.assignID = assignID;
	}

	public Category getCateg() {
		return categ;
	}

	public void setCateg(Category categ) {
		this.categ = categ;
	}

	public int getCatID() {
		return catID;
	}

	public void setCatID(int catID) {
		this.catID = catID;
	}

	public Courses getCourse() {
		return course;
	}
	public Assignment clonit(){
		try {
			return (Assignment)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public void setPrefix(String prefix)
		{
			this.prefix = prefix;
		}
	
	public String getPrefix()
		{
			return this.prefix;
		}
		
	public void setAssignNum(int number)
		{
			this.assignNum = number;
			
		}
			
	public int getAssignNum()
		{
			return this.assignNum;
		}		
			
	public int getMaxScore()
	{
		return this.categ.getMaxScore();	
	}
	
			
	public String toString()
		{
			String result = "Assignment Name: " + this.assignName + " Category: " + this.categ.getCategoryName();
			return result;
		}
	
	//public String toString2()
	//{
	   //String result ="Assignment Name: " + this.assignName + " Category name: " + this.categ.getCategoryName(); 	  
	   //return result;
	//}
	
}
	

