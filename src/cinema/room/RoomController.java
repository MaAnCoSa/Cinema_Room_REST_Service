package cinema.room;

// Spring Boot classes for server-client interaction.
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList; // To structure certain data.
import java.util.UUID;      // To generate the token for a ticket.

// Importing the main Room object for the Cinema.
import static cinema.Main.room;

// All functions here will manage the requests made by a client.
@RestController
public class RoomController {

    // List of bought seats (tickets).
    private ArrayList<Ticket> tickets = new ArrayList<>();

    // We initialize the earned income as 0.
    private int income = 0;

    // Endpoint to see the available seats.
    @GetMapping("/seats")
    public Room seatsInfo() {
        return room;
    }

    // Endpoint to purchase a seat. It shows the row, column and price of the seat, as well
    // as a token that you can use to get a refund.
    @PostMapping("/purchase")
    public Ticket purchaseSeat(@RequestParam int row, @RequestParam int col) {
        // We create a reference for the list of available seats (still the same list).
        ArrayList<Seat> seats = cinema.Main.room.getAvailable_seats();
        // We create a Seat object to represent the purchased seat.
        Seat purchasedSeat = new Seat(row, col);
        // If the seat doesn't exist...
        if (row < 1 || row > 9 || col < 1 || col > 9) {
            // ...an error is thrown.
            throw new noSeat("The number of a row or a column is out of bounds!");
        }
        // If the seat does exist and is available...
        if (seats.contains(purchasedSeat)) {
            // ...we generate the token.
            UUID token = UUID.randomUUID();

            // We now add the seat to the list of purchased tickets.
            tickets.add(new Ticket(token, purchasedSeat));
            // We update the income.
            income += purchasedSeat.getPrice();
            // We now remove that seat from the list of available seats.
            seats.remove(purchasedSeat);
            // We return the generated ticket.
            return tickets.get(tickets.size() - 1);
        // If the ticket is not available...
        } else {
            // ...an error is thrown informing the client of that fact.
            throw new noSeat("The ticket has been already purchased!");
        }
    }

    // Endpoint for refunding a ticket.
    @PostMapping("/return")
    public Seat returnTicket(String tok) {
        // We get the token for the ticket that the client wants to return.
        UUID token = UUID.fromString(tok);
        // We look for any bought ticket with that token.
        for (int i = 0; i < tickets.size(); i++) {
            // If the ticket is found...
            if (tickets.get(i).getToken() == token) {
                // ...we get a reference for the list fo available seats.
                ArrayList<Seat> seats = cinema.Main.room.getAvailable_seats();
                // We add this seat into that list, once again.
                seats.add(tickets.get(i).getTicket());
                // We update the income, subtracting the money we just refunded.
                income -= tickets.get(i).getTicket().getPrice();
                // We remove that ticket from the list of bought tickets.
                tickets.remove(tickets.get(i).getTicket());
                // We now return the information fo the sea that just got available.
                return new Seat(tickets.get(i).getTicket().getRow(), tickets.get(i).getTicket().getColumn());
            }
        }
        // If no ticket was found with the given token, we throw an error.
        throw new noSeat("Wrong token!");
    }

    // Endpoint for managers to check the statistics of the Cinema.
    @PostMapping("/stats")
    public Statistics stats(@RequestParam String password) {
        // If the password is never received...
        if (password.isEmpty()) {
            // ...we throw an error.
            throw new wrongPassword("The password is wrong!");
        // If the password is correct...
        } else if (password == "super_secret") {
            // We generate the statistics and return them.
            Statistics stat = new Statistics(income, room.getAvailable_seats().size(), tickets.size());
            return stat;
        // If the password was incorrect...
        } else {
            // ...we throw an error.
            throw new wrongPassword("The password is wrong!");
        }
    }

    // Throws a 400 error.
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    class noSeat extends RuntimeException {
        public noSeat(String cause) {
            super(cause);
        }
    }

    // Throws a 401 error.
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    class wrongPassword extends RuntimeException {
        public wrongPassword(String cause) {
            super(cause);
        }
    }
}
