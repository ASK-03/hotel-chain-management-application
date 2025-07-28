package main.dao.interfaces;

import main.dto.Room;

import java.util.List;

public interface RoomDAO {
    void addRoom(Room room);
    List<Room> getRoomsForHotel(int hotelId);
    Room getRoom(int roomId, int hotelId);
}
