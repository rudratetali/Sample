package global.coda.ams.beans;

public class Passenger {
	private int pnrId;
	private String passengerName;
	private int seatNo;
	public int getPnrId() {
		return pnrId;
	}
	public void setPnrId(int pnrId) {
		this.pnrId = pnrId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int i) {
		this.seatNo = i;
	}

}
