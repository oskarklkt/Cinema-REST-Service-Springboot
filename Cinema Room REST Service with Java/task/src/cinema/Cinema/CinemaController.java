package cinema.Cinema;

import cinema.Seat.Seat;
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
    public ResponseEntity<Object> purchaseTicket(@RequestParam int row, @RequestParam int column) {
        return cinemaService.purchaseTicket(row, column);
    }
}
