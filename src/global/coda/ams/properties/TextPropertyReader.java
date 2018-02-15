package global.coda.ams.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TextPropertyReader {
	private String database;
	private String user;
	private String password;
	 Properties props;
	 FileInputStream fis;
	
	public TextPropertyReader()
	{
	 props = new Properties();
    try {
    	 fis = new  FileInputStream("C:\\Users\\Admin\\Desktop\\user.properties");
    	 props.load(fis);
    	 this.setDatabase();
    	 this.setUser();
    	 this.setPassword();
    	 
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase() {
		this.database = props.getProperty("database");
		
	}

	public String getUser() {
		return user;
	}

	public void setUser() {
		this.user =props.getProperty("user");
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword() {
		this.password = props.getProperty("password");
		
	}
	
	
	
}
