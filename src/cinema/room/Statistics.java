package cinema.room;

// The statistics object just stores the income, the number of available
// seats and the number of occupied seats.
public class Statistics {
    private int income;
    private int numSeats;
    private int numTickets;

    public Statistics(int income, int numSeats, int numTickets) {
        this.income = income;
        this.numSeats = numSeats;
        this.numTickets = numTickets;
    }
}
