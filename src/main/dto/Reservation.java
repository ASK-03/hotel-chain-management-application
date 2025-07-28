package main.dto;

import java.sql.Timestamp;

public class Reservation {
    private int reservationId;
    private int hotelId;
    private int guestId;
    private int roomNumber;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private Timestamp reservationDate;
    private String status;


    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public Timestamp getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Timestamp getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Timestamp reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation Details:\n" +
                "Reservation ID   : " + reservationId + "\n" +
                "Hotel ID         : " + hotelId + "\n" +
                "Guest ID         : " + guestId + "\n" +
                "Room Number      : " + roomNumber + "\n" +
                "Check-in Date    : " + checkInDate + "\n" +
                "Check-out Date   : " + checkOutDate + "\n" +
                "Reservation Date : " + reservationDate + "\n" +
                "Status           : " + status;
    }
}
