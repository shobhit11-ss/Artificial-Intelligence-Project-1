import java.util.*;

public class queen {
	int SIZE = 8;
String LightsOut(String pqueue) //Initialization
{
	for(int i=0;i<64;i++)
	{
		pqueue += "0";
	}
return pqueue;
}
	
void SendIt(String pqueue) //Print Function
{
	for(int i=0;i<SIZE;i++)
	{
		for(int j=0;j<SIZE;j++)
		{
			System.out.print(pqueue.charAt(SIZE*i+j));
		}
		System.out.println();
	}
}

boolean FIA(String pqueue) //Checks position of queen is valid
{
	for(int i=0;i<SIZE;i++)
	{
		int rsum = 0;
		int csum = 0;
		for(int j=0;j<SIZE;j++)
		{
			rsum += (int)pqueue.charAt(SIZE*i+j)-48;
			csum += (int)pqueue.charAt(SIZE*j+i)-48;
		}
		if(rsum>1 || csum>1)
			return false;
	}
		for(int i=0;i<SIZE;i++)
		{
			int sum_left = 0;
			int sum_right = 0;
			for(int j=0;j+i<SIZE;j++)
			{
				sum_left += (int)pqueue.charAt(SIZE*j+j+i)-48;
				sum_right += (int)pqueue.charAt(SIZE*(j+i)+j)-48;
			}
			if(sum_left>1 || sum_right>1)
				return false;
		}
		for(int i=0;i<2*SIZE-1;i++)
		{
			int sum_left = 0;
			int sum_right = 0;
			if(i<SIZE)
			{
			   for(int j=0;i-j>=0;j++)
			   {
				   sum_left += (int)pqueue.charAt(SIZE*j+i-j)-48;
			   }
			}
			else
			{
			   for(int j=i-SIZE+1;j<SIZE;j++)
			   {
			       sum_right += (int)pqueue.charAt(SIZE*j+i-j)-48;
			   }
			}
			if(sum_left>1 || sum_right>1)
				return false;
		}
				
return true;
}

 int Position(String pqueue) //Position of the queen
{
	int i;
	int[][] arr_queue= new int[SIZE][SIZE];
	for(i=0;i<SIZE;i++)
	{
		for(int j=0;j<SIZE;j++)
		{
			arr_queue[i][j] = (int)pqueue.charAt(SIZE*i+j)-48;
			//System.out.print(arr_queue[i][j]);
		}
		//System.out.println();
	}
	for(i=0;i<SIZE;i++)
	{
		int flag = 0;
		for(int j=0;j<SIZE;j++)
		{
			if(arr_queue[i][j] == 1)
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

	public static void main(String[] args) 
	{
		
		PriorityQueue<String> pqueue = new PriorityQueue<String>();
		queen q = new queen();
		String sol = "";
		sol = q.LightsOut(sol);
		pqueue.add(sol);
		int count = 1;
		while(!(pqueue.isEmpty()))
		{
			String req = pqueue.poll();
			if(!(q.FIA(req)))
				continue;
			int i = q.Position(req);
			if(i == q.SIZE)
			{
				System.out.println("\n-|SOLUTION "+count+"|-");
				q.SendIt(req);
				count++;
			}
			else
			{
				for(int j=0;j<q.SIZE;j++)
				{
					req = req.substring(0,q.SIZE*i+j)+"1"+req.substring(q.SIZE*i+j+1);
					pqueue.offer(req);
					req = req.substring(0,q.SIZE*i+j)+"0"+req.substring(q.SIZE*i+j+1);
				}
			}
		}
	}

}
