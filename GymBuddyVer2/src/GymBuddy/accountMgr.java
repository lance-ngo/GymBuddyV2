package GymBuddy;

import java.util.Scanner;
import java.sql.*;
import java.util.Date;


public class accountMgr {
	public static User temp;
	public static boolean validateUser(String name, String pw) {
		boolean validLogin = false;
		try {
			//defining sql db driver to use
			Class.forName("com.mysql.jdbc.Driver"); 
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
			
			//prepared   statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from members where username = name and password = pass
			PreparedStatement oPrStmt = con
					.prepareStatement("select * from members where username=? and password=?");// ? represents some parameter to include
			
			oPrStmt.setString(1, name); //setting paramter 1 in prepared stmt to name
			oPrStmt.setString(2, pw); 	//setting parameter 2 in prepared stmt to pw
			ResultSet resultMembers = oPrStmt.executeQuery();	//execute query
			
			if(resultMembers.next()) {
				String uName = resultMembers.getString("username");
				String pWord = resultMembers.getString("password");
				String fName = resultMembers.getString("firstName");
				String lName = resultMembers.getString("lastName");
				int iD = resultMembers.getInt("id");
				validLogin = true;
				
				temp = new User(uName, pWord, fName, lName);
			}
			
			
		}catch (Exception e) {
			System.out.println(e);
		}	
		return validLogin;
	}
	
	public boolean createInConsole() {
		Scanner scan1 = new Scanner(System.in);
		System.out.println("enter username");
		String userN = scan1.nextLine();
		System.out.println("enter password");
		String passW = scan1.nextLine();
		System.out.println("enter first name");
		String firstN = scan1.nextLine();
		System.out.println("enter last name");
		String lastN = scan1.nextLine();
		
		if(createAccount(userN, passW, firstN, lastN)) {
			System.out.println("account creation success");
			return true;
		}
		System.out.println("account creation failed");
		return false;
	}
	
	public boolean loginConsole() {
		Scanner scan2 = new Scanner(System.in);
		System.out.println("enter username");
		String userN = scan2.nextLine();
		System.out.println("enter password");
		String passW = scan2.nextLine();
		
		if(validateUser(userN, passW)) {
			System.out.println("login successful");
			return true;
		}
		System.out.println("login failed");
		return false;
	}
	
	public static boolean createAccount(String uName, String pw, String first, String last) {
		boolean accountCreated = false;
		
		try {
			//defining sql db driver to use
			Class.forName("com.mysql.jdbc.Driver"); 
			
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
			int countMember = 0;
			//prepared   statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from members where username = name and password = pass
			String findCtr = "SELECT * from counter WHERE type = ?";
			PreparedStatement oPrStmt = con
					.prepareStatement(findCtr);// ? represents some parameter to include
			oPrStmt.setString(1, "members");
		
			
			ResultSet resultMembers = oPrStmt.executeQuery();	//execute query
			resultMembers.next();
			countMember = resultMembers.getInt("count")+1;
			
			
			String sql = "INSERT INTO members (username, password, firstName, lastName, id) "
					+ "VALUES (?, ?, ?, ?,?)";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, uName);
			statement.setString(2, pw);
			statement.setString(3, first);
			statement.setString(4, last);
			statement.setInt(5, countMember);
			
			
			
			int newAccounts = statement.executeUpdate();
			if(newAccounts >0) {
				accountCreated = true;
				String sql2 = "UPDATE counter SET count = ? WHERE type = ?";
				PreparedStatement updateStmt = con.prepareStatement(sql2);
				updateStmt.setInt(1, countMember);
				updateStmt.setString(2, "members");
				updateStmt.execute();
			}
			
		}
			catch (Exception e) {
				System.out.println(e);
			}
		return accountCreated;
		
	}
	
	@SuppressWarnings("deprecation")
	public int extendShip(User user, int months) {
		user.getExpire().setMonth(user.getExpire().getMonth()+months);
		user.setBalance(user.getBalance() + months*50);
		user.detActive();
		//System.out.println("Your new expiration date is: " + dateExpire);
		//System.out.println("Your new balance is: " + balanceDue);
		saveUserInfo(user);
		return user.getBalance();
	}
	
	public int cancelShip(User user) {
		int diff=0;
		if(user.getExpire().getYear()==new Date().getYear())
		{
			diff=Math.abs(user.getExpire().getMonth()-new Date().getMonth());
		}
		else
		{
			diff=12-new Date().getMonth()+user.getExpire().getMonth();
		}
		user.setBalance(user.getBalance()-(diff*50));
		user.setExpire(new Date());
		user.deactivate();
		saveUserInfo(user);
		return user.getBalance();
	}
	
	public boolean saveUserInfo(User user) {
		boolean infoSaved = false;
		try {
			//defining sql db driver to use
			Class.forName("com.mysql.jdbc.Driver"); 
			
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
			
			//prepared   statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from members where username = name and password = pass
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String newExpire = sdf.format(user.getExpire());
		
			String update = "UPDATE members SET username=?, password=?, firstName=?, lastName=?,"
					+ "dateExpire=?, active=?, balancer=? WHERE id = ?";
			PreparedStatement updateUser = con
					.prepareStatement(update);// ? represents some parameter to include
			updateUser.setString(1, user.getUname());
			updateUser.setString(2, user.getPword());
			updateUser.setString(3, user.getFirst());
			updateUser.setString(4, user.getLast());
			updateUser.setString(5, newExpire);
			updateUser.setBoolean(6,user.getActive());
			updateUser.setInt(7, user.getBalance());
			updateUser.setInt(8, user.getId());
			
			int rowsUpdated = updateUser.executeUpdate();
			if (rowsUpdated > 0)
				infoSaved = true;
			return infoSaved;

		
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return infoSaved;
	}
}
