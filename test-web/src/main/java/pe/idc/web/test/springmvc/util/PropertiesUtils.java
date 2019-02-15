package pe.idc.web.test.springmvc.util;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author otheo
 */
public final class PropertiesUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);

    private static final Properties APP_PROPERTIES;
    private static final String APP_PROPERTIES_PATH = "properties/test-web/app.properties";
    private static final Properties MESSAGES_PROPERTIES;
    private static final String MESSAGES_PROPERTIES_PATH = "resources/messages.properties";

    static {
        APP_PROPERTIES = new Properties();
        MESSAGES_PROPERTIES = new Properties();

        try {
            APP_PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(APP_PROPERTIES_PATH));
        } catch (IOException ex) {
            LOGGER.error("Unable to initialize APP_PROPERTIES.", ex);
        }

        try {
            MESSAGES_PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(MESSAGES_PROPERTIES_PATH));
        } catch (IOException ex) {
            LOGGER.error("Unable to initialize MESSAGES_PROPERTIES.", ex);
        }
    }

    private PropertiesUtils() {
    }

    public static String getAppProperty(String name) {
        return (APP_PROPERTIES == null) ? null : APP_PROPERTIES.getProperty(name);
    }

    public static String getMessage(String name) {
        return (MESSAGES_PROPERTIES == null) ? null : MESSAGES_PROPERTIES.getProperty(name);
    }

}
