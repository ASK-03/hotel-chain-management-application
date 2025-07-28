package main.dao.postgresImpl;

import main.dao.interfaces.GuestDAO;
import main.database.IDatabaseConnection;
import main.database.PostgresDBConnection;
import main.dto.Guest;

import java.sql.*;

public class PostgresGuestDAO implements GuestDAO {

    @Override
    public void addGuest(Guest guest) {
        String sql = "INSERT INTO guest (name, email, phone, hotel_id) VALUES (?, ?, ?, ?)";
        try {
            IDatabaseConnection<Connection> db = PostgresDBConnection.getInstance();
            Connection conn = db.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhone());
            stmt.setInt(4, guest.getHotelId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    guest.setGuestId(generatedKeys.getInt(1));
                    System.out.println("Successfully registered the Guest with Id: " + guest.getGuestId());
                }
            }

        } catch (SQLException e) {
            System.err.println("Error in adding this guest\n  More Detail: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
