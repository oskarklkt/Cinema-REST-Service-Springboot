package cinema.Cinema;


import cinema.Exception.ExceptionHandler;
import cinema.Seat.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaService {


    Cinema cinema;
    ExceptionHandler exceptionHandler;

    private List<Seat> takenSeats;

    public CinemaService (@Autowired ExceptionHandler exceptionHandler) {
        this.cinema = new Cinema(9, 9);
        this.exceptionHandler = exceptionHandler;
        this.takenSeats = new ArrayList<>();
    }

    public Cinema getCinema() {
        return cinema;
    }

    public ResponseEntity<Object> purchaseTicket(int row, int column) {
        if (row < 1 || row > cinema.getRows() || column < 1 || column > cinema.getColumns()) {
            return exceptionHandler.handleException(exceptionHandler.getSeatOutOfBoundsException());
        }
        for (Seat seat : takenSeats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return exceptionHandler.handleException(exceptionHandler.getTakenSeatException());
            }
        }
        Seat seat = cinema.getSeats().get(row + column - 2);
        takenSeats.add(seat);
        cinema.getSeats().remove(seat);
        return ResponseEntity.ok(seat);
    }


}
