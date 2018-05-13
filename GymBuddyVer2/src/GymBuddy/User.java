package GymBuddy;

import java.text.ParseException;
import java.util.*;
import java.util.Scanner;


	public class  User implements Comparable
	{
		private String username;
		private String password;
		private String firstN;
		private String lastN;
		private int id;
		private Date dateExpire;
		private boolean active;
		private int balanceDue;
		
		
		public User(String u, String p, String f, String l)
		{
			username=u;
			password=p;
			firstN=f;
			lastN=l;
			
			dateExpire = new Date();
			active = false;
			balanceDue = 0;
		}
		
		public String getUname() {
			return username;
		}
		
		public String getPword() {
			return password;
		}
		
		public String getFirst() {
			return firstN;
		}
		
		public String getLast() {
			return lastN;
		}
		
		public Date getExpire() {
			return dateExpire;
		}
		
		public void setExpire(Date expiration) {
			dateExpire = expiration;
		}
		
		public int getId() {
			return id;
		}
		
		public boolean getActive() {
			return active;
		}
		
		public int getBalance() {
			return balanceDue;
		}
		
		public void setBalance(int newBal) {
			balanceDue = newBal;
		}
		
		public void deactivate() {
			active = false;
		}
		
		public boolean detActive()
		{
			
			Date today = new Date();
			if (today.compareTo(dateExpire) < 0)
				active = true;
			
			return active;
		}
		
		public int extendShip(int months)
		{
			dateExpire.setMonth(dateExpire.getMonth()+months);
			balanceDue =balanceDue+ months * 50;
			detActive();
			System.out.println("Your new expiration date is: " + dateExpire);
			System.out.println("Your new balance is: " + balanceDue);
			return balanceDue;
			
		}
		
		public void cancelShip()
		{
			int diff=0;
			if(dateExpire.getYear()==new Date().getYear())
			{
				diff=Math.abs(dateExpire.getMonth()-new Date().getMonth());
			}
			else
			{
				diff=12-new Date().getMonth()+dateExpire.getMonth();
			}
			balanceDue=balanceDue-(diff*50);
			dateExpire=new Date();
			active=false;
			System.out.println("Your new expiration date is: " + dateExpire);
			System.out.println("Your new balance is: " + balanceDue);

		}
		
		public int compareTo(Object u)
		{
			User x=(User)u;
			if(this.username.compareTo(x.username)!=0)
			{
				return this.username.compareTo(x.username);
			}
			else
			return this.password.compareTo(x.password);
		}
	


public static void main(String[] args) throws ParseException {
	int menuChoice = 0;
	Scanner menuScan = new Scanner(System.in);
	while(menuChoice != 9) {
	
	System.out.println("Select function");
	System.out.println("1. Create new user");
	System.out.println("2. Login Member");
	System.out.println("3. Leave feedback");
	System.out.println("4. View feedback");
	System.out.println("5. Create workout");
	System.out.println("9. Exit");
	
	menuChoice = menuScan.nextInt();
	
	switch(menuChoice) {
	case 1:{
		accountMgr mgr1 = new accountMgr();
		mgr1.createInConsole();
		break;
			}
	case 2:{
		accountMgr mgr2 = new accountMgr();
		mgr2.loginConsole();
		break;
			}
	case 3:{
		FeedbackManager consoleFb = new FeedbackManager();
		consoleFb.addFbConsole();
		break;
	}
	case 4:{
		FeedbackManager getFb = new FeedbackManager();
		getFb.getFeedback();
		break;
	}
	case 5:{
		WorkoutManager woConsole = new WorkoutManager();
		woConsole.addWoConsole();
		break;
	}
	
	}
	//menuScan.close();
	
	}
	
	
	return;
}
}

	
	
		
