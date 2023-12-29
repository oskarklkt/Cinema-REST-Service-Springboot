package cinema.Cinema;


import cinema.Exception.ApiExceptionHandler;
import cinema.Seat.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaService {


    Cinema cinema;
    ApiExceptionHandler apiExceptionHandler;

    private List<Seat> takenSeats;

    @Autowired
    public CinemaService (ApiExceptionHandler apiExceptionHandler) {
        this.cinema = new Cinema(9, 9);
        this.apiExceptionHandler = apiExceptionHandler;
        this.takenSeats = new ArrayList<>();
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
        Seat seat = cinema.getSeats().get(row + column - 2);
        takenSeats.add(seat);
        cinema.getSeats().remove(seat);
        return ResponseEntity.ok(seat);
    }


}
