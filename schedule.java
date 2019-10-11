import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

class data
{
Statement stmt;
Connection conn;
Scanner sc;
int choice,choice2;
String event,date,time,query,query2,query3,udate,utime,uevent,ndate;
data()
{
try
{
Class.forName("com.mysql.jdbc.Driver");
conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule","root","");
stmt=conn.createStatement();
sc=new Scanner(System.in);
}
catch(Exception  e)
{
System.out.println("Connection failed");
}
}
void input()
{
try
{
System.out.println("-- Welcome to platform to get aware of upcoming events-");
System.out.println("Enter Date of event");
date=sc.next();
System.out.println("Enter time of event");
time=sc.next();
System.out.println("Enter event name");
event=sc.next();
query="insert into schedule values('"+date+"','"+time+"','"+event+"')";
stmt.execute(query);
System.out.println("------Data recorded Succesfuly-----");
System.out.println(" ");
boolean x=true;
boolean y=true;
while(x==true)    {
System.out.println("Enter 1 to update record...... ");
System.out.println("Enter 2 to search record..... ");
System.out.println("Enter 3 to view record.....");
System.out.println("Enter 4 to exit.....");
choice=sc.nextInt();

if(choice==1)
{

while(y==true)
{
System.out.println("----Enter 1 to update date---");
System.out.println("----Enter 2 to update time---");
System.out.println("----Enter 3 to Exit----");
choice2=sc.nextInt();
switch(choice2)
{
case 1:
System.out.println("Enter new date");
udate=sc.next();
System.out.println("Enter name of event for which you want to update");
uevent=sc.next();
query2="update schedule set date='"+udate+"' where event='"+uevent+"' ";
stmt.execute(query2);
System.out.println("Updated successfully");
break;
case 2:
System.out.println("Enter new time");
utime=sc.next();
System.out.println("Enter name of event for which you want to update");
uevent=sc.next();
query2="update schedule set time='"+utime+"' where event='"+uevent+"' ";
stmt.execute(query2);
System.out.println("Updated successfully");
break;
case 3:
y=false;
break;
}}}
else if(choice==2)
{
System.out.println("choose the date for which you want to search event");
ndate=sc.next();
query2="select * from schedule where date='"+ndate+"' ";
stmt.execute(query2);
System.out.println("your schedule for this date is:");
ResultSet pointer=stmt.executeQuery(query2);
while(pointer.next())
{
System.out.println(pointer.getString(3));
}

}
else if(choice==3)
{

query3="select * from schedule";
ResultSet pointer=stmt.executeQuery(query3);
while(pointer.next())
{
System.out.println(pointer.getString(1)+" "+pointer.getString(2)+" "+pointer.getString(3));
}
}
else
{
x=false;
System.out.println("BYE!!!");
break;
}
}
}
catch(Exception e)
{
System.out.println(e);
}
}
}
public class schedule {

public static void main(String args[])
{
data p1=new data();
p1.input();
}

}



