package main.service;

import main.dao.interfaces.HotelDAO;
import main.dto.Hotel;

public class HotelService {
    private final HotelDAO hotelDAO;

    public HotelService(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    public void addHotel(Hotel hotel) {
        this.hotelDAO.addHotel(hotel);
    }

    public Hotel getHotel(int hotelId) {
        return this.hotelDAO.getHotel(hotelId);
    }
}
