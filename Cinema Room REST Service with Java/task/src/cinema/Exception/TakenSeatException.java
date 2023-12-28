package cinema.Exception;

import org.springframework.stereotype.Component;

@Component
public class TakenSeatException extends RuntimeException {
    public TakenSeatException() {
        super("The ticket has been already purchased!");
    }
}
