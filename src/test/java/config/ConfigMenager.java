package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigMenager {

    private static final Properties configProperties = new Properties();
    private static final Properties userProperties = new Properties();

    static {
        loadProperties(configProperties, "config.properties");
        loadProperties(userProperties, "users.properties");
    }

    private static void loadProperties(Properties props, String filename) {
        try (InputStream input = ConfigMenager.class.getClassLoader().getResourceAsStream(filename)) {
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("Not found: " + filename);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading " + filename + ": " + e.getMessage());
        }
    }

    public static String getConfig(String key) {
        return configProperties.getProperty(key);
    }

    public static String getUser(String key) {
        return userProperties.getProperty(key);
    }
}
