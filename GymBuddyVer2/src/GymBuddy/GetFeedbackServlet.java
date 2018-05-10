package GymBuddy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetFeedback
 */
@WebServlet("/GetFeedbackServlet")
public class GetFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      public static FeedbackManager mgr=new FeedbackManager(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFeedbackServlet() {
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
		mgr.getFeedback();
		
		RequestDispatcher rd=request.getRequestDispatcher("ShowFeedback.jsp");
		rd.forward(request,response);
	}

}
