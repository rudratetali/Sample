package global.coda.ams.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.coda.ams.beans.CrewMembers;
import global.coda.ams.delegate.AdminOperation;

/**
 * Servlet implementation class LeaveRequest
 */
@WebServlet("/Admin/LeaveRequest")
public class LeaveRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CrewMembers> crewList=null;
		
			 crewList= AdminOperation.leaveRequests();
			 if(crewList!=null)
			 {
				 RequestDispatcher rd = request.getRequestDispatcher("LeaveRequest.jsp");
					request.setAttribute("listCrew", crewList);
					rd.include(request, response);
			 }
			 else
			 {
			 RequestDispatcher rd = request.getRequestDispatcher("LeaveRequest.jsp");
				request.setAttribute("noLeave","true");
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
