package main.service;

import main.dao.interfaces.ReservationDAO;
import main.dto.Reservation;

import java.util.List;

public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public boolean makeReservation(Reservation reservation) {
            return this.reservationDAO.makeReservation(reservation);
    }

    public boolean cancelRoomReservation(int reservationId) {
        return this.reservationDAO.cancelRoomReservation(reservationId);
    }

    public List<Reservation> getReservationsForHotel(int hotelId) {
        return this.reservationDAO.getReservationsForHotel(hotelId);
    }
}
