import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Prototype {
	public static void main(String args[])
	{
		try 
		{
		      FileReader fr=new FileReader("test.txt");
		      BufferedReader br=new BufferedReader(fr);
		      String line;
		      while ((line=br.readLine()) != null) 
		      {
		        System.out.println(line);
		      }
		}
		catch (IOException e) {System.out.println(e);}
	}
	 
}
