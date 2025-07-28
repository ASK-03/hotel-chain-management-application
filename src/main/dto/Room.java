package main.dto;

public class Room {
    private int roomNumber;
    private RoomType type;
    private boolean available;
    private int hotelId;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "Room Details:\n" +
                "Room Number : " + roomNumber + "\n" +
                "Room Type   : " + type + "\n" +
                "Available   : " + (available ? "Yes" : "No") + "\n" +
                "Hotel ID    : " + hotelId;
    }
}
