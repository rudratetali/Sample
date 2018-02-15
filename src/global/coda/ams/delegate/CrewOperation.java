package global.coda.ams.delegate;

import java.sql.SQLException;
import java.util.List;

import global.coda.ams.beans.CrewMembers;
import global.coda.ams.beans.Flight;
import global.coda.ams.beans.FlightBook;
import global.coda.ams.dao.Crewdao;

public class CrewOperation {

	public static CrewMembers crewLogin(int userId, String password) {
		CrewMembers crew = Crewdao.crewLoginDao(userId, password);
		if(crew==null)
		{
			System.out.println("Invalied Details");
		}
		return crew;
	}

	public static List<Flight> getAssignedFlights(int crewId) throws SQLException {
		List<Flight> flightlist = Crewdao.getAssignedFlightsDao(crewId);
		if (flightlist == null) {
			System.out.println("No flights are assigned for today!!!");
		}
		return flightlist;

	}

	public static List<CrewMembers> displayCoCrew(int crewId, int userSchedule) throws SQLException {
		List<CrewMembers> crewList = Crewdao.displayCoCrewDao(crewId, userSchedule);
		
		if (crewList==null) {
			System.out.println("The Crew are yet to be Allocated");
		} 
		return crewList;
	}

	public static boolean insertLeave(int crewId, int leaveCount, String dateofLeave) {
		boolean leaveRecorded=Crewdao.insertLeaveDao(crewId, leaveCount, dateofLeave);
		if(leaveRecorded)
		{
			System.out.println("leave request not registered in database");
		}
		return leaveRecorded;
	}

	public static boolean insertCompensate(int crewId, int leaveCount, List<String> dateofLeave) {
		System.out.println(dateofLeave +" in delegate");
		boolean sucessComp = Crewdao.insertCompensateDao(crewId, leaveCount, dateofLeave);
		if(sucessComp)
		{
			System.out.println("Compensation request not registered in database");
		}
		return sucessComp;
	}

	public static CrewMembers viewProfile(int crewId) throws SQLException {
		
		CrewMembers crewMember = Crewdao.viewProfileDao(crewId);
		if (crewMember==null) {
			System.out.println("Sorry the record is missing ... Please Intimate Admin about it");			
		} 
		return crewMember;
	}

	public static List<FlightBook> slotRequest(int crewId, String destination, String date) throws SQLException {

		
		List<FlightBook> listFlight = Crewdao.slotRequestDao(crewId, destination, date);
		if(listFlight==null)
		{
			System.out.println("No Flights available for this schedule");
		}
		return listFlight;

	}

	public static boolean slotRecord(int scheduleNo, int crewId) throws SQLException {
		boolean slotSuccess = Crewdao.slotRecordDao(scheduleNo, crewId); 
		if(slotSuccess)
		{
			System.out.println("Slot not recorded properly into the database");
		}
		return slotSuccess;
	}
}
