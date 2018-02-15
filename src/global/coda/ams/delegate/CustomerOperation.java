package global.coda.ams.delegate;

import java.sql.SQLException;
import java.util.List;

import global.coda.ams.beans.Customer;
import global.coda.ams.beans.Flight;
import global.coda.ams.beans.FlightBook;
import global.coda.ams.beans.Passenger;
import global.coda.ams.dao.Customerdao;

public class CustomerOperation {

	public static FlightBook bookFlight(int scheduleNo, String category, int seatCount) {
		FlightBook flightObject = Customerdao.bookFlightDao(scheduleNo, category, seatCount);
		if (flightObject == null) {
			System.out.println("Unable to retrive the details of the Flight");
		}
		return flightObject;
	}

	public static boolean registerCustomer(Customer customer) {
		boolean successRegister = false;

		successRegister = Customerdao.registerCustomerDao(customer);
		if (successRegister) {
			System.out.println("Registration not successful.. Try agian Later...");
		}

		return successRegister;

	}

	public static Customer customerLogin(int userId, String password) {
		Customer customer = Customerdao.customerLoginDao(userId, password);
		if (customer == null) {
			System.out.println("Invalied Login Details");
		}
		return customer;
	}

	public static List<Flight> search(String fromPlace, String toPlace, String date, int seatCount, String category,
			String order) {
		
		List<Flight> listFlight = null;
		try {
			listFlight=Customerdao.searchDao(fromPlace, toPlace, date, seatCount, category, order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (listFlight==null) {
			System.out.println("No Flights available for the Current Search");
		} 

		return listFlight;
	}

	public static List<Flight> display() {
		List<Flight> listFlight = null;
		try {
			listFlight = Customerdao.displayDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (listFlight == null) {
			System.out.println("Sorry No flights Available");
		}
		return listFlight;
	}

	public static List<FlightBook> viewBooking(int userName) {
		List<FlightBook> flightList = null;
		try {
			flightList = Customerdao.viewBookingDao(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (flightList == null) {
			System.out.println("No Booking history available....");
		}

		return flightList;
	}

	public static List<FlightBook> SearchBookingDate(int userName, String date) {

		List<FlightBook> flightList = null;
		try {
			flightList = Customerdao.SearchBookingDateDao(userName, date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (flightList == null) {
			System.out.println("No Booking history available....");
		}
		return flightList;
	}

	public static List<FlightBook> SearchBookingPNR(int userName, int PNR) {

		List<FlightBook> flightList = null;
		try {
			flightList = Customerdao.SearchBookingPNRDao(userName, PNR);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (flightList == null) {
			System.out.println("No Booking history available....");
		}
		return flightList;
	}

	public static float cancelBooking(int cancelPNR) {
		float amount = 0;
		try {
			amount = Customerdao.cancelBookingDao(cancelPNR);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (amount == 0) {
			System.out.println("Error in Cancellation");
		}
		return amount;
	}

	public static boolean bookingRecord(FlightBook flightBook) {
		boolean bookingSuccess = false;
		try {
			bookingSuccess = Customerdao.bookingRecordDao(flightBook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookingSuccess;
	}

	public static List<Integer> showAvailableSeat(int userPnr)
			 {
		List<Integer> seatAvailable = null;
		try {
			seatAvailable = Customerdao.showAvailableSeatDao(userPnr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seatAvailable;
	}

	public static boolean checkin(List<Passenger> passengerList, String[] stringArray) {
		boolean passengerRetrival = false;
		passengerRetrival = Customerdao.checkin(passengerList, stringArray);
		if (passengerRetrival) {
			System.out.println("Error in the check-in process");
		}
		return passengerRetrival;
	}

	public static List<Passenger> displayPassenger(int checkinPNR) {
		List<Passenger> passengerList = Customerdao.displayPassengerDao(checkinPNR);

		return passengerList;

	}

}
