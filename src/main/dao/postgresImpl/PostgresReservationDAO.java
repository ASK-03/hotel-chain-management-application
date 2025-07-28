package main.dao.postgresImpl;

import main.dao.interfaces.ReservationDAO;
import main.database.IDatabaseConnection;
import main.database.PostgresDBConnection;
import main.dto.Reservation;

import java.sql.*;
import java.util.List;

public class PostgresReservationDAO implements ReservationDAO {
    @Override
    public boolean makeReservation(Reservation reservation) {
        String insertSql = "INSERT INTO reservations (hotel_id, guest_id, room_id, check_in_date, check_out_date, reservation_date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String updateRoomSql = "UPDATE room SET available = false WHERE room_id = ?";

        try {
            IDatabaseConnection<Connection> db = PostgresDBConnection.getInstance();
            Connection conn = db.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            try (
                    PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement updateRoomStmt = conn.prepareStatement(updateRoomSql)
            ) {
                // Fill insert statement
                insertStmt.setInt(1, reservation.getHotelId());
                insertStmt.setInt(2, reservation.getGuestId());
                insertStmt.setInt(3, reservation.getRoomNumber());
                insertStmt.setTimestamp(4, reservation.getCheckInDate());
                insertStmt.setTimestamp(5, reservation.getCheckOutDate());
                insertStmt.setTimestamp(6, reservation.getReservationDate());
                insertStmt.setString(7, reservation.getStatus());

                int rowsInserted = insertStmt.executeUpdate();

                // Update room availability
                updateRoomStmt.setInt(1, reservation.getRoomNumber());
                int rowsUpdated = updateRoomStmt.executeUpdate();

                if (rowsInserted > 0 && rowsUpdated > 0) {
                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        reservation.setReservationId(generatedKeys.getInt(1));
                        System.out.println("Successfully reserved with reservation Id: " + reservation.getReservationId());
                    }
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (Exception e) {
                conn.rollback();
                System.err.println("Transaction failed: " + e.getMessage());
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            System.err.println("Database error during reservation: " + e.getMessage());
            return false;
        }
    }




    @Override
    public boolean cancelRoomReservation(int reservationId) {
        String updateReservationSql = "UPDATE reservations SET status = 'CANCELLED' WHERE reservation_id = ?";
        String updateRoomSql = "UPDATE room SET available = true WHERE room_id = (SELECT room_id FROM reservations WHERE reservation_id = ?)";

        try {
            IDatabaseConnection<Connection> db = PostgresDBConnection.getInstance();
            Connection conn = db.getConnection();

            conn.setAutoCommit(false); // Begin transaction

            // Update reservation status
            try (PreparedStatement stmt1 = conn.prepareStatement(updateReservationSql)) {
                stmt1.setInt(1, reservationId);
                int rows1 = stmt1.executeUpdate();

                // Update room availability
                try (PreparedStatement stmt2 = conn.prepareStatement(updateRoomSql)) {
                    stmt2.setInt(1, reservationId);
                    int rows2 = stmt2.executeUpdate();

                    if (rows1 > 0 && rows2 > 0) {
                        conn.commit();
                        return true;
                    } else {
                        conn.rollback(); // Failure
                        System.err.println("Cancellation failed.");
                        return false;
                    }
                }

            } catch (Exception e) {
                conn.rollback();
                throw new RuntimeException("Error during cancellation: " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true); // Reset autocommit
            }
        } catch (Exception e) {
            throw new RuntimeException("DB error: " + e.getMessage(), e);
        }
    }


    @Override
    public List<Reservation> getReservationsForHotel(int hotelId) {
        return List.of();
    }
}
