package GymBuddy;



import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class SearchClassServlet
 */
@WebServlet("/SearchClassServlet")
public class SearchClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;;
       
	public static WorkoutManager mgr=new WorkoutManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 
		String n=request.getParameter("class-name");
	RequestDispatcher rd=request.getRequestDispatcher("SearchResult.jsp");
	rd.forward(request,response);
		
		
	
	}

}
