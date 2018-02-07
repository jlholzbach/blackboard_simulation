import java.io.*;
import java.net.*;
import java.util.Scanner;
public class SessionManager {
   	private static SchoolDatabase SDB;

	public static void main(String args[]) {
// declaration section:
// declare a server socket and a client socket for the server
// declare an input and an output stream
        ServerSocket echoServer = null;
        String line="";
        Scanner scan;
        BufferedReader is;
        InputStream ObjectInputs;
		try {
			SDB = new SchoolDatabase("testDB.db");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        ObjectOutputStream oos;  
        PrintStream os;
        Socket clientSocket = null;
// Try to open a server socket on port 9999
// Note that we can't choose a port less than 1023 if we are not
// privileged users (root)
        try {
           echoServer = new ServerSocket(4444);
        }
        catch (IOException e) {
           System.out.println(e);
        }   
    try {
           clientSocket = echoServer.accept();
           is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           os = new PrintStream(clientSocket.getOutputStream());
           oos = new ObjectOutputStream(os); 
           boolean end=false;
           while (end==false) {
               is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        	   while((line = is.readLine())== null){
            	
            }
             scan=new Scanner(line.trim());
             String s=scan.next();
             if(s.equals("LOGIN")){
            	 //os.println("GO");
            	 String userName=scan.next().trim();
            	 String password=scan.next().trim();
            	 Person p=runCheckLoginTest(userName, password);
            	 System.out.println("sending");            	 
            	 oos.writeObject(p);  
            	 System.out.println("sent");
            	 
             }
             line=null;
             System.out.println("exiting");
             
           }
           is.close();
           oos.close();
           os.close();
           clientSocket.close(); 
           echoServer.close();
           //oos.close();
           //os.close();
           
        }   
    catch (IOException e) {
           System.out.println(e);
        }
    }
	private static Person runCheckLoginTest(String user, String password)
	{
		System.out.println("Testing checkLogin with user: "+user+" and "+password+": test.");
		if(SDB.checkLogin(user, password))
		{
			System.out.println("checkLogin success.");
			return runGetPersonTest(user);
		}
		else
		{
			System.out.println("checkLogin Fail.");
			return (new Person());
		}
	}
	private static Person runGetPersonTest(String user)
	{
		System.out.println("Testing getPerson with user: "+user+".");
		Person test = SDB.getPerson(user);
		if(!test.equals(null))
		{
			System.out.println("getPerson success.");
			System.out.println(test.toString());
			return test;

		}
		else
		{
			System.out.println("getPerson Fail.");
			return new Person();

		}

	}
	
}
