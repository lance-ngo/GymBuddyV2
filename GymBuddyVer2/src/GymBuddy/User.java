package GymBuddy;

import java.util.*;
import java.util.Scanner;

/*public class userTree {
	TreeSet<User> tree;
	Scanner scan;
	ArrayList<User> tree2;
	TreeSet<GymClass> tree3;
	public userTree()
	{
		tree=new TreeSet<>();
		tree2=new ArrayList<User>();
		scan =new Scanner(System.in);
	}
	
	public boolean addUser(String fN, String ln, String uN, String pW)
	{
		User u1 = new User(fN, ln, uN, pW);
		u1.id=tree.size()+1;
		boolean b=tree.add(u1);
		if(b)
		{
			tree2=new ArrayList<>(tree);
		}
		return b;
	}
	
	public boolean addUser()
	{
		scan =new Scanner(System.in);
		
		System.out.println("Registering new user. Please enter your username.");
		String u= scan.nextLine();
		System.out.println("Please enter your password.");
		String p=scan.nextLine();
		System.out.println("Please enter your first name.");
		String f=scan.nextLine();
		System.out.println("Please enter your last name.");
		String l=scan.nextLine();
		User u1= new User(u,p,f,l);
		u1.id=tree.size()+1;
		boolean b=tree.add(u1);
		if(b)
		{
			tree2=new ArrayList<>(tree);
		}
		return b;
		
		
	}
	public void close()
	{
		scan.close();
	}
	
	@SuppressWarnings("unused")
	public void logIn()// implement binary search later!!!!
	{
		
		
		System.out.println("Logging in. Please enter your username.");
		String u= scan.nextLine();
		System.out.println("Please enter your password.");
		String p=scan.nextLine();
		ArrayList<User> list=new ArrayList<>(tree);
		int ct;
		if(list.size()%2==0)
		{
			ct=list.size()/2;
		}
		else
		{
			ct=(list.size()/2)+1;
		}
				
			
		for(User k: tree)
		{
			if(k.username.compareTo(u)==0)
			{
				if(k.password.compareTo(p)==0)
				{
					System.out.println("Login successful!");
					return;
				}
				else
				{
					System.out.println("Incorrect login credentials.");
					return;
				}
				
			}
					
			
		}
		System.out.println("User does not exist");
		return;
	}
	*/
	 class  User implements Comparable
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

	
	
		
