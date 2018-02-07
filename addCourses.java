import java.util.ArrayList;

public class addCourses {
	
	ArrayList<Courses> addC = new ArrayList<Courses>();
	
	public addCourses()
	{
		addC = new ArrayList<Courses>();

	}
	
	public int addCourse(Courses name)
	{
		int id;
		if (addC.contains(name))
		{
			return -1;
		}
		addC.add(name);
		id = addC.indexOf(name);
		return id;
		

	}
	
	public void remove(Courses name)
	{
	if (!(addC.contains(name))){
	//System.out.println("Course doesn't exist");
	}
	else
		addC.remove(name);
    }

	
}
