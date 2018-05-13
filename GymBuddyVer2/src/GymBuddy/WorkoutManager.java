package GymBuddy;


import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-mm-dd");
			//String newExpire = sdf.format(user.getExpire());
			
			while(resultWorkout.next())// display results add later
			{
				String n = resultWorkout.getString("name");
				String i = resultWorkout.getString("instructor");
				java.sql.Date d = resultWorkout.getDate("date");
				String woDate = sdf.format(d);
				int t = resultWorkout.getInt("time");
				int c = resultWorkout.getInt("capacity");
				int e = resultWorkout.getInt("enrolled");
				int idN = resultWorkout.getInt("id");
				
				GymClass workout = new GymClass(n, i, woDate, t, c, e, idN);
				result.add(workout);
			}
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean addWoConsole() throws ParseException {
		Scanner woScan = new Scanner(System.in);
		System.out.println("enter workout name, use trainer name for personal session");
		String woName = woScan.nextLine();
		System.out.println("enter instructor name ");
		String nameInstruct = woScan.nextLine();
		System.out.println("enter date, use mm-dd-yyyy format");
		String dateString = woScan.nextLine();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date date = sdf1.parse(dateString);
		java.sql.Date sqlWoDate = new java.sql.Date(date.getTime());
		
		
		System.out.println("enter time, 24hr without colon eg: 2030");
		int timeWo = woScan.nextInt();
		System.out.println("enter max capacity");
		int cap = woScan.nextInt();
		
		if(addWorkout(woName, nameInstruct, sqlWoDate, timeWo, cap)) {
			System.out.println("workout added");
			return true;
		}
		System.out.println("failed to add workout");
		return false;
	}
	
	public boolean addWorkout(String name, String instructor, java.sql.Date date, int time, int capacity)
	{
		boolean workoutAdded = false;
		try {
			//defining sql db driver to use
			Class.forName("com.mysql.jdbc.Driver"); 
			
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gymbuddy", "qvbingo", "ognib646");
			
			int countWorkout = 0;
			//prepared   statement is used for secure access
			// ? used for data to put in query
			// actual query to execute is
			// select * from members where username = name and password = pass
			String findCtr = "SELECT * from counter WHERE type = ?";
			PreparedStatement oPrStmt = con
					.prepareStatement(findCtr);// ? represents some parameter to include
			oPrStmt.setString(1, "workouts");
		
			
			ResultSet resultWorkouts = oPrStmt.executeQuery();	//execute query
			resultWorkouts.next();
			countWorkout = resultWorkouts.getInt("count")+1;
			
			
			String sql = "INSERT INTO workouts (id, name, instructor, date, time, capacity) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, countWorkout);
			statement.setString(2, name);
			statement.setString(3, instructor);
			statement.setDate(4, date);
			statement.setInt(5, time);
			statement.setInt(6, capacity);
			
			
			
			int newWorkouts = statement.executeUpdate();
			if(newWorkouts >0) {
				workoutAdded = true;
				String sql2 = "UPDATE counter SET count = ? WHERE type = ?";
				PreparedStatement updateStmt = con.prepareStatement(sql2);
				updateStmt.setInt(1, countWorkout);
				updateStmt.setString(2, "workouts");
				updateStmt.execute();
			}
			 
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return workoutAdded;
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

