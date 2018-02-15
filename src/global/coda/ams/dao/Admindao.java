package global.coda.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import global.coda.ams.beans.CrewMembers;
import global.coda.ams.beans.Flight;
import global.coda.ams.beans.FlightBook;

public class Admindao {
	public static CrewMembers adminLoginDao(int userId, String password) {
		MySQLAcess dao = new MySQLAcess();
		CrewMembers crew = null;
		Connection connect = dao.getConnection();
		String query = "Select password,crew_name from airportdb.crew where id=? and designation=?";
		PreparedStatement preparedStatement;
		if(connect==null)
		{
			return null;
		}
		try {
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, "Admin");
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false) {

			} else {
				String userName = resultSet.getString("crew_name");
				String passCode = resultSet.getString("password");
				if (passCode.equals(password)) {
					crew = new CrewMembers();
					crew.setName(userName);
					crew.setCrewId(userId);

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return crew;
	}

	public static List<Flight> displayFlightsDao() throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		String query = "select * from flight;";
		Connection connection = dao.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Flight> listFlight = null;
		if (resultSet.next() == false) {
			listFlight = null;
		} else {
			listFlight = new ArrayList<Flight>();
			do {
				Flight flight = new Flight();
				flight.setFlightId(resultSet.getInt(1));
				flight.setFlightNo(resultSet.getString(2));
				flight.setFlightName(resultSet.getString(3));
				flight.setSource(resultSet.getString(4));
				flight.setDestination(resultSet.getString(5));
				listFlight.add(flight);
			} while (resultSet.next());

		}
		connection.close();
		return listFlight;

	}

	public static boolean addFlightDao(FlightBook flight) {
		boolean addFlight = false;
		MySQLAcess dao = new MySQLAcess();
		String query = "insert into `flight_schedule`(flight_id,date,departure,arrival,economy_fare,business_fare) values(?,?,?,?,?,?);";
		Connection connection = dao.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, flight.getFlightId());

			preparedStatement.setString(2, flight.getDate());
			preparedStatement.setTime(3, flight.getDepartureTime());
			preparedStatement.setTime(4, flight.getArrivalTime());
			preparedStatement.setInt(5, flight.getEconomyFare());
			preparedStatement.setInt(6, flight.getBusinessFare());
			addFlight = preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addFlight;
	}

	public static boolean addnewCrewDao(String crewName, String designation, String contact, String password,
			String languages) throws SQLException, ParseException {
		boolean addedCrew = false;
		MySQLAcess dao = new MySQLAcess();
		String query = "insert into `crew`(crew_name,designation,contact_no,password) values(?,?,?,?);";
		Connection connection = dao.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, crewName);
		preparedStatement.setString(2, designation);
		preparedStatement.setString(3, contact);
		preparedStatement.setString(4, password);
		addedCrew = preparedStatement.execute();
		if (!addedCrew) {
			System.out.println("Languages are inserting");
			int crewId = getCrewIdDao();
			System.out.println(crewId);
			for (String language : languages.split(",")) {
				System.out.println(language);
				query = "insert into `languages` values(?,?);";
				connection = dao.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, crewId);
				preparedStatement.setString(2, language);
				preparedStatement.execute();
			}

		}
		return addedCrew;
	}

	public static int getCrewIdDao() throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		int crewId = 0;
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect
				.prepareStatement("SELECT id\r\n" + "FROM crew\r\n" + "ORDER BY id DESC\r\n" + "LIMIT 1");
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next() == false) {
			crewId = 0;
		} else {
			crewId = resultSet.getInt("id");
		}
		connect.close();
		return crewId;
	}

	public static List<Flight> displayAssignedFlightsDao() throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		List<Flight> flightList = null;

		String query = "select distinct s.flight_schedule_no, f.flight_no,s.date from ((flight as f inner join `flight_schedule`"
				+ " as s on f.id=s.flight_id) inner join slot as sl on sl.flight_schedule_no=s.flight_schedule_no); ";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next() == false) {
			flightList = null;
		} else {
			flightList = new ArrayList<Flight>();

			do {
				Flight flight = new Flight();
				flight.setScheduleNo(resultSet.getInt(1));
				flight.setFlightNo(resultSet.getString(2));
				flight.setDate(resultSet.getString(3));
				query = "select count(a.crew_id) from "
						+ "( slot as a inner join crew as c on c.id=a.crew_id) where a.flight_schedule_no=? and c.designation=?;";
				PreparedStatement preparedState = connect.prepareStatement(query);
				preparedState.setInt(1, flight.getScheduleNo());
				preparedState.setString(2, "Pilot");
				ResultSet result = preparedState.executeQuery();
				result.next();
				flight.setPilotCount(result.getInt(1));
				preparedState.setString(2, "Air");
				result = preparedState.executeQuery();
				result.next();
				flight.setAirCount(result.getInt(1));
				preparedState.setString(2, "Ground");
				result = preparedState.executeQuery();
				result.next();
				flight.setGroundCount(result.getInt(1));
				flight.setTicketClass("Assigned");
				flightList.add(flight);
			} while (resultSet.next());

			connect.close();

		}
		return flightList;
	}

	public static List<Flight> displayUnassignedFlightsDao() throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		List<Flight> flightList = null;
		String query = "select s.flight_schedule_no,f.flight_no,s.date from ( flight_schedule as s inner join flight as f  on f.id=s.flight_id)"
				+ " where s.flight_schedule_no not in  (select distinct flight_schedule_no from slot);";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next() == false) {
			flightList = null;
		} else {
			flightList = new ArrayList<Flight>();
			do {
				Flight flight = new Flight();
				flight.setScheduleNo(resultSet.getInt(1));
				flight.setFlightNo(resultSet.getString(2));
				flight.setDate(resultSet.getString(3));
				flightList.add(flight);
			} while (resultSet.next());
		}

		connect.close();
		return flightList;
	}

	public static List<CrewMembers> displayCrew(int scheduleNo) throws SQLException {
		List<CrewMembers> crewList = null;
		MySQLAcess dao = new MySQLAcess();
		String date = "";
		String query = "select s.date from ( flight_schedule as s inner join flight as f  on f.id=s.flight_id) "
				+ "where s.flight_schedule_no=?;";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, scheduleNo);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next() == false) {
			crewList = null;
		} else {

			date = resultSet.getString(1);
			crewList = new ArrayList<CrewMembers>();
			crewList.addAll(viewCompRequests(date, scheduleNo));
			crewList.addAll(slotRequests(scheduleNo));
			crewList.addAll(viewRestCrew(date, scheduleNo));
		}
		connect.close();

		return crewList;

	}

	public static List<CrewMembers> viewCompRequests(String date, int scheduleNo) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		String query = "select c.id,c.crew_name,c.designation from( crew as c inner join crew_availability as a on c.id=a.crew_id)"
				+ " where a.status=? and date=? and c.id not in (select crew_id from slot_requests where schedule_no=?) ;";
		Connection connect = dao.getConnection();
		List<CrewMembers> crewList = new ArrayList<CrewMembers>();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, "Pending");
		preparedStatement.setString(2, date);
		preparedStatement.setInt(3, scheduleNo);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next() == false) {
			System.out.println("No compensation Requests");
		} else {
			do {
				CrewMembers crew = new CrewMembers();
				crew.setCrewId(resultSet.getInt(1));
				crew.setName(resultSet.getString(2));
				crew.setDesignation(resultSet.getString(3));
				crew.setStatus("Compensate");
				crewList.add(crew);
			} while (resultSet.next());

		}

		connect.close();
		return crewList;
	}

	public static List<CrewMembers> slotRequests(int scheduleNo) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		String query = "select c.id,c.crew_name,c.designation from( crew as c inner join `slot_requests` as s on c.id=s.crew_id)"
				+ " where s.schedule_no=? and s.status=?;";
		Connection connect = dao.getConnection();
		List<CrewMembers> crewList = new ArrayList<CrewMembers>();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, scheduleNo);
		preparedStatement.setString(2, "Pending");
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next() == false) {
			System.out.println("No Slot Requests");
		} else {
			do {
				CrewMembers crew = new CrewMembers();
				crew.setCrewId(resultSet.getInt(1));
				crew.setName(resultSet.getString(2));
				crew.setDesignation(resultSet.getString(3));
				crew.setStatus("SlotRequest");
				crewList.add(crew);
			} while (resultSet.next());

		}

		connect.close();
		return crewList;
	}

	public static List<CrewMembers> viewRestCrew(String date, int scheduleNo) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		String query = "select id,crew_name,designation from crew where id not in (select crew_id from crew_availability as c where  c.date=? and c.status=?) "
				+ "and id not in (select crew_id from slot where flight_schedule_no=?) and designation <> ?;";

		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, date);
		preparedStatement.setString(2, "Unavailable");
		preparedStatement.setInt(3, scheduleNo);
		preparedStatement.setString(4, "Admin");
		ResultSet resultSet = preparedStatement.executeQuery();
		List<CrewMembers> crewList = new ArrayList<CrewMembers>();
		if (resultSet.next() == false) {
			System.out.println("No other Crew Available");
		} else {
			do {
				CrewMembers crew = new CrewMembers();
				crew.setCrewId(resultSet.getInt(1));
				crew.setName(resultSet.getString(2));
				crew.setDesignation(resultSet.getString(3));
				crew.setStatus("Other");
				crewList.add(crew);
			} while (resultSet.next());

		}

		connect.close();
		return crewList;

	}

	public static boolean assignCrewDao(int crewId, int scheduleId, String date) throws SQLException {
		boolean crewAssigned = false;
		MySQLAcess dao = new MySQLAcess();
		String query = "insert into slot values (?,?);";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, scheduleId);
		preparedStatement.setInt(2, crewId);
		crewAssigned = preparedStatement.execute();
		if (!preparedStatement.execute()) {
			compensationGrant(crewId, date);
			slotGrant(crewId, scheduleId);
		}
		connect.close();
		return crewAssigned;

	}

	public static void compensationGrant(int crewId, String date) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		String query = "update crew_availability set status=? where crew_id=? and date=?;";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, "Compensated");
		preparedStatement.setInt(2, crewId);
		preparedStatement.setString(3, date);
		preparedStatement.execute();
		connect.close();
	}

	public static void slotGrant(int crewId, int scheduleNo) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		String query = "update slot_requests set status=? where crew_id=? schedule_no=?;";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, "Granted");
		preparedStatement.setInt(2, crewId);
		preparedStatement.setInt(3, scheduleNo);
		preparedStatement.execute();
		connect.close();
	}

	public static boolean leaveAssignDao(int id) throws SQLException, ParseException {
		System.out.println("in dao the id is "+ id);
		boolean leaveGranted = false;
		int crewId = 0;
		String date = "";
		int leaveCount = 0;
		MySQLAcess dao = new MySQLAcess();
		String query = "select crew_id,date,no_of_days from" + "  leave_requests where id=?";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next() == false) {

		} else {
			crewId = resultSet.getInt(1);
			date = resultSet.getString(2);
			leaveCount = resultSet.getInt(3);

		}
		connect.close();
		
		 query = "update leave_requests set status=?  where id=?";
		 connect = dao.getConnection();
		 preparedStatement = connect.prepareStatement(query);
		 preparedStatement.setString(1, "Granted");
		preparedStatement.setInt(2, id);
		preparedStatement.execute();
		connect.close();
		
		for (int i = 0; i < leaveCount; i++) {
			query = "insert into crew_availability values(?,?,?);";
			connect = dao.getConnection();
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, crewId);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, "Unavailable");
			leaveGranted = preparedStatement.execute();
			if (leaveGranted) {
				return leaveGranted;
			} else {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date result = dateFormat.parse(date);
				Date tommorow = new Date(result.getTime() + (1000 * 60 * 60 * 24));
				date = dateFormat.format(tommorow);
			}

		}
		connect.close();
		return leaveGranted;
	}
	
	public static List<CrewMembers> leaveRequestsDao() throws SQLException, ParseException {
		MySQLAcess dao= new MySQLAcess();
		String query = "select l.id,c.crew_name,c.designation,l.date,l.no_of_days from"
				+ " crew as c inner join leave_requests as l on c.id=l.crew_id and status=?;";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, "Pending");
		ResultSet resultSet = preparedStatement.executeQuery();
		List<CrewMembers> crewList= null;
		if (resultSet.next() == false) {
			System.out.println("No requests to be approved");
		} else {
				crewList=new ArrayList<CrewMembers>();
			do {
				CrewMembers crew = new CrewMembers();
				crew.setCrewId(resultSet.getInt(1));
				crew.setName(resultSet.getString(2));
				crew.setDesignation(resultSet.getString(3));
				crew.setDate(resultSet.getString(4));
				crew.setLeaveCount(resultSet.getInt(5));
				crewList.add(crew);
			} while (resultSet.next());
		}
		return crewList;
		}
}
