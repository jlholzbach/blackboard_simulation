import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;



class SchoolDatabase {
  /**
   * file location of the database,  needed for sqlite
   */
  private static String dbFile;
  private static Connection connection;
  private static  Statement statement;

  /**
   * iniceates the database object.  if the provided file name does not point at a valid database then throw no DB error
   */
  public SchoolDatabase(String dbName)throws ClassNotFoundException
  {
	  Class.forName("org.h2.Driver");
	  SchoolDatabase.dbFile=dbName;
	  makeDB();
  }
  
  public SchoolDatabase()
  {
	  
  }
  
  
  private static void openConnection() throws SQLException
  {
  //random change
	  connection = null;
	  try
	  {
		  connection = DriverManager.getConnection("jdbc:h2:"+dbFile+";IFEXISTS=TRUE","sa","");
	      statement = connection.createStatement();
	      statement.setQueryTimeout(30);
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  private void makeDB()
  {
      try
      {
    	 openConnection();
    	 statement.executeQuery("select * from person");
      }
      catch(SQLException e)
	  {
    	  //if db does not exist, set it up
    	  if(e.getErrorCode()==90013)
    	  {/*
    		  try
    		  {
	    		  BufferedReader br = new BufferedReader(new FileReader("defaultDB.sql"));
	    		  String buffer;
	    		  StringBuffer dbString= new StringBuffer();
	    		  
    			  while ((buffer = br.readLine()) != null) 
    			  {
    				  // this statement reads the line from the file and print it to
    				  // the console.
    				  dbString.append(buffer);
    				  dbString.append("\n");
    			  }
    			  statement.setQueryTimeout(200);
    			  statement.executeUpdate(dbString.toString());
    		  }
    		  catch(Exception f)
    		  {
    			  System.err.println(f.getMessage());
    		  }
    		*/
    		  try
			{
				connection = DriverManager.getConnection("jdbc:h2:"+dbFile+";INIT=RUNSCRIPT FROM 'defaultDB.sql'","sa","");
				statement = connection.createStatement();
	    	      statement.setQueryTimeout(30);
	    	      statement.executeQuery("select * from person");
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	      
    		  
    	  }
    	  else
    	  {
		      // if the error message is "out of memory", 
		      // it probably means no database file is found
    		  System.err.println(e.getMessage());
    	  }
	  }
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
  }
  
  /**
   * When given a username, return Person object
   */
  public void executeTestSelect(String select) {
	  
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		 //String temp =String.format("SELECT * FROM person WHERE user_name = '%s';",userName);
		  rs = statement.executeQuery(select);
		  ResultSetMetaData rsmd=rs.getMetaData();
		  int numberOfColumns = rsmd.getColumnCount();
		  String temp ="";
		  for(int i=1;i<=numberOfColumns;i++)
		  {
			  temp = temp+rsmd.getColumnLabel(i)+" ";	  
		  }
		  System.out.println(temp);
		  while(rs.next()){
			  temp ="";
			  for(int i=1;i<=numberOfColumns;i++)
			  {
				  temp = temp+rs.getString(i)+" ";
			  }
			  System.out.println(temp);
		  }

	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	        connection.close();
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  
  }

  /**
   * When provided a username and password, confirms or denies if the combination is valid
   */
  public static boolean checkLogin(String userName, String password) {
	  Boolean result=false;
	 
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT * FROM person WHERE user_name = '%s' AND password='%s';",userName,password);
		  rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=true; 
		  }	  
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	        connection.close();
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }
  
  public static boolean isNewCourseNameUnique(String course)
  {
	  Boolean result=true;	 
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		 String temp =String.format("SELECT * FROM course WHERE course_name = '%s';",course);
		  rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=false; 
		  }	  
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	        connection.close();
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result; 
  }
  
  public static boolean isNewPerson(String userName)
  {
	  Boolean result=true;	 
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT * FROM person WHERE user_name = '%s';",userName);
		  rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=false; 
		  }	  
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	  statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result; 
  }
  
  private static boolean queryHasResult(String query)
  {
	  Boolean result=false;	 
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		  rs = statement.executeQuery(query);
		  if(rs.next())
		  {
			  result=true; 
		  }	  
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	  statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result; 
  }

  public static ArrayList<Person> getAllPeople()
  {
	  ArrayList<Person> pplList = new ArrayList<Person>();
	  try
	  {
		  openConnection();
		  String temp ="SELECT idPerson FROM person";
		  ResultSet rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {
			  pplList.add(getPerson(rs.getInt("idPerson")));
		  }	
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return pplList;	
  }
  
  public static ArrayList<Courses> getAllCourses()
  {
	  ArrayList<Courses> courseList = new ArrayList<Courses>();
	  try
	  {
		  openConnection();
		  String temp ="SELECT idCourse FROM course";
		  ResultSet rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {
			  courseList.add(getCourse(rs.getInt("idCourse")));
		  }	
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return courseList;	
  }
  
  /**
   * @author Buck Helmke
   * When given a username, return Person object
   * @param userName The user name of the person.
   * @return returns a Person object or null if fail.
   */
  public static Person getPerson(String userName) {
	  Person result = new Person();
	  
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		 String temp =String.format("SELECT * FROM person WHERE user_name = '%s';",userName);
		  rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=new Person(rs.getString("idPerson"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("user_name"),rs.getString("password"),rs.getBoolean("isInstrustor"),rs.getBoolean("isAdmin")); 
		  }	
		  else
		  {
			  result = null;
		  }
		  rs.close();
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;  
  }
  
  /**
   * @author Buck Helmke
   * when given a personID returns a person object or NULL if fail
   * @param personID the id of the person
   * @return a Person object or null if fail
   */
  public static Person getPerson(int personID) {
	  Person result = new Person();  
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT * FROM person WHERE idPerson = '%s';",personID);
		  rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=new Person(rs.getString("idPerson"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("user_name"),rs.getString("password"),rs.getBoolean("isInstrustor"),rs.getBoolean("isAdmin")); 
		  }	
		  else
		  {
			  result = null;
		  }
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	rs.close();
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result; 
  }
  
  /**
   * When given a courseID, return Course object
   */
  public static Courses getCourse(int courseID) {
	  Courses result =null;// = new Courses();
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT course_name, idCourse, idScheme, idScale, isArchive, idPerson FROM course WHERE idCourse='%s';",courseID);
		  rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {  
			  result=new Courses(rs.getString("course_name"),rs.getInt("idCourse"),rs.getInt("idScheme"),rs.getInt("idScale"),getPerson(rs.getInt("idPerson")),rs.getBoolean("isArchive"));
		  }	
		  else
		  {
			  result = null;
		  }
		  rs.close();
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;  
  }
  
  /**
   * When given a course name and an instructor, return Course object
   */
  public static Courses getCourse(String courseName,Person instructor) {
	  Courses result =null;// = new Courses();
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT course_name, idCourse, idScheme, idScale, isArchive, idPerson "+
				  "FROM course WHERE idPerson='%s' AND course_name='%s';",instructor.getID(),courseName);
		  rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {  
			  result=new Courses(rs.getString("course_name"),rs.getInt("idCourse"),rs.getInt("idScheme"),rs.getInt("idScale"),getPerson(rs.getInt("idPerson")),rs.getBoolean("isArchive"));
		  }	
		  else
		  {
			  result = null;
		  }
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	  rs.close();
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;  
  }

  

  /**
   * when given a person, return all courses the person is enroled in as an array of Courses
   * when includeArchive is true only archived courses are returned
   * when includeArchive is false, only non archived courses are returned
   */
  public static ArrayList<Courses> getPersonCourses(Person person, Boolean includeArchive) {
	  ArrayList<Courses> result = new ArrayList<Courses>();
	  ResultSet rs=null;
	  try
	  { 
		  openConnection();
		 String temp =String.format("SELECT course.idCourse, course.course_name, course.idPerson, course.idScheme, course.idScale, course.isArchive FROM course INNER JOIN roll on roll.idCourse =course.idCourse INNER JOIN person on person.idPerson = roll.idPerson WHERE person.user_name = '%s' AND isArchive='%s' ",person.getUserName(),(includeArchive ? 1:0));
		  rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {  
			  result.add(new Courses(rs.getString("course_name"),rs.getInt("idCourse"),rs.getInt("idScheme"),rs.getInt("idScale"),getPerson(rs.getInt("idPerson")),rs.getBoolean("isArchive"))); 
		  }	
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	  rs.close();
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }

  /**
   * when given a course, provide a Roll of all Person's in said course
   */
  public static Roll getCourseRoll(Courses course) {
	  Roll result = new Roll(course);  
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT idCourse, idPerson "+
				  "FROM roll WHERE roll.idCourse = '%s';",course.getCourseID());
		  rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {	  
			  result.addPerson(getPerson(rs.getInt("idPerson"))); 
		  }	
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	  rs.close();
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }

  /**
   * when given a course, return the grade scale associated with the course
   */
  public static GradingScale getScale(Courses course) 
  {
	  GradingScale result = null;
	  try
	  {
		  //System.out.println("Starting getScale");
		  openConnection();
		  String temp =String.format("SELECT scale.idScale, scale.scale_name FROM scale INNER JOIN course on course.idScale = scale.idScale WHERE  course.idCourse='%s';",course.getCourseID());
		  ResultSet rs = statement.executeQuery(temp.toString());
		  
		  if(rs.next())
		  {
			  result = new GradingScale(rs.getString("scale_name"),rs.getInt("idScale"),course.getInstructor());
			  temp =String.format("SELECT sd.idScaleDivision, sd.idScale, sd.grade_name, sd.grade_cutoff FROM scaleDivision as sd WHERE sd.idScale = '%s';",rs.getString("idScale"));
			  Statement statement2 = connection.createStatement();
		      statement2.setQueryTimeout(30);
		      //System.out.println("Starting getDivision");
			  ResultSet sd =statement2.executeQuery(temp.toString());
			  while(sd.next())
			  {
				  //System.out.println("getting Division");
				  result.setGradingScale(sd.getString("grade_name"), sd.getDouble("grade_cutoff"),sd.getInt("idScaleDivision"));
			  } 
			  sd.close();
			  statement2.close();
		  }	
		  else
		  {
			  result = null;
		  }
		  //System.out.println("FINSIH GETSCALE");
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }
  
  /*
   * when given a scaleID, return a GradingScale associated with that ID
   */
  public static GradingScale getScale(int scaleID) 
  {
	  GradingScale result = null;
	  try
	  {
		  openConnection();
		  Statement statement2 = connection.createStatement();
	      statement2.setQueryTimeout(30);
		  String temp =String.format("SELECT scale.idScale, scale.scale_name, course.idPerson FROM scale INNER JOIN course on course.idScale = scale.idScale WHERE scale.idScale ='%s';",scaleID);
		  ResultSet rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result = new GradingScale(rs.getString("scale_name"),rs.getInt("idScale"),getPerson(rs.getString("idPerson")));
			  temp =String.format("SELECT idScaleDivision, idScale, grade_name, grade_cutoff FROM scaleDivision WHERE idScale = '%s';",rs.getString("idScale"));
			  
			  ResultSet sd = statement2.executeQuery(temp.toString());
			  while(sd.next())
			  {
				  result.setGradingScale(sd.getString("grade_name"), sd.getDouble("grade_cutoff"),sd.getInt("idScaleDivision"));
			  } 
			  sd.close();
		  }	
		  else
		  {
			  result = null;
		  }
		  statement2.close();
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  catch(Exception e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }
  
  /*
   * when given an instructor, provide all grading scales associated with that instructor.
   */
  public static ArrayList<GradingScale> getScale(Person instructor) 
  {
	  GradingScale tempScale = null;
	  ArrayList<GradingScale> resList = new ArrayList<GradingScale>();
	  try
	  {
		  openConnection();
		  Statement statement2 = connection.createStatement();
	      statement2.setQueryTimeout(30);
		  String temp =String.format("SELECT scale.idScale, scale.scale_name FROM scale INNER JOIN course on course.idScale = scale.idScale WHERE course.idPerson='%s';",instructor.getID());
		  ResultSet rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {
			  tempScale = new GradingScale(rs.getString("scale_name"),rs.getInt("idScale"),instructor);
			  temp =String.format("SELECT sd.idScaleDivision, sd.idScale, sd.grade_name, sd.grade_cutoff FROM scaleDivision as sd WHERE sd.idScale = '%s';",rs.getString("idScale"));
			  ResultSet sd = statement2.executeQuery(temp.toString());
			  while(sd.next())
			  {
				  tempScale.setGradingScale(sd.getString("grade_name"), sd.getDouble("grade_cutoff"),sd.getInt("idScaleDivision"));
			  } 
			  sd.close();
			  resList.add(tempScale);
			  
		  }	
		  rs.close();
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return resList;
  }

  /**
   * when given a course, return the grade scheme asociated with the course
   */
  public static GradingScheme getScheme(Courses course) {
	  GradingScheme result = null;
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT scheme.idScheme, scheme.scheme_name FROM scheme INNER JOIN "+
				  "course on course.idScheme = scheme.idScheme WHERE course.idCourse='%s';",course.getCourseID() );
		  ResultSet rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {  
			  result = new GradingScheme(rs.getString("scheme_name"),rs.getInt("idScheme"),course.getInstructor());
			  temp =String.format("SELECT sc.idSchemeCategories, sc.idScheme, sc.name,sc.hasPrefix, sc.name_prefix, "+
					  "sc.weight, sc.strategy,sc.has_max_score, sc.max_score, sc.num_drops FROM schemeCategories as sc "+
					  "WHERE sc.idScheme = '%s';",rs.getString("idScheme"));
			  rs.close();
			  statement.close();
			  statement = connection.createStatement();
			  ResultSet sd = statement.executeQuery(temp.toString());
			  while(sd.next())
			  {
				  result.setgradingSchemeCategory(sd.getInt("idSchemeCategories"),sd.getString("name"), sd.getString("strategy"), sd.getInt("weight"),sd.getBoolean("hasPrefix"), sd.getString("name_prefix"), sd.getBoolean("has_max_score"), sd.getInt("max_score"), sd.getInt("num_drops"));
			  } 
			  sd.close();
		  }	
		  else
		  {
			  rs.close();
			  result = null;
		  }
		  
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }
  
  /**
   * when given a schemeID, return the grade scheme asociated with the ID
   */
  public static GradingScheme getScheme(int schemeID) {
	  GradingScheme result = null;
	  try
	  {
		  openConnection();
		  Statement statement2 = connection.createStatement();
	      statement2.setQueryTimeout(60);
		  String temp =String.format("SELECT scheme.idScheme, scheme.scheme_name, person.idPerson FROM scheme "+
				  "INNER JOIN course on course.idScheme = scheme.idScheme "+
				  "INNER JOIN person on course.idPerson = person.idPerson WHERE scheme.idScheme='%s';",schemeID );
		  ResultSet rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {  
			  result = new GradingScheme(rs.getString("scheme_name"),rs.getInt("idScheme"),getPerson(rs.getInt("idPerson")));
			  temp =String.format("SELECT sc.idSchemeCategories, sc.idScheme, sc.name, sc.hasPrefix, sc.name_prefix, sc.weight, sc.strategy,sc.has_max_score, sc.max_score, sc.num_drops FROM schemeCategories as sc WHERE sc.idScheme = '%s';",rs.getString("idScheme"));
			  ResultSet sd = statement2.executeQuery(temp.toString());
			  while(sd.next())
			  {
				  result.setgradingSchemeCategory(sd.getInt("idSchemeCategories"),sd.getString("name"), sd.getString("strategy"), sd.getInt("weight"),sd.getBoolean("hasPrefix"), sd.getString("name_prefix"), sd.getBoolean("has_max_score"), sd.getInt("max_score"), sd.getInt("num_drops"));
			  } 
		  }	
		  else
		  {
			  result = null;
		  }
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }

  /**
   * when given a person, return all grade schemes that that person created in an ArrayList
   */
  public static ArrayList<GradingScheme> getScheme(Person person) {
	  ArrayList<GradingScheme> result = new ArrayList<GradingScheme>();
	  try
	  {
		  openConnection();
		  Statement statement2 = connection.createStatement();
	      statement2.setQueryTimeout(60);
		  String strSchemeList = String.format("SELECT idScheme, scheme_name, idPerson FROM scheme WHERE idPerson='%s'", person.getID());
		  ResultSet schemeList = statement.executeQuery(strSchemeList);
		  while(schemeList.next())
		  {
			  GradingScheme temp = new GradingScheme(schemeList.getString("scheme_name"),schemeList.getInt("idScheme"), person);
			  String strSchemeQuery =String.format("SELECT sc.idSchemeCategories, sc.idScheme, sc.name,sc.hasPrefix, sc.name_prefix, sc.weight, sc.strategy,sc.has_max_score, sc.max_score, sc.num_drops FROM schemeCategories as sc WHERE sc.idScheme = '%s';",schemeList.getInt("idScheme"));
			  ResultSet sd = statement2.executeQuery(strSchemeQuery.toString());
			  while(sd.next())
			  {
				  temp.setgradingSchemeCategory(sd.getInt("idSchemeCategories"),sd.getString("name"), sd.getString("strategy"), sd.getInt("weight"),sd.getBoolean("hasPrefix"), sd.getString("name_prefix"), sd.getBoolean("has_max_score"), sd.getInt("max_score"), sd.getInt("num_drops")); 
			  }	
			  result.add(temp);
		  }
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }
  
  /*
   * when given a course, return all assignment in that course
   */
  public static ArrayList<Assignment> getAssignments(Courses course)
  {
	  Assignment tempAssign = null;
	  ArrayList<Assignment> resList = new ArrayList<Assignment>();
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT a.idAssignment,a.idCourse,a.idSchemeCategories,a.assignment_name, a.assignment_num "+
				  "FROM assignment as a WHERE idCourse = '%s';",course.getCourseID());
		  ResultSet rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {
			  tempAssign = new Assignment(rs.getInt("idAssignment"),rs.getString("assignment_name"),getCategory(rs.getInt("idSchemeCategories")),course,rs.getInt("assignment_num"));
			  resList.add(tempAssign);
		  }	
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return resList;	  
  }
  
  /*
   * when given an assignments ID, return the assignment
   */
  public static Assignment getAssignment(int assignID)
  {
	  Assignment result = null;  
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT idAssignment,idCourse,idSchemeCategories,assignment_name, assignment_num, assignment_prefix "+
				  "FROM assignment WHERE idAssignment = '%s';",assignID);
		  ResultSet rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=new Assignment(rs.getInt("idAssignment"),rs.getString("assignment_name"),getCategory(rs.getInt("idSchemeCategories"))
					  ,getCourse(rs.getInt("idCourse")),rs.getInt("assignment_num"),rs.getString("assignment_prefix")); 
		  }	
		  else
		  {
			  result = null;
		  }
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result; 
  }
  
  /*
   * when given an assignments Name and the Course the assignment belongs to, return the assignment
   */
  public static Assignment getAssignment(Courses course,String assignName)
  {
	  Assignment result = null;  
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT idAssignment,idCourse,idSchemeCategories,assignment_name, assignment_num, assignment_prefix "+
				  "FROM assignment WHERE idCourse = '%s' AND assignment_name='%s';",course.getCourseID(),assignName);
		  ResultSet rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=new Assignment(rs.getInt("idAssignment"),assignName,getCategory(rs.getInt("idSchemeCategories"))
					  ,course,rs.getInt("assignment_num"),rs.getString("assignment_prefix")); 
		  }	
		  else
		  {
			  result = null;
		  }
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }
  /*
   * when given a category, return all assignment in that category
   */
  public static ArrayList<Assignment> getAssignments(Category cat,Courses course)
  {
	  Assignment tempAssign = null;
	  ArrayList<Assignment> resList = new ArrayList<Assignment>();
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT a.idAssignment,a.idCourse,a.idSchemeCategories,a.assignment_name, a.assignment_num, a.assignment_prefix "+
				  "FROM assignment as a WHERE idSchemeCategories = '%s';",cat.getID());
		  ResultSet rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {
			  tempAssign = new Assignment(rs.getInt("idAssignment"),rs.getString("assignment_name"),cat,course,rs.getInt("assignment_num"),rs.getString("assignment_prefix"));
			  resList.add(tempAssign);
		  }	
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return resList;	
  }
  
  public static ArrayList<Assignment> getAssignments(Category cat)
  {
	  Assignment tempAssign = null;
	  ArrayList<Assignment> resList = new ArrayList<Assignment>();
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT a.idAssignment,a.idCourse,a.idSchemeCategories,a.assignment_name, a.assignment_num, a.assignment_prefix "+
				  "FROM assignment as a WHERE idSchemeCategories = '%s';",cat.getID());
		  ResultSet rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {
			  tempAssign = new Assignment(rs.getInt("idAssignment"),rs.getString("assignment_name"),cat,getCourse(rs.getInt("idCourse")),rs.getInt("assignment_num"),rs.getString("assignment_prefix"));
			  resList.add(tempAssign);
		  }	
		  rs.close();
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	  statement.close();
	    	  connection.close();
	      }
	        
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return resList;	
  }
  /*
   * when provided the idSchemeCategorie return a Category
   */
  public static Category getCategory(int catID)
  {
	  Category result = null;  
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT sc.idSchemeCategories, sc.name,sc.hasPrefix, sc.name_prefix, sc.weight, "+
				  "sc.strategy,sc.has_max_score, sc.max_score, sc.num_drops FROM schemeCategories as sc "+
				  "WHERE sc.idSchemeCategories = '%s';",catID);
		  ResultSet rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=new Category(rs.getInt("idSchemeCategories"),rs.getString("name"),rs.getString("strategy")
					  ,rs.getInt("weight"),rs.getBoolean("hasPrefix"),rs.getString("name_prefix"),rs.getBoolean("has_max_score")
					  ,rs.getInt("max_score"),rs.getInt("num_drops")); 
		  }	
		  else
		  {
			  result = null;
		  }
		  rs.close();
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result; 
  }
  
  /*
   * when given an ID return the Grade
   */
  public static AssignmentGrade getAssignmentGrade(int gradeID)
  {
	  AssignmentGrade result = null;  
	  try
	  {
		  openConnection();

		  String temp =String.format("SELECT idGrade, idAssignment, idPerson, grade, excuse, drop_grade, max_grade "+
				  "FROM grade WHERE idGrade = '%s';",gradeID);
		  ResultSet rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=new AssignmentGrade(rs.getInt("idGrade"),getAssignment(rs.getInt("idAssignment")),
					  getPerson(rs.getInt("idPerson")),rs.getDouble("grade"),rs.getBoolean("excuse"),
					  rs.getBoolean("drop_grade"),rs.getInt("max_grade")); 
		  }	
		  else
		  {
			  result = null;
		  }
		  rs.close();
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result; 
  }
  
  /*
   * when given a student and a course, return all grades the student has in given course
   */
  public static ArrayList<AssignmentGrade> getAssignmentGrades(Person student,Courses course)
  {
	  ArrayList<AssignmentGrade> resultList = new ArrayList<AssignmentGrade>();  
	  try
	  {
		  openConnection();

		  String temp =String.format("SELECT grade.idGrade, grade.idAssignment, grade.idPerson, grade.grade, "+
				  "grade.excuse, grade.drop_grade, grade.max_grade "+
				  "FROM grade INNER JOIN assignment on assignment.idAssignment = grade.idAssignment "+
				  "WHERE grade.idPerson = '%s' AND assignment.idCourse='%s';",student.getID(),course.getCourseID());
		  ResultSet rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {
			  resultList.add(new AssignmentGrade(rs.getInt("idGrade"),getAssignment(rs.getInt("idAssignment")),
					  getPerson(rs.getInt("idPerson")),rs.getDouble("grade"),rs.getBoolean("excuse"),
					  rs.getBoolean("drop_grade"),rs.getInt("max_grade"))); 
		  }	
		  
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return resultList; 
  }
  
  /*
   * when given a student and an Assignment, return the grade of the student on the assignment
   */
  public static AssignmentGrade getAssignmentGrade(Person student,Assignment assign)
  {
	  AssignmentGrade result = null;  
	  try
	  {
		  openConnection();

		  String temp =String.format("SELECT idGrade, idAssignment, idPerson, grade, excuse, drop_grade, max_grade "+
				  "FROM grade WHERE idPerson = '%s' AND idAssignment='%s';",student.getID(),assign.getAssignID());
		  ResultSet rs = statement.executeQuery(temp.toString());
		  if(rs.next())
		  {
			  result=new AssignmentGrade(rs.getInt("idGrade"),getAssignment(rs.getInt("idAssignment")),
					  getPerson(rs.getInt("idPerson")),rs.getDouble("grade"),rs.getBoolean("excuse"),
					  rs.getBoolean("drop_grade"),rs.getInt("max_grade")); 
		  }	
		  else
		  {
			  result = null;
		  }
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result; 
  }
  
  /*
   * when given an assignment, return all grades all students have in given assignment
   */
  public static ArrayList<AssignmentGrade> getAssignmentGrades(Assignment assign)
  {
	  ArrayList<AssignmentGrade> resultList = new ArrayList<AssignmentGrade>();  
	  try
	  {
		  openConnection();

		  String temp =String.format("SELECT grade.idGrade, grade.idAssignment, grade.idPerson, grade.grade, "+
				  "grade.excuse, grade.drop_grade, grade.max_grade "+
				  "FROM grade "+
				  "WHERE grade.idAssignment = '%s';",assign.getAssignID());
		  ResultSet rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {
			  resultList.add(new AssignmentGrade(rs.getInt("idGrade"),getAssignment(rs.getInt("idAssignment")),
					  getPerson(rs.getInt("idPerson")),rs.getDouble("grade"),rs.getBoolean("excuse"),
					  rs.getBoolean("drop_grade"),rs.getInt("max_grade"))); 
		  }	
		  
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return resultList; 
  }
  
  public static ArrayList<Courses> getInstructedCourses(Person person,boolean getArchived) 
  {
	  ArrayList<Courses> result =new ArrayList<Courses>();// = new Courses();
	  ResultSet rs=null;
	  try
	  {
		  openConnection();
		  String temp =String.format("SELECT idCourse FROM course WHERE idPerson='%s' AND isArchive=%s;",person.getID(),(getArchived ? 1:0));
		  rs = statement.executeQuery(temp.toString());
		  while(rs.next())
		  {  
			  result.add(getCourse(rs.getInt("idCourse")));
		  }	
	  }
	  catch(SQLException e)
	  {
		  System.err.println(e.getMessage());
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	statement.close();
	        connection.close();
	      }
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }

 
  /*
   * adds a new student to the database. 
   * return 1 for success
   * return -1 for fail
 * @throws SQLException 
   */
  public static void addStudent(String userName, String userID, String firstName, String lastName, String password) throws Exception 
  {  
	  try
	  {
		  if(isNewPerson(userName))
		  {
			  String temp =String.format("INSERT INTO person (`idPerson`,`first_name`,`last_name`,`password`,`isInstrustor`,`isAdmin`, "+
					  "`user_id`,`user_name`) VALUES(NULL,'%s','%s','%s',0,0,%s,'%s');",firstName,lastName,password,userID,userName);
			  executeUpdate(temp);
		  }
		  else
		  {
			  Exception e = new Exception("Attempting to add a non new person to the DB");
			  throw e;
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  /**
   * adds a person to the database, Assumes that this is a new person.
 * @throws Exception 
   */
  public static Person addPerson(Person person) throws IllegalArgumentException, SQLException 
  {  
	  try
	  {
		  if(person.getID()==-1 && isNewPerson(person.getUserName()) )
		  {
			  String temp =String.format("INSERT INTO person (`idPerson`,`first_name`,`last_name`,`password`,`isInstrustor`, "+
				"`isAdmin`,`user_name`) "+
				"VALUES(NULL,'%s','%s','%s','%s','%s','%s');"
				,person.getFirstName(),person.getLastName(),person.getPassword(),(person.isInstructor() ? 1:0),
				(person.isAdmin() ? 1:0),person.getUserName());
			  person.setID(executeUpdate(temp));
			  return person;
		  }
		  else
		  {
			  IllegalArgumentException i = new IllegalArgumentException ("Attempting to add a non new person to the DB");
			  throw i;
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  

  /**
   * sets a person as an instructor
 * @throws SQLException 
   *//*
  public  makePersonInstructor(Person person) {
    // Bouml preserved body begin 00021C09
    // Bouml preserved body end 00021C09
  }*/
  private static void addStudentToCourse(Person student, Courses course) throws SQLException 
  {
	  try
	  {
		  if(student.getID()!=-1)
		  {
			  String temp =String.format("INSERT INTO roll VALUES(NULL,'%s','%s');",course.getCourseID(),student.getID());
			  executeUpdate(temp);
		  }
		  else
		  {
			  IllegalArgumentException e = new IllegalArgumentException("Student must have a valid ID");
			  throw e;
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  

  private static void addStudentToCourse(String studentID, String courseID) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("INSERT INTO roll VALUES(NULL,'%s','%s');",courseID,studentID);
		  executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  private static int addCourse(Courses course) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("INSERT INTO course VALUES(NULL,'%s',%s,%s,%s,0);",course.getCourseName(),course.getInstructor().getID(),course.getSchemeID(),course.getScaleID());
		  return executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static GradingScale addScale(GradingScale scale) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("INSERT INTO scale VALUES(NULL,'%s');",scale.getScaleName());
		  int scaleID = executeUpdate(temp);
		  ArrayList<Grade> division = (ArrayList<Grade>) scale.getGradingScale();
		  for(int i=0; i<division.size();i++)
		  {
			  division.get(i).setGradeID(addScaleDivision(division.get(i),scaleID));
		  }
		  scale.setScaleID(scaleID);
		  return scale;
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static int addScaleDivision(Grade SD,int scaleID) throws SQLException
  {
	  try
	  {
		  String temp =String.format("INSERT INTO scaleDivision VALUES(NULL,%s,'%s',%s);"
				  ,scaleID,SD.getletterGrade(),SD.getCutoffscore());
		  return executeUpdate(temp);		  
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static GradingScheme addScheme(GradingScheme scheme) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("INSERT INTO scheme VALUES(NULL,'%s','%s');",scheme.getSchemeName(),scheme.getCreator().getID());
		  int schemeID = executeUpdate(temp);
		  ArrayList<Category> category = (ArrayList<Category>) scheme.getGradingScheme();
		  for(int i=0; i<category.size();i++)
		  {
			  Category ct = category.get(i);
			  ct.setID(addCategorie(ct,schemeID));
		  }
		  scheme.setSchemeID(schemeID);
		  return scheme;
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static int addCategorie(Category cat,int schemeID) throws SQLException
  {
	  try
	  {
		  String temp =String.format("INSERT INTO schemeCategories VALUES(NULL,%s,'%s',%s,'%s',%s,'%s',%s,'%s',%s);"
				  ,schemeID,cat.getCategoryName(),(cat.getPrefixStatus() ? 1:0),cat.getPrefix(),cat.getCategoryWeight(),cat.getCategoryStrategy()
				  ,(cat.getHasMaxScore() ? 1:0),cat.getMaxScore(),cat.getNumDrops());
		  return executeUpdate(temp);		  
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  } 
  }
  
  public static int addAssignment(Assignment assign) throws SQLException, Exception
  {
	  try
	  {
		  if(assign.getAssignID()==-1)
		  {
			  String temp =String.format("INSERT INTO assignment(`idAssignment`,`idCourse`,`idSchemeCategories`, "+
					  "`assignment_name`,`assignment_prefix`, `assignment_num`) "+
					  "VALUES(NULL,%s,%s,'%s','%s',%s);"
					  ,assign.getCourseID(),assign.getCatID(),assign.getAssignName(),assign.getPrefix(),assign.getAssignNum());
			  return executeUpdate(temp);
		  }
		  else
		  {
			  Exception e = new Exception("Not a new assignment, based on its ID");
			  throw e;
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  } 
  }
  
  public static int addAssignmentGrade(AssignmentGrade AG) throws SQLException
  {
	  try
	  {
		  String temp =String.format("INSERT INTO grade VALUES(NULL,%s,%s,'%s',%s,%s,%s);"
				  ,AG.getAssignID(),AG.getStudentID(),AG.getGrade(),(AG.getExcused() ? 1:0),(AG.getDropped() ? 1:0),AG.getMaxScore());
		  return executeUpdate(temp);		  
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  } 
  }
  
  public static NewCourse addNewCourse(NewCourse NC)
  {
	  try {
		int courseID = addCourse(NC.getCourse());
		NC.setScale(addScale(NC.getScale())); 
		NC.setScheme(addScheme(NC.getScheme()));
		NC.getCourse().setScaleID(NC.getScheme().getSchemeID());
		NC.getCourse().setSchemeID(NC.getScale().getScaleID());
		NC.getCourse().setID(courseID);
		updateCourse(NC.getCourse());
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  return NC;
  }
  
  /*
   * will also remove any Assignmnet Grades associated with this assignmnet
   */
  public static void removeAssignment(Assignment assign) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("DELETE FROM assignment WHERE idAssignment='%s'",assign.getAssignID());
		  executeUpdate(temp.toString());
		  temp =String.format("DELETE FROM grade WHERE idAssignment='%s'",assign.getAssignID());
		  executeUpdate(temp.toString());
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void removeAssignmentGrade(AssignmentGrade AG) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("DELETE FROM grade WHERE idGrade='%s'",AG.getGradeID());
		  executeUpdate(temp.toString());
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  /*
   * will also remove all assignmnets that use this Category
   */
  public static void removeCategory(Category cat) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("DELETE FROM schemeCategories WHERE idSchemeCategories='%s'",cat.getID());
		  executeUpdate(temp.toString());
		  ArrayList<Assignment> aList =getAssignments(cat);
		  for(Assignment a : aList)
		  {
			  removeAssignment(a);
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void removeCourse(Courses course)throws SQLException 
  {
	  try
	  {
		  String temp =String.format("DELETE FROM roll WHERE idCourse='%s'",course.getCourseID()); 
		  executeUpdate(temp); 
		  temp =String.format("DELETE FROM grade WHERE idAssignment in (SELECT idAssignment "+
				  "FROM assignment WHERE assignment.idCourse='%s')",course.getCourseID());
		  executeUpdate(temp);
		  temp =String.format("DELETE FROM assignment WHERE idCourse='%s'",course.getCourseID());
		  executeUpdate(temp);
		  temp =String.format("DELETE FROM course WHERE idCourse='%s'",course.getCourseID());
		  executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void removeStudentFromCourse(Person student, Courses course) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("DELETE FROM roll WHERE idCourse='%s' AND idPerson='%s'",course.getCourseID(),student.getID());
		  executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  /*
   * remove a person, all of there assignmnet grades, and there enrollment in courses from the DB
   * Will FALE if there is an attempt to remove an instructor
   */
  public static void removeStudent(Person person) throws SQLException,Exception 
  {
	  try
	  {
		  if(!person.isInstructor() && !person.isAdmin())
		  {
			  String temp =String.format("DELETE FROM person WHERE idPerson='%s'",person.getID());
			  executeUpdate(temp.toString());
			  temp =String.format("DELETE FROM roll WHERE idPerson='%s'",person.getID());
			  executeUpdate(temp.toString());
			  temp =String.format("DELETE FROM grade WHERE idPerson='%s'",person.getID());
			  executeUpdate(temp.toString());
		  }
		  else
		  {
			  Exception e = new Exception("Invalid person type.");
			  throw e;
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  /*
   * Removes the instructor, any courses the instructor teaches, all info about those courses from the DB
   * Will throw exception if used on a student person
   */
  public static void removeInstructor(Person person)throws SQLException,Exception 
  {
	  try
	  {
		  if(person.isInstructor() && !person.isAdmin())
		  {
			  ArrayList<Courses> cList = getInstructedCourses(person,true);
			  for(int i=0; i<cList.size();i++)
			  {
				  removeCourse(cList.get(i));
			  }
			  cList = getInstructedCourses(person,false);
			  for(int i=0; i<cList.size();i++)
			  {
				  removeCourse(cList.get(i));
			  }
			  String temp =String.format("DELETE FROM person WHERE idPerson='%s'",person.getID());
			  executeUpdate(temp.toString());
		  }
		  else
		  {
			  Exception e = new Exception("Invalid person type.");
			  throw e;
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  


  public static void removeScale(GradingScale GS) throws SQLException 
  {
	  try
	  {
		  for(int i=0;i<GS.size();i++)
		  {
			  removeScaleDivision(GS.getGradingScale().get(i));
		  }
		  String temp =String.format("DELETE FROM scale WHERE idScale='%s'",GS.getScaleID());
		  executeUpdate(temp.toString());
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void removeScaleDivision(Grade grade) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("DELETE FROM scaleDivision WHERE idScaleDivision='%s'",grade.getGradeID());
		  executeUpdate(temp.toString());
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }

  public static void removeScheme(GradingScheme SG) throws SQLException 
  {
	  try
	  {
		  try
		  {
			  for(Category cat :SG.getGradingscheme())
			  {
				  removeCategory(cat);
			  }
			  String temp =String.format("DELETE FROM scheme WHERE idScheme='%s'",SG.getSchemeID());
			  executeUpdate(temp.toString());
		  }
		  catch(SQLException e)
		  {
			  throw e;
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void removeStudentFromCourse(String studentID, String courseID) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("DELETE FROM roll WHERE idCourse='%s' AND idPerson='%s'",courseID,studentID);
		  executeUpdate(temp.toString());
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  
  
  public static void updatePerson(Person person) throws SQLException
  {  
	  try
	  {
		  String temp =String.format("UPDATE person set first_name='%s', last_name='%s', password='%s', isInstrustor='%s', isAdmin='%s' WHERE user_name='%s'",person.getFirstName(),person.getLastName(),person.getPassword(),(person.isInstructor()) ? 1:0,(person.isAdmin())? 1:0,person.getUserName());
		  executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void updateCourse(Courses course) throws SQLException 
  {
	  try
	  {
		  String temp =String.format("UPDATE course set course_name='%s', idPerson='%s', idScheme='%s', idScale='%s', isArchive='%s' WHERE idCourse='%s'"
				  ,course.getCourseName(),course.getInstructor().getID(),course.getSchemeID(),course.getScaleID(),(course.isArchived() ? 1:0),course.getCourseID());
		  executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }	
  }
  
  public static void updateScale(GradingScale gs) throws SQLException
  {  
	  try
	  {
		  String temp =String.format("UPDATE scale set scale_name='%s' WHERE idScale='%s'"
				  ,gs.getScaleName(),gs.getScaleID());
		  executeUpdate(temp);
		  for(int i=0;i<gs.getGradingScale().size();i++)
		  {
			  String query = String.format("SELECT * FROM scaleDivision WHERE idScaleDivision='%s'",gs.getGradingScale().get(i).getGradeID());
			  if(queryHasResult(query))
			  {
				  updateDivision((Grade) gs.getGradingScale().get(i)); 
			  }
			  else
			  {
				  if(gs.getGradingScale().get(i).getGradeID()==-1)
				  {
					  addScaleDivision(gs.getGradingScale().get(i),gs.getScaleID());
				  }
				  else
				  {
					  removeScaleDivision(gs.getGradingScale().get(i));
				  }
			  }
			  
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void updateDivision(Grade grade) throws SQLException
  {  
	  try
	  {
		  String temp =String.format("UPDATE scaleDivision set grade_name='%s', grade_cutoff='%s' WHERE idScaleDivision='%s'"
				  ,grade.getletterGrade(),grade.getCutoffscore(),grade.getGradeID());
		  executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void updateScheme(GradingScheme gs) throws SQLException
  {  
	  try
	  {
		  String temp =String.format("UPDATE scheme set scheme_name='%s', idPerson='%s' WHERE idScheme='%s'"
				  ,gs.getSchemeName(),gs.getCreator().getID(),gs.getSchemeID());
		  executeUpdate(temp);
		  for(int i=0;i<gs.size();i++)
		  {
			  String query = String.format("SELECT * FROM schemeCategories WHERE idSchemeCategories='%s'",gs.getCategory(i).getID());
			  if(queryHasResult(query))
			  {
				  updateCategory(gs.getCategory(i));
			  }
			  else
			  {
				  if(gs.getCategory(i).getID()==-1)
				  {
					  addCategorie(gs.getCategory(i),gs.getSchemeID());
				  }
				  else
				  {
					  removeCategory(gs.getCategory(i));
				  }
			  }
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void updateCategory(Category cat) throws SQLException
  {  
	  try
	  {
		  String temp =String.format("UPDATE schemeCategories set name='%s', hasPrefix='%s', name_prefix='%s', "+
				  "weight='%s', strategy='%s', has_max_score='%s', max_score='%s',num_drops='%s' "+
				  "WHERE idSchemeCategories='%s'"
				  ,cat.getCategoryName(),(cat.getPrefixStatus() ? 1:0),cat.getPrefix(),cat.getCategoryWeight()
				  ,cat.getCategoryStrategy(),(cat.getHasMaxScore() ? 1:0),cat.getMaxScore(),cat.getNumDrops(),cat.getID());
		  executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  /*
   * when given a roll, updates the database with the changes to that roll
   */
  public static void updateRoll(Roll roll) throws SQLException
  {  
	  try
	  {
		  for(Person tbr : roll.getToBeRemoved())
		  {
			  System.out.println("Removing Person : "+tbr.getFullName()+" From Roll: "+roll.getCourse().toString());
			  removeStudentFromCourse(tbr, roll.getCourse());
		  }
		  for(Person tba : roll.getToBeAdded())
		  {
			  System.out.println("Addind Person : "+tba.getFullName()+" From Roll: "+roll.getCourse().toString());
			  addStudentToCourse(tba, roll.getCourse());
		  }
	  }
	  catch(SQLException e)
	  {
		  throw e;
	  }
  }
  
  public static void updateAssignment(Assignment assignment) throws SQLException 
  {
	  String temp =String.format("UPDATE assignment set idCourse='%s', idSchemeCategories='%s', assignment_name='%s', assignment_num='%s' WHERE idAssignment='%s'"
			  ,assignment.getCourseID(),assignment.getCatID(),assignment.getAssignName(),assignment.getAssignNum(),assignment.getAssignID());
	  try
	  {
		  executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  System.out.println("Exception Caused by call: "+temp);
		  throw e;
	  }
  }
  public static void updateAssignmentGrade(AssignmentGrade AG) throws SQLException
  {  
	  System.out.println("Calling UpdateAssignmentGrade with: "+AG.toString());
	  String temp =String.format("UPDATE grade set grade='%s', excuse='%s', drop_grade='%s', max_grade='%s' WHERE idGrade='%s'"
			  ,AG.getGrade()+"",(AG.getExcused() ? 1:0), (AG.getDropped() ? 1:0),AG.getMaxScore(), AG.getGradeID()); 
	  try
	  {
		  executeUpdate(temp);
	  }
	  catch(SQLException e)
	  {
		  System.out.println("Exception Caused by call: "+temp);
		  throw e;
	  }
  }
  
  /*
   * updates every assignment grade in a GradeSheet, not dependent on the grade having changed.
   */
  public static void updateGradeSheet(GradeSheet sheet) throws SQLException 
  {
  	for(AssignmentGrade AG : sheet.getAssignments())
  	{
  		updateAssignmentGrade(AG);
  	}
  }
  
  private static int executeUpdate(String query) throws SQLException
  {
	  int result = -1;
	  try
	  {
		  System.out.println("updating");
		  openConnection();
		  result=statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
		  if(result==1)
		  {
			  ResultSet rs =statement.getGeneratedKeys();
			  rs.next();
			  result = rs.getInt(1);
		  }
		  //connection.close();
	  }
	  catch(SQLException e)
	  {
		//  System.err.println(e.getMessage());
		  throw e;
	  }
	  finally
	  {
	    try
	    {
	      if(connection != null)
	      {
	    	  statement.close();
	    	  connection.close();
	      }
	        
	    }
	    catch(SQLException e)
	    {
	        // connection close failed.
	        System.err.println(e);
	    }
	  }
	  return result;
  }

	
	/*
	 * assumes that the incoming Scale is either a brand new scale or an existing scale
	 *  being applied to a different course then origionaly intended 
	 */
	public static Courses setScale(Courses course, GradingScale scale) throws SQLException 
	{
		if(course.getScaleID()!=scale.getScaleID())
		{
			course.setScaleID(addScale(scale));
			//updateScale(scale);
		}
		return course;
	}
	
	/*
	 * assumes that the incoming Scheme is either a brand new Scheme or an existing Scheme
	 *  being applied to a different course then origionaly intended 
	 */
	public static Courses setScheme(Courses course, GradingScheme scheme) throws SQLException 
	{
		if(course.getSchemeID()!=scheme.getSchemeID())
		{
			course.setSchemeID(addScheme(scheme));
			updateCourse(course);
		}
		return course;
		
	}

}
