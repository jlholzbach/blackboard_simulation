import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ExServer extends Thread {

   private ServerSocket Server;
   
 

   public static void main(String argv[]) throws Exception {
	   
     new ExServer();
     Scanner scan=new Scanner(System.in);
     boolean exit=true;
     while(exit){
    	 System.out.println("type exit at anytime to quit server");
    	 String s=scan.next();
    	 if(s.equalsIgnoreCase("exit")){
    		 exit=false;
    	 }
     }
     System.exit(0);
   }
   
   public ExServer() throws Exception {
     Server = new ServerSocket(4444);
     System.out.println("Server listening on port 4444.");
     this.start();
   } 

   public void run() {
     while(true) {
       try {
        System.out.println("Waiting for connections.");
        Socket client = Server.accept();
        System.out.println("Accepted a connection from: "+client.getInetAddress());
        Connect c = new Connect(client);
       } catch(Exception e) {}
     }
   }
}

class Connect extends Thread {
   private Socket client = null;
   private ObjectInputStream ois = null;
   private ObjectOutputStream oos = null;
 	private static SchoolDatabase SchoolDatabase;

    
   public Connect() {}

   public Connect(Socket clientSocket) {
     client = clientSocket;
     try {
			SchoolDatabase = new SchoolDatabase("testH2");
		} catch (ClassNotFoundException e1) {
		
			e1.printStackTrace();
		}
     try {
      ois = new ObjectInputStream(client.getInputStream());
      oos = new ObjectOutputStream(client.getOutputStream());
     } catch(Exception e1) {
         try {
            client.close();
         }catch(Exception e) {
           System.out.println(e.getMessage());
         }
         return;
     }
     this.start();
   }

  
   @SuppressWarnings("static-access")
public void run() {
      try {
    	  Packet p;
    	  boolean go=true;
    	  while(go){
    		  
         while((p=(Packet)ois.readObject())==null){
        	 
         }
    	  //oos.writeObject(new Date());
         //oos.flush();
         // close streams and connections
		 oos.reset();


         if(p.funct.equals("LOGIN")){
        	 Scanner scan=new Scanner(p.parameters);
        	 String userName=scan.next().trim();
        	 String password=scan.next().trim();
        	 Person per=runCheckLoginTest(userName, password);
        	 oos.writeObject(per);  
         }
         else if(p.funct.equals("GETCOURSES")){
        	 ArrayList<Courses> c=null;
        	 if(p.person.isInstructor()){
            	 c=SchoolDatabase.getInstructedCourses(p.person, false);  
        	 }else{
        	 c=SchoolDatabase.getPersonCourses(p.person, false);   
        	 }
        	 
        	 oos.writeObject(c);
         }
         else if(p.funct.equals("GETCOURSESARCHIVED")){
        	 ArrayList<Courses> c=null;
        	 if(p.person.isInstructor()){
            	 c=SchoolDatabase.getInstructedCourses(p.person, true);  
        	 }else{
        	 c=SchoolDatabase.getPersonCourses(p.person, true);   
        	 }
        	 oos.writeObject(c);
         }else if(p.funct.equals("GETROLL")){        	 
        	 oos.writeObject(SchoolDatabase.getCourseRoll(p.course));
         }
         else if(p.funct.equals("ALLROLL")){        	 
        	 oos.writeObject(SchoolDatabase.getAllPeople());
         }
         else if(p.funct.equals("UPDATEROLL")){
        	    	 
        	 SchoolDatabase.updateRoll(p.roll);
         }else if(p.funct.equals("GETASSIGNMENTGRADE")){
        	    	 
        	 oos.writeObject(SchoolDatabase.getAssignmentGrades(p.person, p.course));
         }
         else if(p.funct.equals("GETASSIGNMENT")){
	    	 
        	 oos.writeObject(SchoolDatabase.getAssignments(p.course));
         }
         else if(p.funct.equals("UPDATEPERSON")){
        	 SchoolDatabase.updatePerson(p.person);
         }
         else if(p.funct.equals("UNIQUECOURSENAME")){
        	 boolean b=SchoolDatabase.isNewCourseNameUnique(p.parameters);
        	 Boolean n=(Boolean) b;
        	 oos.writeObject(n);       	 
         }
         else if(p.funct.equals("REMOVECOURSE")){
        	 System.out.println("in remove course");
        	 SchoolDatabase.removeCourse(p.course);
        	 System.out.println("Exiting remove course");
        	 
         }else if(p.funct.equals("ADDNEWCOURSE")){
        	 oos.writeObject(SchoolDatabase.addNewCourse(p.newcourse));
         }
         else if(p.funct.equals("UPDATECOURSE")){
        	 SchoolDatabase.updateCourse(p.course);        	 
         }
         else if(p.funct.equals("ADDSCALE")){
        	oos.writeObject(SchoolDatabase.addScale(p.scale));
        	 
         }
         else if(p.funct.equals("UPDATESCALE")){
         	SchoolDatabase.updateScale(p.scale);
         	 
          }
         else if(p.funct.equals("ADDSCHEME")){
         	SchoolDatabase.addScheme(p.scheme);
         	 
          }
         else if(p.funct.equals("UPDATESCHEME")){
          	SchoolDatabase.updateScheme(p.scheme);
          	 
           }
         else if(p.funct.equals("GETSCALES")){
        	 oos.writeObject(SchoolDatabase.getScale(p.person));
        	 
         }
         else if(p.funct.equals("GETSCHEME")){
        	 ArrayList<GradingScheme> s=SchoolDatabase.getScheme(p.person);
        	 System.out.println("Size:"+s.size());
        	 for(int i=0;i<s.size();i++){
        		 System.out.println(s.get(i).toString());
        	 }
        	 oos.reset();
        	 

        	 oos.writeObject(s);
        	 
         }
         else if(p.funct.equals("GETPERSON")){
        	 oos.writeObject(SchoolDatabase.getPerson(p.parameters));
         }
         else if(p.funct.equals("ALLCOURSE")){
        	 oos.writeObject(SchoolDatabase.getAllCourses());        	 
         }
         else if(p.funct.equals("ADDPERSON")){
        	 oos.writeObject(SchoolDatabase.addPerson(p.person));
         }
         else if(p.funct.equals("REMOVEPERSONDB")){
        	 SchoolDatabase.removeStudent(p.person);
         }
         else if(p.funct.equals("CHECKANDGETCOURSE")){
        	 oos.writeObject(SchoolDatabase.getCourse(p.parameters,p.person));
         }
         else if(p.funct.equals("GRADEREPORT")){
        	 GradeRecordingReport r=new GradeRecordingReport( p.people,p.course);
        	 
        	 oos.writeObject(r.generateReport());
         }
         else if(p.funct.equals("STUDENTREPORT")){
        	 StudentReportGenerator r=new StudentReportGenerator(p.people,SchoolDatabase.getScale(p.course),SchoolDatabase.getScheme(p.course));
        	 
        	 oos.writeObject(r.generate(p.course));
         }
         
         else if(p.funct.equals("UPDATEGRADESHEET")){
        	 SchoolDatabase.updateGradeSheet(p.sheet);
         }
         else if(p.funct.equals("REMOVECATEGORY")){
        	 SchoolDatabase.removeCategory(p.cat);
         }
         else if(p.funct.equals("UPDATEASSIGNMENT")){
        	 SchoolDatabase.updateAssignment(p.assignment);
         }
         else if(p.funct.equals("ADDASSIGNMENT")){
        	 oos.writeObject(SchoolDatabase.addAssignment(p.assignment));
         }
         else if(p.funct.equals("REMOVEASSIGNMENT")){
        	 SchoolDatabase.removeAssignment(p.assignment);
         }
         else if(p.funct.equals("GETSCALECOURSE")){
        	 oos.writeObject(SchoolDatabase.getScale(p.course));
         }
         else if(p.funct.equals("GETSCHEMECOURSE")){
        	 oos.writeObject(SchoolDatabase.getScheme(p.course));
         }
         else if(p.funct.equals("SETSCALECOURSE")){
        	 oos.writeObject(SchoolDatabase.setScale(p.course,p.scale));
         }
         else if(p.funct.equals("SETSCHEMECOURSE")){
        	 
        	 SchoolDatabase.setScheme(p.course,p.scheme);
         }
         else if(p.funct.equals("UPDATEASSIGNMENTGRADE")){
        	 System.out.println("ExServer update Assignment Grade");
        	 SchoolDatabase.updateAssignmentGrade(p.assignmentGrade);
         }
         else if(p.funct.equals("ADDASSIGNMENTGRADE")){
        	 SchoolDatabase.addAssignmentGrade(p.assignmentGrade);
         }
         else if(p.funct.equals("EXIT")){
        	 break;
         }
    	 }
         
         ois.close();
         oos.close();
         client.close(); 
      } catch(Exception e) {
    	  System.out.println("HEllo EXception");
    	  System.err.println(e.getCause());
    	  System.err.println(e.getMessage());
      }       
   }
	private static Person runCheckLoginTest(String user, String password)
	{
		if(SchoolDatabase.checkLogin(user, password))
		{
			return runGetPersonTest(user);
		}
		else
		{
			return (new Person());
		}
	}
	private static Person runGetPersonTest(String user)
	{
		Person test = SchoolDatabase.getPerson(user);
		if(!test.equals(null))
		{
			return test;

		}
		else
		{
			System.out.println("getPerson Fail.");
			return new Person();

		}

	}
}
