package cinema.Exception;

import org.springframework.stereotype.Component;

@Component
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("The password is wrong!");
    }
}