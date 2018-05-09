package GymBuddy;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class staffMgr {
	public static User temp;
	
	public boolean validateStaff(String Name, String pw) {
		boolean validLogin = false;
		try {
			//defining sql db driver to use
			Class.forName("com.mysql.jdbc.Driver"); 
			
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
			
			//prepared   statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from members where username = name and password = pass
			PreparedStatement oPrStmt = con
					.prepareStatement("select * from staff where username=? and password=?");// ? represents some parameter to include
			
			oPrStmt.setString(1, Name); //setting paramter 1 in prepared stmt to name
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
}



