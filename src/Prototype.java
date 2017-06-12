import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Prototype {
	
	public int location; //餐廳位子
	public static ArrayList<Integer> locationList = new ArrayList<Integer>();
	
	public static final int deliverDistance = 10;
	public static final int deliverBill = 500;
	
	public Prototype (int location) throws Exception
	{
		if(this.locationList.contains( (Integer)location) == true)
		{
			throw new locationHasBeenTakenException();			
		}
		else
		{
			this.location = location;
			this.locationList.add(location);
		}
	}
	
	public static String getAllLocation()
	{
		String output = "";
		for(int i = 0 ; i < locationList.size() ; i++)
		{
			output += (locationList.get(i) + " ");
		}
		return output;		
	}
	
	public static void main(String args[]) throws Exception
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
		Prototype p1 = new Prototype(10);
		System.out.println(Prototype.getAllLocation() );
		Prototype p2 = new Prototype(20);
		System.out.println(Prototype.getAllLocation() );
		Prototype p3 = new Prototype(21);
		System.out.println(Prototype.getAllLocation() );
		
	}
	 
	public boolean checkDeliverByDistance(int yourLocation)
	{
		boolean output = true;
		if( Math.abs( (yourLocation - this.location) ) > 10)
		{
			output = false;
		}
		return output;
	}
	public boolean checkDeliverByBill(int bill)
	{
		boolean output = true;
		if(bill < this.deliverBill)
		{			
			output = false;
		}
		return output;
	}
}

class locationHasBeenTakenException extends Exception
{
	public locationHasBeenTakenException() 
	{
		super("該店家的位置已經被搶走了");
	}
}
