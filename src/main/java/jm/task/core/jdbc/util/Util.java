package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {

    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:postgresql://localhost:3306/test_database";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static SessionFactory getSessionFactory(){
        return new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}

