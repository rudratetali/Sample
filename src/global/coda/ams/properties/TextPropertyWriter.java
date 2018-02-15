package global.coda.ams.properties;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TextPropertyWriter {
	
	

	    public static void main(String args[]) throws FileNotFoundException, IOException {
	    	
	        //Creating properties files from Java program
	    	Properties props = new Properties();
	        FileOutputStream fos = new FileOutputStream("C:\\Users\\Admin\\Desktop\\user.properties");
	      
	        props.setProperty("database", "airportdb");
	        props.setProperty("user", "root");
	        props.setProperty("password", "pramilarani");
	      
	        //writing properties into properties file from Java
	        props.store(fos, "Properties file generated from Java program");
	      
	        fos.close();
	      
	    }
	


	

}
