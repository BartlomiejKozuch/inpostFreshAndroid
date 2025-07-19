package config;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Properties;

public class ConfigMenager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigMenager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Not found config.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error in config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
