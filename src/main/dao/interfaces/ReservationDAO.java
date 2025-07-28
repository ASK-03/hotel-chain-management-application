package main.dao.interfaces;

import main.dto.Reservation;

import java.util.List;

public interface ReservationDAO {
    boolean makeReservation(Reservation reservation);
    boolean cancelRoomReservation(int reservationId);
    List<Reservation> getReservationsForHotel(int hotelId);
}
