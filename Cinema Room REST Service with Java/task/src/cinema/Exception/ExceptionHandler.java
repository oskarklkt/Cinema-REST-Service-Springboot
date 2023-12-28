package cinema.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    TakenSeatException takenSeatException;
    SeatOutOfBoundsException seatOutOfBoundsException;

    public TakenSeatException getTakenSeatException() {
        return takenSeatException;
    }

    public void setTakenSeatException(TakenSeatException takenSeatException) {
        this.takenSeatException = takenSeatException;
    }

    public SeatOutOfBoundsException getSeatOutOfBoundsException() {
        return seatOutOfBoundsException;
    }

    public void setSeatOutOfBoundsException(SeatOutOfBoundsException seatOutOfBoundsException) {
        this.seatOutOfBoundsException = seatOutOfBoundsException;
    }

    public ExceptionHandler(@Autowired TakenSeatException takenSeatException, @Autowired SeatOutOfBoundsException seatOutOfBoundsException) {
        this.takenSeatException = takenSeatException;
        this.seatOutOfBoundsException = seatOutOfBoundsException;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleException(RuntimeException ex) {
        return ResponseEntity.badRequest().body("{\"error\":\"" + ex.getMessage() + "\"}");
    }

}