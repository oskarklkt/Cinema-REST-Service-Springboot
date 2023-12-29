package cinema.Cinema;

import cinema.Seat.Seat;
import cinema.Ticket.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController (CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping(value = "/seats")
    public Cinema getCinemaSeats() {
        return cinemaService.getCinema();
    }

    @PostMapping(value = "/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestBody Seat seat) {
        return cinemaService.purchaseTicket(seat.getRow(), seat.getColumn());
    }

    @PostMapping(value = "/return")
    public ResponseEntity<Object> returnTicket(@RequestBody Token token) {
        return cinemaService.returnTicket(token);
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<Object> getStats(@RequestParam Optional<String> password) {
        return cinemaService.getCinemaStats(password.orElse(""));
    }
}
