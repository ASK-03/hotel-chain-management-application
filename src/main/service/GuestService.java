package main.service;

import main.dao.interfaces.GuestDAO;
import main.dto.Guest;

public class GuestService {
    private final GuestDAO guestDAO;

    public GuestService(GuestDAO guestDAO) {
        this.guestDAO = guestDAO;
    }

    public void addGuest(Guest guest) {
        this.guestDAO.addGuest(guest);
    }
}
