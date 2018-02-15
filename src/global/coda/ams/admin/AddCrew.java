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
 * Servlet implementation class AddCrew
 */
@WebServlet("/Admin/AddCrew")
public class AddCrew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCrew() {
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
		
			if(!AdminOperation.addnewCrew(request.getParameter("crewname"),request.getParameter("designation"),request.getParameter("contact"),request.getParameter("password"),request.getParameter("languages")))
			{
				RequestDispatcher rd = request.getRequestDispatcher("AdminLoginMenu.jsp");
				request.setAttribute("addedCrew", "true");
				rd.include(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("AdminLoginMenu.jsp");
				request.setAttribute("addedCrew", "false");
				rd.include(request, response);
			}
		
		
	}



}