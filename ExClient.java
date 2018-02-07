
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ExClient {
	 static ObjectOutputStream oos = null;
     static ObjectInputStream ois = null;
   public static void main(String argv[]) {
     
      Socket socket = null;
      try {
        // open a socket connection
        socket = new Socket("localhost", 4444);
        // open I/O streams for objects
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

    	loginInterface login= new loginInterface(); 
      } catch(Exception e) {
        System.out.println(e.getMessage());
      }
   }
   public static void cutConnections(){
	   try {
		oos.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
       try {
		ois.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
   }
	 public static Person sendLogin( String x) throws ClassNotFoundException, IOException{
		 Packet p= new Packet("LOGIN");
		 p.addParameters(x);
		 oos.reset();
		 oos.writeObject(p);		 		  
		  Person per= new Person();
		try {
			per = (Person)ois.readObject();
		} catch (IOException e) {
			System.out.println("Problem recieving data");
			e.printStackTrace();
		}
		  
		  return per;
	 }
	 public static ArrayList<Courses> getPersonCoursesCurrent(Person per) throws IOException{
		 Packet p=new Packet("GETCOURSES");
		 p.addPerson(per);
		 oos.reset();
		 oos.writeObject(p);
		 ArrayList<Courses> c=null;
		try {
			c = (ArrayList<Courses>)ois.readObject();
		} catch (IOException e) {
			System.out.println("Problem recieving data");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;

	 }
	 public static ArrayList<Courses> getPersonCoursesArchived(Person per) throws IOException{
		 Packet p=new Packet("GETCOURSESARCHIVED");
		 p.addPerson(per);
		 oos.reset();
		 oos.writeObject(p);
		 ArrayList<Courses> c=null;
		try {
			c = (ArrayList<Courses>)ois.readObject();
		} catch (IOException e) {
			System.out.println("Problem recieving data");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;

	 }
	 public static ArrayList<GradingScale> getInstructorsScale(Person per) throws IOException{
		 Packet p=new Packet("GETSCALES");
		 p.addPerson(per);
		 oos.reset();
		 oos.writeObject(p);
		 ArrayList<GradingScale> c=null;
		try {
			c = (ArrayList<GradingScale>)ois.readObject();
		} catch (IOException e) {
			System.out.println("Problem recieving data");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;

	 }
	 public static ArrayList<GradingScheme> getInstructorsScheme(Person per) throws IOException, ClassNotFoundException{
		 Packet p=new Packet("GETSCHEME");
		 p.addPerson(per);
		 oos.reset();
		 oos.writeObject(p);
		// System.out.println("here 2.975");
		 ArrayList<GradingScheme> c=null;
		
			c = (ArrayList<GradingScheme>)ois.readObject();
		//	 System.out.println("here 2.977");

		
	//	System.out.println("Size client:"+c.size());
		return c;

	 }
	 public static void updatePerson(Person per) throws IOException{
		 Packet p=new Packet("UPDATEPERSON");
		 p.addPerson(per);
		 oos.reset();
		 oos.writeObject(p);

	 }
	 
	 public static Person addPerson(Person per) throws IOException, ClassNotFoundException{
		 Packet p=new Packet("ADDPERSON");
	//	 System.out.println("In add Person");
		 p.addPerson(per);
		 oos.reset();
		 oos.writeObject(p);
		 Person per2=null;
		 per2=(Person)ois.readObject();
		 return per2;

	 }
	 public static Person getPerson(String userName) throws IOException{
		 Packet p=new Packet("GETPERSON");
		 p.addParameters(userName);
		 oos.reset();
		 oos.writeObject(p);
		 Person c=null;
			try {
				c = (Person)ois.readObject();
			} catch (IOException e) {
				System.out.println("Problem recieving data");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return c;

	 }
	 public static Courses checkAndGetCourse(String title,Person per) throws IOException{
		 Packet p=new Packet("CHECKANDGETCOURSE");
		 p.addParameters(title);
		 p.addPerson(per);
		 oos.reset();
		 oos.writeObject(p);
		 Courses c=null;
			try {
				c = (Courses)ois.readObject();
			} catch (IOException e) {
				System.out.println("Problem recieving data");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return c;

	 }
	 public static ArrayList<Courses> getAllCourses() throws IOException, ClassNotFoundException{
		 Packet p=new Packet("ALLCOURSE");
		 oos.reset();
		 oos.writeObject(p);
		 
		 return (ArrayList<Courses>)ois.readObject();
	 }
	 
	 public static Courses getSingleCourse(String courseName) throws IOException{
		 Packet p=new Packet("SINGLECOURSE");
		 p.addParameters(courseName);
		 oos.reset();
		 oos.writeObject(p);
		 Courses c=null;
			try {
				c = (Courses)ois.readObject();
			} catch (IOException e) {
				System.out.println("Problem recieving data");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return c;
	 }
	 public static GradingScale getScale(Courses course) throws IOException{
		 Packet p=new Packet("GETSCALECOURSE");
		 p.addCourse(course);
		 oos.reset();
		 oos.writeObject(p);
		 GradingScale c=null;
			try {
				c = (GradingScale)ois.readObject();
			} catch (IOException e) {
				System.out.println("Problem recieving data");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	 }
	 public static GradingScheme getScheme(Courses course) throws IOException{
		 Packet p=new Packet("GETSCHEMECOURSE");
		 p.addCourse(course);
		 oos.reset();
		 oos.writeObject(p);
		 GradingScheme c=null;
			try {
				c = (GradingScheme)ois.readObject();
			} catch (IOException e) {
				System.out.println("Problem recieving data");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	 }
	 public static  void updateCourse(Courses course) throws IOException{
		 Packet p=new Packet("UPDATECOURSE");
		 p.addCourse(course);
		 oos.reset();
		 oos.writeObject(p);

	 }
	 public static  Roll getRoll(Courses course) throws IOException, ClassNotFoundException{
		 Packet p=new Packet("GETROLL");
		 p.addCourse(course);
		 oos.reset();
		 oos.writeObject(p);
		 return (Roll)ois.readObject();

	 }
	 public static  ArrayList<Person> getAll() throws IOException, ClassNotFoundException{
		 Packet p=new Packet("ALLROLL");
		 oos.reset();
		 oos.writeObject(p);
		 return (ArrayList<Person>)ois.readObject();

	 }
	 public static  void updateRoll(Roll roll) throws IOException{
		 Packet p=new Packet("UPDATEROLL");
		 p.addRoll(roll);
		// System.out.println("updating roll");
	//	 System.out.println("size to be added "+roll.toBeAdded.size());
		 oos.reset();
		 oos.writeObject(p);

	 }
	 public static ArrayList<AssignmentGrade> getAssignmentGrade(Person per, Courses c) throws IOException, ClassNotFoundException{
		 Packet p=new Packet("GETASSIGNMENTGRADE");
		 p.addPerson(per);
		 p.addCourse(c);
		 oos.reset();
		 oos.writeObject(p);
		 return(ArrayList<AssignmentGrade>)ois.readObject();

	 }
	 public static ArrayList<Assignment> getAssignment(Courses c) throws IOException, ClassNotFoundException{
		 Packet p=new Packet("GETASSIGNMENT");
		 p.addCourse(c);
		 oos.reset();
		 oos.writeObject(p);
		 return(ArrayList<Assignment>)ois.readObject();

	 }
	 public static GradingScale addScale(GradingScale scale) throws IOException{
		 Packet p=new Packet("ADDSCALE");
		 p.addScale(scale);
		 oos.reset();

		 oos.writeObject(p);
		 GradingScale g=null;
		 
		 try {
			g= (GradingScale)ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;

	 }
	 public static  void addScheme(GradingScheme scheme) throws IOException{
		 Packet p=new Packet("ADDSCHEME");
		 p.addScheme(scheme);
		 oos.reset();

		 oos.writeObject(p);

	 }
	 public static  void updateScheme(GradingScheme scheme) throws IOException{
		 Packet p=new Packet("UPDATESCHEME");
		 p.addScheme(scheme);
		 oos.reset();

		 oos.writeObject(p);

	 }
	 public static  void updateGradeSheet(GradeSheet sheet) throws IOException{
		 Packet p=new Packet("UPDATEGRADESHEET");
		 p.addGradeSheet(sheet);
		 oos.reset();

		 oos.writeObject(p);

	 }
	 public static  void updateAssignment(Assignment a) throws IOException{
		 Packet p=new Packet("UPDATEASSIGNMENT");
		 p.addAssignment(a);
		 oos.reset();

		 oos.writeObject(p);

	 }
	 public static  int addAssignment(Assignment a) throws IOException, ClassNotFoundException{
		 Packet p=new Packet("ADDASSIGNMENT");
		 p.addAssignment(a);
		 oos.reset();

		 oos.writeObject(p);
		 return (Integer)ois.readObject();

	 }
	 public static  void removeAssignment(Assignment a) throws IOException{
		 Packet p=new Packet("REMOVEASSIGNMENT");
		 p.addAssignment(a);
		 oos.reset();
		 oos.writeObject(p);

	 }
	 public static  void updateAssignmentGrade(AssignmentGrade a) throws IOException{
		// System.out.println("ExClient update assignment grade");
		 Packet p=new Packet("UPDATEASSIGNMENTGRADE");
		 p.addAssignmentGrade(a);
		 oos.reset();
		 oos.writeObject(p);

	 }
	 public static  void addAssignmentGrade(AssignmentGrade a) throws IOException{
		 Packet p=new Packet("ADDASSIGNMENTGRADE");
		 p.addAssignmentGrade(a);
		 oos.reset();
		 oos.writeObject(p);

	 }
	 public static  Courses setScale(GradingScale scale,Courses c) throws IOException, ClassNotFoundException{
		 Packet p=new Packet("SETSCALECOURSE");
		 p.addScale(scale);
		 p.addCourse(c);
		 oos.reset();

		 oos.writeObject(p);
		 return (Courses)ois.readObject();

	 }
	 public static void removePersonFromDB(Person per) throws IOException{
		 Packet p=new Packet("REMOVEPERSONDB");
		 p.addPerson(per);
		 oos.reset();
		 oos.writeObject(p);
	 }
	 public static String getGradeReport(Courses course, ArrayList<Person> per) throws IOException, ClassNotFoundException{
		 
		 oos.reset();
		 Packet p= new Packet("GRADEREPORT");
		 p.addCourse(course);
		 p.addPeople(per);
		 oos.writeObject(p);
		 
		 return (String)ois.readObject();
		 
	 }
	 public static String getStudentReport(Courses course, ArrayList<Person> per) throws IOException, ClassNotFoundException{
		 oos.reset();
		 Packet p= new Packet("STUDENTREPORT");
		 p.addCourse(course);
		 p.addPeople(per);
		 oos.writeObject(p);
		 
		 return (String)ois.readObject();
	 }
	 public static  void setScheme(GradingScheme scale,Courses c) throws IOException{
		 Packet p=new Packet("SETSCHEMECOURSE");
		 p.addScheme(scale);
		 p.addCourse(c);
		 oos.reset();

		 oos.writeObject(p);

	 }
	 public static  void updateScale(GradingScale scale) throws IOException{
		 Packet p=new Packet("UPDATESCALE");
		 p.addScale(scale);
		 oos.reset();

		 oos.writeObject(p);

	 }
	 public static  boolean uniqueCourseName(String s) throws IOException, ClassNotFoundException{
		 Packet p=new Packet("UNIQUECOURSENAME");
		 p.addParameters(s);
		 oos.reset();

		 oos.writeObject(p);		 
		 boolean b=false;		 
		 Boolean bb=(Boolean)ois.readObject();
		 b=bb.booleanValue();
		 return b;
	 }
	 public static  void removeCourse(Courses c) throws IOException{
		 Packet p= new Packet("REMOVECOURSE");
		 p.addCourse(c);
		 oos.reset();

		 oos.writeObject(p);		 
	 }
	 
	 public static  NewCourse addCourse(NewCourse c) throws IOException, ClassNotFoundException{
		 Packet p= new Packet("ADDNEWCOURSE");
		 p.addNewCourse(c);		 
		 oos.reset();

		 oos.writeObject(p);
		return (NewCourse)ois.readObject();
	 }
	 public static void exit() throws IOException{
		 Packet p= new Packet("EXIT");
		 oos.reset();

		 oos.writeObject(p);
	 }
	 public static void removeCategory(Category c) throws IOException{
		 Packet p= new Packet("REMOVECATEGORY");
		 p.addCategory(c);
		 oos.reset();
		 oos.writeObject(p);
	 }
	 
}