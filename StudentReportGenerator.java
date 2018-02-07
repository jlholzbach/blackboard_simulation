
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class StudentReportGenerator 
{
	ArrayList<Person> students;
    GradingScale scale;
    GradingScheme scheme;
    static SchoolDatabase sdb;
    
	 public StudentReportGenerator(ArrayList<Person> students, GradingScale scale, GradingScheme scheme)
    {
    	this.students = students;
    	this.scale = scale;
    	this.scheme = scheme;
 
    }
	
    String result = "";
    String temp = "";
    int num_categories = 0;
    double semester_average = 0;
    String letterGrade;
    Date date = new Date();
    
	public String generate(Courses course) 
	{
		result=result+course.getCourseName()+ "  " + date + "\n";
		result=result+"-------------------------------------------------------------- \n";
		
		
		for(int i=0;i<students.size();i++)
		 {
		int studentAverage=0;
		boolean defined = true;
		result=result+students.get(i).getFullName()+"\n \n";
		
		num_categories=scheme.getGradingScheme().size();
		
		ArrayList<AssignmentGrade> assign =SchoolDatabase.getAssignmentGrades(students.get(i),course);
		for(int j=0;j<num_categories;j++)
		{
			
			result=result+(scheme.getCategory(j).getCategoryName()+"  ("+scheme.getCategory(j).getCategoryWeight())+
			"%"+")"+":\n";
			
			for (int k = 0; k < assign.size(); k++)
			{
				
				if(assign.get(k).getAssignment().getCateg().getCategoryName().equalsIgnoreCase(scheme.getCategory(j).getCategoryName()))
				//if(assign.get(k).getAssignment().getCatID()==scheme.getCategory(j).getID())
				{

					result=result+(assign.get(k).getAssignment().getAssignName());
					result=result+" "+ assign.get(k).getGrade()+"";
					result=result+""+("/"+ assign.get(k).getMaxScore());
					result=result+"\n";
				
				}
				
			}
			
			if(scheme.getCategory(j).getNumDrops()>=1)
			{
				int num_drops = scheme.getCategory(j).getNumDrops();
				result=result+"Average (after dropping "+scheme.getCategory(j).getNumDrops()+"): ";
				result=result+ avgGradeAfterDrops(assign, num_drops,scheme.getCategory(j))+"\n";
				result=result+"        (without drops): ";
				result=result+ avgCategoryGrade(assign,scheme.getCategory(j))+"\n\n";
				semester_average += ((scheme.getCategory(j).getCategoryWeight()/100.0)* avgGradeAfterDrops(assign, num_drops,scheme.getCategory(j)));
			
			}
			
			

			if(scheme.getCategory(j).getNumDrops()==0)
			{
				result=result+"Average: ";
				
				Double check = avgCategoryGrade(assign,scheme.getCategory(j));
				if(check.isNaN() == true)
				{
					result = result + "not defined";
					defined = false;
				}
				
				if(check.isNaN() == false)
				{
					result=result+check;
					semester_average += ((scheme.getCategory(j).getCategoryWeight()/100.0)* avgCategoryGrade(assign,scheme.getCategory(j)));
					
				}
				
				result=result+"\n \n";
				
				
			}
		
		
		for(int k = 0; k < scale.size(); k++)
		{
			
			if(semester_average >= scale.getGradingScale().get(k).getCutoffscore())
			{
				letterGrade = scale.getGradingScale().get(k).getletterGrade();
				break;
			}
			
		}
		}
			result=result+temp;
			result=result+"Semester Average: ";
			
			if(defined == true)
			{
				result=result+ semester_average +" = "+ letterGrade+"\n\n";
			}
			
			if(defined == false)
			{
				
				result = result + "Not avaliable";
			}
			semester_average = 0.0;
		    
		}
		 
		    return result;
	}
	
	

	private double avgGradeAfterDrops(ArrayList<AssignmentGrade> assign, int numDrops, Category cat) {
		
		for (int s = 0; s < assign.size(); s++)
		{
			assign.get(s).setDropped(false);
		}
		double total = 0.0;
		double possible = 0.0;
		double dropped = 0.0;
		double dropped_score = 0.0;
		double smallest = assign.get(0).getGrade();
		double amountDroped=0;
		int dropped_index = 0;
	
		
		for (int k = 0; k < numDrops; k++)
		{
			smallest=Integer.MAX_VALUE;

			for (int i = 0; i < assign.size() ; i++)
			{
				if(cat.getID()==assign.get(i).getAssignment().getCatID() && !assign.get(i).getDropped())
				{	
					//if (i+1 <= assign.size())
					//{	
						if ((assign.get(i).getGrade()  <  smallest) && (assign.get(i).getDropped() != true))
						{
							smallest = assign.get(i).getGrade();
							dropped = smallest;
							dropped_index = i;
							//assign.get(dropped_index).setDropped(true);
						}

						/*if ((assign.get(i).getGrade()  >  smallest) && (assign.get(i).getDropped() != true))
						{
							//smallest = assign.get(i).getGrade();
							dropped = smallest;
							//dropped_index = i;
							//assign.get(dropped_index).setDropped(true);

						}*/
					//}
				}
			} // end for	
			amountDroped +=smallest; 
			dropped_score += assign.get(dropped_index).getMaxScore();
			assign.get(dropped_index).setDropped(true);


		} // end for
		for (int j=0; j < assign.size(); j++)
		{
			if((assign.get(j).getAssignment().getCateg().getCategoryName()).equalsIgnoreCase(cat.getCategoryName())) 
				//if(cat.getCategoryName().equalsIgnoreCase(assign.get(j).getAssignment().getCateg().getCategoryName()))
			{	
				total += assign.get(j).getGrade();
				possible += assign.get(j).getMaxScore();

			}
		} // end for
		
		double tpossible = possible- dropped_score;
		double ttotal = total-amountDroped;
		//System.out.println("possible: "+possible+ "dropedScore: "+dropped_score+" total: "+total+" amountDroped: "+amountDroped);
		return (ttotal/tpossible) * 100.0;
		
	}
	
	private double avgCategoryGrade(ArrayList<AssignmentGrade> assign, Category cat) {
		
		double total = 0;
		double possible= 0;
		for (int i=0; i < assign.size(); i++)
		{
			
			if((assign.get(i).getAssignment().getCateg().getCategoryName()).equalsIgnoreCase(cat.getCategoryName())) 
			//if(cat.getID()==assign.get(i).getAssignment().getCatID())
			{
				total += assign.get(i).getGrade();
				possible += assign.get(i).getMaxScore();
			}
			
		}

		return (total/possible) * 100.0;
	}

}
	


