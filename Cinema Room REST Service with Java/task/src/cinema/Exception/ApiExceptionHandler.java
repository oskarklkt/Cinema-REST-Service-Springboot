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

    WrongPasswordException wrongPasswordException;
    public TakenSeatException getTakenSeatException() {
        return takenSeatException;
    }


    public SeatOutOfBoundsException getSeatOutOfBoundsException() {
        return seatOutOfBoundsException;
    }


    public WrongPasswordException getWrongPasswordException() {
        return wrongPasswordException;
    }

    public WrongTokenException getWrongTokenException() {
        return wrongTokenException;
    }

    @Autowired
    public ApiExceptionHandler(TakenSeatException takenSeatException,
                               SeatOutOfBoundsException seatOutOfBoundsException,
                               WrongTokenException wrongTokenException,
                               WrongPasswordException wrongPasswordException) {
        this.takenSeatException = takenSeatException;
        this.seatOutOfBoundsException = seatOutOfBoundsException;
        this.wrongTokenException = wrongTokenException;
        this.wrongPasswordException = wrongPasswordException;
    }


    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Object> handleWrongPasswordException(RuntimeException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ TakenSeatException.class,
                        SeatOutOfBoundsException.class,
                        WrongTokenException.class})
    public ResponseEntity<Object> handleException(RuntimeException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }






}