package GymBuddy;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemebershipServlet
 */

@WebServlet("/MemebershipServlet")

public class MemebershipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		String m=request.getParameter("months");
	//	
		LoginServlet.Act.extendShip(LoginServlet.Act.temp, Integer.parseInt(m));
			RequestDispatcher rd=request.getRequestDispatcher("SubscribeConfirm.html");
			rd.forward(request,response);
	
		

	
		//out.close();
	}
	
	

}
