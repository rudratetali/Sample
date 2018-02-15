package global.coda.ams.delegate;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import global.coda.ams.beans.CrewMembers;
import global.coda.ams.beans.Flight;
import global.coda.ams.beans.FlightBook;
import global.coda.ams.dao.Admindao;

public class AdminOperation {

	Logger log = Logger.getLogger(AdminOperation.class);

	public AdminOperation() {
		BasicConfigurator.configure();
	}

	public CrewMembers adminLogin(int userId, String password) {

		CrewMembers crew = Admindao.adminLoginDao(userId, password);
		if (crew == null) {
			// System.out.println("Invalied details");
			log.debug("Invalid Details");
		}
		return crew;
	}

	public static List<Flight> displayFlights() throws SQLException {

		List<Flight> listFlight = Admindao.displayFlightsDao();
		if (listFlight == null) {
			System.out.println("Sorry No flights Available");
		}

		return listFlight;

	}

	public static boolean addFlight(int flightId, String date, String deptTime, String arrTime, String eFare,
			String bFare) {
		FlightBook flight = new FlightBook();

		flight.setFlightId(flightId);
		flight.setDate(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date result = null;
		try {
			result = dateFormat.parse(deptTime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Time departTime = new java.sql.Time(result.getTime());
		flight.setDepartureTime(departTime);
		try {
			result = dateFormat.parse(arrTime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Time arr = new java.sql.Time(result.getTime());

		flight.setArrivalTime(arr);
		flight.setEconomyFare(new Integer(eFare));
		flight.setBusinessFare(new Integer(bFare));
		boolean addFlight = Admindao.addFlightDao(flight);
		return addFlight;

	}

	public static boolean addnewCrew(String crewName, String designation, String contact, String password,
			String languages) {
		boolean addedCrew = false;
		try {
			addedCrew = Admindao.addnewCrewDao(crewName, designation, contact, password, languages);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (addedCrew) {
			System.out.println("Error on adding a new crew");
		}
		return addedCrew;

	}

	public static List<Flight> displayAssignedFlights() throws SQLException {

		List<Flight> flightList = Admindao.displayAssignedFlightsDao();

		if (flightList == null) {
			System.out.println("No flights are assigned yet!!!");
		}
		return flightList;
	}

	public static List<Flight> displayUnassignedFlights() throws SQLException {

		List<Flight> flightList = Admindao.displayUnassignedFlightsDao();

		if (flightList == null) {
			System.out.println("No assigned Flights");
		}
		return flightList;
	}

	public static List<CrewMembers> displayCrew(int scheduleNo) {
		List<CrewMembers> crewList = null;
		try {
			crewList = Admindao.displayCrew(scheduleNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (crewList == null) {
			System.out.println("No crew Available to Assign...");
		}

		return crewList;

	}

	public static boolean assignCrew(int crewId, int scheduleId, String date) {
		boolean assignedCrew = false;
		try {
			assignedCrew = Admindao.assignCrewDao(crewId, scheduleId, date);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (assignedCrew) {
			System.out.println("Error Occured !!! Please try again later");
		}
		return assignedCrew;
	}

	public static boolean leaveAssign(int id) {
		boolean leaveGranted = false;
		try {
			leaveGranted = Admindao.leaveAssignDao(id);
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (leaveGranted) {
			System.out.println("Error in assigning leave");
		}
		return leaveGranted;
	}

	public static List<CrewMembers> leaveRequests() {

		List<CrewMembers> crewList = null;
		try {
			crewList = Admindao.leaveRequestsDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (crewList == null) {
			System.out.println("No requests to be approved");
		}
		return crewList;
	}

}
