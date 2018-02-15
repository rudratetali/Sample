package global.coda.ams.beans;

public class CrewMembers extends Person {
private int crewId;
private String designation;
private int workingDays;
private int leaveCount;
private int compensationDays;
private String password;
private String languages;
private String date;
private String status;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getLanguages() {
	return languages;
}
public void setLanguages(String languages) {
	this.languages = languages;
}
public int getCrewId() {
	return crewId;
}
public void setCrewId(int crewId) {
	this.crewId = crewId;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public int getWorkingDays() {
	return workingDays;
}
public void setWorkingDays(int workingDays) {
	this.workingDays = workingDays;
}
public int getLeaveCount() {
	return leaveCount;
}
public void setLeaveCount(int leaveCount) {
	this.leaveCount = leaveCount;
}
public int getCompensationDays() {
	return compensationDays;
}
public void setCompensationDays(int compensationDays) {
	this.compensationDays = compensationDays;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
