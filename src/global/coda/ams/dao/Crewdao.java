package global.coda.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import global.coda.ams.beans.CrewMembers;
import global.coda.ams.beans.Flight;
import global.coda.ams.beans.FlightBook;

public class Crewdao {
	public static CrewMembers crewLoginDao(int userId, String password) {
		MySQLAcess dao = new MySQLAcess();
		CrewMembers crew = null;
		Connection connect = dao.getConnection();
		String query = "Select password,crew_name,designation from airportdb.crew where id=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false) {
				crew = null;

			} else {
				String userName = resultSet.getString("crew_name");
				String passCode = resultSet.getString("password");
				String designation=resultSet.getString(3);
				if (passCode.equals(password)) {
					crew = new CrewMembers();
					crew.setName(userName);
					crew.setCrewId(userId);
					crew.setDesignation(designation);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return crew;
	}

	public static List<Flight> getAssignedFlightsDao(int crewId) throws SQLException {

		MySQLAcess dao = new MySQLAcess();
		String query = "select s.flight_schedule_no,f.flight_no,f.airline,f.boarding_place,f.destination,s.date,"
				+ "s.departure,s.arrival from ((flight as f inner join flight_schedule as s on f.id=s.flight_id)"
				+ "inner join slot as sl on sl.flight_schedule_no=s.flight_schedule_no ) where sl.crew_id=?; ";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, crewId);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Flight> flightlist = null;
		if (resultSet.next() == false) {
			flightlist = null;
		} else {
			flightlist = new ArrayList<Flight>();
			do {

				Flight flight = new Flight();
				flight.setScheduleNo(resultSet.getInt(1));
				flight.setFlightNo(resultSet.getString(2));
				flight.setFlightName(resultSet.getString(3));
				flight.setSource(resultSet.getString(4));
				flight.setDestination(resultSet.getString(5));
				flight.setDate(resultSet.getString(6));
				flight.setDepartureTime(resultSet.getTime(7));
				flight.setArrivalTime(resultSet.getTime(8));
				flightlist.add(flight);

			} while (resultSet.next());

		}

		connect.close();
		return flightlist;

	}

	public static List<CrewMembers> displayCoCrewDao(int crewId, int userSchedule) throws SQLException {

		MySQLAcess dao = new MySQLAcess();
		String query = "select c.id,c.crew_name,c.designation,c.contact_no from (crew as c inner join slot as sl on sl.crew_id=c.id) where sl.flight_schedule_no= ? and sl.crew_id <> ? ;";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, userSchedule);
		preparedStatement.setInt(2, crewId);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<CrewMembers> crewList = null;
		if (resultSet.next() == false) {
			crewList = null;
		} else {
			crewList = new ArrayList<CrewMembers>();
			do {
				CrewMembers crew = new CrewMembers();
				crew.setCrewId(resultSet.getInt(1));
				crew.setName(resultSet.getString(2));
				crew.setDesignation(resultSet.getString(3));
				crew.setContact(resultSet.getString(4));
				crewList.add(crew);
			} while (resultSet.next());
		}

		return crewList;
	}

	public static boolean insertLeaveDao(int crewId, int leaveCount, String dateofLeave) {
		MySQLAcess dao = new MySQLAcess();
		String query = "insert into leave_requests values(?,?,?,?);";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement;
		boolean leaveRecorded = false;
		try {
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, crewId);
			preparedStatement.setString(2, dateofLeave);
			preparedStatement.setInt(3, leaveCount);
			preparedStatement.setString(4, "Request");
			leaveRecorded = preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return leaveRecorded;
	}

	public static boolean insertCompensateDao(int crewId, int leaveCount, List<String> dateofLeave) {
		MySQLAcess dao = new MySQLAcess();
		boolean sucessComp = false;
		System.out.println(dateofLeave +" in dao");
		for (String date : dateofLeave) {
			String query = "insert into `crew_availability` values(?,?,?);";
			Connection connect = dao.getConnection();
			PreparedStatement preparedStatement;
			try {
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setInt(1, crewId);
				preparedStatement.setString(2, date);
				preparedStatement.setString(3, "Pending");
				sucessComp = preparedStatement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sucessComp;
	}

	public static CrewMembers viewProfileDao(int crewId) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		String query = "select id,crew_name,designation,contact_no from crew where id=? ";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, crewId);
		ResultSet resultSet = preparedStatement.executeQuery();
		CrewMembers crewMember = null;
		if (resultSet.next() == false) {
			 crewMember = null;
			//return crewMember;
		} else {
			crewMember = new CrewMembers();
			do {
				crewMember.setCrewId(resultSet.getInt(1));
				crewMember.setName(resultSet.getString(2));
				crewMember.setDesignation(resultSet.getString(3));
				crewMember.setContact(resultSet.getString(4));
			} while (resultSet.next());
		}
		connect.close();

		query = "select language from languages where crew_id=? ";
		connect = dao.getConnection();
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, crewId);
		resultSet = preparedStatement.executeQuery();
		List<String> languageList = new ArrayList<String>();
		if (resultSet.next() == false) {
			System.out.println("Languages are not been updated in the profile");
		} else {
			do {
				String language = resultSet.getString(1);
				languageList.add(language);
			} while (resultSet.next());
		}
		StringBuffer Languages = new StringBuffer();
		;
		for (String language : languageList) {
			Languages.append(language + "\t");

		}
		String crewLanguages = Languages.toString();
		crewMember.setLanguages(crewLanguages);
		connect.close();
		query = "select count(date) from `crew_availability` where crew_id=? and (status='Pending' or status='Unavailable'); ";
		connect = dao.getConnection();
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, crewId);
		resultSet = preparedStatement.executeQuery();
		resultSet.next();
		crewMember.setLeaveCount(resultSet.getInt(1));

		return crewMember;
	}
	
	public static List<FlightBook> slotRequestDao(int crewId, String destination, String date) throws SQLException {

		MySQLAcess dao = new MySQLAcess();
		List<FlightBook> listFlight = null;
		String query = "select s.flight_schedule_no,f.airline,f.boarding_place,s.departure from `flight_schedule` as s inner join `flight` as f on f.id=s.flight_id where f.destination=? and s.date=?;";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, destination);
		preparedStatement.setString(2, date);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next() == false) {
			listFlight = null;
		} else {
			 listFlight= new ArrayList<FlightBook>();
			do {
				FlightBook flight = new FlightBook();
				flight.setScheduleNo(resultSet.getInt(1));
				flight.setFlightName(resultSet.getString(2));
				flight.setSource(resultSet.getString(3));
				flight.setDepartureTime(resultSet.getTime(4));
				listFlight.add(flight);

			} while (resultSet.next());
		}

		connect.close();
		return listFlight;

	}
	
	public static boolean slotRecordDao(int scheduleNo, int crewId) throws SQLException {
		boolean slotSuccess = false; 
		MySQLAcess dao = new MySQLAcess();
		String query = "insert into `slot_requests` (crew_id,schedule_no) values (?,?);";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, crewId);
		preparedStatement.setInt(2, scheduleNo);
		slotSuccess=preparedStatement.execute();
		return slotSuccess;
	}
}
