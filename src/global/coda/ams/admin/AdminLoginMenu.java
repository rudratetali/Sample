package global.coda.ams.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLoginMenu
 */
@WebServlet("/Admin/AdminLoginMenu")
public class AdminLoginMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation= request.getParameter("operation");
		switch(operation)
		{
		case "Add Flight":
			response.sendRedirect("DisplayFlight");
			break;
		case "Add Crew":
			response.sendRedirect("AddCrew.jsp");
			break;
		case "Assign Flight":
			response.sendRedirect("AssignFlights");
			break;
		case "Leave Request":
			response.sendRedirect("LeaveRequest"); 
			break;
		case "Logout":
			HttpSession adminUser= request.getSession();
			adminUser.invalidate();
			response.sendRedirect("../MainMenu.jsp");
			break;
			
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
