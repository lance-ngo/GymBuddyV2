package GymBuddy;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateClass
 */
@WebServlet("/CreateClassServlet")
public class CreateClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static WorkoutManager mgr3=new WorkoutManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String n=request.getParameter("ClassName");
		String i=request.getParameter("Instructor-name");
		String D=request.getParameter("Date");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date date = new java.util.Date();
		try {
			date = sdf1.parse(D);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlWoDate = new java.sql.Date(date.getTime());
		
		String T=request.getParameter("Time");
		String C=request.getParameter("Capacity");
		
	
		
        
		
		mgr3.addWorkout(n, i, sqlWoDate,  Integer.parseInt(T),Integer.parseInt(C));
		RequestDispatcher rd=request.getRequestDispatcher("CreateConfirm.html");
		rd.forward(request,response);
	}

}
