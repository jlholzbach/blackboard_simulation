import java.io.IOException;
import java.util.*;

public class GradeRecordingReportTestDriver {

	/**
	 * @param args
	 */
	private static SchoolDatabase SDB;
	public static void main(String[] args) {
		try {
			SDB = new SchoolDatabase("testh2");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Courses cmsc420 = SchoolDatabase.getCourse(1);
		Roll cmsc420Roll = SDB.getCourseRoll(cmsc420);
		ArrayList<Person> pArr = new ArrayList<Person>();
		for(int i=0;i<cmsc420Roll.roll.size();i++)
		{
			pArr.add(cmsc420Roll.roll.get(i));
			//pArr[i]=cmsc420Roll.roll.get(i);
		}
		GradeRecordingReport GRR = new GradeRecordingReport(pArr,cmsc420);
		try {
			String output = GRR.generateReport();
			System.out.print(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
