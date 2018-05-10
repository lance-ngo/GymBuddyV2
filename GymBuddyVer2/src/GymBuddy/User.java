package GymBuddy;

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
	


public static void main(String[] args) {
	accountMgr act = new accountMgr();
	if (act.validateUser("lance", "abc123"))
		System.out.println("login successful");
	else
		System.out.println("login failed");
	
	return;
}
}

	
	
		
