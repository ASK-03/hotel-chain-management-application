package main.dao.postgresImpl;

import main.dao.interfaces.HotelDAO;
import main.database.IDatabaseConnection;
import main.database.PostgresDBConnection;
import main.dto.Hotel;

import java.sql.*;

public class PostgresHotelDAO implements HotelDAO {

    @Override
    public void addHotel(Hotel hotel) {
        String sql = "INSERT INTO hotel (name, location) VALUES (?, ?)";
        try {

            IDatabaseConnection<Connection> db = PostgresDBConnection.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getLocation());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    hotel.setHotelId(generatedKeys.getInt(1));
                    System.out.println("Successfully registered the Hotel with Id: " + hotel.getHotelId());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hotel getHotel(int hotelId) {
        String sql = "SELECT * FROM hotel WHERE hotel_id = ?";
        try {

            IDatabaseConnection<Connection> db = PostgresDBConnection.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, hotelId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotelId(rs.getInt("hotel_id"));
                hotel.setName(rs.getString("name"));
                hotel.setLocation(rs.getString("location"));
                return hotel;
            } else {
                System.out.println("No hotel found with ID: " + hotelId);
            }
        } catch (SQLException e) {
            System.err.println("Error Finding Hotel using hotel_id:" + hotelId + "\n More Details:" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
