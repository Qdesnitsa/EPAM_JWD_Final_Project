package by.sidina.it_team.dao.connection;

import by.sidina.it_team.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Properties PROPERTIES = new Properties();
    private static final String DATABASE_PROPERTIES = "database.properties";
    private static final String PROPERTIES_URL = "db.url";
    private static final String PROPERTIES_PASSWORD = "db.password";
    private static final String PROPERTIES_USER = "db.user";
    private static final String PROPERTIES_DRIVER = "db.driver";
    private static final String PROPERTIES_POOL_SIZE = "db.poolsize";
    private static String url;
    private static String password;
    private static String user;
    private static String driver;
    private static String poolSize;

    private ConnectionFactory() {
    }

    static {
        try (InputStream inputStream = ConnectionFactory.class.getClassLoader()
                .getResourceAsStream(DATABASE_PROPERTIES)) {
            PROPERTIES.load(inputStream);
            url = PROPERTIES.getProperty(PROPERTIES_URL);
            password = PROPERTIES.getProperty(PROPERTIES_PASSWORD);
            user = PROPERTIES.getProperty(PROPERTIES_USER);
            driver = PROPERTIES.getProperty(PROPERTIES_DRIVER);
            poolSize = PROPERTIES.getProperty(PROPERTIES_POOL_SIZE);
        } catch (IOException e) {
            LOGGER.error("Cannot get information from database.properties file");
        }
    }

    public static Connection getConnection() throws DAOException {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Can not find database driver", e);
        } catch (SQLException ex) {
            throw new DAOException("Can not get connection", ex);
        }
    }

    public static int getPoolSize() {
        return Integer.parseInt(poolSize);
    }
}
