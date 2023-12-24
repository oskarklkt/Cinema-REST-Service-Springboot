package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    private CinemaService cinemaService;

    public CinemaController (CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping(value = "/seats")
    public Cinema getCinemaSeats() {
        return cinemaService.getCinema();
    }
}
