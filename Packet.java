import java.io.Serializable;
import java.util.ArrayList;


public class Packet implements Serializable {
	String funct=null;
	String parameters=null;
	Person person=null;
	Courses course=null;
	GradingScale scale=null;
	GradingScheme scheme=null;
	GradeSheet sheet=null;
	Roll roll=null;
	Category cat=null;
	ArrayList<Person> people=null;
	NewCourse newcourse=null;
	Assignment assignment=null;
	AssignmentGrade assignmentGrade=null;
	public Packet(String s){
		funct=s;
	}
	public void addCategory(Category c){
		cat=c;
	}
	public void addParameters(String p){
		parameters=p;
	}
	public void addAssignmentGrade(AssignmentGrade a){
		assignmentGrade=a;
	}
	public void addCourse(Courses c){
		course=c;
	}
	public void addAssignment(Assignment a){
		assignment =a;
	}
	public void addRoll(Roll r){
		roll=r;
	}
	public void addGradeSheet(GradeSheet g){
		sheet=g;
	}
	public void addPerson(Person p){
		person=p;
	}
	public void addPeople(ArrayList<Person> p){
		people=p;
	}
	public void addScale(GradingScale s){
		scale=s;
	}
	public void addScheme(GradingScheme s){
		scheme=s;
	}
	public void addNewCourse(NewCourse c) {
		// TODO Auto-generated method stub
		newcourse =c;
		
	}

}
