package global.coda.ams.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.coda.ams.delegate.CustomerOperation;

/**
 * Servlet implementation class CancelTicket
 */
@WebServlet("/Customer/Cancel")
public class Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cancel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pnrId=new Integer( request.getParameter("PNRNo"));
		float amount=0;
		
			amount= CustomerOperation.cancelBooking(pnrId);
		
			
		
		if(amount==0)
		{			
			RequestDispatcher rd= request.getRequestDispatcher("CustomerViewMenu.jsp");
			request.setAttribute("alreadyCheck", true);
			rd.include(request, response);
		}
		else
		{
		RequestDispatcher rd= request.getRequestDispatcher("CustomerViewMenu.jsp");
		request.setAttribute("cancelled", true);
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


