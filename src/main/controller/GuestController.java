package main.controller;

import main.dto.Guest;
import main.service.GuestService;

public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    public void addGuest(String name, String email, String phoneNumber, int hotelId) {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setEmail(email);
        guest.setPhone(phoneNumber);
        guest.setHotelId(hotelId);

        guestService.addGuest(guest);
    }
}
