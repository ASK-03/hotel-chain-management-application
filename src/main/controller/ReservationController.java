package main.controller;

import main.dto.Reservation;
import main.service.ReservationService;

import java.sql.Timestamp;

public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public boolean makeReservation(int guestId, int hotelId, int roomNumber, Timestamp checkIn, Timestamp checkOut) {
        Reservation reservation = new Reservation();

        reservation.setGuestId(guestId);
        reservation.setHotelId(hotelId);
        reservation.setRoomNumber(roomNumber);
        reservation.setCheckInDate(checkIn);
        reservation.setCheckOutDate(checkOut);
        reservation.setReservationDate(new Timestamp(System.currentTimeMillis()));
        reservation.setStatus("CONFIRM");

        return reservationService.makeReservation(reservation);
    }

    public boolean cancelReservation(int reservationId) {
        return reservationService.cancelRoomReservation(reservationId);
    }
}
