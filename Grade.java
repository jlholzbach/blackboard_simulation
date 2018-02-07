import java.io.Serializable;

public class Grade implements Serializable
{	
	private String lettergrade;
	private double cutoffscore;
	private int gradeID;
	
	public Grade(String lettergrade, double cutoffscore)
	{
		this.lettergrade = lettergrade;
		this.cutoffscore = cutoffscore;
		this.gradeID=-1;
	}
	
	public Grade(String lettergrade, double cutoffscore,int id)
	{
		this.lettergrade = lettergrade;
		this.cutoffscore = cutoffscore;
		this.gradeID=id;
	}
	
	public void setLetterGrade(String grade)
	{
		this.lettergrade = grade;
	}
	
	public void setCutoff(double cutoff)
	{
		this.cutoffscore = cutoff;
	}
	
	public String getletterGrade()
	{
		return lettergrade;
	}
	
	public double getCutoffscore()
	{
		return cutoffscore;
	}

	public int getGradeID() {
		return gradeID;
	}

	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}
	
	public String toString()
	{
		return "Letter Grade " + lettergrade + "";

	}
}