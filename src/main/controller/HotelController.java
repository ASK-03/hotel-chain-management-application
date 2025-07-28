package main.controller;

import main.dto.Hotel;
import main.service.HotelService;

public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public Hotel getHotelDetails(int hotelId) {
        return hotelService.getHotel(hotelId);
    }

    public void addHotel(String name, String location) {
        Hotel hotel = new Hotel();

        hotel.setName(name);
        hotel.setLocation(location);

        hotelService.addHotel(hotel);
    }
}
