package cinema.room;

// imports ArrayList class.
import java.util.ArrayList;

// This class generates the list of available seats in the Room object.
public class Room {
    private int total_rows;     // There will be 9 rows.
    private int total_columns;  // There will be 9 columns.
    private ArrayList<Seat> available_seats = new ArrayList<>(); // List of available seats.

    // Constructor. This generates the 81 seats.
    public Room() {
        this.total_rows = 9;
        this.total_columns = 9;

        // Generating 81 seat objects.
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                this.available_seats.add(new Seat(i, j));
            }
        }
    }

    // Getter for the list of available seats.
    public ArrayList<Seat> getAvailable_seats() {
        return available_seats;
    }
}
