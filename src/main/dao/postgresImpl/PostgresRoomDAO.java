package main.dao.postgresImpl;

import main.dao.interfaces.RoomDAO;
import main.database.IDatabaseConnection;
import main.database.PostgresDBConnection;
import main.dto.Room;
import main.dto.RoomType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresRoomDAO implements RoomDAO {
    @Override
    public void addRoom(Room room) {
        String sql = "INSERT INTO room (type, available, hotel_id) VALUES (?, ?, ?)";
        try {

            IDatabaseConnection<Connection> db = PostgresDBConnection.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, room.getType() == RoomType.SINGLE ? "SINGLE" : "DOUBLE");
            stmt.setBoolean(2, room.isAvailable());
            stmt.setInt(3, room.getHotelId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    room.setRoomNumber(generatedKeys.getInt(1));
                    System.out.println("Successfully registered the Hotel with Id: " + room.getRoomNumber());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> getRoomsForHotel(int hotelId) {
        String sql = "SELECT * FROM room WHERE hotel_id = ?";
        try {

            IDatabaseConnection<Connection> db = PostgresDBConnection.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, hotelId);

            ResultSet rs = stmt.executeQuery();

            List<Room> rooms = new ArrayList<>();

            while (rs.next()) {
                Room room = new Room();
                room.setRoomNumber(rs.getInt("room_id"));
                room.setType(rs.getString("type").equals("SINGLE") ? RoomType.SINGLE : RoomType.DOUBLE );
                room.setAvailable(rs.getBoolean("available"));
                room.setHotelId(rs.getInt("hotel_id"));
                rooms.add(room);
            }

            return rooms;

        } catch (SQLException e) {
            System.err.println("Error Finding rooms using hotel_id: " + hotelId + "\n More Details:" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    @Override
    public Room getRoom(int roomId, int hotelId) {
        String sql = "SELECT * FROM room WHERE room_id = ? AND hotel_id = ?";
        try {

            IDatabaseConnection<Connection> db = PostgresDBConnection.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, roomId);
            stmt.setInt(2, hotelId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Room room = new Room();
                room.setRoomNumber(rs.getInt("room_id"));
                room.setType(rs.getString("type").equalsIgnoreCase("SINGLE") ? RoomType.SINGLE : RoomType.DOUBLE);
                room.setAvailable(rs.getBoolean("available"));
                room.setHotelId(rs.getInt("hotel_id"));

                return room;
            } else {
                System.err.println("No room found with room_id: " + roomId);
            }

        } catch (SQLException e) {
            System.err.println("Error Finding Room using room_id: " + roomId + " and hotel_id: " + hotelId + "\n More Details:" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
