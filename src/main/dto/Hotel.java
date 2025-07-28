package main.dto;

public class Hotel {
    private int hotelId;
    private String name;
    private String location;

    public Hotel () {}

    public void setHotelId(int hotelId) { this.hotelId = hotelId; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }

    public int getHotelId() { return this.hotelId; }
    public String getName() { return this.name; }
    public String getLocation() { return this.location; }

    @Override
    public String toString() {
        return "Hotel Details:\n" +
                "ID       : " + hotelId + "\n" +
                "Name     : " + name + "\n" +
                "Location : " + location;
    }
}
