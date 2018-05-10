package GymBuddy;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
public class WorkoutManager {
	public ArrayList<GymClass> result=new ArrayList<>();
	public boolean overCapacity;
	
	public void SearchClassN(String Name)
	{
		result.clear();
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
					.prepareStatement("select * from workouts where name LIKE ?");// ? represents some parameter to include
			
			oPrStmt.setString(1, "%"+Name + "%"); //setting paramter 1 in prepared stmt to name
			ResultSet resultWorkout = oPrStmt.executeQuery();	//execute query
			
			while(resultWorkout.next())// display results add later
			{
				String n = resultWorkout.getString("name");
				String i = resultWorkout.getString("instructor");
				int t = resultWorkout.getInt("time");
				int c = resultWorkout.getInt("capacity");
				int e = resultWorkout.getInt("enrolled");
				
				GymClass workout = new GymClass(n, i, t, c, e);
				result.add(workout);
			}
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	
	public boolean addWorkout(String name, String instructor, java.sql.Date date, int time, int capacity)
	{
		int ct=0;
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
					.prepareStatement("select * from workouts  where id > 0");// ? represents some parameter to include
			ResultSet resultMembers = oPrStmt.executeQuery();	//execute query
			
			while(resultMembers.next())// display results add later
			{
				ct++;
			}
			
			PreparedStatement oPrStmnt2=con.prepareStatement("INSERT INTO workouts (name, instructor, date,time,capacity,enrolled,id) VALUES (?, ?, ?, ?,?,?,?)");
			oPrStmnt2.setInt(1, ct++);
			oPrStmnt2.setString(2, name);
			oPrStmnt2.setString(3, instructor);
			oPrStmnt2.setDate(4, date);
			oPrStmnt2.setInt(5, time);
			oPrStmnt2.setInt(6, capacity);
			oPrStmnt2.setString(7,"");
			int rowsInserted = oPrStmnt2.executeUpdate();
			if (rowsInserted > 0)
			{
			    return true;
			}
			 
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean deleteWorkout(String name)
	{
		try {
			//defining sql db driver to use
			Class.forName("com.mysql.jdbc.Driver"); 
			
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
			
			//prepared   statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from members where username = name and password = pass
			PreparedStatement statement = con.prepareStatement("DELETE FROM workouts WHERE name=?");
			statement.setString(1, name);
			 
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
			    return true;
			}
			
			
			
			 
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean updateClassEnrollment(int id,int enrollct, int ct)
	{
		if(result.get(ct).capacity<result.get(ct).enrolled+1)
		{
			 return overCapacity=true;
		}
		try {
			//defining sql db driver to use
			Class.forName("com.mysql.jdbc.Driver"); 
			
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
			
			//prepared   statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from members where username = name and password = pass
			PreparedStatement statement = con.prepareStatement("UPDATE workouts set enrolled=? WHERE id=?");
			statement.setInt(1, enrollct+1);
			statement.setInt(2, id);
			 
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
			    return overCapacity=true;
			}
			
			
			
			 
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return overCapacity=false;
	}
	
	public boolean enroll(String name)
	{
		try {
			//defining sql db driver to use
			Class.forName("com.mysql.jdbc.Driver"); 
			
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
		String sql = "UPDATE workouts SET enrolled=concat(enrolled,?)";
		 
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, name);
		
		 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
		    return true;
		}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

}

