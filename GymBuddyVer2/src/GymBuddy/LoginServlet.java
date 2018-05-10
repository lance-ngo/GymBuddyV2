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
		 public static accountMgr Act=new accountMgr(); 


		public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


			String n=request.getParameter("username");
			String p=request.getParameter("password");
			System.out.println(n);
			System.out.println(p);
			if(Act.validateUser(n,p))
			{
				
				RequestDispatcher rd=request.getRequestDispatcher("MemberProfile.jsp");
				rd.forward(request,response);
			}

			else {
				
				RequestDispatcher rd=request.getRequestDispatcher("LoginFail.html");
				rd.include(request,response);
				}

				
		}
		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

				
	
				String f=request.getParameter("firstN");
				String l=request.getParameter("lastN");
				String u=request.getParameter("userN");
				String p=request.getParameter("password");
				if(Act.createAccount(u, p, f, l))
				{
					RequestDispatcher rd=request.getRequestDispatcher("createSuccess.jsp");
					rd.forward(request,response);
				}
				else
				{
					RequestDispatcher rd=request.getRequestDispatcher("createFail.jsp");
					rd.forward(request,response);
				}
				
				
				

				
			}

}
