import java.io.IOException;
import java.util.ArrayList;


public class StudentReportGeneratorTestDriver {

	private static SchoolDatabase SDB;
	public static void main(String[] args) {
		try {
			SDB= new SchoolDatabase("testDB.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ArrayList<Person> students, GradingScale scale, GradingScheme scheme)
		try {
			SDB = new SchoolDatabase("test2.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Courses cmsc420 = SDB.getCourse(1);
		StudentReportGenerator SRG = new StudentReportGenerator(SDB.getCourseRoll(cmsc420).roll,
				SDB.getScale(cmsc420),SDB.getScheme(cmsc420));
		String output = SRG.generate(cmsc420);
		System.out.print(output);
	}

}
