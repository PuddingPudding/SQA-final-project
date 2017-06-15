import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Prototype {
	
	public int location; //餐廳位子
	public static ArrayList<Integer> locationList = new ArrayList<Integer>();
	
	public static final int deliverDistance = 10;
	public static final int deliverBill = 500;
	public static final char[] menuName = {'a','b','c','d','e','f'};
	public static final int[] menuPrice = {50 ,40 ,50 ,65 ,40 ,75 };
	public static final String[] discountCombine = {"ab" , "cd" , "ef" , "abcdef"};
	public static final int[] discountValue = {15 , 25 , 35 , 100};
	
	
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
		System.out.println(p1.order(25, "abc") );
		System.out.println(p2.order(25, "ccee") );
		System.out.println(p2.order(25, "ccdd") );
	}
	 
	public String order(int yourLocation , String orderList)
	{
		String output = "";
		if(this.checkDeliver(yourLocation,300) == true)
		{
			output += "可外送 ";
		}
		else
		{
			output += "不可外送";
		}
		output += "\n你點的餐點為: " + orderList;
		output += "\n一共是: " + this.orderMeal(orderList);
		return output;
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
	public boolean checkDeliver(int yourLocation , int bill)
	{
		return this.checkDeliverByDistance(yourLocation) || this.checkDeliverByBill(bill);
	}
	
	public int orderMeal(String orderList)
	{
		int output = 0;
		char temp;
		int orderCheck; //點餐檢查器，用來檢視你點了什麼餐，他會記錄你字串中的哪個位子裡有該餐點
		int discountNumber = 0; //用來記錄每個折扣分別出現了幾次
		int discountNumberMin = 0;
		char discountChar;
		for(int i = 0 ; i < this.menuName.length ; i++)
		{
			orderCheck = orderList.indexOf(this.menuName[i]);
			while(orderCheck != -1)
			{
				output += this.menuPrice[i];
				orderCheck = orderList.indexOf(this.menuName[i],orderCheck+1);
			}
		}
		
		for(int i = 0 ; i < this.discountCombine.length ; i++)
		{
			discountNumber = 0;
			discountNumberMin = this.discountCombine[i].length();
			for(int j = 0 ; j < this.discountCombine[i].length() ; j++)
			{
				discountNumber = 0;
				discountChar = this.discountCombine[i].charAt(j);
				orderCheck = orderList.indexOf(discountChar);
				if(orderCheck != -1)
				{
					discountNumber++;
				}
				while(orderCheck != -1)
				{
					discountNumber++;
					orderCheck = orderList.indexOf(this.menuName[i],orderCheck+1);
				}
				discountNumberMin = Math.min(discountNumber, discountNumberMin);
			}
			output -= this.discountValue[i]*discountNumberMin;
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
