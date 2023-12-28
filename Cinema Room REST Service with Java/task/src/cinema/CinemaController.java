package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Seat purchaseTicket(@RequestParam int row, @RequestParam int column) {
        return cinemaService.purchaseTicket(row, column);
    }
}
