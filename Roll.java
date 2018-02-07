import java.io.Serializable;
import java.util.ArrayList;


public class Roll implements Serializable
{
	
	ArrayList<Person> roll = new ArrayList<Person>();
	ArrayList<Person> toBeRemoved = new ArrayList<Person>();
	ArrayList<Person> toBeAdded = new ArrayList<Person>();
	Courses course;

	//TODO add method to compaire 2 roll objects and returns a list of people who are in 1 but not the other
	//TODO sort roll list by last name
	public Roll(Courses course)
	{
		this.course = course;
		roll=new ArrayList<Person>();
	}
	
	public Roll(){}
	
	public int addPerson(Person entry)
	{
		int index;
		if (roll.contains(entry))
		{
			//System.out.println("Person is already on roll");
			return -1;
		}
		
		roll.add(entry);
		index = roll.indexOf(entry);
		
		return index;
	}
	public ArrayList<Person> getToBeRemoved(){
		ArrayList<Person> toBeRemovedTemp = toBeRemoved;
		toBeRemoved = new ArrayList<Person>();
		return toBeRemovedTemp;
	}
	public ArrayList<Person> getToBeAdded(){
		ArrayList<Person> toBeAddedTemp = toBeAdded;
		toBeAdded = new ArrayList<Person>();
		return toBeAddedTemp;
	}
	public int addPersonToBeAdded(Person entry)
	{
		int index;
		if (toBeAdded.contains(entry))
		{
		//	System.out.println("Person is already on roll");
			return -1;
		}
		roll.remove(entry);
		roll.add(entry);
		toBeAdded.add(entry);
		index = toBeAdded.indexOf(entry);
		
		return index;
	}
	public int addPersonToBeRemoved(Person entry)
	{
		int index;
		if (toBeRemoved.contains(entry))
		{
		//	System.out.println("Person is already on roll");
			return -1;
		}
		roll.remove(entry);
		
		toBeRemoved.add(entry);
		index = toBeRemoved.indexOf(entry);
		
		return index;
	}
	
	public Person getPerson(int index)
	{
		Person result;
		if(index<(roll.size()) && index >= 0)
		{
			result=roll.get(index);
			return result;
			
		}
	//	System.out.println("Index out of range");
		return null;
	}
	
	
	public void remove(Person entry)
	{
	if (!(roll.contains(entry))){
//	System.out.println("Person is not on roll");
	}
	else
		roll.remove(entry);
    }
	public int findStudent(int userID)
	{
		
		int count=0;
		while(count<roll.size())
		{
			if(roll.get(count).getID()==userID)
			{
				return count;
			}
			count++;
		}
		
		
		return -1;
	}
	
	public int size()
	{
		int size=roll.size();
		return size;
		
	}

	public Courses getCourse() {
		return course;
	}

}
