package cinema;


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

    public Seat purchaseTicket(int row, int column) {
        Seat seat = cinema.getSeats().get(row + column - 2);
        cinema.getSeats().remove(seat);
        return seat;
    }


}
