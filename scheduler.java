package Schedule;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
class Data1
{
	Connection conn;
	Statement stmt;
	String event,date,time,query;
	Scanner sc;
	int choice,choice1;
	Data1()
	{
		try
		{
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Connection
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule","root","");
			
			//creating statement
			stmt=conn.createStatement();
			
			
			//creating scanner object
			sc=new Scanner(System.in);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void operations()
	{
		try
		{
			
			System.out.println("------------My Schedule----------");
			System.out.println("Enter 1 for entering details");
			System.out.println("Enter 2 for printing details");
			System.out.println("Enter 3 for deleting particular schedule");
			System.out.println("Enter 4 for Exit");
			choice=sc.nextInt();
			boolean b=true;
			while(b==true)
			{
				switch(choice)
				{
					case 1:
						details();
						break;
					case 2:
						print();
						break;
					case 3:
						delete();
						break;
					default:
					b=false;
					System.out.println("Exiting");
					System.exit(0);
				}
				System.out.println("-----------------------------");
				System.out.println("Enter 1 for entering details");
				System.out.println("Enter 2 for printing details");
				System.out.println("Enter 3 for deleting particular schedule");
				System.out.println("Enter 4 for Exit");
				choice=sc.nextInt();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}
	}
	void details()
	{
		try
		{
			System.out.println("Enter the type of event");
			event=sc.next();
			System.out.println("Enter the Time of event as hh:mm:ss");
			time=sc.next();
			System.out.println("Enter the Date of event as yy:mm:dd");
			date=sc.next();
			query="insert into schedule_details values('"+event+"','"+time+"','"+date+"')";
			stmt.execute(query);
			System.out.println("--------Data Entered Successfully-----------");	
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
	void print()
	{
		try
		{
			System.out.println("Enter date of event");
			date=sc.next();
			System.out.println("Enter type of event");
			event=sc.next();
			query="select * from schedule_details where Date='"+date+"' and Event_Type='"+event+"'";
			ResultSet pointer=stmt.executeQuery(query);
			while(pointer.next())
			{
				System.out.println("Event Type : "+pointer.getString(1)+" Time : "+pointer.getString(2)+" Date : "+pointer.getString(3));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
	void delete()
	{
		try
		{
			System.out.println("Enter name of event you want to delete");
			event=sc.next();
			System.out.println("Enter Date ");
			date=sc.next();
			query="delete from schedule_details where Event_type='"+event+"' and Date='"+date+"'";
			stmt.execute(query);
			System.out.println("-------------Deleted Successfully---------------");
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
}
public class schedule 
{
	//main method
	public static void main(String args[])
	{
		Data1 d=new Data1();
		d.operations();
	}
}
