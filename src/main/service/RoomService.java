package main.service;

import main.dao.interfaces.RoomDAO;
import main.dto.Room;

import java.util.List;

public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public void addRoom(Room room) {
        this.roomDAO.addRoom(room);
    }

    public List<Room> getRoomsForHotel(int hotelId) {
        return this.roomDAO.getRoomsForHotel(hotelId);
    }

    public Room getRoom(int roomId, int hotelId) {
        return this.roomDAO.getRoom(roomId, hotelId);
    }
}

