package cinema;

import cinema.Seat.Seat;

import java.util.UUID;


public class Ticket {

    Seat ticket;


    public Ticket(Seat ticket) {
        this.ticket = ticket;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
