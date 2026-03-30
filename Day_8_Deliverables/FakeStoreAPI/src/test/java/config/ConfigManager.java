package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton config reader — loads config.properties once at class load time.
 */
public class ConfigManager {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in == null) throw new RuntimeException("config.properties not found on classpath");
            PROPS.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigManager() {}

    public static String get(String key) {
        String value = PROPS.getProperty(key);
        if (value == null) throw new RuntimeException("Missing property: " + key);
        return value;
    }

    public static String baseUrl()           { return get("base.url"); }
    public static String defaultUsername()   { return get("default.username"); }
    public static String defaultPassword()   { return get("default.password"); }
}
