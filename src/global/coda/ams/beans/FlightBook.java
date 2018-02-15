package global.coda.ams.beans;

import java.util.List;

public class FlightBook extends Flight{
	private int pnrId;
	private int customerId;
	private float amount;
	private String ticketClass;
	private String status;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTicketClass() {
		return ticketClass;
	}
	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
	}
	private List<Passenger> passengerList;
	public int getPnrId() {
		return pnrId;
	}
	public void setPnrId(int pnrId) {
		this.pnrId = pnrId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int userName) {
		this.customerId = userName;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	

}
