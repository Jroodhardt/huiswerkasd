package dao.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
    private Properties property;

    public DatabaseProperties() throws IOException, ClassNotFoundException {
        property = new Properties();
        InputStream propertiesStream = getClass().getClassLoader().getResourceAsStream("database.properties");
        property.load(propertiesStream);
    }

    public String getConnectionString() {
        return property.getProperty("connectionString");
    }

    public String getDriver() {
        return property.getProperty("driver");
    }
}

