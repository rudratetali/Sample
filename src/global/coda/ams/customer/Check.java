package global.coda.ams.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.coda.ams.beans.Passenger;
import global.coda.ams.delegate.CustomerOperation;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Customer/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Check() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	static List<Passenger> passengerList = new ArrayList<Passenger>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int checkinPNR = new Integer(request.getParameter("PNRNo"));
		List<Integer> seatAvailable=CustomerOperation.showAvailableSeat(checkinPNR);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		passengerList=CustomerOperation.displayPassenger(checkinPNR);
		if(passengerList!=null)
		{
		out.println("<c:if test=\"${requestScope.checkedIn eq null }\">");
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Check-in</h1>");
		out.println("<script\r\n" + 
				"  src=\"http://code.jquery.com/jquery-3.3.1.min.js\"\r\n" + 
				"  integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\"\r\n" + 
				"  crossorigin=\"anonymous\"></script>");
		out.println("<h3>Select " + passengerList.size() +" seats </h3><br>");
		out.println("<form action=" + "\"Check\"" + "method=" + "'post'" + ">");
		
		for (Integer seat : seatAvailable) {
			if (seat % 4 == 1 || seat % 4 == 0) {
				out.printf("<input class=\"single-checkbox\" type=\"checkbox\" name= \"seatNo\" value=" + seat + ">"
						+ seat + "(W) ");

			} else {
				out.printf("<input class=\"single-checkbox\" type=\"checkbox\" name= \"seatNo\" value=" + seat + ">"
						+ seat);
			}
			if (seat % 4 == 0) {
				out.println("<br>");
			}

		}
		out.println("</p>");
		
		out.println("<input type=" + "\"submit\"" + "name=" + "'submit'" + "value= 'Check-in'>");
		out.println("</form>");
		out.println("</c:if>");
		out.println("<script type=\"text/javascript\">");
		
		out.println("var limit = "+passengerList.size()+";");
		out.println("$('input.single-checkbox').on('change', function(evt) {");
		out.println(" if($(this).siblings(':checked').length >= limit) {");
		out.println("this.checked = false;");
		out.println(" alert(\"allowed only "+passengerList.size()+"\");");
		out.println("}");
		out.println("});");
		out.println("</script>");
		
		out.println("</body>");
		out.println("</html>");
		}
		else
		{
			RequestDispatcher rd= request.getRequestDispatcher("CustomerViewMenu.jsp");
			request.setAttribute("alreadyCheck", true);
			rd.include(request, response);
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String[] stringArray=request.getParameterValues("seatNo");
		if(!CustomerOperation.checkin(passengerList,stringArray))	{	
		RequestDispatcher rd= request.getRequestDispatcher("CustomerViewMenu.jsp");
		request.setAttribute("checkedIn", true);
		rd.forward(request, response);
		}
		else
		{
			RequestDispatcher rd= request.getRequestDispatcher("CustomerViewMenu.jsp");
			request.setAttribute("checkedIn", false);
			rd.forward(request, response);
		}
	}

	
}

