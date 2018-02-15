package global.coda.ams.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.ams.beans.FlightBook;
import global.coda.ams.beans.Passenger;
import global.coda.ams.dao.Customerdao;
import global.coda.ams.delegate.CustomerOperation;

/**
 * Servlet implementation class transaction
 */
@WebServlet("/Customer/transaction")
public class transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	static FlightBook flightBook = new FlightBook();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		HttpSession session = request.getSession();
		FlightBook flightObject = (FlightBook) session.getAttribute("flightSession");
		try {
			HttpSession user = request.getSession();
			int pnr_id = Customerdao.getPNR();
			pnr_id++;
			
			flightBook.setSeatsEconomy(flightObject.getSeatsEconomy());
			flightBook.setSeatsBusiness(flightObject.getSeatsBusiness());
			flightBook.setScheduleNo(flightObject.getScheduleNo());
			flightBook.setFlightNo(flightObject.getFlightNo());
			flightBook.setCustomerId((int) user.getAttribute("userID"));
			flightBook.setPnrId(pnr_id);
			flightBook.setDate(flightObject.getDate());
			flightBook.setTicketClass(flightObject.getTicketClass());
			flightBook.setAmount(flightObject.getAmount());
			flightBook.setStatus("Booked");
			flightBook.setCount(flightObject.getCount());

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>Passenger Details</h1>");
			out.println("<form action='transaction' method='post'>");
			for (int i = 1; i <= flightBook.getCount(); i++) {

				out.print("Name of passenger : &nbsp ");
				out.print("<input type="+"\"text\""+" name="+"\"passenger"+i+"\" required>");
				out.println("<br>");

			}
			out.println("<input type='submit' name='Sumbit' value='Add details'   />");
			out.println("</form>");
			
			 RequestDispatcher rd=request.getRequestDispatcher("transaction.jsp"); 
			
			 rd.include(request, response);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Passenger> passengerList = new ArrayList<Passenger>();
		for (int i = 1; i <= flightBook.getCount(); i++) {
			Passenger newPassenger = new Passenger();
			newPassenger.setPnrId(flightBook.getPnrId());
			
			newPassenger.setPassengerName(request.getParameter("passenger"+i));
			newPassenger.setSeatNo(0);
			passengerList.add(newPassenger);
		}
		flightBook.setPassengerList(passengerList);
		
			
		if(!CustomerOperation.bookingRecord(flightBook))
		{
			RequestDispatcher rd=request.getRequestDispatcher("CustomerLoginMenu.jsp");  
			 request.setAttribute("transaction", "true");
			         rd.include(request, response);  
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("CustomerLoginMenu.jsp");  
			 request.setAttribute("transaction", "false");
			         rd.include(request, response);  
		}
		
		
	}

	
}
