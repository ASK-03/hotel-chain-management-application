package main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDBConnection implements IDatabaseConnection<Connection>{

    private static PostgresDBConnection instance;

    private PostgresDBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgresSQL JDBC Driver not found.", e);
        }
    }

    public static synchronized PostgresDBConnection getInstance() {
        if (instance == null) {
            instance = new PostgresDBConnection();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/hoteldb";
        String user = "postgres";
        String password = "password";
        return DriverManager.getConnection(url, user, password);
    }
}
