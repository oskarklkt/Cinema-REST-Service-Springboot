package cinema.Cinema;


import cinema.Exception.ApiExceptionHandler;
import cinema.Seat.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

@Service
public class CinemaService {


    Cinema cinema;
    ApiExceptionHandler apiExceptionHandler;

    private ConcurrentLinkedQueue<Seat> takenSeats;

    private ConcurrentHashMap<UUID, Seat> soldTickets;

    @Autowired
    public CinemaService (ApiExceptionHandler apiExceptionHandler) {
        this.cinema = new Cinema(9, 9);
        this.apiExceptionHandler = apiExceptionHandler;
        this.takenSeats = new ConcurrentLinkedQueue<>();
        this.soldTickets = new ConcurrentHashMap<>();
    }

    public Cinema getCinema() {
        return cinema;
    }

    public ResponseEntity<Object> purchaseTicket(int row, int column) {
        if (row < 1 || row > cinema.getRows() || column < 1 || column > cinema.getColumns()) {
            return apiExceptionHandler.handleException(apiExceptionHandler.getSeatOutOfBoundsException());
        }
        for (Seat seat : takenSeats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return apiExceptionHandler.handleException(apiExceptionHandler.getTakenSeatException());
            }
        }
        Seat seat = null;
        for (Seat s : cinema.getSeats()) {
            if (s.getRow() == row && s.getColumn() == column) {
                seat = s;
            }
        }
        takenSeats.add(seat);
        cinema.getSeats().remove(seat);
        UUID uuid = UUID.randomUUID();
        soldTickets.put(uuid, seat);
        return ResponseEntity.ok(new LinkedHashMap<>(Map.of("token", uuid, "ticket", seat)));
    }


}
