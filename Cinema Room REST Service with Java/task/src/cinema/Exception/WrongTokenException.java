package cinema.Exception;

import org.springframework.stereotype.Component;

@Component
public class WrongTokenException extends RuntimeException {
    public WrongTokenException() {
        super("Wrong token!");
    }

}
