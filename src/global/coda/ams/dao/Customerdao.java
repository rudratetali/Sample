package global.coda.ams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import global.coda.ams.beans.Customer;
import global.coda.ams.beans.Flight;
import global.coda.ams.beans.FlightBook;
import global.coda.ams.beans.Passenger;

public class Customerdao {
	public static boolean registerCustomerDao(Customer customer) {
		boolean successRegister = false;
		MySQLAcess dao = new MySQLAcess();
		try {
			Connection connect = dao.getConnection();
			if (null == connect) {
				System.out.println("Connection failed");
				System.exit(0);
			}

			PreparedStatement preparedStatement = connect.prepareStatement(
					"INSERT INTO customer (id, name, gender, contact_number, password) VALUES (?, ?, ?, ?, ?);");
			preparedStatement.setInt(1, customer.getUserName());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getGender());
			preparedStatement.setString(4, customer.getContact());
			preparedStatement.setString(5, customer.getPassword());
			successRegister = preparedStatement.execute();

			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return successRegister;
	}

	public static Customer customerLoginDao(int userId, String password) {
		Customer customer = null;
		MySQLAcess dao = new MySQLAcess();
		Connection connect = dao.getConnection();
		String query = "SELECT name,password from airportdb.customer where id=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false) {

			} else {
				String userName = resultSet.getString("name");
				String passcode = resultSet.getString("password");
				if (passcode.equals(password)) {
					customer = new Customer();
					customer.setUserName(userId);
					customer.setName(userName);

				} else {

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	public static List<Flight> displayDao() throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		String query = "SELECT distinct boarding_place,destination FROM airportdb.flight;";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Flight> listFlight = new ArrayList<Flight>();
		if (resultSet.next() == false) {
			listFlight = null;

		} else {
			do {
				Flight flight = new Flight();
				flight.setSource(resultSet.getString(1));
				flight.setDestination(resultSet.getString(2));
				listFlight.add(flight);
			} while (resultSet.next());

		}
		connect.close();
		return listFlight;

	}

	public static List<FlightBook> viewBookingDao(int userName) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		Connection connect = dao.getConnection();
		String query = "select t.pnr,f.flight_no,f.airline,f.boarding_place,f.destination,s.date,s.departure,s.arrival,t.no_of_seats from ((transaction as t inner join flight_schedule as s on t.flight_schedule_no=s.flight_schedule_no)inner join flight as f on s.flight_id=f.id) where t.customer_id=? and t.status<>'Cancelled';";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, userName);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<FlightBook> flightList = null;
		if (resultSet.next() == false) {
			flightList = null;
		} else {
			flightList = new ArrayList<FlightBook>();
			do {
				FlightBook flightBook = new FlightBook();
				flightBook.setPnrId(resultSet.getInt(1));
				flightBook.setFlightNo(resultSet.getString(2));
				flightBook.setFlightName(resultSet.getString(3));
				flightBook.setSource(resultSet.getString(4));
				flightBook.setDestination(resultSet.getString(5));
				flightBook.setDate(resultSet.getString(6));
				flightBook.setDepartureTime(resultSet.getTime(7));
				flightBook.setArrivalTime(resultSet.getTime(8));
				flightBook.setCount(resultSet.getInt(9));
				flightList.add(flightBook);

			} while (resultSet.next());

		}
		return flightList;
	}

	public static List<FlightBook> SearchBookingDateDao(int userName, String date) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		Connection connect = dao.getConnection();
		String query = "select t.pnr,f.flight_no,f.airline,f.boarding_place,f.destination,s.date,s.departure,s.arrival,t.no_of_seats from ((transaction as t inner join flight_schedule as s on t.flight_schedule_no=s.flight_schedule_no)inner join flight as f on s.flight_id=f.id) where t.customer_id=? and t.status<> 'Cancelled' and s.date=?;";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, userName);
		preparedStatement.setString(2, date);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<FlightBook> flightList = null;
		if (resultSet.next() == false) {
			flightList = null;
		} else {
			flightList = new ArrayList<FlightBook>();
			do {
				FlightBook flightBook = new FlightBook();
				flightBook.setPnrId(resultSet.getInt(1));
				flightBook.setFlightNo(resultSet.getString(2));
				flightBook.setFlightName(resultSet.getString(3));
				flightBook.setSource(resultSet.getString(4));
				flightBook.setDestination(resultSet.getString(5));
				flightBook.setDate(resultSet.getString(6));
				flightBook.setDepartureTime(resultSet.getTime(7));
				flightBook.setArrivalTime(resultSet.getTime(8));
				flightBook.setCount(resultSet.getInt(9));
				flightList.add(flightBook);

			} while (resultSet.next());

		}
		connect.close();
		return flightList;
	}

	public static List<FlightBook> SearchBookingPNRDao(int userName, int PNR) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		Connection connect = dao.getConnection();
		String query = "select t.pnr,f.flight_no,f.airline,f.boarding_place,f.destination,s.date,s.departure,s.arrival,t.no_of_seats from ((transaction as t inner join flight_schedule as s on t.flight_schedule_no=s.flight_schedule_no)inner join flight as f on s.flight_id=f.id) where t.customer_id=? and t.status<>'Cancelled' and t.pnr=?;";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, userName);
		preparedStatement.setInt(2, PNR);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<FlightBook> flightList = null;
		if (resultSet.next() == false) {
			flightList = null;
		} else {
			flightList = new ArrayList<FlightBook>();
			do {
				FlightBook flightBook = new FlightBook();
				flightBook.setPnrId(resultSet.getInt(1));
				flightBook.setFlightNo(resultSet.getString(2));
				flightBook.setFlightName(resultSet.getString(3));
				flightBook.setSource(resultSet.getString(4));
				flightBook.setDestination(resultSet.getString(5));
				flightBook.setDate(resultSet.getString(6));
				flightBook.setDepartureTime(resultSet.getTime(7));
				flightBook.setArrivalTime(resultSet.getTime(8));
				flightBook.setCount(resultSet.getInt(9));
				flightList.add(flightBook);

			} while (resultSet.next());

		}
		connect.close();
		return flightList;
	}

	@SuppressWarnings("resource")
	public static float cancelBookingDao(int cancelPNR) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		Connection connect = dao.getConnection();
		String query = "select f.flight_no,f.airline,f.boarding_place,f.destination,s.date,s.departure,s.arrival,t.no_of_seats,t.flight_class,s.economy_seats,s.business_seats,t.price from ((transaction as t inner join flight_schedule as s on t.flight_schedule_no=s.flight_schedule_no)inner join flight as f on s.flight_id=f.id) where t.pnr=? and t.status<>'Checked-in';";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, cancelPNR);
		ResultSet resultSet = preparedStatement.executeQuery();
		FlightBook flight = new FlightBook();
		if (resultSet.next() == false) {
			System.out.println("Error in Cancellation Database rtrival error!!!");
			return 0;
		} else {

			do {

				flight.setFlightNo(resultSet.getString(1));
				flight.setFlightName(resultSet.getString(2));
				flight.setSource(resultSet.getString(3));
				flight.setDestination(resultSet.getString(4));
				flight.setDate(resultSet.getString(5));
				flight.setDepartureTime(resultSet.getTime(6));
				flight.setArrivalTime(resultSet.getTime(7));
				flight.setCount(resultSet.getInt(8));
				flight.setTicketClass(resultSet.getString(9));
				flight.setSeatsEconomy(resultSet.getInt(10));
				flight.setSeatsBusiness(resultSet.getInt(11));
				flight.setAmount(resultSet.getFloat(12));

			} while (resultSet.next());
			connect.close();

			connect = dao.getConnection();
			query = "update transaction set status='Cancelled' where pnr=?;";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, cancelPNR);
			if (preparedStatement.execute()) {
				System.out.println("Error in Cancellation");
				return 0;
			}
			connect.close();

			if (flight.getTicketClass().equals("E")) {
				connect = dao.getConnection();
				query = "update `flight_schedule` set economy_seats=? where flight_schedule_no=?;";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setInt(1, flight.getSeatsEconomy() + flight.getCount());
				preparedStatement.setInt(2, flight.getScheduleNo());
				if (preparedStatement.execute()) {
					System.out.println("Error in updating seats");
					return 0;
				}
				connect.close();

			} else {
				connect = dao.getConnection();
				query = "update `flight_schedule` set business_seats=? where flight_schedule_no=?;";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setInt(1, flight.getSeatsBusiness() + flight.getCount());
				preparedStatement.setInt(2, flight.getScheduleNo());
				if (preparedStatement.execute()) {
					System.out.println("Error in updating seats");
					return 0;
				}
				connect.close();
			}

		}
		return flight.getAmount();
	}

	public static int getPNR() throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		int pnr_id = 0;
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect
				.prepareStatement("SELECT pnr\r\n" + "FROM transaction\r\n" + "ORDER BY pnr DESC\r\n" + "LIMIT 1");
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next() == false) {
			pnr_id = 0;
		} else {
			pnr_id = resultSet.getInt("pnr");
		}
		connect.close();
		return pnr_id;
	}

	@SuppressWarnings("resource")
	public static boolean bookingRecordDao(FlightBook flightBook) throws SQLException {
		boolean finalDecision;
		MySQLAcess dao = new MySQLAcess();
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(
				"INSERT INTO `transaction` (`pnr`, `customer_id`, `flight_schedule_no`,  `flight_class`, `no_of_seats`,`status`, `price`) VALUES ('"
						+ flightBook.getPnrId() + "', '" + flightBook.getCustomerId() + "', '"
						+ flightBook.getScheduleNo() + "', '" + flightBook.getTicketClass() + "', '"
						+ flightBook.getCount() + "', '" + flightBook.getStatus() + "', '" + flightBook.getAmount()
						+ "');");
		finalDecision = preparedStatement.execute();
		connect.close();
		List<Passenger> passengerList = flightBook.getPassengerList();
		boolean updateCount = false;
		for (Passenger passenger : passengerList) {

			connect = dao.getConnection();
			preparedStatement = connect.prepareStatement(
					"INSERT INTO `passenger` ( `pnr`, `passenger_name`, `seat_no`) VALUES ('" + passenger.getPnrId()
							+ "', '" + passenger.getPassengerName() + "', '" + passenger.getSeatNo() + "');");
			boolean insertPassenger = preparedStatement.execute();
			if (insertPassenger) {
				System.out.println("Error in insert Passenger");
			}
			connect.close();

			if (flightBook.getTicketClass().equals("E")) {
				connect = dao.getConnection();
				String query = "update flight_schedule set `economy_seats`= ? where flight_schedule_no=?";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setInt(1, (flightBook.getSeatsEconomy() - flightBook.getCount()));
				preparedStatement.setInt(2, flightBook.getScheduleNo());
				updateCount = preparedStatement.execute();
				if (updateCount) {
					System.out.println("Error in update Count");
				}

				connect.close();

			} else {
				connect = dao.getConnection();
				String query = "update flight_schedule set `business_seats`= ? where flight_schedule_no=?";
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setInt(1, (flightBook.getSeatsBusiness() - flightBook.getCount()));
				preparedStatement.setInt(2, flightBook.getScheduleNo());
				updateCount = preparedStatement.execute();
				if (updateCount) {
					System.out.println("Error in update Count");
				}
				connect.close();
				updateCount = insertPassenger && updateCount;
			}

		}
		finalDecision = finalDecision && updateCount;

		return finalDecision;
	}

	public static boolean checkin(List<Passenger> passengerList, String[] stringArray) {
		boolean passengerRetrival = false;
		MySQLAcess dao = new MySQLAcess();
		int i = 1;
		for (Passenger passenger : passengerList) {
			String query = "update passenger set seat_no= ? where passenger_name=? and pnr=?";
			Connection connect = dao.getConnection();
			try {
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				preparedStatement.setInt(1, new Integer(stringArray[i - 1]));
				preparedStatement.setString(2, passenger.getPassengerName());
				preparedStatement.setInt(3, passenger.getPnrId());
				passengerRetrival = preparedStatement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		return passengerRetrival;
	}

	public static FlightBook bookFlightDao(int scheduleNo, String category, int seatCount) {
		FlightBook flightObject = new FlightBook();
		MySQLAcess dao = new MySQLAcess();
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connect.prepareStatement(
					"select  f.flight_no,f.airline,f.boarding_place,f.destination,s.date,s.economy_fare,"
							+ "s.business_fare,s.departure,s.arrival,s.economy_seats,s.business_seats from flight as f "
							+ "inner join flight_schedule as s on f.id=s.flight_id where s.flight_schedule_no=?");
			preparedStatement.setInt(1, scheduleNo);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false) {
				flightObject = null;

			} else {

				do {

					flightObject.setScheduleNo(scheduleNo);
					flightObject.setFlightNo(resultSet.getString(1));
					flightObject.setFlightName(resultSet.getString(2));
					flightObject.setSource(resultSet.getString(3));
					flightObject.setDestination(resultSet.getString(4));
					flightObject.setDate(resultSet.getString(5));
					flightObject.setEconomyFare(resultSet.getInt(6));
					flightObject.setBusinessFare(resultSet.getInt(7));
					flightObject.setDepartureTime(resultSet.getTime(8));
					flightObject.setArrivalTime(resultSet.getTime(9));
					flightObject.setSeatsEconomy(resultSet.getInt(10));
					flightObject.setSeatsBusiness(resultSet.getInt(11));
					flightObject.setTicketClass(category);
					flightObject.setCount(seatCount);

					int costofTicket = 0;
					if (category.equals("E")) {
						costofTicket = seatCount * flightObject.getEconomyFare();

					} else if (category.equals("B")) {
						costofTicket = seatCount * flightObject.getBusinessFare();

					}
					flightObject.setAmount(costofTicket);

				} while (resultSet.next());
			}
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flightObject;
	}

	public static List<Passenger> displayPassengerDao(int checkinPNR) {
		List<Passenger> passengerList = new ArrayList<Passenger>();
		MySQLAcess dao = new MySQLAcess();
		String query = "select p.passenger_name,p.seat_no from passenger as p inner join transaction as t on t.pnr=p.pnr where t.pnr=?";
		Connection connect = dao.getConnection();

		PreparedStatement preparedStatement;
		try {

			preparedStatement = connect.prepareStatement(query);

			preparedStatement.setInt(1, checkinPNR);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false) {

			} else {

				if (!resultSet.getString(2).equals("0")) {

					return null;
				}

				query = "update transaction set status= ? where pnr=?";
				connect = dao.getConnection();
				preparedStatement = connect.prepareStatement(query);
				preparedStatement.setString(1, "Checked-in");
				preparedStatement.setInt(2, checkinPNR);
				preparedStatement.execute();
				connect.close();

				do {

					Passenger newPassenger = new Passenger();
					newPassenger.setPnrId(checkinPNR);
					newPassenger.setPassengerName(resultSet.getString(1));
					passengerList.add(newPassenger);
				} while (resultSet.next());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passengerList;

	}

	public static List<Flight> searchDao(String fromPlace, String toPlace, String date, int seatCount, String category,
			String order) throws SQLException {
		String query = "select  s.flight_schedule_no,f.flight_no,f.airline,f.boarding_place,f.destination,s.date,s.economy_fare,s.business_fare,s.economy_seats,s.business_seats,s.departure,s.arrival from flight as f inner join flight_schedule as s on f.id=s.flight_id where f.boarding_place='"
				+ fromPlace + "' and f.destination='" + toPlace + "' and s.date='" + date + "' order by " + order + ";";
		MySQLAcess dao = new MySQLAcess();
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Flight> listFlight = null;
		if (resultSet.next() == false) {
			listFlight = null;
		} else {
			listFlight = new ArrayList<Flight>();
			do {
				Flight flight = new Flight();
				flight.setScheduleNo(resultSet.getInt(1));
				flight.setFlightNo(resultSet.getString(2));
				flight.setFlightName(resultSet.getString(3));
				flight.setSource(resultSet.getString(4));
				flight.setDestination(resultSet.getString(5));
				flight.setDate(resultSet.getString(6));
				flight.setEconomyFare(resultSet.getInt(7));
				flight.setBusinessFare(resultSet.getInt(8));
				flight.setSeatsEconomy(resultSet.getInt(9));
				flight.setSeatsBusiness(resultSet.getInt(10));
				flight.setDepartureTime(resultSet.getTime(11));
				flight.setArrivalTime(resultSet.getTime(12));
				listFlight.add(flight);
			} while (resultSet.next());

		}
		connect.close();

		return listFlight;
	}

	public static List<Integer> showAvailableSeatDao(int userPnr) throws SQLException {
		MySQLAcess dao = new MySQLAcess();
		int startNo = 0;
		int endNo = 0;
		int flightScheduleNo = 0;
		String query = "select seats.start_seat_no,seats.ending_seat_no,t.flight_schedule_no from ((seats inner join flight_schedule as f on f.flight_id=seats.flight_id )inner join transaction as t on f.flight_schedule_no=t.flight_schedule_no)  where t.pnr=? and seats.flight_class=t.flight_class;";
		Connection connect = dao.getConnection();
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, userPnr);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next() == false) {
			System.out.println("Invalied PNR selected");
		} else {
			startNo = resultSet.getInt(1);
			endNo = resultSet.getInt(2);
			flightScheduleNo = resultSet.getInt(3);
		}
		connect.close();
		List<Integer> seatAvailable = new ArrayList<Integer>();
		for (int i = startNo; i <= endNo; i++) {
			seatAvailable.add(i);
		}
		connect.close();

		query = "select seat_no from passenger where seat_no <> 0 and pnr in (select pnr from transaction where flight_schedule_no=?); ";
		connect = dao.getConnection();
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, flightScheduleNo);
		resultSet = preparedStatement.executeQuery();
		List<Integer> seatBooked = new ArrayList<Integer>();
		if (resultSet.next() == false) {
			seatBooked.add(0);
		} else {
			do {
				seatBooked.add(resultSet.getInt(1));
			} while (resultSet.next());
		}
		connect.close();
		seatAvailable.removeAll(seatBooked);

		return seatAvailable;
	}
}
