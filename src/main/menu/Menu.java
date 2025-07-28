package main.menu;

import main.controller.MainController;
import main.dto.Hotel;
import main.dto.Room;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    private final MainController mainController;

    public Menu(MainController mainController) {
        this.mainController = mainController;
    }


    public void showMainMenu() {
        System.out.println("Welcome to Hilton Hotel Chain Management Application!");

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Admin Login");
            System.out.println("2. Guest Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    guestMenu();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void guestMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\nGuest Menu:");
            System.out.println("1. View Available Rooms in a Hotel");
            System.out.println("2. Make Reservation (US07)");
            System.out.println("3. Cancel Reservation (US08)");
            System.out.println("4. Back to main.Main Menu");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void makeReservation() {
        try {
            System.out.println("\nMake a Reservation:");

            System.out.print("Guest ID: ");
            int guestId = sc.nextInt();
            System.out.print("Hotel ID: ");
            int hotelId = sc.nextInt();
            System.out.print("Room Number: ");
            int roomNumber = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            System.out.print("Check-in Date (yyyy-MM-dd HH:mm:ss): ");
            String checkInStr = sc.nextLine();

            System.out.print("Check-out Date (yyyy-MM-dd HH:mm:ss): ");
            String checkOutStr = sc.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp checkIn = null;
            Timestamp checkOut = null;

            try {
                Date checkInDate = sdf.parse(checkInStr);
                Date checkOutDate = sdf.parse(checkOutStr);
                checkIn = new Timestamp(checkInDate.getTime());
                checkOut = new Timestamp(checkOutDate.getTime());
            } catch (ParseException pe) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss");
                return;
            }

            boolean success = mainController.makeReservation(guestId, hotelId, roomNumber, checkIn, checkOut);

            if (success) {
                System.out.println("Reservation successful!");
            } else {
                System.out.println("Reservation failed. Please try again.");
            }
        } catch (InputMismatchException ime) {
            System.out.println("Invalid input. Please enter numeric values for IDs and room number.");
            sc.nextLine(); // clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private void cancelReservation() {
        try {
            System.out.println("\nCancel Reservation:");
            System.out.print("Reservation ID: ");
            int reservationId = sc.nextInt();

            boolean success = mainController.cancelReservation(reservationId);
            if (success) {
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation failed. Please check the Reservation ID.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    private void viewAvailableRooms() {
        try {
            System.out.print("\nEnter Hotel ID to view available rooms: ");
            int hotelId = sc.nextInt();

            List<Room> availableRooms = mainController.getAvailableRooms(hotelId);

            if (availableRooms.isEmpty()) {
                System.out.println("No available rooms found for this hotel.");
            } else {
                System.out.println("Available Rooms:");
                for (Room room : availableRooms) {
                    System.out.println(room.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving available rooms.");
        }
    }


    private void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Hotel (US01)");
            System.out.println("2. View Hotel Details (US02)");
            System.out.println("3. Add Room to Hotel (US03)");
            System.out.println("4. View All Rooms of Hotel (US04)");
            System.out.println("5. View Specific Room Details (US05)");
            System.out.println("6. Add New Guest to Hotel (US06)");
            System.out.println("7. Back to main.Main Menu");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addHotel();
                    break;
                case 2:
                    viewHotelDetails();
                    break;
                case 3:
                    addRoom();
                    break;
                case 4:
                    viewAllRoomsOfHotel();
                    break;
                case 5:
                    viewSpecificRoom();
                    break;
                case 6:
                    addGuest();
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addGuest() {
        System.out.println("\nAdd Guest Information Below");
        System.out.print("Guest Name: ");
        String name = sc.nextLine();
        System.out.print("Guest Email: ");
        String email = sc.nextLine();
        System.out.print("Guest Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Hotel ID: ");
        int hotelId = sc.nextInt();

        mainController.addGuest(name, email, phoneNumber, hotelId);
    }

    private void viewSpecificRoom() {
        System.out.print("\nRoom ID: ");
        int roomId = sc.nextInt();
        System.out.print("Hotel ID: ");
        int hotelId = sc.nextInt();

        Room room = mainController.viewSpecificRoom(roomId, hotelId);

        System.out.println(room.toString());
    }

    private void viewAllRoomsOfHotel() {
        System.out.println("\nHotel ID: ");
        int hotelId = sc.nextInt();

        List<Room> rooms = mainController.viewAllRoomsOfHotel(hotelId);
        System.out.println(rooms.toString());
    }

    private void addRoom() {
        System.out.println("\nAdd Room Information Below:");
        System.out.print("Room Type (SINGLE/DOUBLE): ");
        String typeInString = sc.nextLine();
        System.out.print("Room Availability: ");
        boolean availability = sc.nextBoolean();
        System.out.print("Hotel ID: ");
        int hotelId = sc.nextInt();

        mainController.addRoom(typeInString, availability, hotelId);
    }

    private void viewHotelDetails() {
        System.out.print("Hotel ID: ");
        int hotelId = sc.nextInt();

        Hotel hotel = mainController.viewHotelDetails(hotelId);
        System.out.println(hotel.toString());
    }

    private void addHotel() {
        System.out.println("\nAdd Hotel Information Below:");
        System.out.print("Hotel Name: ");
        String name = sc.nextLine();
        System.out.print("Location: ");
        String location = sc.nextLine();

        mainController.addHotel(name, location);
    }
}
