package main;

import main.controller.*;
import main.dao.interfaces.GuestDAO;
import main.dao.interfaces.HotelDAO;
import main.dao.interfaces.ReservationDAO;
import main.dao.interfaces.RoomDAO;
import main.dao.postgresImpl.PostgresGuestDAO;
import main.dao.postgresImpl.PostgresHotelDAO;
import main.dao.postgresImpl.PostgresReservationDAO;
import main.dao.postgresImpl.PostgresRoomDAO;
import main.menu.Menu;
import main.service.GuestService;
import main.service.HotelService;
import main.service.ReservationService;
import main.service.RoomService;

public class Main {
    public static void main(String[] args) {
        // TODO:
        // The below can be implemented using Factory Design Pattern
        // to get decoupled code and achieve better extensibility
        // {

        // setup room main.service and main.controller
        RoomDAO postgresRoomDAO = new PostgresRoomDAO();
        RoomService roomService = new RoomService(postgresRoomDAO);
        RoomController roomController = new RoomController(roomService);

        // setup hostel main.service and main.controller
        HotelDAO postgresHotelDAO = new PostgresHotelDAO();
        HotelService hotelService = new HotelService(postgresHotelDAO);
        HotelController hotelController = new HotelController(hotelService);

        // setup reservation main.service and main.controller
        ReservationDAO postgresReservationDAO = new PostgresReservationDAO();
        ReservationService reservationService = new ReservationService(postgresReservationDAO);
        ReservationController reservationController = new ReservationController(reservationService);

        // setup guest main.service and main.controller
        GuestDAO postgresGuestDAO = new PostgresGuestDAO();
        GuestService guestService = new GuestService(postgresGuestDAO);
        GuestController guestController = new GuestController(guestService);

        // Setup main main.controller
        MainController mainController = new MainController(hotelController, roomController, reservationController, guestController);

        // }

        Menu menu = new Menu(mainController);
        menu.showMainMenu();
    }
}
