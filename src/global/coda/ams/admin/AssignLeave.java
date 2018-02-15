package global.coda.ams.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.coda.ams.delegate.AdminOperation;

/**
 * Servlet implementation class AssignLeave
 */
@WebServlet("/Admin/AssignLeave")
public class AssignLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignLeave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int leaveId= new Integer(request.getParameter("LNo"));
		
			boolean leaveGranted=AdminOperation.leaveAssign(leaveId);
			if(leaveGranted)
			{
				RequestDispatcher rd = request.getRequestDispatcher("AdminLoginMenu.jsp");
				request.setAttribute("leaveGranted", "false");
				rd.include(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("AdminLoginMenu.jsp");
				request.setAttribute("leaveGranted", "true");
				rd.include(request, response);
			}
		 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
