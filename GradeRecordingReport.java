import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.io.*;


public class GradeRecordingReport 
{
	ArrayList<Person> listofstudents; //Represents the list of students for the grade report generator
	Courses course; //Represents the course for which the grades will be generated
	String gradereport = ""; //Represents a string representation of the grade report
	static SchoolDatabase sdb; //Represents the school database for which the students are enrolled
	
	//Constructs a grading report Generator
	public GradeRecordingReport(ArrayList<Person> people, Courses course)
	{
		this.listofstudents = people;
		this.course = course;
	}
	
	public double calcAvgStandard(Person student, Category category)
	{
		double sumofpoints = 0.0; //Represents the total sum of assignment grades
		double sumofmaxscore = 0.0; //Represents the total sum of the maximum scores for each assignment
		double categoryscore = 0.0; //Represents the sum of the points divided by the sum of the maximum scores
	
		//Collects a list of assignments for the student in specified course
		ArrayList<AssignmentGrade> gradelist = SchoolDatabase.getAssignmentGrades(student, course);
		
		boolean undefined = true; //Represents if a particular category average is undefined
			
		//Goes through the list of assignments and calculates the average for the category
		for(int k = 0; k < gradelist.size(); k++)
		{
			if((gradelist.get(k).getAssignment().getCateg().getCategoryName()).equalsIgnoreCase(category.getCategoryName()))
			{
				sumofpoints = sumofpoints + gradelist.get(k).getGrade();
				sumofmaxscore = sumofmaxscore + gradelist.get(k).getMaxScore();
				undefined = false;
			}
		}	
		//If there are no assignments defined in a category, then the average is undefined
		if(undefined)
			return -1;
			
		categoryscore = (sumofpoints)/(sumofmaxscore);
		categoryscore = (categoryscore) * (100.0);
		return categoryscore;
	}
	
	
	
	//Calculates the student average for one student for grading strategies where Excuses are allowed
	public double calcAvgExcusesAllowed(Person student, Category category)
	{
		double sumofpoints = 0.0; //Represents the total sum of assignment grades
		double sumofmaxscore = 0.0; //Represents the total sum of the maximum scores for each assignment
		double categoryscore = 0.0; //Represents the sum of the points divided by the sum of the maximum scores
	
		//Collects a list of assignments for the student in specified course
		ArrayList<AssignmentGrade> gradelist = SchoolDatabase.getAssignmentGrades(student, course);
		
		boolean undefined = true; //Represents if the category average is undefined
			
		//Goes through the list of assignments and calculates the average for each individual category
		for(int k = 0; k < gradelist.size(); k++)
		{
			if((gradelist.get(k).getAssignment().getCateg().getCategoryName()).equalsIgnoreCase(category.getCategoryName()) 
					&& gradelist.get(k).getExcused() != true)
			{
				sumofpoints = sumofpoints + gradelist.get(k).getGrade();
				sumofmaxscore = sumofmaxscore + gradelist.get(k).getMaxScore();
				undefined = false;
			}
		}	
		//If a student is excused from all assignments or there are no assignments defined for a category, then the average is undefined
		if(undefined)
			return -1;
		
		categoryscore = (sumofpoints)/(sumofmaxscore);
		categoryscore = (categoryscore) * (100.0);
		return categoryscore;
	}
	
	
	//Calculates the average for one student where drops are allowed
	public double calcAvgDropsAllowed(Person student, Category category)
	{
		double sumofpoints = 0.0;
		double sumofmaxscore = 0.0;
		double categoryscore = 0.0;
		
		//Collects a list of assignments for the student in specified course
		ArrayList<AssignmentGrade> gradelist = SchoolDatabase.getAssignmentGrades(student, course);
		
		int numberofassignments = 0; //Represents the number of assignments in the category
		boolean firstassignment = true;
		boolean undefined = true; //Represents whether the category average is undefined
		
		for(int i = 0; i < gradelist.size(); i++)
			gradelist.get(i).setDropped(false);
		
		//Determines which assignment has the lowest grade
		for(int p = 0; p <= category.getNumDrops(); p++)
		{
			double lowestgrade = 0.0;
			for(int n = 0; n < gradelist.size(); n++)
			{
				//Sets the first assignment grade in the list of assignments to be the lowest score
				if((gradelist.get(n).getAssignment().getCateg().getCategoryName()).equalsIgnoreCase(category.getCategoryName())
						&& firstassignment == true && gradelist.get(n).getDropped() == false)
				{
					lowestgrade = gradelist.get(n).getGrade();
					firstassignment = false;
					n++;
				}
				
				//Sets the next assignment grade to be the lowest score if it is less than the lowest grade variable
				if((gradelist.get(n).getAssignment().getCateg().getCategoryName()).equalsIgnoreCase(category.getCategoryName())
						&& lowestgrade > gradelist.get(n).getGrade() && gradelist.get(n).getDropped() != true)
				{
					lowestgrade = gradelist.get(n).getGrade();
				}	
			}
			
			//Goes through the entire list of assignments again to set the assignment with the lowest grade to be dropped
			for(int s = 0; s < gradelist.size(); s++)
			{
				if((gradelist.get(s).getAssignment().getCateg().getCategoryName()).equalsIgnoreCase(category.getCategoryName())
						&& gradelist.get(s).getGrade() == lowestgrade && gradelist.get(s).getDropped() != true)
				{
					gradelist.get(s).setDropped(true);
				}
			}
		}
			
		//Calculates the average after drops
		for(int d = 0; d < gradelist.size(); d++)
		{
			if((gradelist.get(d).getAssignment().getCateg().getCategoryName()).equalsIgnoreCase(category.getCategoryName()) 
					&& gradelist.get(d).getDropped() != true)
			{
				sumofpoints = sumofpoints + gradelist.get(d).getGrade();
				sumofmaxscore = sumofmaxscore + gradelist.get(d).getMaxScore();
				numberofassignments++;
				undefined = false;
			}
		}		
		if(numberofassignments < category.getNumDrops())
			undefined = true;
			
		if(undefined)
			return -1;
			
		categoryscore = (sumofpoints)/(sumofmaxscore);
		categoryscore = (categoryscore) * (100.0);
		return categoryscore;
	}
	
	public String generateReport() throws IOException, ClassNotFoundException
	{	
		//Sorts the list of students in ascending order according to last name
		Collator sorter = Collator.getInstance();
		Person temp;
		if(listofstudents.size() > 1)
		{
			for(int j = 0; j < listofstudents.size(); j++)
			{
				for(int i = j+1; i < listofstudents.size(); i++)
				{
					if(sorter.compare(listofstudents.get(j).getLastName(), listofstudents.get(i).getLastName()) > 0)
					{
						temp = listofstudents.get(j);
						listofstudents.set(j,  listofstudents.get(i));
						listofstudents.set(i, temp);
					}
				}
			}
		}
		
		//Goes through the entire list of students and generates the grade report for each student
		for(int i = 0; i < listofstudents.size(); i++)
		{
			String lettergrade = "";
			double finalgrade = 0.0;
			
			for(int j = 0; j < SchoolDatabase.getScheme(course).size(); j++)
			{
				if(SchoolDatabase.getScheme(course).getCategory(j).getCategoryStrategy().equalsIgnoreCase("Standard"))
				{
					
					double categoryaverage = 0.0;
					categoryaverage = calcAvgStandard(listofstudents.get(i), SchoolDatabase.getScheme(course).getCategory(j));
					
					if(categoryaverage == -1)
					{
						gradereport = "Not Available";
					}
					else
					{
						double categoryscore = 0.0;
						categoryscore = (categoryaverage) * (SchoolDatabase.getScheme(course).getCategory(j).getCategoryWeightPercent());
						//System.out.println(SchoolDatabase.getScheme(course).getCategory(j).getCategoryName()+" average: "+categoryscore);
						finalgrade = finalgrade + categoryscore;
					}
				}
				
				if(SchoolDatabase.getScheme(course).getCategory(j).getCategoryStrategy().equalsIgnoreCase("Excuses Allowed"))
				{
					double categoryaverage = 0.0;
					categoryaverage = calcAvgExcusesAllowed(listofstudents.get(i), SchoolDatabase.getScheme(course).getCategory(j));

					if(categoryaverage == -1)
					{
						gradereport = "Not Available";
					}
					else
					{
						double categoryscore = 0.0;
						categoryscore = (categoryaverage) * (SchoolDatabase.getScheme(course).getCategory(j).getCategoryWeightPercent());
						//System.out.println(SchoolDatabase.getScheme(course).getCategory(j).getCategoryName()+" average: "+categoryscore);
						finalgrade = finalgrade + categoryscore;
					}
				}
				
				if(SchoolDatabase.getScheme(course).getCategory(j).getCategoryStrategy().equalsIgnoreCase("Drops Allowed"))
				{
					double categoryaverage = 0.0;
					categoryaverage = calcAvgDropsAllowed(listofstudents.get(i), SchoolDatabase.getScheme(course).getCategory(j));
					
					if(categoryaverage == -1)
					{
						gradereport = "Not Available";
					}
					else
					{
						double categoryscore = 0.0;
						categoryscore = (categoryaverage) * (SchoolDatabase.getScheme(course).getCategory(j).getCategoryWeightPercent());
						//System.out.println(SchoolDatabase.getScheme(course).getCategory(j).getCategoryName()+" average: "+categoryscore);
						finalgrade = finalgrade + categoryscore;
					}
				}
			}
			
			for(int m = 0; m < SchoolDatabase.getScale(course).size(); m++)
			{
				if(finalgrade >= SchoolDatabase.getScale(course).getGradingScale().get(m).getCutoffscore())
				{
					lettergrade = SchoolDatabase.getScale(course).getGradingScale().get(m).getletterGrade();
					break;
				}
			}
			
			if(gradereport.equals("Not Available"))
			{
				gradereport = listofstudents.get(i).getLastName() + ", " + listofstudents.get(i).getFirstName() + " " + "Not Available";
			}
			else
			{
				gradereport = gradereport + '\n' + listofstudents.get(i).getLastName() + ", " + listofstudents.get(i).getFirstName() + " " + Double.toString(finalgrade) + " " + lettergrade;
			}
		}	
		
		Date date = new Date();
		String result = course.getCourseName()+ "  " + date + "\n";
		result = result+"-------------------------------------------------------------- \n";
		result = result + gradereport;
		return result;
	}
}
