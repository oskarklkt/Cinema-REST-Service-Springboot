package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
