package GymBuddy;

import java.sql.*;
import java.util.ArrayList;

public class FeedbackManager 
{
	public ArrayList<String> fbResult=new ArrayList<>();
	
	public void addFeedback(String feedback)
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
			PreparedStatement oPrStmt = con
				.prepareStatement("SELECT * FROM counter where field = feedbacks ");
			
			ResultSet resultFeedback=oPrStmt.executeQuery();
			int id = resultFeedback.getInt("count");
		
			String sql1="Insert INTO feedbacks(id, response) VALUES (?,?)";
			PreparedStatement statement=con.prepareStatement(sql1);
			
	
			statement.setInt(1,id+1);
			statement.setString(2, feedback);
		
			
			int rowsInserted= statement.executeUpdate(); 
			if(rowsInserted > 0) {
				System.out.println("A new feedback was collected successfully!");
				String sql2="UPDATE counter SET count= ? WHERE field = feedback";
				PreparedStatement updateStmt = con.prepareStatement(sql2);
				updateStmt.setInt(1, id+1);
			}
		
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void getFeedback(){
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
					.prepareStatement("select * from feedback where id >0");// ? represents some parameter to include
			
			ResultSet resultFeedback = oPrStmt.executeQuery();	//execute query
			
			while(resultFeedback.next())// display results add later
			{
				String fBack = resultFeedback.getString("response");
				
				fbResult.add(fBack);
			}
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}
