package global.coda.ams.beans;

public class Customer extends Person {
protected int userName;
private String password;


public int getUserName() {
	return userName;
}
public void setUserName(int userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
