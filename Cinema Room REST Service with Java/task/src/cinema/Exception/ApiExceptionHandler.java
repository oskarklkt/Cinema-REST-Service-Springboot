package cinema.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    TakenSeatException takenSeatException;
    SeatOutOfBoundsException seatOutOfBoundsException;

    WrongTokenException wrongTokenException;
    public TakenSeatException getTakenSeatException() {
        return takenSeatException;
    }


    public SeatOutOfBoundsException getSeatOutOfBoundsException() {
        return seatOutOfBoundsException;
    }

    public WrongTokenException getWrongTokenException() {
        return wrongTokenException;
    }

    @Autowired
    public ApiExceptionHandler(TakenSeatException takenSeatException,SeatOutOfBoundsException seatOutOfBoundsException, WrongTokenException wrongTokenException) {
        this.takenSeatException = takenSeatException;
        this.seatOutOfBoundsException = seatOutOfBoundsException;
        this.wrongTokenException = wrongTokenException;
    }



    @ExceptionHandler({SeatOutOfBoundsException.class, TakenSeatException.class})
    public ResponseEntity<Object> handleException(RuntimeException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}