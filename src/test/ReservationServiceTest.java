package test;

import main.dao.interfaces.ReservationDAO;
import main.dao.postgresImpl.PostgresReservationDAO;
import main.dto.Reservation;
import main.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    private ReservationDAO reservationDAO;
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationDAO = Mockito.mock(PostgresReservationDAO.class);
        reservationService = new ReservationService(reservationDAO);
    }

    @Test
    void makeReservation_ShouldReturnTrue_WhenReservationSucceeds() {
        Reservation reservation = Mockito.mock(Reservation.class);

        // Mock DAO behavior
        when(reservationDAO.makeReservation(reservation)).thenReturn(true);

        boolean result = reservationService.makeReservation(reservation);

        assertTrue(result);
        verify(reservationDAO, times(1)).makeReservation(reservation);
    }


    @Test
    void makeReservation_ShouldReturnFalse_WhenReservationFails() {
        Reservation reservation = Mockito.mock(Reservation.class);

        // Mock DAO behavior
        when(reservationDAO.makeReservation(reservation)).thenReturn(false);

        boolean result = reservationService.makeReservation(reservation);

        assertFalse(result);
        verify(reservationDAO, times(1)).makeReservation(reservation);
    }
}
