import java.util.*;
public class eigthqueen
{
	int SIZE = 8;
	
	String initialise(String arr)
	{
		for(int i=0;i<SIZE;i++)
			for(int j=0;j<SIZE;j++)
				arr+="0";
		return arr;
	}
	
	int find(String arr)
	{
	    int i;
		for(i=0;i<SIZE;i++)
		{
			int flag = 0;
			for(int j=0;j<SIZE;j++)
			{
				if(arr.charAt(SIZE*i+j) == '1')
				{
					flag = 1;
					break;
				}
			}
			if(flag == 0)
				return i;
		}
		return i;
	}
	
	boolean isValid(String arr)
	{
		for(int i =0;i < SIZE; i++)
		{
			int sum_row = 0;
			int sum_col = 0;
			for(int j = 0;j < SIZE; j++)
			{
				sum_row += (int)arr.charAt(SIZE*i+j)-48;
				sum_col += (int)arr.charAt(SIZE*j+i)-48;
			}
			if(sum_row > 1 || sum_col > 1)
				return false;
		}
		for(int i = 0;i < SIZE; i++)
		{
			int sum_left = 0;
			int sum_right = 0;
			for(int j = 0;j+i < SIZE; j++)
			{
				sum_left += (int)arr.charAt(SIZE*j+j+i)-48;
				sum_right += (int)arr.charAt(SIZE*(j+i)+j)-48;
			}
			if(sum_left > 1 || sum_right > 1)
				return false;
		}
		for(int i = 0;i < 2*SIZE-1; i++)
		{
			int sum_left = 0;
			int sum_right = 0;
			if(i < SIZE)
			{
			   for(int j = 0;i-j >= 0; j++)
			   {
				   sum_left += (int)arr.charAt(SIZE*j+i-j)-48;
			   }
			}
			else
			{
			   for(int j = i-SIZE+1;j < SIZE; j++)
			   {
			       sum_right += (int)arr.charAt(SIZE*j+i-j)-48;
			   }
			}
			if(sum_left > 1 || sum_right > 1)
				return false;
		}
		return true;
	}
	
	
	void display(String arr)
	{
		for(int i = 0;i < SIZE; i++)
		{
			for(int j = 0;j < SIZE; j++)
			{
				if(arr.charAt(SIZE*i+j) == '1')
					System.out.print("1 ");
				else System.out.print("_ ");
			}
		System.out.println();
		}
	}
	
	public static void main(String args[])
	{
		eigthqueen obj = new eigthqueen();
		String sol = "";
		sol = obj.initialise(sol);
		PriorityQueue<String> ucs = new PriorityQueue<String>();
		ucs.add(sol);
		int count = 1;
		while(!(ucs.isEmpty()))
		{
			String ans = ucs.poll();
			if(!(obj.isValid(ans)))
				continue;
			int i = obj.find(ans);
			if(i == obj.SIZE)
			{
				System.out.println("\n****Solution "+count+"****");
				obj.display(ans);
				count++;
			}
			else
			{
				for(int j = 0;j < obj.SIZE; j++)
				{
					ans = ans.substring(0, obj.SIZE*i+j)+"1"+ans.substring(obj.SIZE*i+j+1);
					ucs.offer(ans);
					ans = ans.substring(0, obj.SIZE*i+j)+"0"+ans.substring(obj.SIZE*i+j+1);
				}
			}
			
		}
	}
}