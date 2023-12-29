package cinema.Cinema;


import cinema.Exception.ApiExceptionHandler;
import cinema.Seat.Seat;
import cinema.Statistics;
import cinema.Ticket.Ticket;
import cinema.Ticket.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class CinemaService {


    Cinema cinema;
    ApiExceptionHandler apiExceptionHandler;

    private ConcurrentLinkedQueue<Seat> takenSeats;

    private HashMap<UUID, Ticket> soldTickets;

    @Autowired
    public CinemaService (ApiExceptionHandler apiExceptionHandler) {
        this.cinema = new Cinema(9, 9);
        this.apiExceptionHandler = apiExceptionHandler;
        this.takenSeats = new ConcurrentLinkedQueue<>();
        this.soldTickets = new HashMap<>();
    }

    public Cinema getCinema() {
        return cinema;
    }

    public ResponseEntity<Object> purchaseTicket(int row, int column) {
        if (row < 1 || row > cinema.getRows() || column < 1 || column > cinema.getColumns()) {
            return apiExceptionHandler.handleException(apiExceptionHandler.getSeatOutOfBoundsException());
        }
        for (Seat seat : takenSeats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return apiExceptionHandler.handleException(apiExceptionHandler.getTakenSeatException());
            }
        }
        Seat seat = null;
        for (Seat s : cinema.getSeats()) {
            if (s.getRow() == row && s.getColumn() == column) {
                seat = s;
            }
        }
        takenSeats.add(seat);
        cinema.getSeats().remove(seat);
        Ticket ticket = new Ticket(seat);
        Token token = new Token();
        soldTickets.put(token.getToken(), ticket);
        return ResponseEntity.ok(Map.of("token", token.getToken(), "ticket", ticket.getTicket()));
    }

    public ResponseEntity<Object> returnTicket(Token token) {
        if (soldTickets.containsKey(token.getToken())) {
            Ticket ticket = soldTickets.get(token.getToken());
            Seat seat = ticket.getTicket();
            cinema.getSeats().add(seat);
            soldTickets.remove(token.getToken());
            takenSeats.remove(seat);
            return ResponseEntity.ok(ticket);
        } else {
            return apiExceptionHandler.handleException(apiExceptionHandler.getWrongTokenException());
        }
    }

    public ResponseEntity<Object> getCinemaStats(String password) {
        if (password.equals("super_secret")) {
            int income = 0;
            for (Seat seat : takenSeats) {
                income += seat.getPrice();
        }
        int available = cinema.getColumns() * cinema.getRows() - takenSeats.size();
        int purchased = takenSeats.size();

        return ResponseEntity.ok(new Statistics(income, available, purchased));
        } else {
            return apiExceptionHandler.handleWrongPasswordException(apiExceptionHandler.getWrongPasswordException());
        }
    }


}
