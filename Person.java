
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Person implements Serializable
{
	 
	   private String firstName;
	   private String lastName;
	   private String userName;
	   private int id;
	   private String password;
	   private boolean isAdmin;
	   private boolean isInstructor;
	   ArrayList enrolledCourses;
	   private boolean isActive;
	   private boolean nullPerson=false;
	   private static SchoolDatabase sdb = new SchoolDatabase();
   
	public Person()
   {	
	   nullPerson=true;
	  
   }
   
	public Person(String firstName, String lastName, String userName, String password, boolean instructor, boolean admin)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.userName = userName;
      this.password = password;
      this.isInstructor = instructor;
      this.isAdmin = admin;
      this.enrolledCourses = new ArrayList();
      isActive = true;
      nullPerson=false;
      this.id=-1;
   }
	
	public Person(int id, String firstName, String lastName, String userName, String password, boolean instructor, boolean admin)
   {
	  this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.userName = userName;
      this.password = password;
      this.isInstructor = instructor;
      this.isAdmin = admin;
      this.enrolledCourses = new ArrayList();
      isActive = true;
      nullPerson=false;
   }
	
	public Person(String id, String firstName, String lastName, String userName, String password, boolean instructor, boolean admin)
	   {
		  this.id = Integer.parseInt(id);
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.userName = userName;
	      this.password = password;
	      this.isInstructor = instructor;
	      this.isAdmin = admin;
	      this.enrolledCourses = new ArrayList();
	      isActive = true;
	      nullPerson=false;
	   }
   
   public Person(String userName)
   {
	   this.userName=userName;
   }
   public boolean isNullPerson(){
	   return nullPerson;
   }
   
 

   public String getFullName()
   {
	   return firstName+" "+lastName;
   }
   
   public String getFirstName() 
   {
	   return firstName;
   }

   public String getLastName() 
   {
	   return lastName;
   }
   
   public String getUserName()
   {
	   return userName;
   }
   
   public String getPassword()
   {
	   return this.password;
   }
   
   public boolean isInstructor() 
   {
	   return isInstructor;
   }
   
   
   public boolean isAdmin() 
   {
	   return isAdmin;
   }
   
  
   public List getCoursesEnrolled() 
   {
	   return enrolledCourses;
	   
   }
   
   public int getID()
   {
	   return id;
   }
   
   
   
   public void setUserName(String userName) 
   {
	   this.userName = userName;
   }
   
   
   public void setPassword(String password)
   {  
		this.password = password; 
	}
   
   
   public void setID(int id) 
   {
	   this.id= id;
   }
   
   public void setID(String id)
   {
	   this.id = Integer.getInteger(id);
   }
   
   public void setFirstName(String first)
		{
			this.firstName = first;
		}
		
	public void setLastName(String last)
		{
			this.lastName = last;
		}	
   public void addCourses(String course)
   {
   	   enrolledCourses.add(course);
   }
   
   public void setAsAdmin()
   {
	   this.isAdmin=true;
   }
    
  
   public void setProfessor(boolean type)
		{
			this.isInstructor = type;
		}
		
	public void setAdmin(boolean type)
		{
			this.isAdmin = type;
		}
			
   public String toString()
   {
	 	return userName;
	   
   }
   
	public String toStringVerbose()
		{
					String result = "";
	   	  result = result + "Name: " + firstName + " "+lastName+" userName: "+userName+ " userID: " + id + " Title: ";
	   	  if(isAdmin) {result += " Administrator ";}
	   	  if(isInstructor) {result += " Instructor ";}
	   	  else {result += " Student ";}
	   	  
	   	  return result;
		}
	
   public void updatePersonInDB() throws SQLException
   {
	   try {
		sdb.updatePerson(this);
	} catch (SQLException e) {
		throw e;
	}
   }
  
}
