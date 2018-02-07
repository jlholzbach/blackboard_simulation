import java.io.Serializable;
import java.util.*;

public class GradingScale implements Serializable
{
	//Creates an empty GradingScale with its name
	public GradingScale(String scaleName, Person creator)
	{
		gradingscale = new ArrayList<Grade>();
		this.scaleName=scaleName;
		this.creator = creator;
		this.scaleID=-1;
	}
	public GradingScale(String scaleName,int scale, Person creator)
	{
		gradingscale = new ArrayList<Grade>();
		this.scaleName=scaleName;
		this.creator = creator;
		this.scaleID=scale;
	}
	
	public void setGradingScale(String scaleDivisionName, double cutoff)
	{
		Grade grade = new Grade(scaleDivisionName, cutoff);
		gradingscale.add(grade);
	}
	
	public void setGradingScale(String scaleDivisionName, double cutoff,int sdID)
	{
		Grade grade = new Grade(scaleDivisionName, cutoff,sdID);
		gradingscale.add(grade);
	}
	public void setGradeArray(ArrayList<Grade> gList)
	{
		this.gradingscale=gList;
	}
	
	public void setName(String name)
	{
		this.scaleName = name;
	}
	
	public ArrayList<Grade> getGradingScale()
	{
		return gradingscale;
	}
	
	public String getScaleName()
	{
		return this.scaleName;
	}
	public int getScaleID() {
		return scaleID;
	}
	
	public String toString()
	{
		String temp = this.scaleName+" ";
		//for(int i=0;i<this.gradingscale.size();i++)
		//{
			//temp = temp+((Grade)this.gradingscale.get(i)).getletterGrade()+" "+((Grade)this.gradingscale.get(i)).getCutoffscore();
		//}
		return temp;
	}
	
	public String toStringVerbose()
	{
		String temp = this.scaleName+" ";
		for(int i=0;i<this.gradingscale.size();i++)
		{
			temp = temp+((Grade)this.gradingscale.get(i)).getletterGrade()+" "+((Grade)this.gradingscale.get(i)).getCutoffscore();
		}
		return temp;
	}
		
	public void setScaleID(int scaleID2) {
		this.scaleID=scaleID2;	
	}
	public int size() 
	{
		return gradingscale.size();
	}
		
	private double cutoffscore;
	ArrayList<Grade> gradingscale;
	private String scaleName;
	private int scaleID;
	private Person creator;
	public String letterGrade(double semesterAverage) {
		// TODO Auto-generated method stub
		return "none";
	}
	
	
}
