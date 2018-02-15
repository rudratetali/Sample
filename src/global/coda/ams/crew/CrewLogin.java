package global.coda.ams.crew;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.ams.beans.CrewMembers;
import global.coda.ams.delegate.CrewOperation;

/**
 * Servlet implementation class CrewLogin
 */
@WebServlet("/Crew/CrewLogin")
public class CrewLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrewLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = new Integer(request.getParameter("name"));
		String password = request.getParameter("password");
		CrewMembers crew = CrewOperation.crewLogin(userId, password);

		if (crew != null) {
			
			HttpSession crewUser = request.getSession(true);
			crewUser.setAttribute("crewID", crew.getCrewId());
			crewUser.setAttribute("crewName", crew.getName());
			crewUser.setAttribute("designation", crew.getDesignation());
			response.sendRedirect("CrewLoginMenu.jsp");

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("CrewLogin.jsp");
			request.setAttribute("crewValid", "false");
			rd.include(request, response);

		}

	}

}
