package cinema.room;

public class Seat {
    // Seat identifiers.
    private int row;
    private int column;
    private int price;

    public Seat(int row, int col) {
        this.row = row;
        this.column = col;
        // If a seat is in row 4 or lower, it costs 10.
        // Otherwise, it just costs 8.
        if (this.row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }

    // Getters for the row, column and price of a seat.
    public int getRow() { return row; }
    public int getColumn() { return column; }
    public int getPrice() { return price; }

}
