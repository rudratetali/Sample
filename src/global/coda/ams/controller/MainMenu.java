package global.coda.ams.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenu
 */
@WebServlet("/MainMenu")
public class MainMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation= request.getParameter("user");
		
		if(operation.equals("Admin"))
		{
			response.sendRedirect("Admin/AdminLogin.jsp");
		}
		else if(operation.equalsIgnoreCase("Crew"))
		{
			
			response.sendRedirect("Crew/CrewLogin.jsp");  
	        
		}
		else if(operation.equalsIgnoreCase("Customer"))
		{
			
			response.sendRedirect("Customer/CustomerLogin.jsp");  
	        
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
