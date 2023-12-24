package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    Cinema cinema;


    public CinemaService () {
        this.cinema = new Cinema(9, 9);
    }

    public Cinema getCinema() {
        return cinema;
    }


}
