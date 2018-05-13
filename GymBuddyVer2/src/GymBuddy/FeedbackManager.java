package GymBuddy;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FeedbackManager 
{
	public ArrayList<String> fbResult=new ArrayList<>();
	
	public boolean addFbConsole() {
		System.out.println("Enter feedback");
		
		BufferedReader fbIn = new BufferedReader(new InputStreamReader(System.in));
		String fb = "holder";
		try {
			fb = fbIn.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fbIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(addFeedback(fb)) {
			System.out.println("Thank you for your feedback");
			return true;
		}
		System.out.println("Feedback failed to add");
		return false;
		
	}
	public boolean addFeedback(String feedback)
	{
		boolean fbAdded = false;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
			int fbCounter = 0;
			String findCtr = "SELECT * from counter WHERE type = ?";
			PreparedStatement oPrStmt = con
					.prepareStatement(findCtr);// ? represents some parameter to include
			oPrStmt.setString(1, "feedback");
			
			ResultSet resultFeedback=oPrStmt.executeQuery();
			resultFeedback.next();
			fbCounter = resultFeedback.getInt("count")+1;
			
			String sql = "INSERT INTO feedback (response, id) "
					+ "VALUES (?, ?)";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, feedback);
			statement.setInt(2, fbCounter);
		
			
			int rowsInserted= statement.executeUpdate(); 
			if(rowsInserted > 0) {
				fbAdded = true;
				String sql2 = "UPDATE counter SET count = ? WHERE type = ?";
				PreparedStatement updateStmt = con.prepareStatement(sql2);
				updateStmt.setInt(1, fbCounter);
				updateStmt.setString(2, "feedback");
				updateStmt.execute();
			}
			
			
		
		}catch (Exception e) {
			System.out.println(e);
		}
		return fbAdded;
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
				System.out.println(fBack);
				fbResult.add(fBack);
			}
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}
