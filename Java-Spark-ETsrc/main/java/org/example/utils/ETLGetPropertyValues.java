package org.example.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class ETLGetPropertyValues {
    String result = "";
    InputStream inputStream;
    public String getPropValues() throws IOException {
        String writeOutputFile = null;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            Date time = new Date(System.currentTimeMillis());
            // get the property value and print it out
            String user = prop.getProperty("user");
            writeOutputFile = prop.getProperty("writeOutputFile");
            String outputFileType = prop.getProperty("outputFileType");
            String columnSeperator = prop.getProperty("columnSeperator");
            result = "User = " + user + ", outputFileType = " + outputFileType + ", columnSeperator = " + columnSeperator;
            System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return writeOutputFile;
    }
}
