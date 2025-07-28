package main.controller;

import main.dto.Room;
import main.dto.RoomType;
import main.service.RoomService;

import java.util.List;

public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public Room viewSpecificRoom(int roomId, int hotelId) {
        return this.roomService.getRoom(roomId, hotelId);
    }

    public List<Room> viewAllRoomsOfHotel(int hotelId) {
        return roomService.getRoomsForHotel(hotelId);
    }

    public void addRoom(String typeInString, boolean availability, int hotelId) throws Exception {
        RoomType roomType;
        if (typeInString.equalsIgnoreCase("SINGLE")) {
            roomType = RoomType.SINGLE;
        }
        else if (typeInString.equalsIgnoreCase("DOUBLE")) {
            roomType = RoomType.DOUBLE;
        }
        else {
            throw new Exception("RoomType should be SINGLE/DOUBLE");
        }

        Room room = new Room();
        room.setType(roomType);
        room.setAvailable(availability);
        room.setHotelId(hotelId);

        roomService.addRoom(room);
    }

    public List<Room> getAvailableRooms(int hotelId) {
        List<Room> rooms = viewAllRoomsOfHotel(hotelId);
        return rooms.stream()
                .filter(Room::isAvailable)
                .toList();
    }
}
