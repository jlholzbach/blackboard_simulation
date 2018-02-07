//Needs Grading strategy 
//Option to add max score
//Method/Methods to modify scheme
import java.io.Serializable;
import java.util.*;

public class GradingScheme implements Serializable
{
	
	private int schemeID;
	private String schemeName;
	private String categoryname;
	private Person schemeCreator;
	private ArrayList<Category> gradingscheme;
	
	//use this constructor when making a brand new scheme
	public GradingScheme(String schemeName, Person creator)
	{
		setGradingscheme(new ArrayList<Category>());
		this.schemeName = schemeName;
		schemeID=-1;
		this.schemeCreator = creator;
	}
	
	//use this constructor when building the scheme from existing info, such as the database
	public GradingScheme(String schemeName, int schemeID, Person creator)
	{
		setGradingscheme(new ArrayList<Category>());
		this.schemeName = schemeName;
		this.schemeID=schemeID;
		this.schemeCreator=creator;
	}
	
	//used for making a new category
	public void setgradingSchemeCategory(String categoryName, String gradingStrategy, int categoryweight, boolean prefix, String prefixName, boolean hasMaxScore,int maxScore, int numOfDrops)
	{
		Category category = new Category(categoryName, gradingStrategy, categoryweight, prefix,prefixName, hasMaxScore,maxScore, numOfDrops);
		getGradingscheme().add(category);
	}
	
	//used for making a category object from existing info, such as the DB
	public void setgradingSchemeCategory(int ID,String categoryName, String gradingStrategy, int categoryweight, boolean prefix, String prefixName, boolean hasMaxScore,int maxScore, int numOfDrops)
	{
		Category category = new Category(ID,categoryName, gradingStrategy, categoryweight, prefix,prefixName, hasMaxScore,maxScore, numOfDrops);
		getGradingscheme().add(category);
	}
	
	public ArrayList<Category> getGradingScheme()
	{
		return getGradingscheme();
	}
	
	
	public String getSchemeName() {
		return schemeName;
	}

	public Category getCategory(int index)
	{
		return getGradingscheme().get(index);
	}

	public Person getCreator()
	{
		return schemeCreator;
	}
	
	public void setName(String name)
	{
		this.schemeName = name;
	}

	public void setGradingscheme(ArrayList<Category> gradingscheme) {
		this.gradingscheme = gradingscheme;
	}

	public ArrayList<Category> getGradingscheme() {
		return gradingscheme;
	}
	
	public int size()
	{
		return gradingscheme.size();
	}

	public int getSchemeID() {
		return this.schemeID;
	}		

	public String toString()
	{
		String result=schemeName+" ";
		return result;
	}
	
	public String toStringVerbose()
	{
		String result=schemeName+" ";
		for(int i=0; i<gradingscheme.size();i++)
		{
			//result=result+gradingscheme.get(i).toString()+" ";
		}
		return result;
	}

	public void setSchemeID(int schemeID2) {
		this.schemeID = schemeID2;
	}
	
	//what is this for?
	/*public int gradingStrategy(String strategy)
	{
		if(strategy.equalsIgnoreCase("standard"))
		{
			
		}
		
		else if(strategy.equalsIgnoreCase("Drops Allowed"))
		{
			
		}
		
		else if(strategy.equalsIgnoreCase("Excuses Allowed"))
		{
			
		}
		
		else
			return 0;
	}*/
}
