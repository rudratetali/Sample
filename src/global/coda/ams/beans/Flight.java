package global.coda.ams.beans;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight  {
	private int flightId;
	private int scheduleNo;
	private String flightNo;
	private String flightName;
	private String source;
	private String destination;
	private Date date;
	private int economyFare;
	private int businessFare;
	private int seatsEconomy;
	private int seatsBusiness;
	private Time arrivalTime;
	private Time departureTime;
	private int pilotCount;
	private int airCount;
	private int groundCount;
	private String ticketClass;
	
	public String getTicketClass() {
		return ticketClass;
	}

	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
	}

	public int getPilotCount() {
		return pilotCount;
	}

	public void setPilotCount(int pilotCount) {
		this.pilotCount = pilotCount;
	}

	public int getAirCount() {
		return airCount;
	}

	public void setAirCount(int airCount) {
		this.airCount = airCount;
	}

	public int getGroundCount() {
		return groundCount;
	}

	public void setGroundCount(int groundCount) {
		this.groundCount = groundCount;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateofJourney=dateFormat.format(date);
		return dateofJourney;
	}

	public void setDate(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    
		try {
			this.date =  df.parse(date);
		} catch (ParseException e) {
			System.out.println("Invalied Date Format");
		} 
	}
	
	public int getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}


	public Time getArrivalTime() {
		
		return arrivalTime ;
	}

	public void setArrivalTime(Time time) {
		
				
		this.arrivalTime =  time;
	}
public Time getDepartureTime() {
		
		return departureTime ;
	}

	public void setDepartureTime(Time time) {
		
		
				
		this.departureTime =  time;
	}
	public int getEconomyFare() {
		return economyFare;
	}

	public void setEconomyFare(int economyFare) {
		this.economyFare = economyFare;
	}

	public int getBusinessFare() {
		return businessFare;
	}

	public void setBusinessFare(int businessFare) {
		this.businessFare = businessFare;
	}

	public int getSeatsEconomy() {
		return seatsEconomy;
	}

	public void setSeatsEconomy(int seatsEconomy) {
		this.seatsEconomy = seatsEconomy;
	}

	public int getSeatsBusiness() {
		return seatsBusiness;
	}

	public void setSeatsBusiness(int seatsBusiness) {
		this.seatsBusiness = seatsBusiness;
	}



	

	

//	public int compareTo(Flight flightdetailObject) {
//		return (this.arritime.compareTo(flightdetailObject.time));
//			
//	}

	
}
