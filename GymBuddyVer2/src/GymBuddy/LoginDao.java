package GymBuddy;


import java.sql.*;

public class LoginDao {

	public static boolean validateUser(String name, String pw) {
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
					.prepareStatement("select * from members where id > 0");// ? represents some parameter to include
			
			ResultSet resultMembers = oPrStmt.executeQuery();	//execute query
			
			
			
			
		}catch (Exception e) {
			System.out.println(e);
		}	
		return validLogin;
	}
}
