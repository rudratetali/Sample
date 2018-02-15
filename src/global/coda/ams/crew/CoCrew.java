package global.coda.ams.crew;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
 * Servlet implementation class CoCrew
 */
@WebServlet("/Crew/CoCrew")
public class CoCrew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoCrew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scheduleId=new Integer(request.getParameter("SNo"));
		HttpSession crewUser = request.getSession();
		int crewId = (int) (crewUser.getAttribute("crewID"));
		List<CrewMembers> crewList =null;
		try {
			 crewList=CrewOperation.displayCoCrew(crewId,scheduleId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("CoCrew.jsp");
		request.setAttribute("listCrew",crewList );
		rd.include(request, response);
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	

}
