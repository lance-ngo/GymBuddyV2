package GymBuddy;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")

	public class LoginServlet extends HttpServlet {
/**
* 
*/
		private static final long serialVersionUID = -5034226832463270122L;
		static accountMgr Act=new accountMgr(); 


		public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String n=request.getParameter("username");
			String p=request.getParameter("password");
			System.out.println(n);
			System.out.println(p);
			if(Act.validateUser(n,p))
			{
				//response.sendRedirect("Welcome as called by sendRedirect");
				RequestDispatcher rd=request.getRequestDispatcher("MemberProfile.html");
				rd.forward(request,response);
			}

			else {
				out.print("Sorry username or password error");
				RequestDispatcher rd=request.getRequestDispatcher("LoginFail.html");
				rd.include(request,response);
				}

				out.close();
		}

}
