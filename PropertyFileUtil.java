package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class PropertyFileUtil 
{

	public static String getcellvaluekey(String key) throws Throwable, IOException
	{
		Properties configproperties= new Properties();
		configproperties.load(new FileInputStream(new File("./ConfigFile/Environment.properties")));
		
		
		return configproperties.getProperty(key);
		
	}
}
