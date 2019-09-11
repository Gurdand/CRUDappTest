package util;

import exception.ApplicationException;
import model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DBHelper {

    private static Connection connection;

    private static Configuration configuration;

    public Connection getConnection() throws ApplicationException {
        if (connection == null) {

            DBProperties properties = new DBProperties();

            try {
                DriverManager.registerDriver((Driver) Class.forName(properties.getAppProperty("driver")).getDeclaredConstructor().newInstance());

                connection = DriverManager.getConnection(properties.getAppProperty("url"),
                        properties.getAppProperty("login"), properties.getAppProperty("password"));

            } catch (Exception e) {
                e.printStackTrace();
                throw new ApplicationException("Ошибка создания connection!");
            }
        }

        return connection;
    }


    public Configuration getConfiguration() throws ApplicationException {
        if (configuration == null) {

            DBProperties properties = new DBProperties();

            try {
                configuration = new Configuration();

                configuration.addAnnotatedClass(User.class);

                configuration.setProperty("hibernate.connection.url", properties.getAppProperty("url"));
                configuration.setProperty("hibernate.connection.driver_class", properties.getAppProperty("driver"));
                configuration.setProperty("hibernate.connection.username", properties.getAppProperty("login"));
                configuration.setProperty("hibernate.connection.password", properties.getAppProperty("password"));
                configuration.setProperty("hibernate.show_sql", properties.getAppProperty("showSQL"));
                configuration.setProperty("hibernate.hbm2ddl.auto", properties.getAppProperty("hbm2ddl"));
                configuration.setProperty("hibernate.dialect", properties.getAppProperty("dialect"));

            } catch (Exception e) {
                e.printStackTrace();
                throw new ApplicationException("Ошибка создания конфигурации!");
            }
        }
        return configuration;
    }

}
