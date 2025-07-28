package main.controller;

import main.dto.Hotel;
import main.dto.Room;

import java.sql.Timestamp;
import java.util.List;

public class MainController {
    private final HotelController hotelController;
    private final RoomController roomController;
    private final ReservationController reservationController;
    private final GuestController guestController;

    public MainController(HotelController hotelController, RoomController roomController, ReservationController reservationController, GuestController guestController) {
        this.hotelController = hotelController;
        this.roomController = roomController;
        this.reservationController = reservationController;
        this.guestController = guestController;
    }

    public void addGuest(String name, String email, String phoneNumber, int hotelId) {
        guestController.addGuest(name, email, phoneNumber, hotelId);
    }

    public Room viewSpecificRoom(int roomId, int hotelId) {
        return roomController.viewSpecificRoom(roomId, hotelId);
    }

    public List<Room> viewAllRoomsOfHotel(int hotelId) {
        return roomController.viewAllRoomsOfHotel(hotelId);
    }

    public void addRoom(String typeInString, boolean availability, int hotelId) {
        try {
            roomController.addRoom(typeInString, availability, hotelId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Hotel viewHotelDetails(int hotelId) {
        return hotelController.getHotelDetails(hotelId);
    }

    public void addHotel(String name, String location) {
        hotelController.addHotel(name, location);
    }

    public boolean makeReservation(int guestId, int hotelId, int roomNumber, Timestamp checkIn, Timestamp checkOut) {
        return reservationController.makeReservation(guestId, hotelId, roomNumber, checkIn, checkOut);
    }

    public boolean cancelReservation(int reservationId) {
        return reservationController.cancelReservation(reservationId);
    }

    public List<Room> getAvailableRooms(int hotelId) {
        return roomController.getAvailableRooms(hotelId);
    }
}
