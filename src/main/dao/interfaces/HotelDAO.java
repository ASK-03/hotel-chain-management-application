package main.dao.interfaces;

import main.dto.Hotel;

public interface HotelDAO {
    void addHotel(Hotel hotel);
    Hotel getHotel(int hotelId);
}
