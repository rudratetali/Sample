package global.coda.ams.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.ams.beans.CrewMembers;
import global.coda.ams.delegate.AdminOperation;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/Admin/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
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
		AdminOperation admin= new AdminOperation();
		int userId = new Integer(request.getParameter("name"));
		String password = request.getParameter("password");
		CrewMembers crew = admin.adminLogin(userId, password);

		if (crew != null) {
			
			HttpSession adminUser = request.getSession(true);
			adminUser.setAttribute("adminID",crew.getCrewId() );
			adminUser.setAttribute("adminName",crew.getName());
			response.sendRedirect("AdminLoginMenu.jsp");

		} else {
			RequestDispatcher rd=request.getRequestDispatcher("AdminLogin.jsp");  
			 request.setAttribute("adminValid", "false");
			         rd.include(request, response);  
		}

	}
	}
	


