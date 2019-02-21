import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class getPropValues {
	
	public String getProperties(String IMURL) throws IOException {
		
		String result = "";
		Properties prop = new Properties();
		String prop_file = "config.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(prop_file);
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("Property file not found");
		}
	    result = prop.getProperty(IMURL);
		
		return result;
	}

}
