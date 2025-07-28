package main.dto;

public class Guest {
    private int guestId;
    private String name;
    private String email;
    private String phone;
    private int hotelId;

    public Guest () {}

    public void setGuestId(int guestId) { this.guestId = guestId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }

    public int getGuestId() { return this.guestId; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public String getPhone() { return this.phone; }
    public int getHotelId() { return this.hotelId; }

    @Override
    public String toString() {
        return "Guest Details:\n" +
                "Guest ID  : " + guestId + "\n" +
                "Name      : " + name + "\n" +
                "Email     : " + email + "\n" +
                "Phone     : " + phone + "\n" +
                "Hotel ID  : " + hotelId;
    }
}
