package cinema.room;

import java.util.UUID;

public class Ticket {
    private UUID token;     // The token in case a refund is needed.
    private Seat ticket;    // The information of the seat itself.

    public Ticket(UUID token, Seat ticket) {
        this.ticket = ticket;
        this.token = token;
    }

    // Getters for the token and the seat information (ticket).
    public UUID getToken() { return token; }
    public Seat getTicket() { return ticket; }

}
